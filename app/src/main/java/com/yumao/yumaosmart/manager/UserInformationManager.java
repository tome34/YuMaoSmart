package com.yumao.yumaosmart.manager;

import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.yumao.yumaosmart.constant.Constant.USER_GRADE;

/**
 * Created by kk on 2017/7/1.
 */

public class UserInformationManager {

    private User userBean =null;
    private File mImageDir;

    private static UserInformationManager sUserInformationManager;

    private UserInformationManager(){}

    //单例模式
    public static UserInformationManager getInstance(){
        if (sUserInformationManager == null){
            synchronized (LoginManager.class){
                if (sUserInformationManager == null){
                    sUserInformationManager = new UserInformationManager();
                }
            }
        }

        return sUserInformationManager;
    }



    //获取用户信息
    public User getUserInformation(){

        String userData = SPUtils.getString(UiUtilities.getContex(), Constant.USER_DATA);
            userBean = new Gson().fromJson(userData, User.class);
        return userBean ;
    }



    private int user_grade ; //等级
    private String mRolesString;
    private List<String> mRoles;
    //检查当前用户的权限,共有5级: grade 等级:1为分公司,2为总店,3为城市分店,4为合伙人,5,为注册会员
    public int userGrade() {
        String userData = SPUtils.getString(UiUtilities.getContex(), Constant.USER_DATA);

        //判断是否登录
        if (!TextUtils.isEmpty(userData)) {
            User userBean = new Gson().fromJson(userData, User.class);
            //角色集合
            mRoles = userBean.getRoles();

            mRolesString = String.valueOf(mRoles);
            LogUtils.d("tag", "角色:" + mRolesString);
            if (mRolesString.contains("RegionalAgentOwner")) {
                LogUtils.d("tag", "分公司");
                user_grade = 1;
                SPUtils.putInt(UiUtilities.getContex(), USER_GRADE, user_grade);

            } else if (mRolesString.contains("StoreOwner")) {
                if (userBean.getVendor().getVendor_type() == 3) {
                    LogUtils.d("tag", "总店");
                    user_grade = 2;
                    SPUtils.putInt(UiUtilities.getContex(), USER_GRADE, user_grade);

                } else if (userBean.getVendor().getVendor_type() == 2) {
                    LogUtils.d("tag", "城市分店");
                    user_grade = 3;
                    SPUtils.putInt(UiUtilities.getContex(), USER_GRADE, user_grade);

                }
            } else if (mRolesString.contains("SalesPerson")) {
                LogUtils.d("tag", "合伙人,v店");
                user_grade = 4;
                SPUtils.putInt(UiUtilities.getContex(), USER_GRADE, user_grade);

            } else {
                LogUtils.d("tag", "普通会员");
                user_grade = 5;
                SPUtils.putInt(UiUtilities.getContex(), USER_GRADE, user_grade);


            }

        }
        return user_grade;

    }

    //判断用户的等级

    /**
     *创建存储图片的路径
     * @return
     */
    public void createImageDir() {
        mImageDir = new File(Environment.getExternalStorageDirectory(), "avatar");
        // 檢查路徑是否存在
        if (!mImageDir.exists()) {
            mImageDir.mkdirs();
        }
    }

/*    //创建文件并获取其路径的Uri
    private void initFile() {
        // 创建File对象，用于存储拍照后生成的图片
        File outputImage = new File(path + profilePicFileName);//使用应用关联缓存目录存放图片
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 24) {
            //7.0之前，调用Uri的FromFile()方法将File对象转换为Uri对象，
            //这个Uri对象标识着user_profile_picture.jpg这张图片的本地真实路径
                    imageUri = Uri.fromFile(outputImage);
        } else {
            //7.0之后，直接使用本地真实路径的Uri被认为是不安全的，会抛出FileUriExposed异常。
            //因此使用FileProvider的getUriForFile方法将Uri转换成一个封装过的Uri对象
            //FileProvider是一个特殊的内容提供器，我们需要在Manifest中对其进行定义
            //(具体请查询Manifest中<provider>..<provider/>)
            imageUri = FileProvider.getUriForFile(getActivity(),
                    BuildConfig.APPLICATION_ID+".fileprovider", outputImage);
        }
    }*/

    /**
     * 獲取圖片的文件夾路徑
     *
     * @return
     */
    public File getImageDir() {
        return mImageDir;
    }
    /**
     * 獲取裁剪圖片的uri
     *
     * @return
     */
    public Uri getImageUri(String fileName) {
        File file = new File(getImageDir(), fileName);
        //  LogUtils.e(file.toString());
        // 檢查文件是否存在
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();

            file.setWritable(Boolean.TRUE);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Uri.fromFile(file);// 返回格式化后的uri
    }



}

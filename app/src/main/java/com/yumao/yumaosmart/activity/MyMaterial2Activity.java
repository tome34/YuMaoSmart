package com.yumao.yumaosmart.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bigkoo.pickerview.TimePickerView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.itheima.roundedimageview.RoundedImageView;
import com.soundcloud.android.crop.Crop;
import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.event.BirthDayEvent;
import com.yumao.yumaosmart.event.GenderEvent;
import com.yumao.yumaosmart.event.PersonalCenterEvent;
import com.yumao.yumaosmart.event.PhoneNumEvent;
import com.yumao.yumaosmart.event.SweetNameEvent;
import com.yumao.yumaosmart.manager.UserInformationManager;
import com.yumao.yumaosmart.mode.AvatarMode;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.FileUtils;
import com.yumao.yumaosmart.utils.ImageUtils;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.ToastUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.yumao.yumaosmart.widgit.MyMaterialItemView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;

public class MyMaterial2Activity extends BaseItemActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {


    @BindView(R.id.tv_titlle)
    TextView mTvTitlle;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.tv_mymaterial_title)
    TextView mTvMymaterialLogo;
    @BindView(R.id.activity_my_material_nick_name)
    MyMaterialItemView mActivityMyMaterialNickName;
    @BindView(R.id.activity_my_material_phone)
    MyMaterialItemView mActivityMyMaterialPhone;
    @BindView(R.id.activity_my_material_address)
    MyMaterialItemView mActivityMyMaterialAddress;
    @BindView(R.id.activity_my_material_gender)
    MyMaterialItemView mActivityMyMaterialGender;
    @BindView(R.id.activity_my_material_data_of_birth)
    MyMaterialItemView mActivityMyMaterialDataOfBirth;
    @BindView(R.id.activity_my_material_id)
    MyMaterialItemView mActivityMyMaterialId;
    @BindView(R.id.activity_my_material_real_name)
    MyMaterialItemView mActivityMyMaterialRealName;
    @BindView(R.id.activity_my_material_reset_password)
    MyMaterialItemView mActivityMyMaterialResetPassword;
    @BindView(R.id.activity_my_material2)
    LinearLayout mActivityMyMaterial2;
    @BindView(R.id.layout_mymaterial_touxiong)
    RelativeLayout mLayoutMymaterialTouxiong;
    @BindView(R.id.activity_my_material_button_back)
    Button mActivityMyMaterialButtonBack;
    @BindView(R.id.iv_my_material_touxiang)
    RoundedImageView mIvMyMaterialTouxiang;


    private Context mContext;
    private Intent mIntent;
    private String mGender;
    public TimePickerView pvTime;
    private Activity mActivity;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient mClient;
    private Uri mImageUri;
    private Uri mCropUri;
    private Bitmap mBitmap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;
    private File mOriginalFile;
    //private boolean flag =true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_material2);

        ButterKnife.bind(this);
        initToobar("我的资料");

        initData();


        EventBus.getDefault().register(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSweetNameEvent(SweetNameEvent e) {
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPhoneNumEvent(PhoneNumEvent e) {
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGenderEvent(GenderEvent e) {
        initData();
        LogUtils.d("tag", "sp:" + SPUtils.getString(UiUtilities.getContex(), Constant.AVATAR_URL));

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBirthDayEvent(BirthDayEvent e) {
       /* mPostion = e.getPostion();
        DisPlay disPlay = mList.get(mPostion);
        mOtherBean= (MyMaterialOtherBean) disPlay;
        mOtherBean.setContent(e.getBirthDay());
        mList.remove(mPostion);
        mList.add(mPostion,mOtherBean);
        mMultiItemTypeAdapter.notifyDataSetChanged();*/
        String birthDay = e.getBirthDay();
        initData();
    }


    private void initData() {
        mContext = MyMaterial2Activity.this;
        String userData = SPUtils.getString(MyMaterial2Activity.this, Constant.USER_DATA);
        if (TextUtils.isEmpty(userData)) {
            LogUtils.d("tag", "用户信息为空:");
        } else {
            User userBean = UserInformationManager.getInstance().getUserInformation();
            String token = userBean.getToken();
            LogUtils.d("tag", "token:" + token);
            //设置头像
            String logoUrl = SPUtils.getString(UiUtilities.getContex(), Constant.AVATAR_URL);
            Picasso.with(mContext).load(logoUrl).placeholder(R.mipmap.first_page_person_icon_touxiang).into(mIvMyMaterialTouxiang);
            mLayoutMymaterialTouxiong.setOnClickListener(this);


            //设置昵称
            TextView nickName = (TextView) mActivityMyMaterialNickName.findViewById(R.id.tv_my_material_other_content);
            String nack_name = SPUtils.getString(UiUtilities.getContex(), Constant.NICK_NAME);
            nickName.setText(nack_name);
            mActivityMyMaterialNickName.setOnClickListener(this);

            //手机号码
            TextView phone = (TextView) mActivityMyMaterialPhone.findViewById(R.id.tv_my_material_other_content);
            String phone2 = SPUtils.getString(UiUtilities.getContex(), Constant.PHONE);
            phone.setText(phone2);
            mActivityMyMaterialPhone.setOnClickListener(this);

            //我的收货地址
            TextView address = (TextView) mActivityMyMaterialAddress.findViewById(R.id.tv_my_material_other_content);
            address.setText("");
            mActivityMyMaterialAddress.setOnClickListener(this);

            //性别
            TextView gender = (TextView) mActivityMyMaterialGender.findViewById(R.id.tv_my_material_other_content);
            String gender2 = SPUtils.getString(UiUtilities.getContex(), Constant.GENDER);
            if ("M".equals(gender2)) {
                gender.setText("男");
            } else if ("F".equals(gender2)) {
                gender.setText("女");
            } else {
                gender.setText("保密");
            }
            mActivityMyMaterialGender.setOnClickListener(this);

            //生日
            TextView dataOfBirth = (TextView) mActivityMyMaterialDataOfBirth.findViewById(R.id.tv_my_material_other_content);
            String dataOfBirth2 = SPUtils.getString(UiUtilities.getContex(), Constant.DATA_OF_BIRTH);
            dataOfBirth.setText(dataOfBirth2);
            mActivityMyMaterialDataOfBirth.setOnClickListener(this);

            //我的推荐码
            TextView id = (TextView) mActivityMyMaterialId.findViewById(R.id.tv_my_material_other_content);
            id.setText(userBean.getId() + "");

            //实名认证
            TextView realName = (TextView) mActivityMyMaterialRealName.findViewById(R.id.tv_my_material_other_content);
            realName.setText("未认证");
            mActivityMyMaterialRealName.setOnClickListener(this);

            //重置密码
            TextView resetPassword = (TextView) mActivityMyMaterialResetPassword.findViewById(R.id.tv_my_material_other_content);
            resetPassword.setText("");
            mActivityMyMaterialResetPassword.setOnClickListener(this);

            //退出当前登录
            mActivityMyMaterialButtonBack.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.layout_mymaterial_touxiong: //更换头像
                new AlertView(null, null, "取消", null,
                        new String[]{"拍照", "从相机选择"},
                        this, AlertView.Style.ActionSheet, new OnItemClickListener() {
                    public void onItemClick(Object o, int position) {
                        switch (position) {
                            case 0:  //拍照

                                //tryOpenCamera();
                                String[] perms2 = {Manifest.permission.CAMERA};
                                if (EasyPermissions.hasPermissions(MyMaterial2Activity.this, perms2)) {//检查是否获取该权限
                                    LogUtils.d("已获得权限");
                                    openCamera(mIntent);
                                } else {
                                    //第二个参数是被拒绝后再次申请该权限的解释
                                    //第三个参数是请求码
                                    //第四个参数是要申请的权限
                                    EasyPermissions.requestPermissions(MyMaterial2Activity.this, "拍照需要摄像头权限", 2, perms2);
                                }

                                break;
                            case 1:  //从相机选择
//所要申请的权限
                                String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                                if (EasyPermissions.hasPermissions(MyMaterial2Activity.this, perms)) {//检查是否获取该权限
                                    LogUtils.d("已获得权限");
                                    openAlbum(mIntent);
                                } else {
                                    //第二个参数是被拒绝后再次申请该权限的解释
                                    //第三个参数是请求码
                                    //第四个参数是要申请的权限
                                    EasyPermissions.requestPermissions(MyMaterial2Activity.this, "必要的权限", 0, perms);
                                }

                                break;
                        }
                    }
                }).show();
                break;
            case R.id.activity_my_material_nick_name:  //更换昵称
                mIntent = new Intent(this, NickNameActivity.class);
                startActivity(mIntent);
                break;
            case R.id.activity_my_material_phone:    //更换手机号码
                mIntent = new Intent(this, PhoneNumberActivity.class);
                startActivity(mIntent);
                break;
            case R.id.activity_my_material_address:   //收货地址
                mIntent = new Intent(this, AddressActivity.class);
                startActivity(mIntent);
                break;
            case R.id.activity_my_material_gender:   //更换性别
               /* MyMaterialOtherDelegate delegate = new MyMaterialOtherDelegate(MyMaterial2Activity.this);
                delegate.handgender();*/
                new AlertView("选择性别", null, "取消", null,
                        new String[]{"男", "女"},
                        this, AlertView.Style.ActionSheet, new OnItemClickListener() {
                    public void onItemClick(Object o, int position) {
                        switch (position) {
                            case 0:  //男
                                mGender = "M";
                                putGender(mGender);

                                break;
                            case 1:  //女

                                mGender = "F";
                                putGender(mGender);
                                break;
                        }
                    }
                }).show();
                break;
            case R.id.activity_my_material_data_of_birth: //生日
                handBirthDay();

                break;
            case R.id.activity_my_material_real_name:  //更换实名认证
                mIntent = new Intent(this, RealNameActivity.class);
                startActivity(mIntent);
                break;

            case R.id.activity_my_material_reset_password:  //更换密码
                mIntent = new Intent(this, ReSetPasswordActivity.class);
                startActivity(mIntent);
                break;

            //退出当前登录
            case R.id.activity_my_material_button_back:
                SPUtils.clear(UiUtilities.getContex());
                EventBus.getDefault().post(new PersonalCenterEvent());
                finish();
                break;

        }
    }

    //生日的时间选择器
    private void handBirthDay() {
        pvTime = new TimePickerView.Builder(MyMaterial2Activity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String time = format.format(date);
                LogUtils.d("tag", "time:" + time);
                putBirthDay(time); //更改生日

                //UiUtilities.getUser().setDate_of_birth(time);
                // EventBus.getDefault().post(new BirthDayEvent(time,5));
            }

        })
                .setLabel("年", "月", "日", "", "", "")
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//default is all
                .setCancelText("取消")
                .setSubmitText("确定")
                .setCancelColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleColor(Color.WHITE)
                .setTitleText("出生日期")
                .setTitleBgColor(Color.argb(255, 237, 86, 80))
                .build();

        pvTime.show();
    }

    //更改生日
    private void putBirthDay(String time) {
        final String times = time;
        FormBody.Builder builder = new FormBody.Builder();

        FormBody body = builder
                .add("date_of_birth", times)
                .build();
        OkHttpUtils
                .put()
                .url(Constant.BASE_URL + "customers/" + SPUtils.getInt(MyMaterial2Activity.this, Constant.USER_CID))
                .addHeader("X-API-TOKEN", SPUtils.getString(MyMaterial2Activity.this, Constant.TOKEN))
                .requestBody(body)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(UiUtilities.getContex(), "修改失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        Toast.makeText(UiUtilities.getContex(), "修改成功", Toast.LENGTH_SHORT).show();
                        SPUtils.putString(UiUtilities.getContex(), Constant.DATA_OF_BIRTH, times);
                        EventBus.getDefault().post(new BirthDayEvent(times, 5));
                    }
                });
    }

    //尝试打开相机 无权限则申请权限
   /* private void tryOpenCamera() {
        //判断sdk大于6.0则先请求动态访问相机的权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ContextCompat.checkSelfPermission(UiUtilities.getContex(),
                    Manifest.permission.READ_CONTACTS) !=
                    PackageManager.PERMISSION_GRANTED)) {
                //进入到这里代表没有权限，下面申请权限
                //用户点击同意或拒绝后会进入onRequestPermissionsResult
                requestPermissions(
                        new String[]{Manifest.permission.CAMERA},
                        111);

            } else {
                //sdk大于6.0，但已有权限

                openCamera(mIntent);
            }
        } else {
            //sdk小于6.0 注意：Manifest中应已配置了相机的使用权限
            openCamera(mIntent);
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
     /*   switch (requestCode){
            case 111:
                openCamera(mIntent);
                break;
        }*/
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        LogUtils.d("获取成功的权限" + perms);
        switch (requestCode) {
            case 0:
                openAlbum(mIntent);
                break;
            case 2:
                openCamera(mIntent);
                break;
        }

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        LogUtils.d("获取失败的权限" + perms);
    }


    //打开相册
    private void openAlbum(Intent intent) {

        //返回被选中项的URI
        intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//得到所有图片的URI
        startActivityForResult(intent, 125);

    }

    //打开相机
    private void openCamera(Intent intent) {
        try {
            // 开启相机应用程序获取并返回图片
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            UserInformationManager.getInstance().createImageDir();
            // 獲取拍照的圖片的uri
            mImageUri = UserInformationManager.getInstance().getImageUri("234.jpg");

            // 指明存储图片或视频的地址URI
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            startActivityForResult(intent, 126);
        } catch (Exception e) {
            Toast.makeText(MyMaterial2Activity.this, "相机无法启动，请先开启相机权限", Toast.LENGTH_LONG).show();
        }
    }

    //图片裁剪
    private void cropPic(Uri sourceUri) {
        mCropUri = UserInformationManager.getInstance().getImageUri("123.jpg");
        LogUtils.d("cheegon" + mCropUri);

        Crop.of(sourceUri, mCropUri).asSquare().start(this);
        // LogUtils.d(new File(mCropUri.getPath()).toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 125:
                if (resultCode == RESULT_OK) {
                    if (data == null) {
                        ToastUtils.showShort(MyMaterial2Activity.this, "相冊內容為空");
                    } else {
                        cropPic(data.getData());//裁剪圖片
                    }
                }
                break;
            //相机
            case 126:
                if (resultCode == RESULT_OK) {
                    cropPic(mImageUri);//裁剪圖片
                }
                break;
            case Crop.REQUEST_CROP:
                if (resultCode == RESULT_OK) {
                    LogUtils.d("tag", "裁剪图片成功:" + mCropUri);
                    // 裁剪后的图片地址
                    mOriginalFile = new File(Environment.getExternalStorageDirectory() + "/avatar/123.jpg");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        outputUploadFile(mOriginalFile);
                    }
                }).start();


                    //執行網絡請求,把圖片post上去
                    // postImage(mCropUri);
                   // upLoad(mCropUri);

                }
                break;
            default:
                break;
        }
    }
    //压缩后的图片
    private void outputUploadFile(File originalFile) {
        File saveFile =  new File(Environment.getExternalStorageDirectory() + "/avatar/1233.jpg");// 压缩后图片地址
        FileUtils.createFileByDeleteOldFile(saveFile);
        compressFile(originalFile, saveFile);
            upLoad(saveFile);
    }

    /**
     * 根据图片质量的来压缩图片
     *
     * @param originalFile
     * @param saveFile
     */
    private void compressFile(File originalFile, File saveFile) {
        Bitmap bitmap = ImageUtils.getBitmap(originalFile.getAbsolutePath(),480,800);
        ImageUtils.saveCompressByQualityImage(bitmap, 100 * 1024L, false, saveFile);// 压缩质量随便你调,现在限定为300Kb
    }



    public byte[] Bitmap2Bytes(String filePath) {
        Bitmap bm = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        return baos.toByteArray();
    }

    public byte[] readStream(String imagepath) throws Exception {
        FileInputStream fs = new FileInputStream(imagepath);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while (-1 != (len = fs.read(buffer))) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        fs.close();
        return outStream.toByteArray();
    }

    //更改头像
    private void postImage(Uri cropUri) {
        Uri iconUri = cropUri;
        File file = null;
        try {
            //uri转成文件
            file = new File(new URI(cropUri.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //  LogUtils.d(file.getAbsolutePath()+"路径");
        // String s = String.valueOf(Bitmap2Bytes(file.getAbsolutePath()));

        LogUtils.d("图片" + Bitmap2Bytes(file.getAbsolutePath()));
        LogUtils.d("tag", "file:" + file);

        int anInt = SPUtils.getInt(UiUtilities.getContex(), Constant.USER_CID);
        LogUtils.d("tag", "cid:" + anInt);

        // RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), file);// 二进制文件流类型

        try {


            OkHttpUtils
                    .post()
                    .url(Constant.BASE_URL + "customers/" + SPUtils.getInt(MyMaterial2Activity.this, Constant.USER_CID) + "/avatar")
                    .url(Constant.BASE_URL + "debug/post")
                    .addHeader("X-API-TOKEN", SPUtils.getString(MyMaterial2Activity.this, Constant.TOKEN))
                    // .addFile("avatar","123.jpeg",fileBody1)
                    //  .params("avatar",file) //先把图片压缩,二进制文件
                    //.addParams("avatar",readStream(file.getAbsolutePath()))
                    // .file(file)
                    .build()
                    .execute(new Callback() {
                        @Override
                        public Object parseNetworkResponse(Response response, int id) throws Exception {
                            return null;
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtils.d("失败了" + e.toString());
                        }

                        @Override
                        public void onResponse(Object response, int id) {
                            LogUtils.d("成功了" + response.toString());
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void upLoad(final File saveFile) {
        String url = Constant.BASE_URL + "customers/" + SPUtils.getInt(MyMaterial2Activity.this, Constant.USER_CID) + "/avatar";// url地址你自己填

        //String url=Constant.BASE_URL+"debug/post";
        // 裁剪的图片,看一下路径是否对图片后缀是否对
        // File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/avatar/123.jpg");
       /* File file = null;
        try {
            file = new File(new URI(cropUri.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/
        LogUtils.d("tag", "图片file:" + saveFile);
        // RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);// 表单类型
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), saveFile);// 二进制文件流类型
        String boundary = "xx--------------------------------------------------------------xx";
        String fileName = "123.jpg";
        MultipartBody mBody = new MultipartBody.Builder(boundary).setType(MultipartBody.FORM)
                .addFormDataPart("avatar", fileName, fileBody)
                .build();
        Request request = new Request.Builder().url(url).post(mBody)
                .addHeader("X-API-TOKEN", SPUtils.getString(MyMaterial2Activity.this, Constant.TOKEN))
                .build();

        OkHttpClient mOkHttpClient = new OkHttpClient();
        //   OkHttpClient mOkHttpClient = new OkHttpClient();
        // OkHttpClient okHttpClient = MaccRetrofit.getInstance().obtainClient();


        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new okhttp3.Callback() {

           @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.d("错误:" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    LogUtils.d("请求返回:" + str);
                } else {

                    String str = response.networkResponse().toString();
                    LogUtils.d("请求返回2:" + str);
                    LogUtils.d("tag", "当前线程:" + Thread.currentThread());
                }
                String string = response.body().string();
                AvatarMode avatarBean = new Gson().fromJson(string, AvatarMode.class);
                //请求成功返回头像
                String avatar_url = avatarBean.getAvatar_url();
                LogUtils.d("tag","avatar:"+avatarBean.getAvatar_url());

                Toast.makeText(MyMaterial2Activity.this, "更换成功", Toast.LENGTH_SHORT).show();
                SPUtils.putString(UiUtilities.getContex(), Constant.AVATAR_URL, avatar_url);

                EventBus.getDefault().post(new GenderEvent("1", 1));

            }
        });

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MyMaterial2 Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mClient, getIndexApiAction());
        mClient.disconnect();
    }


    private class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.v("TAG", "request:" + request.toString());
            long t1 = System.nanoTime();
            Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
            Log.v("TAG", String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.i("TAG", "response body:" + content);
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    }

    private void putGender(final String gender) {
        FormBody.Builder builder = new FormBody.Builder();

        FormBody body = builder
                .add("gender", gender)
                .build();
        OkHttpUtils
                .put()
                .url(Constant.BASE_URL + "customers/" + SPUtils.getInt(MyMaterial2Activity.this, Constant.USER_CID))
                .addHeader("X-API-TOKEN", SPUtils.getString(MyMaterial2Activity.this, Constant.TOKEN))
                .requestBody(body)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(UiUtilities.getContex(), "修改失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        Toast.makeText(UiUtilities.getContex(), "修改成功", Toast.LENGTH_SHORT).show();
                        SPUtils.putString(UiUtilities.getContex(), Constant.GENDER, gender);
                        EventBus.getDefault().post(new GenderEvent(gender, 1));
                    }
                });
    }

/*
    *//**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     *//*
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MyMaterial2 Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }*/
/*
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mClient, getIndexApiAction());
        mClient.disconnect();
    }*/
}

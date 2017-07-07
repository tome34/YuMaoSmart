package com.yumao.yumaosmart.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.itheima.roundedimageview.RoundedImageView;
import com.soundcloud.android.crop.Crop;
import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.manager.UserInformationManager;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.ToastUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.yumao.yumaosmart.widgit.MyMaterialItemView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Response;

public class MyMaterial2Activity extends BaseItemActivity implements View.OnClickListener {


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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   // private GoogleApiClient mClient;
    private Uri mImageUri;
    private Uri mCropUri;
    private boolean flag =true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_material2);

        ButterKnife.bind(this);
        initToobar("我的资料");

        initData();
        flag = false;

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag){
            initData();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        flag = true;
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

                                tryOpenCamera();

                                break;
                            case 1:  //从相机选择

                                openAlbum(mIntent);
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
                mIntent = new Intent(this ,AddressActivity.class);
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
                finish();
                break;

        }
    }

    //尝试打开相机 无权限则申请权限
    private void tryOpenCamera() {
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 111:
                openCamera(mIntent);
                break;
        }
    }

    //打开相机
    private void openAlbum(Intent intent) {
        //返回被选中项的URI
        intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//得到所有图片的URI
        startActivityForResult(intent, 125);

    }
    //打开相册
    private void openCamera(Intent intent) {
        try {
            // 开启相机应用程序获取并返回图片
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            UserInformationManager.getInstance().createImageDir();
            // 獲取拍照的圖片的uri
            mImageUri = UserInformationManager.getInstance().getImageUri("234.jpeg");
            // 指明存储图片或视频的地址URI
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            startActivityForResult(intent, 126);
        } catch (Exception e) {
            Toast.makeText(MyMaterial2Activity.this, "相机无法启动，请先开启相机权限", Toast.LENGTH_LONG).show();
        }
    }

    //图片裁剪
    private void cropPic(Uri sourceUri) {
        mCropUri = UserInformationManager.getInstance().getImageUri("123.jpeg");


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
            case 126:
                if (resultCode == RESULT_OK) {
                    cropPic(mImageUri);//裁剪圖片
                }
                break;
            case Crop.REQUEST_CROP:
                if (resultCode == RESULT_OK) {
                        LogUtils.d("tag","裁剪图片成功:"+mCropUri);
                    //執行網絡請求,把圖片post上去
                    //postImage(mCropUri);
                }
                break;
            default:
                break;
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
                        SPUtils.putString(UiUtilities.getContex(),Constant.GENDER,gender);
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

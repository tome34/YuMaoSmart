package com.yumao.yumaosmart.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.MainActivity;
import com.yumao.yumaosmart.callback.OpenIdCallback;
import com.yumao.yumaosmart.callback.UserCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.mode.OpenIdMode;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;

import cn.sharesdk.wechat.utils.WXAppExtendObject;
import cn.sharesdk.wechat.utils.WXMediaMessage;
import cn.sharesdk.wechat.utils.WechatHandlerActivity;
import okhttp3.Call;

/**
 * Created by kk on 2017/6/30.
 */

public class WXEntryActivity extends WechatHandlerActivity implements IWXAPIEventHandler {

    /**
     * 处理微信发出的向第三方应用请求app message
     * <p>
     * 在微信客户端中的聊天页面有“添加工具”，可以将本应用的图标添加到其中
     * 此后点击图标，下面的代码会被执行。Demo仅仅只是打开自己而已，但你可
     * 做点其他的事情，包括根本不打开任何页面
     */
    public void onGetMessageFromWXReq(WXMediaMessage msg) {
        if (msg != null) {
            Intent iLaunchMyself = getPackageManager().getLaunchIntentForPackage(getPackageName());
            startActivity(iLaunchMyself);
        }
    }

    /**
     * 处理微信向第三方应用发起的消息
     * <p>
     * 此处用来接收从微信发送过来的消息，比方说本demo在wechatpage里面分享
     * 应用时可以不分享应用文件，而分享一段应用的自定义信息。接受方的微信
     * 客户端会通过这个方法，将这个信息发送回接收方手机上的本demo中，当作
     * 回调。
     * <p>
     * 本Demo只是将信息展示出来，但你可做点其他的事情，而不仅仅只是Toast
     */
    public void onShowMessageFromWXReq(WXMediaMessage msg) {
        if (msg != null && msg.mediaObject != null
                && (msg.mediaObject instanceof WXAppExtendObject)) {
            WXAppExtendObject obj = (WXAppExtendObject) msg.mediaObject;
            Toast.makeText(this, obj.extInfo, Toast.LENGTH_SHORT).show();
        }
    }

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_result);
        //初始化微信api
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);

        /**
         *
         * 处理微信授权登录结果需要监听接口IWXAPIEventHandler
         * 注意：授权登录回调返回到net.sourceforge.simcpux.wxapi.WXEntryActivity中，
         * 包名和类名不能改变
         *
         */
        boolean handleIntent = api.handleIntent(getIntent(), this);
    }

    //IWXAPIEventHandler接口的onReq方法进行回调
    @Override
    public void onReq(BaseReq baseReq) {
            LogUtils.d("tag","回调:"+baseReq);
    }

    //应用请求微信的响应结果将通过onResp回调
    @Override
    public void onResp(BaseResp baseResp) {
    //用户授权成功

        LogUtils.d("tag","回调2:"+baseResp);

        if (baseResp.getType() == ConstantsAPI.COMMAND_SENDAUTH && baseResp.errCode == BaseResp.ErrCode.ERR_OK ) {
        //if(BaseResp.ErrCode.ERR_OK == 0){
            String code = ((SendAuth.Resp) baseResp).code;//授权码
            netQue(code);
        }else{
            finish();
        }

    }

    private void netQue(String mCode) {
        String code = mCode;
        OkHttpUtils
                .get()
                .url("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+Constant.APP_ID+"&secret="+"12a3c47c140d5c90c3a40aabced529ce"+"&code="+code+"&grant_type=authorization_code")
                .build()
                .execute(new OpenIdCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(OpenIdMode response, int id) {

                        String access_token = response.getAccess_token();
                        String openid = response.getOpenid();

                        netquese(access_token,openid);
                    }
                });
    }

    private void netquese(String access_token, String openid) {

        OkHttpUtils
                .post()
                .url(Constant.BASE_URL+"wechat/login")
                .addParams("access_token",access_token)
                .addParams("openid",openid)
                .build()
                .execute(new UserCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(WXEntryActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(WXEntryActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        User mUserBean = new Gson().fromJson(response, User.class);
                        String token = mUserBean.getToken();
                        int cid = mUserBean.getId();
                        String avatar_url = mUserBean.getAvatar_url();
                        String nick_name = mUserBean.getNick_name();
                        String phone = mUserBean.getPhone();
                        String date_of_birth = mUserBean.getDate_of_birth();
                        String gender = mUserBean.getGender();

                        Toast.makeText(UiUtilities.getContex(),"登录成功", Toast.LENGTH_SHORT).show();
                        SPUtils.putString(UiUtilities.getContex(), Constant.TOKEN, token);
                        SPUtils.putString(UiUtilities.getContex(), Constant.USER_DATA, response);

                        //保存用户头像
                        SPUtils.putString(UiUtilities.getContex(),Constant.AVATAR_URL,avatar_url);
                        LogUtils.d("tag","用户头像:"+avatar_url);
                        //保存cid号
                        SPUtils.putInt(UiUtilities.getContex(),Constant.USER_CID,cid);
                        //保存用户昵称
                        SPUtils.putString(UiUtilities.getContex(),Constant.NICK_NAME, nick_name);
                        //保存用户手机号码
                        SPUtils.putString(UiUtilities.getContex(),Constant.PHONE, phone);
                        //保存生日
                        SPUtils.putString(UiUtilities.getContex(),Constant.NICK_NAME, date_of_birth);
                        //性别
                        SPUtils.putString(UiUtilities.getContex(),Constant.GENDER, gender);
                            LogUtils.d("tag","微信登录回调:"+response);

                        Intent intent = new Intent(WXEntryActivity.this, MainActivity.class);
                        //intent.putExtra("wxId","1");
                        startActivity(intent);
                        finish();
                    }
                });
    }
}

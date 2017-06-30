package wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.callback.OpenIdCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.mode.OpenIdMode;
import com.yumao.yumaosmart.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by kk on 2017/6/30.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

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
        api.handleIntent(getIntent(),this);
    }

    //IWXAPIEventHandler接口的onReq方法进行回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //应用请求微信的响应结果将通过onResp回调
    @Override
    public void onResp(BaseResp baseResp) {
    //用户授权成功


        //if (baseResp.getType() == ConstantsAPI.COMMAND_SENDAUTH && baseResp.errCode == BaseResp.ErrCode.ERR_OK ) {
        if(BaseResp.ErrCode.ERR_OK == 0){
            String code = ((SendAuth.Resp) baseResp).code;//授权码
            Toast.makeText(this, "授权码code = " + code, Toast.LENGTH_SHORT).show();
                LogUtils.d("code:"+baseResp);
            netQue(code);
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
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }
}
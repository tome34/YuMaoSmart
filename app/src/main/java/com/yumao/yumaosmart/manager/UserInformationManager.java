package com.yumao.yumaosmart.manager;

/**
 * Created by kk on 2017/7/1.
 */

public class UserInformationManager {



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

    //检查当前用户的权限,共有5级:


}

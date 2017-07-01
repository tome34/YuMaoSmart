package com.yumao.yumaosmart.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.List;

/**
 * 6.0运行时权限管理
 * Created by cheegon on 3/14/2017.
 */

public class PermissionsManager {
    /**
     * 检查是否拥有权限
     *
     * @param context 上下文
     * @param perms   权限的可变参数
     */
    public static boolean hasPermissions(Context context, String... perms) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String perm : perms) {
            boolean hasPerm = ContextCompat.checkSelfPermission(context, perm) == PackageManager.PERMISSION_GRANTED;
            if (!hasPerm) {
                return false;
            }
        }
        return true;
    }

    public static void requestPermissions() {

    }

    /**
     * 权限检查的回调
     */
    public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {

        void onPermissionsGranted(int requestCode, List<String> perms);

        void onPermissionsDenied(int requestCode, List<String> perms);

    }
}

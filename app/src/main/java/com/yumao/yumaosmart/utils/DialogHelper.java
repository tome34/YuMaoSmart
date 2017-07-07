package com.yumao.yumaosmart.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;


/**
 * 对话框的工具类
 * Created by cheegon on 3/9/2017.
 */

public final class DialogHelper {
    /**
     * 获取dialog
     *
     * @param context
     * @return
     */
    public static AlertDialog.Builder getDialog(Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        //  AlertDialog.Builder dialog = new AlertDialog.Builder(context, R.style.App_Theme_Dialog_Alert);// 可以在这里自定义dialog
        return dialog;
    }

    /**
     * 获取一个普通的进度progressDialog
     *
     * @param context
     * @return
     */
    public static ProgressDialog getProgressDialog(Context context) {
        return new ProgressDialog(context);
    }

    /**
     * 获取一个可以取消的进度progressDialog
     *
     * @param context
     * @param cancleable
     * @return
     */
    public static ProgressDialog getProgressDialog(Context context, boolean cancleable) {
        ProgressDialog progressDialog = getProgressDialog(context);
        progressDialog.setCancelable(cancleable);
        return progressDialog;
    }

    /**
     * 获取一个带消息提示的progressDialog
     *
     * @param context
     * @param message
     * @return
     */
    public static ProgressDialog getProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = getProgressDialog(context);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    /**
     * 获取一个带cancleable和消息的progressDialog
     *
     * @param context
     * @param cancleable
     * @param message
     * @return
     */
    public static ProgressDialog getProgressDialog(Context context, boolean cancleable, String message) {
        ProgressDialog progressDialog = getProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancleable);
        return progressDialog;
    }

    /**
     * 获取一个带标题的progressdialog
     *
     * @param context
     * @param title
     * @param cancleable
     * @param message
     * @return
     */
    public static ProgressDialog getProgressDialog(Context context, String title, boolean cancleable, String message) {
        ProgressDialog progressDialog = getProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancleable);
        progressDialog.setTitle(title);
        return progressDialog;
    }

    /**
     * 获取带有确定按钮的对话框
     *
     * @param context
     * @param message
     * @param dialogCallBack
     */
    public static AlertDialog.Builder getAlertDialog(Context context, String message, final DialogCallBack dialogCallBack) {
        return getAlertDialog(context, "提示", message, dialogCallBack);
    }

    /**
     * 获取带有确定按钮的对话框
     *
     * @param context
     * @param message
     * @param dialogCallBack
     */
    public static AlertDialog.Builder getAlertDialog(Context context, String title, String message, final DialogCallBack dialogCallBack) {
        return getDialog(context).setTitle(title)
                .setCancelable(true)
                .setMessage(message)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogCallBack.pressCancle();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogCallBack.pressSure();
                    }
                });
                /*.setNegativeButton("取消", (dialog, which) -> {
                    dialogCallBack.pressCancle();
                })
                .setPositiveButton("确定", (dialog, which) -> {
                    dialogCallBack.pressSure();
                });*/
    }

    /**
     * 获取一个没有取消按钮的信息对话框
     *
     * @param context
     * @param message
     * @return
     */
    public static AlertDialog.Builder getMessageDialog(Context context, String message) {
        return getMessageDialog(context, "", message, false);

    }

    /**
     * 获取一个没有取消按钮的信息对话框
     *
     * @param context
     * @param title
     * @param message
     * @param positiveText
     * @return
     */
    public static AlertDialog.Builder getMessageDialog(Context context, String title, String message, String positiveText) {
        return getDialog(context).setCancelable(false)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText, null);
    }

    /**
     * 获取一个没有取消按钮的信息对话框
     *
     * @param context
     * @param title
     * @param message
     * @param cancelable
     * @return
     */
    public static AlertDialog.Builder getMessageDialog(Context context, String title, String message, boolean cancelable) {
        return getDialog(context).setCancelable(cancelable)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", null);
    }

    public interface DialogCallBack {
        void pressSure();

        void pressCancle();
    }
}

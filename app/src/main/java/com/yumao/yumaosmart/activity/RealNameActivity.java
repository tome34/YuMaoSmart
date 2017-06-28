package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.IdentityDialogAdapter;
import com.yumao.yumaosmart.base.BaseItemActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kk on 2017/3/9.
 */
public class RealNameActivity extends BaseItemActivity {
    @BindView(R.id.edit_testidentity_activity_name)
    EditText mEditTestidentityActivityName;
    @BindView(R.id.edit_testidentity_activity_num)
    EditText mEditTestidentityActivityNum;
    @BindView(R.id.iv_test_identity_activity_front)
    ImageView mIvTestIdentityActivityFront;
    @BindView(R.id.iv_test_identity_activity_back)
    ImageView mIvTestIdentityActivityBack;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testidentity);
        initToobar(getString(R.string.title_test_identity));
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mList.add("拍照");
        mList.add("从相册选择");
        mList.add("取消");
    }

    @OnClick({R.id.iv_test_identity_activity_front, R.id.iv_test_identity_activity_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_test_identity_activity_front:
                handIdentity();
                break;
            case R.id.iv_test_identity_activity_back:
                handIdentity();
                break;
        }
    }

    private void handIdentity() {
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setAdapter(new IdentityDialogAdapter(this,R.layout.item_material_touxiang_dialog,mList))
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        switch (position) {

                            case 0:
                                Intent cameraIntent = new Intent(
                                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivity(cameraIntent);
                                break;
//
                            case 1:
                                Intent intent = new Intent();
                                intent.addCategory(Intent.CATEGORY_OPENABLE);
                                intent.setType("image/*");
                                //根据版本号不同使用不同的Action
                                if (Build.VERSION.SDK_INT <19) {
                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                }else {
                                    intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                                }
//                                        mActivity.startActivityForResult(intent, REQUEST_CODE);

                                break;
                            case 2:

                                dialog.dismiss();
                                break;

                        }
                    }
                })
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        dialog.show();
    }
}

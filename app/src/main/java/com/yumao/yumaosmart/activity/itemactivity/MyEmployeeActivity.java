package com.yumao.yumaosmart.activity.itemactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyEmployeeActivity extends BaseItemActivity {

    @BindView(R.id.iv_activity_my_employee_touxiang)
    ImageView mIvActivityMyEmployeeTouxiang;
    @BindView(R.id.tv_personnal_petname)
    TextView mTvPersonnalPetname;
    @BindView(R.id.tv_activity_my_employee_petname)
    TextView mTvActivityMyEmployeePetname;
    @BindView(R.id.tv_activity_my_employee_identity)
    TextView mTvActivityMyEmployeeIdentity;
    @BindView(R.id.tv_activity_my_employee_money_getted)
    TextView mTvActivityMyEmployeeMoneyGetted;
    @BindView(R.id.iv_activity_my_employee_money_getted_detail)
    ImageView mIvActivityMyEmployeeMoneyGettedDetail;
    @BindView(R.id.rellayout_activity_my_employee_gettedmoney)
    RelativeLayout mRellayoutActivityMyEmployeeGettedmoney;
    @BindView(R.id.tv_activity_my_employee_money_ungetted)
    TextView mTvActivityMyEmployeeMoneyUngetted;
    @BindView(R.id.iv_activity_my_employee_money_ungetted_detail)
    ImageView mIvActivityMyEmployeeMoneyUngettedDetail;
    @BindView(R.id.rellayout_activity_my_employee_ungettedmoney)
    RelativeLayout mRellayoutActivityMyEmployeeUngettedmoney;
    @BindView(R.id.tv_activity_my_employee_money_logked)
    TextView mTvActivityMyEmployeeMoneyLogked;
    @BindView(R.id.iv_activity_my_employee_money_locked_detail)
    ImageView mIvActivityMyEmployeeMoneyLockedDetail;
    @BindView(R.id.rellayout_activity_my_employee_lockedmoney)
    RelativeLayout mRellayoutActivityMyEmployeeLockedmoney;
    @BindView(R.id.tv_activity_my_employee_bank_logked)
    TextView mTvActivityMyEmployeeBankLogked;
    @BindView(R.id.iv_activity_my_employee_bank_locked_detail)
    ImageView mIvActivityMyEmployeeBankLockedDetail;
    @BindView(R.id.rellayout_activity_my_employee_lockedbank)
    RelativeLayout mRellayoutActivityMyEmployeeLockedbank;
    @BindView(R.id.activity_my_employee)
    LinearLayout mActivityMyEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_employee);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_my_employee));
    }

    @OnClick({R.id.rellayout_activity_my_employee_gettedmoney, R.id.rellayout_activity_my_employee_ungettedmoney, R.id.rellayout_activity_my_employee_lockedmoney, R.id.rellayout_activity_my_employee_lockedbank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rellayout_activity_my_employee_gettedmoney:
                break;
            case R.id.rellayout_activity_my_employee_ungettedmoney:
                break;
            case R.id.rellayout_activity_my_employee_lockedmoney:
                break;
            case R.id.rellayout_activity_my_employee_lockedbank:
                startActivity(new Intent(this,LockBandActivity.class));
                break;
        }
    }
}

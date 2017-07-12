package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.LanMujiangXuanAdapter;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.callback.LanMuJingXuanCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.inter.OnItemClickListener;
import com.yumao.yumaosmart.mode.LanMuJingXuanBean;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.yumao.yumaosmart.base.MyApplication.mContext;

public class LanMujingxuanActivity extends BaseItemActivity {


    @BindView(R.id.iv_first_item_person_topleft)
    ImageView mIvFirstItemPersonTopleft;
    @BindView(R.id.iv_first_item_person_topright)
    RoundedImageView mIvFirstItemPersonTopright;
    @BindView(R.id.tv_first_list_user_name)
    TextView mTvFirstListUserName;
    @BindView(R.id.tv_first_page_person_tel)
    ImageView mTvFirstPagePersonTel;
    @BindView(R.id.tv_first_page_shanjiajieshao)
    ImageView mTvFirstPageShanjiajieshao;
    @BindView(R.id.first_banner_iv)
    ImageView mFirstBannerIv;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.activity_lan_mujingxuan)
    LinearLayout mActivityLanMujingxuan;
    private LanMujiangXuanAdapter mAdapter;

    List<String> mImageList ; //栏目精选图片集合
    List<String> mTiltisList ; //栏目精选标题集合
    List<Integer> mIdList ;     //产品id
    List<Integer> mPriceList ;  //产品价格
    List<Integer> mProductCostList ; //产品的成本价
    List<Integer> mResalePriceList ; //产品的转售价格,以转卖价优先
    List<String> mNumberList ;//产品编号
    private String mLogoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lan_mujingxuan);

        initToobar("栏目精选");
        ButterKnife.bind(this);

        initData(); //初始化数据



    }


    private void initView() {

        // 竖直方向的网格样式，每行2个Item
        GridLayoutManager mLayoutManager = new GridLayoutManager(UiUtilities.getContex(), 2, OrientationHelper.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                //return super.canScrollVertically();
                // 直接禁止垂直滑动
                return false;
            }
        };
        //设置管理器
        mRecyclerview.setLayoutManager(mLayoutManager);
        //设置adapter
        mAdapter = new LanMujiangXuanAdapter(LanMujingxuanActivity.this,mImageList,mTiltisList,mResalePriceList,mPriceList,mNumberList);

        mRecyclerview.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItenClick(View view, int position) {
                Toast.makeText(UiUtilities.getContex(), "被点击了" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongCllick(View view, int position) {

            }
        });

    }

    private void initData() {

        Intent intent = getIntent();
        int vsid = intent.getIntExtra("id", -1);
            LogUtils.d("tag","vsid:"+vsid);
        String bannerImage = intent.getStringExtra("BannerImage");
        //设置banner图
        Picasso.with(UiUtilities.getContex()).load(bannerImage).fit().into(mFirstBannerIv);
        //首页左边商城头像
        Picasso.with(mContext).load(mLogoUri).resize(180,180).placeholder(R.mipmap.yumao_mall).into(mIvFirstItemPersonTopleft);

        //用户头像
        String iconUrl = SPUtils.getString(UiUtilities.getContex(), Constant.AVATAR_URL);
        Picasso.with(mContext).load(iconUrl).placeholder(R.mipmap.first_page_person_icon_touxiang).into(mIvFirstItemPersonTopright);

        //用户名

        String niceName = SPUtils.getString(UiUtilities.getContex(), Constant.NICK_NAME);
        mTvFirstListUserName.setText(niceName);
        mTvFirstListUserName.setTextColor(Color.BLACK);


        mImageList = new ArrayList<>(); //栏目精选图片集合
         mTiltisList = new ArrayList<>(); //栏目精选标题集合
         mIdList = new ArrayList<>();     //产品id
         mPriceList = new ArrayList<>();  //产品价格
         mProductCostList = new ArrayList<>() ; //产品的成本价
         mResalePriceList = new ArrayList<>(); //产品的转售价格,以转卖价优先
        mNumberList = new ArrayList<>(); //产品的编号

        OkHttpUtils
                .get()
                .url(Constant.BASE_URL+"vendor-sections/"+vsid+"/section-featured/products")  //栏目详情页
                .addParams("page","1")
                .build()
                .execute(new LanMuJingXuanCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                            LogUtils.d("tag","失败");
                    }

                    @Override
                    public void onResponse(List<LanMuJingXuanBean> response, int id) {
                        LogUtils.d("tag","成功");
                        mLogoUri = response.get(1).getVendor().getLogo();
                        for (int i = 0 ; i <response.size();i++){

                            mImageList.add(response.get(i).getThumb()); //图片
                            mTiltisList.add(response.get(i).getName());
                            mIdList.add(response.get(i).getId());
                            mPriceList.add(response.get(i).getPrice());
                            mProductCostList.add(response.get(i).getProduct_cost());
                            mResalePriceList.add(response.get(i).getResale_price());

                            //产品编号
                            int a = (int) Math.floor((float)(mPriceList.get(i) - mProductCostList.get(i)) / mPriceList.get(i) * 0.6 * 100);
                            String number = getNumber(a);
                                LogUtils.d("编号:"+number);
                            int length = String.valueOf(mIdList.get(i)).length();
                            StringBuffer stringBuffer = new StringBuffer();
                            if(length<8){
                                int i1 = 8 - length;
                                for(int j =0;j<i1;j++){
                                    stringBuffer.append(0);
                                }
                                stringBuffer.append(mIdList.get(i));
                            }
                                LogUtils.d("最终编号:"+number+stringBuffer);
                               String s = number + stringBuffer;

                            //编号加入集合
                            mNumberList.add(s);
                        }

                        initView(); //等数据加载完,初始化视图

                    }


                });
    }

    //获得编号
    public String getNumber(int a) {
        char[] numChar =new char[2];
        String s = String.valueOf(a);
        char[] chars = s.toCharArray();
        LogUtils.d("chars:"+chars[0]+" "+chars[1]);
        if (chars[0] == '0'){
            numChar[0] ='X' ;
        }else if(chars[0] == '1'){
            numChar[0] ='A' ;
        }else if(chars[0] == '2'){
            numChar[0] ='B' ;
        }else if(chars[0] == '3'){
            numChar[0] ='C' ;
        }else if(chars[0] == '4'){
            numChar[0] ='D' ;
        }else if(chars[0] == '5'){
            numChar[0] ='E' ;
        }else if(chars[0] == '6'){
            numChar[0] ='F' ;
        }else if(chars[0] == '7'){
            numChar[0] ='G' ;
        }else if(chars[0] == '8'){
            numChar[0] ='H' ;
        }else if(chars[0] == '9'){
            numChar[0] ='I' ;
        }

        if(chars[1] == '0'){
            numChar[1] ='X' ;
        }else if(chars[1] == '1'){
            numChar[1] ='A' ;
        }else if(chars[1] == '2'){
            numChar[1] ='B' ;
        }else if(chars[1] == '3'){
            numChar[1] ='C' ;
        }else if(chars[1] == '4'){
            numChar[1] ='D' ;
        }else if(chars[1] == '5'){
            numChar[1] ='E' ;
        }else if(chars[1] == '6'){
            numChar[1] ='F' ;
        }else if(chars[1] == '7'){
            numChar[1] ='G' ;
        }else if(chars[1] == '8'){
            numChar[1] ='H' ;
        }else if(chars[1] == '9'){
            numChar[1] ='I' ;
        }

            LogUtils.d("111:"+numChar[0]+" "+numChar[1]);
        char[] cha = new char[2];
        cha[0]='X';
        cha[1]='X';
            LogUtils.d("222:"+cha[0]+" "+cha[1]);

        return new String(numChar);
    }

}

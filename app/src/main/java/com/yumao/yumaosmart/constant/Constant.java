package com.yumao.yumaosmart.constant;

/**
 * Created by kk on 2017/2/27.
 */

public interface Constant  {
    public static final int TYPE_COUNT_FIRST_PAGER = 4;

    public static final  String BASE_URL="https://test-dist.yumao168.com/api/";

    public static final  int ADDRESS_CREATE=0;
    public static final  int ADDRESS_UPDATE=1;

    //sp存储的值
    String SP_FILE_NAME = "setting";
    //首页搜索历史记录
    String SP_FILE_SEARCH = "search_history";
    //存储用户的token
    public static final String TOKEN = "token";

    //存储用户的信息
    String USER_DATA = "user_data";

    //appid
    public static final String APP_ID = "wxc22e6c182ddd6959";
    //用户的权限等级
    public static final String USER_GRADE ="user_grade";
    //存储用户的id
    public static final String USER_CID ="user_cid";
    //保存用户头像
    public static final String AVATAR_URL ="avatar_url";
    //保存用户昵称
    public static final String NICK_NAME ="nick_name";
    //保存用户手机号码
    public static final String PHONE ="phone";
    //保存生日
    public static final String DATA_OF_BIRTH ="data_of_birth";

    //性别
    public static final String GENDER ="gender";

    //产品id
    public static final String PRODUCT_ID ="product_id";

    //列表二级分类id:categoryId
    public static final String CATEGORY_ID ="category_id";

    //购物车的json数据
    public static final String CART_JSON ="cart_json";

    //搜索结果keyword
    public static final String SEARCH_RESULT ="search_result";

    //搜索跳转类型
    public static final String SEARCH_TAGE ="search_tage";

}

package eric.cn.com.varnish.utils;

/**
 * Handler 地址
 * 
 * @author Administrator
 * 
 */
public class MsgConfig {
	// 生成保单------------------------------------------------------------------
	public static final byte MSG_WHAT_InsuranceOrder = 0;
	/** 成功 */
	public static final byte MSG_ARG_InsuranceOrder_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_InsuranceOrder_FAILURE = 2;
	// 获取订单列表------------------------------------------------------------------
	public static final byte MSG_WHAT_OrderList = 1;
	/** 成功 */
	public static final byte MSG_ARG_OrderList_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_OrderList_FAILURE = 2;
	// 获取保养信息------------------------------------------------------------------
	public static final byte MSG_WHAT_UpkeepDetails = 2;
	/** 成功 */
	public static final byte MSG_ARG_UpkeepDetails_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_UpkeepDetails_FAILURE = 2;
	// 取消保养订单------------------------------------------------------------------
	public static final byte MSG_WHAT_CancelUpkeepOrder = 3;
	/** 成功 */
	public static final byte MSG_ARG_CancelUpkeepOrder_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_CancelUpkeepOrder_FAILURE = 2;
	// 获取保险信息------------------------------------------------------------------
	public static final byte MSG_WHAT_InsuranceDetails = 4;
	/** 成功 */
	public static final byte MSG_ARG_InsuranceDetails_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_InsuranceDetails_FAILURE = 2;
	// 评论------------------------------------------------------------------
	public static final byte MSG_WHAT_Evaluate = 5;
	/** 成功 */
	public static final byte MSG_ARG_Evaluate_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_Evaluate_FAILURE = 2;
	// 显示车检报告------------------------------------------------------------------
	public static final byte MSG_WHAT_CarExamine = 8;
	/** 成功 */
	public static final byte MSG_ARG_CarExamine_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_CarExamine_FAILURE = 2;
	// 登录------------------------------------------------------------------
	public static final byte MSG_WHAT_Login = 9;
	/** 成功 */
	public static final byte MSG_ARG_Login_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_Login_FAILURE = 2;
	// 验证码倒计时------------------------------------------------------------------
	public static final byte MSG_WHAT_VAR = 10;
	// 发送验证码------------------------------------------------------------------
	public static final byte MSG_WHAT_Verify = 11;
	/** 成功 */
	public static final byte MSG_ARG_Verify_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_Verify_FAILURE = 2;
	// 微信支付信息------------------------------------------------------------------
	public static final byte MSG_WHAT_WeiXingInfo = 12;
	/** 成功 */
	public static final byte MSG_ARG_WeiXingInfo_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_WeiXingInfo_FAILURE = 2;
	// 支付宝支付信息------------------------------------------------------------------
	public static final byte MSG_WHAT_ZhiFuBaoInfo = 13;
	/** 成功 */
	public static final byte MSG_ARG_ZhiFuBaoInfo_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_ZhiFuBaoInfo_FAILURE = 2;
	// 支付宝支付信息------------------------------------------------------------------
	public static final byte MSG_WHAT_Camera = 14;
	// 上传头像------------------------------------------------------------------
	public static final byte MSG_WHAT_UpPicture = 15;
	/** 成功 */
	public static final byte MSG_ARG_UpPicture_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_UpPicture_FAILURE = 2;
	// 获取个人信息------------------------------------------------------------------
	public static final byte MSG_WHAT_MyInfo = 16;
	/** 成功 */
	public static final byte MSG_ARG_MyInfo_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_MyInfo_FAILURE = 2;
	// 获取个人信息------------------------------------------------------------------
	public static final byte MSG_WHAT_UpDataInfo = 17;
	/** 成功 */
	public static final byte MSG_ARG_UpDataInfo_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_UpDataInfo_FAILURE = 2;
	// 获取车检报告列表------------------------------------------------------------------
	public static final byte MSG_WHAT_ReportList = 18;
	/** 成功 */
	public static final byte MSG_ARG_ReportList_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_ReportList_FAILURE = 2;
	// 获取城市------------------------------------------------------------------
	public static final byte MSG_WHAT_CityList = 19;
	/** 成功 */
	public static final byte MSG_ARG_CityList_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_CityList_FAILURE = 2;
	// 地址管理------------------------------------------------------------------
	public static final byte MSG_WHAT_AddressManager = 20;
	/** 成功 */
	public static final byte MSG_ARG_AddressManager_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_AddressManager_FAILURE = 2;
	// 地址列表------------------------------------------------------------------
	public static final byte MSG_WHAT_AddressList = 21;
	/** 成功 */
	public static final byte MSG_ARG_AddressList_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_AddressList_FAILURE = 2;
	// 默认地址------------------------------------------------------------------
	public static final byte MSG_WHAT_AddressDefault = 22;
	/** 成功 */
	public static final byte MSG_ARG_AddressDefault_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_AddressDefault_FAILURE = 2;
	// 选择车型------------------------------------------------------------------
	public static final byte MSG_WHAT_CarType = 23;
	/** 成功 */
	public static final byte MSG_ARG_CarType_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_CarType_FAILURE = 2;
	/** 结束 */
	public static final byte MSG_ARG_CarType_END = 3;
	// 车型管理,增删改------------------------------------------------------------------
	public static final byte MSG_WHAT_CarManager = 24;
	/** 成功 */
	public static final byte MSG_ARG_CarManager_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_CarManager_FAILURE = 2;
	// 车型列表------------------------------------------------------------------
	public static final byte MSG_WHAT_CarList = 25;
	/** 成功 */
	public static final byte MSG_ARG_CarList_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_CarList_FAILURE = 2;
	// 默认车型------------------------------------------------------------------
	public static final byte MSG_WHAT_CarDefault = 26;
	/** 成功 */
	public static final byte MSG_ARG_CarDefault_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_CarDefault_FAILURE = 2;
	// 短信动态码认证------------------------------------------------------------------
	public static final byte MSG_WHAT_SmSverify = 27;
	/** 成功 */
	public static final byte MSG_ARG_SmSverify_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_SmSverify_FAILURE = 2;
	// 修改支付密码接口------------------------------------------------------------------
	public static final byte MSG_WHAT_PayPassword = 28;
	/** 成功 */
	public static final byte MSG_ARG_PayPassword_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_PayPassword_FAILURE = 2;
	// 调起微信支付------------------------------------------------------------------
	public static final byte MSG_WHAT_PayWeiXi = 29;
	// 农行支付------------------------------------------------------------------
	public static final byte MSG_WHAT_PayNongHang = 30;
	/** 成功 */
	public static final byte MSG_ARG_PayNongHang_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_PayNongHang_FAILURE = 2;
	// 获取Vip卡信息------------------------------------------------------------------
	public static final byte MSG_WHAT_VipCardInfo = 31;
	/** 成功 */
	public static final byte MSG_ARG_VipCardInfo_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_VipCardInfo_FAILURE = 2;
	// 获取Vip卡信息列表------------------------------------------------------------------
	public static final byte MSG_WHAT_VipCardInfoList = 32;
	/** 成功 */
	public static final byte MSG_ARG_VipCardInfoList_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_VipCardInfoList_FAILURE = 2;
	// 获取Vip卡信息列表------------------------------------------------------------------
	public static final byte MSG_WHAT_RechargeableOrder = 33;
	/** 成功 */
	public static final byte MSG_ARG_RechargeableOrder_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_RechargeableOrder_FAILURE = 2;
	// 添加油卡------------------------------------------------------------------
	public static final byte MSG_WHAT_AddOilCard = 34;
	/** 成功 */
	public static final byte MSG_ARG_AddOilCard_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_AddOilCard_FAILURE = 2;
	// 获取油卡------------------------------------------------------------------
	public static final byte MSG_WHAT_GetOilCard = 35;
	/** 成功 */
	public static final byte MSG_ARG_GetOilCard_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_GetOilCard_FAILURE = 2;
	// 建行支付------------------------------------------------------------------
	public static final byte MSG_WHAT_PayJianHang = 36;
	/** 成功 */
	public static final byte MSG_ARG_PayJianHang_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_PayJianHang_FAILURE = 2;
	// 充值地址------------------------------------------------------------------
	public static final byte MSG_WHAT_Rechargeable_Address = 37;
	/** 成功 */
	public static final byte MSG_ARG_Rechargeable_Address_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_Rechargeable_Address_FAILURE = 2;
	// 充值地址------------------------------------------------------------------
	public static final byte MSG_WHAT_RecordInfo = 38;
	/** 成功 */
	public static final byte MSG_ARG_RecordInfo_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_RecordInfo_FAILURE = 2;
	// 显示等待窗口------------------------------------------------------------------
	public static final byte MSG_WHAT_GetDefaultAddress = 39;
	/** 成功 */
	public static final byte MSG_ARG_GetDefaultAddress_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_GetDefaultAddress_FAILURE = 2;
	/** 记录为空 */
	public static final byte MSG_ARG_GetDefaultAddress_NULL = 3;
	// 充值会员信息完善------------------------------------------------------------------
	public static final byte MSG_WHAT_RechargeableInfo = 40;
	/** 成功 */
	public static final byte MSG_ARG_RechargeableInfo_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_RechargeableInfo_FAILURE = 2;

	// 获取优惠券列表------------------------------------------------------------------
	public static final byte MSG_WHAT_Coupons = 41;
	/** 成功 */
	public static final byte MSG_ARG_Coupons_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_Coupons_FAILURE = 2;
	// 优惠券领取------------------------------------------------------------------
	public static final byte MSG_WHAT_CouponsReceive = 42;
	/** 成功 */
	public static final byte MSG_ARG_CouponsReceive_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_CouponsReceive_FAILURE = 2;
	// 优惠券领取------------------------------------------------------------------
	public static final byte MSG_WHAT_MyProgressDialog = 43;
	/** 成功 */
	public static final byte MSG_ARG_MyProgressDialog_Open = 1;
	/** 失败 */
	public static final byte MSG_ARG_MyProgressDialog_clase = 2;

	// 充值返现------------------------------------------------------------------
	public static final byte MSG_WHAT_GetOilLogNet = 44;
	/** 成功 */
	public static final byte MSG_ARG_GetOilLogNet_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_GetOilLogNet_FAILURE = 2;
	// 获取绑定银行卡信息------------------------------------------------------------------
	public static final byte MSG_WHAT_GetBBC = 45;
	/** 成功 */
	public static final byte MSG_ARG_GetBBC_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_GetBBC_FAILURE = 2;
	// 添加绑定银行卡信息------------------------------------------------------------------
	public static final byte MSG_WHAT_AddBBC = 46;
	/** 成功 */
	public static final byte MSG_ARG_AddBBC_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_AddBBC_FAILURE = 2;
	/** 提示信息 */
	public static final byte MSG_ARG_AddBBC_SHOW = 3;
	// 修改绑定银行卡信息------------------------------------------------------------------
	public static final byte MSG_WHAT_EditBBC = 47;
	/** 成功 */
	public static final byte MSG_ARG_EditBBC_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_EditBBC_FAILURE = 2;
	/** 提示信息 */
	public static final byte MSG_ARG_EditBBC_SHOW = 3;
	// 获取账户余额------------------------------------------------------------------
	public static final byte MSG_WHAT_AccountBalance = 48;
	/** 成功 */
	public static final byte MSG_ARG_AccountBalance_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_AccountBalance_FAILURE = 2;
	// 获取默认银行卡------------------------------------------------------------------
	public static final byte MSG_WHAT_ObtainDefaultCard = 49;
	/** 成功 */
	public static final byte MSG_ARG_ObtainDefaultCard_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_ObtainDefaultCard_FAILURE = 2;
	/** 成功无值 **/
	public static final byte MSG_ARG_ObtainDefaultCard_VALUE = 3;
	// 获取银行卡列表------------------------------------------------------------------
	public static final byte MSG_WHAT_BankCardTable = 50;
	/** 成功 */
	public static final byte MSG_ARG_BankCardTable_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_BankCardTable_FAILURE = 2;
	// 支付密码认证------------------------------------------------------------------
	public static final byte MSG_WHAT_PayPasswordValidation = 51;
	/** 成功 */
	public static final byte MSG_ARG_PayPasswordValidation_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_PayPasswordValidation_FAILURE = 2;
	// 添加银行卡------------------------------------------------------------------
	public static final byte MSG_WHAT_AddBankCard = 52;
	/** 成功 */
	public static final byte MSG_ARG_AddBankCard_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_AddBankCard_FAILURE = 2;
	// 修改默认银行卡------------------------------------------------------------------
	public static final byte MSG_WHAT_ModifyDefaultBankCard = 53;
	/** 成功 */
	public static final byte MSG_ARG_ModifyDefaultBankCard_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_ModifyDefaultBankCard_FAILURE = 2;
	// 提现详细------------------------------------------------------------------
	public static final byte MSG_WHAT_WithdrawalDetails = 54;
	/** 成功 */
	public static final byte MSG_ARG_WithdrawalDetails_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_WithdrawalDetails_FAILURE = 2;
	// 获取开户银行列表------------------------------------------------------------------
	public static final byte MSG_WHAT_ChooseBank = 55;
	/** 成功 */
	public static final byte MSG_ARG_ChooseBank_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_ChooseBank_FAILURE = 2;
	// 银行卡解绑------------------------------------------------------------------
	public static final byte MSG_WHAT_BankCardUnbundling = 56;
	/** 成功 */
	public static final byte MSG_ARG_BankCardUnbundling_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_BankCardUnbundling_FAILURE = 2;
	// 判断是否设置了支付密码------------------------------------------------------------------
	public static final byte MSG_WHAT_PayPassword2 = 57;
	/** 成功 */
	public static final byte MSG_ARG_PayPassword2_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_PayPassword2_FAILURE = 2;
	// 订单详情 保险------------------------------------------------------------------
	public static final byte MSG_WHAT_OrderDetailsInsurance = 58;
	/** 成功 */
	public static final byte MSG_ARG_OrderDetailsInsurance_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_OrderDetailsInsurance_FAILURE = 2;
	// 是否完善信息或绑定银行卡
	// 首页------------------------------------------------------------------
	public static final byte MSG_WHAT_TatusBankMessage_MAIN = 59;
	/** 成功 */
	public static final byte MSG_ARG_TatusBankMessage_MAIN_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_TatusBankMessage_MAIN_FAILURE = 2;
	// 是否完善信息或绑定银行卡
	// 完善------------------------------------------------------------------
	public static final byte MSG_WHAT_TatusBankMessage_WEE = 60;
	// 获取完善信息------------------------------------------------------------------
	public static final byte MSG_WHAT_OilCardInfo = 61;
	/** 成功 */
	public static final byte MSG_ARG_OilCardInfo_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_OilCardInfo_FAILURE = 2;
	// 提交完善信息------------------------------------------------------------------
	public static final byte MSG_WHAT_SetOilCardInfo = 62;
	/** 成功 */
	public static final byte MSG_ARG_SetOilCardInfo_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_SetOilCardInfo_FAILURE = 2;
	/** 提示 */
	public static final byte MSG_ARG_SetOilCardInfo_SHOW = 3;
	// 获取经纬度------------------------------------------------------------------
	public static final byte MSG_WHAT_GetCoordinates = 62;
	/** 成功 */
	public static final byte MSG_ARG_GetCoordinates_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_GetCoordinates_FAILURE = 2;
	// 获取首页加盟商列表------------------------------------------------------------------
	public static final byte MSG_WHAT_JoiningTrader = 63;
	/** 成功 */
	public static final byte MSG_ARG_JoiningTrader_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_JoiningTrader_FAILURE = 2;
	// 获取加盟店详情------------------------------------------------------------------
	public static final byte MSG_WHAT_ItsFranchiseesDetails = 64;
	/** 成功 */
	public static final byte MSG_ARG_ItsFranchiseesDetails_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_ItsFranchiseesDetails_FAILURE = 2;
	// 网络获取广告图------------------------------------------------------------------
	public static final byte MSG_WHAT_PictureDynamic = 65;
	/** 成功 */
	public static final byte MSG_ARG_PictureDynamic_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_PictureDynamic_FAILURE = 2;
	/** 正在加载中 */
	public static final byte MSG_ARG_PictureDynamic_POSS = 3;

	// 添加银行卡------------------------------------------------------------------
	public static final byte MSG_WHAT_CouponStable = 66;
	/** 成功 */
	public static final byte MSG_ARG_CouponStable_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_CouponStable_FAILURE = 2;
	// 调起支付宝------------------------------------------------------------------
	public static final byte MSG_WHAT_PAY_ZHI = 67;

	// 获取加油站与加盟店坐标和基本信息------------------------------------------------------------------
	public static final byte MSG_WHAT_ObtainCoordinates = 68;
	/** 成功 */
	public static final byte MSG_ARG_ObtainCoordinates_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_ObtainCoordinates_FAILURE = 2;
	// 获取到店保养信息------------------------------------------------------------------
	public static final byte MSG_WHAT_DdDetails = 69;
	/** 成功 */
	public static final byte MSG_ARG_DdDetails_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_DdDetails_FAILURE = 2;
	// 倒计时------------------------------------------------------------------
	public static final byte MSG_WHAT_DJS = 70;
	// 获取洗车服务店铺接口------------------------------------------------------------------
	public static final byte MSG_WHAT_WashCarService = 71;
	/** 成功 */
	public static final byte MSG_ARG_WashCarService_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_WashCarService_FAILURE = 2;
	// 提交洗车订单接口------------------------------------------------------------------
	public static final byte MSG_WHAT_SubmitWashCarOrder = 72;
	/** 成功 */
	public static final byte MSG_ARG_SubmitWashCarOrder_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_SubmitWashCarOrder_FAILURE = 2;
	// 获取购买洗车服务接口------------------------------------------------------------------
	public static final byte MSG_WHAT_BuyWashCarOrder = 73;
	/** 成功 */
	public static final byte MSG_ARG_BuyWashCarOrder_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_BuyWashCarOrder_FAILURE = 2;
	// 生成购买洗车服务订单接口------------------------------------------------------------------
	public static final byte MSG_WHAT_GenerateWashCarOrder = 74;
	/** 成功 */
	public static final byte MSG_ARG_GenerateWashCarOrder_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_GenerateWashCarOrder_FAILURE = 2;
	// 获取过期代金券------------------------------------------------------------------
	public static final byte MSG_WHAT_MyVouchersOverdue = 75;
	/** 成功 */
	public static final byte MSG_ARG_MyVouchersOverdue_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_MyVouchersOverdue_FAILURE = 2;
	// 兑换代金券------------------------------------------------------------------
	public static final byte MSG_WHAT_VouchersExchange = 75;
	/** 成功 */
	public static final byte MSG_ARG_VouchersExchange_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_VouchersExchange_FAILURE = 2;
	// 获取URL地址------------------------------------------------------------------
	public static final byte MSG_WHAT_WebUrl = 76;
	/** 成功 */
	public static final byte MSG_ARG_WebUrl_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_WebUrl_FAILURE = 2;
	// 兑换洗车劵接口------------------------------------------------------------------
	public static final byte MSG_WHAT_WashCashCoupon = 77;
	/** 成功 */
	public static final byte MSG_ARG_WashCashCoupon_SUCCESS = 1;
	/** 失败 */
	public static final byte MSG_ARG_WashCashCoupon_FAILURE = 2;
}

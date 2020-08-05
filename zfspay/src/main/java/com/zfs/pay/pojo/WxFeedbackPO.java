package com.zfs.pay.pojo;

import java.io.Serializable;

/**
 * t_wx_feedback
 * @author 
 */
public class WxFeedbackPO implements Serializable {
    private Long wxpayid;

    private String returnCode;

    private String returnMsg;

    private String appid;

    private String mchId;

    private String deviceInfo;

    private String nonceStr;

    private String sign;

    private String resultCode;

    private String errCode;

    private String errCodeDes;

    private String openid;

    private String isSubscribe;

    private String tradeType;

    private String bankType;

    private Integer totalFee;

    private String feeType;

    private Integer cashFee;

    private String cashFeeType;

    private Integer couponFee;

    private Integer couponCount;

    private String transactionId;

    private String outTradeNo;

    private String attach;

    private String timeEnd;

    private static final long serialVersionUID = 1L;

    public Long getWxpayid() {
        return wxpayid;
    }

    public void setWxpayid(Long wxpayid) {
        this.wxpayid = wxpayid;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public Integer getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Integer couponFee) {
        this.couponFee = couponFee;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WxFeedbackPO other = (WxFeedbackPO) that;
        return (this.getWxpayid() == null ? other.getWxpayid() == null : this.getWxpayid().equals(other.getWxpayid()))
            && (this.getReturnCode() == null ? other.getReturnCode() == null : this.getReturnCode().equals(other.getReturnCode()))
            && (this.getReturnMsg() == null ? other.getReturnMsg() == null : this.getReturnMsg().equals(other.getReturnMsg()))
            && (this.getAppid() == null ? other.getAppid() == null : this.getAppid().equals(other.getAppid()))
            && (this.getMchId() == null ? other.getMchId() == null : this.getMchId().equals(other.getMchId()))
            && (this.getDeviceInfo() == null ? other.getDeviceInfo() == null : this.getDeviceInfo().equals(other.getDeviceInfo()))
            && (this.getNonceStr() == null ? other.getNonceStr() == null : this.getNonceStr().equals(other.getNonceStr()))
            && (this.getSign() == null ? other.getSign() == null : this.getSign().equals(other.getSign()))
            && (this.getResultCode() == null ? other.getResultCode() == null : this.getResultCode().equals(other.getResultCode()))
            && (this.getErrCode() == null ? other.getErrCode() == null : this.getErrCode().equals(other.getErrCode()))
            && (this.getErrCodeDes() == null ? other.getErrCodeDes() == null : this.getErrCodeDes().equals(other.getErrCodeDes()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getIsSubscribe() == null ? other.getIsSubscribe() == null : this.getIsSubscribe().equals(other.getIsSubscribe()))
            && (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
            && (this.getBankType() == null ? other.getBankType() == null : this.getBankType().equals(other.getBankType()))
            && (this.getTotalFee() == null ? other.getTotalFee() == null : this.getTotalFee().equals(other.getTotalFee()))
            && (this.getFeeType() == null ? other.getFeeType() == null : this.getFeeType().equals(other.getFeeType()))
            && (this.getCashFee() == null ? other.getCashFee() == null : this.getCashFee().equals(other.getCashFee()))
            && (this.getCashFeeType() == null ? other.getCashFeeType() == null : this.getCashFeeType().equals(other.getCashFeeType()))
            && (this.getCouponFee() == null ? other.getCouponFee() == null : this.getCouponFee().equals(other.getCouponFee()))
            && (this.getCouponCount() == null ? other.getCouponCount() == null : this.getCouponCount().equals(other.getCouponCount()))
            && (this.getTransactionId() == null ? other.getTransactionId() == null : this.getTransactionId().equals(other.getTransactionId()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getAttach() == null ? other.getAttach() == null : this.getAttach().equals(other.getAttach()))
            && (this.getTimeEnd() == null ? other.getTimeEnd() == null : this.getTimeEnd().equals(other.getTimeEnd()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWxpayid() == null) ? 0 : getWxpayid().hashCode());
        result = prime * result + ((getReturnCode() == null) ? 0 : getReturnCode().hashCode());
        result = prime * result + ((getReturnMsg() == null) ? 0 : getReturnMsg().hashCode());
        result = prime * result + ((getAppid() == null) ? 0 : getAppid().hashCode());
        result = prime * result + ((getMchId() == null) ? 0 : getMchId().hashCode());
        result = prime * result + ((getDeviceInfo() == null) ? 0 : getDeviceInfo().hashCode());
        result = prime * result + ((getNonceStr() == null) ? 0 : getNonceStr().hashCode());
        result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());
        result = prime * result + ((getResultCode() == null) ? 0 : getResultCode().hashCode());
        result = prime * result + ((getErrCode() == null) ? 0 : getErrCode().hashCode());
        result = prime * result + ((getErrCodeDes() == null) ? 0 : getErrCodeDes().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getIsSubscribe() == null) ? 0 : getIsSubscribe().hashCode());
        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
        result = prime * result + ((getBankType() == null) ? 0 : getBankType().hashCode());
        result = prime * result + ((getTotalFee() == null) ? 0 : getTotalFee().hashCode());
        result = prime * result + ((getFeeType() == null) ? 0 : getFeeType().hashCode());
        result = prime * result + ((getCashFee() == null) ? 0 : getCashFee().hashCode());
        result = prime * result + ((getCashFeeType() == null) ? 0 : getCashFeeType().hashCode());
        result = prime * result + ((getCouponFee() == null) ? 0 : getCouponFee().hashCode());
        result = prime * result + ((getCouponCount() == null) ? 0 : getCouponCount().hashCode());
        result = prime * result + ((getTransactionId() == null) ? 0 : getTransactionId().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getAttach() == null) ? 0 : getAttach().hashCode());
        result = prime * result + ((getTimeEnd() == null) ? 0 : getTimeEnd().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wxpayid=").append(wxpayid);
        sb.append(", returnCode=").append(returnCode);
        sb.append(", returnMsg=").append(returnMsg);
        sb.append(", appid=").append(appid);
        sb.append(", mchId=").append(mchId);
        sb.append(", deviceInfo=").append(deviceInfo);
        sb.append(", nonceStr=").append(nonceStr);
        sb.append(", sign=").append(sign);
        sb.append(", resultCode=").append(resultCode);
        sb.append(", errCode=").append(errCode);
        sb.append(", errCodeDes=").append(errCodeDes);
        sb.append(", openid=").append(openid);
        sb.append(", isSubscribe=").append(isSubscribe);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", bankType=").append(bankType);
        sb.append(", totalFee=").append(totalFee);
        sb.append(", feeType=").append(feeType);
        sb.append(", cashFee=").append(cashFee);
        sb.append(", cashFeeType=").append(cashFeeType);
        sb.append(", couponFee=").append(couponFee);
        sb.append(", couponCount=").append(couponCount);
        sb.append(", transactionId=").append(transactionId);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", attach=").append(attach);
        sb.append(", timeEnd=").append(timeEnd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
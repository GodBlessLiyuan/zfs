package com.zfs.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_ali_feedback
 * @author 
 */
public class AliFeedbackPO implements Serializable {
    private Date notifyTime;

    private String notifyType;

    private String notifyId;

    private String appId;

    private String version;

    private String signType;

    private String tradeNo;

    private String outTradeNo;

    private String outBizNo;

    private String buyerId;

    private String buyerLogonId;

    private String sellerId;

    private String sellerEmail;

    private String tradeStatus;

    private Float totalAmount;

    private Float receiptAmount;

    private Float invoiceAmount;

    private Float buyerPayAmount;

    private Float pointAmount;

    private Float refundFee;

    private String subject;

    private String body;

    private Date gmtCreate;

    private Date gmtPayment;

    private Date gmtRefund;

    private Date gmtClose;

    private String fundBillList;

    private static final long serialVersionUID = 1L;

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Float receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Float getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Float invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Float getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(Float buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public Float getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(Float pointAmount) {
        this.pointAmount = pointAmount;
    }

    public Float getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Float refundFee) {
        this.refundFee = refundFee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public Date getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(Date gmtClose) {
        this.gmtClose = gmtClose;
    }

    public String getFundBillList() {
        return fundBillList;
    }

    public void setFundBillList(String fundBillList) {
        this.fundBillList = fundBillList;
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
        AliFeedbackPO other = (AliFeedbackPO) that;
        return (this.getNotifyTime() == null ? other.getNotifyTime() == null : this.getNotifyTime().equals(other.getNotifyTime()))
            && (this.getNotifyType() == null ? other.getNotifyType() == null : this.getNotifyType().equals(other.getNotifyType()))
            && (this.getNotifyId() == null ? other.getNotifyId() == null : this.getNotifyId().equals(other.getNotifyId()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getSignType() == null ? other.getSignType() == null : this.getSignType().equals(other.getSignType()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getOutBizNo() == null ? other.getOutBizNo() == null : this.getOutBizNo().equals(other.getOutBizNo()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getBuyerLogonId() == null ? other.getBuyerLogonId() == null : this.getBuyerLogonId().equals(other.getBuyerLogonId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getSellerEmail() == null ? other.getSellerEmail() == null : this.getSellerEmail().equals(other.getSellerEmail()))
            && (this.getTradeStatus() == null ? other.getTradeStatus() == null : this.getTradeStatus().equals(other.getTradeStatus()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getReceiptAmount() == null ? other.getReceiptAmount() == null : this.getReceiptAmount().equals(other.getReceiptAmount()))
            && (this.getInvoiceAmount() == null ? other.getInvoiceAmount() == null : this.getInvoiceAmount().equals(other.getInvoiceAmount()))
            && (this.getBuyerPayAmount() == null ? other.getBuyerPayAmount() == null : this.getBuyerPayAmount().equals(other.getBuyerPayAmount()))
            && (this.getPointAmount() == null ? other.getPointAmount() == null : this.getPointAmount().equals(other.getPointAmount()))
            && (this.getRefundFee() == null ? other.getRefundFee() == null : this.getRefundFee().equals(other.getRefundFee()))
            && (this.getSubject() == null ? other.getSubject() == null : this.getSubject().equals(other.getSubject()))
            && (this.getBody() == null ? other.getBody() == null : this.getBody().equals(other.getBody()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtPayment() == null ? other.getGmtPayment() == null : this.getGmtPayment().equals(other.getGmtPayment()))
            && (this.getGmtRefund() == null ? other.getGmtRefund() == null : this.getGmtRefund().equals(other.getGmtRefund()))
            && (this.getGmtClose() == null ? other.getGmtClose() == null : this.getGmtClose().equals(other.getGmtClose()))
            && (this.getFundBillList() == null ? other.getFundBillList() == null : this.getFundBillList().equals(other.getFundBillList()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNotifyTime() == null) ? 0 : getNotifyTime().hashCode());
        result = prime * result + ((getNotifyType() == null) ? 0 : getNotifyType().hashCode());
        result = prime * result + ((getNotifyId() == null) ? 0 : getNotifyId().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getSignType() == null) ? 0 : getSignType().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getOutBizNo() == null) ? 0 : getOutBizNo().hashCode());
        result = prime * result + ((getBuyerId() == null) ? 0 : getBuyerId().hashCode());
        result = prime * result + ((getBuyerLogonId() == null) ? 0 : getBuyerLogonId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getSellerEmail() == null) ? 0 : getSellerEmail().hashCode());
        result = prime * result + ((getTradeStatus() == null) ? 0 : getTradeStatus().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getReceiptAmount() == null) ? 0 : getReceiptAmount().hashCode());
        result = prime * result + ((getInvoiceAmount() == null) ? 0 : getInvoiceAmount().hashCode());
        result = prime * result + ((getBuyerPayAmount() == null) ? 0 : getBuyerPayAmount().hashCode());
        result = prime * result + ((getPointAmount() == null) ? 0 : getPointAmount().hashCode());
        result = prime * result + ((getRefundFee() == null) ? 0 : getRefundFee().hashCode());
        result = prime * result + ((getSubject() == null) ? 0 : getSubject().hashCode());
        result = prime * result + ((getBody() == null) ? 0 : getBody().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtPayment() == null) ? 0 : getGmtPayment().hashCode());
        result = prime * result + ((getGmtRefund() == null) ? 0 : getGmtRefund().hashCode());
        result = prime * result + ((getGmtClose() == null) ? 0 : getGmtClose().hashCode());
        result = prime * result + ((getFundBillList() == null) ? 0 : getFundBillList().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", notifyTime=").append(notifyTime);
        sb.append(", notifyType=").append(notifyType);
        sb.append(", notifyId=").append(notifyId);
        sb.append(", appId=").append(appId);
        sb.append(", version=").append(version);
        sb.append(", signType=").append(signType);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", outBizNo=").append(outBizNo);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", buyerLogonId=").append(buyerLogonId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", sellerEmail=").append(sellerEmail);
        sb.append(", tradeStatus=").append(tradeStatus);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", receiptAmount=").append(receiptAmount);
        sb.append(", invoiceAmount=").append(invoiceAmount);
        sb.append(", buyerPayAmount=").append(buyerPayAmount);
        sb.append(", pointAmount=").append(pointAmount);
        sb.append(", refundFee=").append(refundFee);
        sb.append(", subject=").append(subject);
        sb.append(", body=").append(body);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtPayment=").append(gmtPayment);
        sb.append(", gmtRefund=").append(gmtRefund);
        sb.append(", gmtClose=").append(gmtClose);
        sb.append(", fundBillList=").append(fundBillList);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.zfs.common.pojo;

import java.io.Serializable;

/**
 * t_phone_info
 * @author 
 */
public class PhoneInfoPO implements Serializable {
    private Long mReId;

    private Long modelId;

    private String productModel;

    private String productDevice;

    private String buildid;

    private String productBoard;

    private String productName;

    private String manufacturer;

    private String productCuptsm;

    private String procVersion;

    private String boardPlatForm;

    private Integer lcdDensity;

    private String versdk;

    private String fingerprint;

    private String pinpai;

    private Long cpuProcessNum;

    private String xinghao;

    private String netHostName;

    private static final long serialVersionUID = 1L;

    public Long getmReId() {
        return mReId;
    }

    public void setmReId(Long mReId) {
        this.mReId = mReId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductDevice() {
        return productDevice;
    }

    public void setProductDevice(String productDevice) {
        this.productDevice = productDevice;
    }

    public String getBuildid() {
        return buildid;
    }

    public void setBuildid(String buildid) {
        this.buildid = buildid;
    }

    public String getProductBoard() {
        return productBoard;
    }

    public void setProductBoard(String productBoard) {
        this.productBoard = productBoard;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProductCuptsm() {
        return productCuptsm;
    }

    public void setProductCuptsm(String productCuptsm) {
        this.productCuptsm = productCuptsm;
    }

    public String getProcVersion() {
        return procVersion;
    }

    public void setProcVersion(String procVersion) {
        this.procVersion = procVersion;
    }

    public String getBoardPlatForm() {
        return boardPlatForm;
    }

    public void setBoardPlatForm(String boardPlatForm) {
        this.boardPlatForm = boardPlatForm;
    }

    public Integer getLcdDensity() {
        return lcdDensity;
    }

    public void setLcdDensity(Integer lcdDensity) {
        this.lcdDensity = lcdDensity;
    }

    public String getVersdk() {
        return versdk;
    }

    public void setVersdk(String versdk) {
        this.versdk = versdk;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getPinpai() {
        return pinpai;
    }

    public void setPinpai(String pinpai) {
        this.pinpai = pinpai;
    }

    public Long getCpuProcessNum() {
        return cpuProcessNum;
    }

    public void setCpuProcessNum(Long cpuProcessNum) {
        this.cpuProcessNum = cpuProcessNum;
    }

    public String getXinghao() {
        return xinghao;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao;
    }

    public String getNetHostName() {
        return netHostName;
    }

    public void setNetHostName(String netHostName) {
        this.netHostName = netHostName;
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
        PhoneInfoPO other = (PhoneInfoPO) that;
        return (this.getmReId() == null ? other.getmReId() == null : this.getmReId().equals(other.getmReId()))
            && (this.getModelId() == null ? other.getModelId() == null : this.getModelId().equals(other.getModelId()))
            && (this.getProductModel() == null ? other.getProductModel() == null : this.getProductModel().equals(other.getProductModel()))
            && (this.getProductDevice() == null ? other.getProductDevice() == null : this.getProductDevice().equals(other.getProductDevice()))
            && (this.getBuildid() == null ? other.getBuildid() == null : this.getBuildid().equals(other.getBuildid()))
            && (this.getProductBoard() == null ? other.getProductBoard() == null : this.getProductBoard().equals(other.getProductBoard()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getManufacturer() == null ? other.getManufacturer() == null : this.getManufacturer().equals(other.getManufacturer()))
            && (this.getProductCuptsm() == null ? other.getProductCuptsm() == null : this.getProductCuptsm().equals(other.getProductCuptsm()))
            && (this.getProcVersion() == null ? other.getProcVersion() == null : this.getProcVersion().equals(other.getProcVersion()))
            && (this.getBoardPlatForm() == null ? other.getBoardPlatForm() == null : this.getBoardPlatForm().equals(other.getBoardPlatForm()))
            && (this.getLcdDensity() == null ? other.getLcdDensity() == null : this.getLcdDensity().equals(other.getLcdDensity()))
            && (this.getVersdk() == null ? other.getVersdk() == null : this.getVersdk().equals(other.getVersdk()))
            && (this.getFingerprint() == null ? other.getFingerprint() == null : this.getFingerprint().equals(other.getFingerprint()))
            && (this.getPinpai() == null ? other.getPinpai() == null : this.getPinpai().equals(other.getPinpai()))
            && (this.getCpuProcessNum() == null ? other.getCpuProcessNum() == null : this.getCpuProcessNum().equals(other.getCpuProcessNum()))
            && (this.getXinghao() == null ? other.getXinghao() == null : this.getXinghao().equals(other.getXinghao()))
            && (this.getNetHostName() == null ? other.getNetHostName() == null : this.getNetHostName().equals(other.getNetHostName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getmReId() == null) ? 0 : getmReId().hashCode());
        result = prime * result + ((getModelId() == null) ? 0 : getModelId().hashCode());
        result = prime * result + ((getProductModel() == null) ? 0 : getProductModel().hashCode());
        result = prime * result + ((getProductDevice() == null) ? 0 : getProductDevice().hashCode());
        result = prime * result + ((getBuildid() == null) ? 0 : getBuildid().hashCode());
        result = prime * result + ((getProductBoard() == null) ? 0 : getProductBoard().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getManufacturer() == null) ? 0 : getManufacturer().hashCode());
        result = prime * result + ((getProductCuptsm() == null) ? 0 : getProductCuptsm().hashCode());
        result = prime * result + ((getProcVersion() == null) ? 0 : getProcVersion().hashCode());
        result = prime * result + ((getBoardPlatForm() == null) ? 0 : getBoardPlatForm().hashCode());
        result = prime * result + ((getLcdDensity() == null) ? 0 : getLcdDensity().hashCode());
        result = prime * result + ((getVersdk() == null) ? 0 : getVersdk().hashCode());
        result = prime * result + ((getFingerprint() == null) ? 0 : getFingerprint().hashCode());
        result = prime * result + ((getPinpai() == null) ? 0 : getPinpai().hashCode());
        result = prime * result + ((getCpuProcessNum() == null) ? 0 : getCpuProcessNum().hashCode());
        result = prime * result + ((getXinghao() == null) ? 0 : getXinghao().hashCode());
        result = prime * result + ((getNetHostName() == null) ? 0 : getNetHostName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mReId=").append(mReId);
        sb.append(", modelId=").append(modelId);
        sb.append(", productModel=").append(productModel);
        sb.append(", productDevice=").append(productDevice);
        sb.append(", buildid=").append(buildid);
        sb.append(", productBoard=").append(productBoard);
        sb.append(", productName=").append(productName);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", productCuptsm=").append(productCuptsm);
        sb.append(", procVersion=").append(procVersion);
        sb.append(", boardPlatForm=").append(boardPlatForm);
        sb.append(", lcdDensity=").append(lcdDensity);
        sb.append(", versdk=").append(versdk);
        sb.append(", fingerprint=").append(fingerprint);
        sb.append(", pinpai=").append(pinpai);
        sb.append(", cpuProcessNum=").append(cpuProcessNum);
        sb.append(", xinghao=").append(xinghao);
        sb.append(", netHostName=").append(netHostName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.rpa.server.vo;

import com.rpa.common.pojo.PhoneInfoPO;
import lombok.Data;

import java.io.Serializable;

/**
 * t_modal_res
 * @author 
 */
@Data
public class PhoneInfoVO implements Serializable {

    private String productModel;

    private String productDevice;

    private String buildid;

    private String productBoard;

    private String productName;

    private String manufacturer;

    private String productCuptsm;

    private String boardPlatForm;

    private Integer lcdDensity;

    private String versdk;

    private String fingerprint;

    private String pinpai;

    private Long cpuProcessNum;

    private String xinghao;

    private String netHostName;

    private String procVersion;


    public static PhoneInfoVO convert(PhoneInfoPO modalResPO){
        PhoneInfoVO modalResVO=new PhoneInfoVO();
        modalResVO.setProductModel(modalResPO.getProductModel());
        modalResVO.setProductDevice(modalResPO.getProductDevice());
        modalResVO.setBuildid(modalResPO.getBuildid());
        modalResVO.setProductBoard(modalResPO.getProductBoard());
        modalResVO.setProductName(modalResPO.getProductName());
        modalResVO.setManufacturer(modalResPO.getManufacturer());
        modalResVO.setProductCuptsm(modalResPO.getProductCuptsm());
        modalResVO.setProcVersion(modalResPO.getProcVersion());
        modalResVO.setBoardPlatForm(modalResPO.getBoardPlatForm());
        modalResVO.setLcdDensity(modalResPO.getLcdDensity());
        modalResVO.setVersdk(modalResPO.getVersdk());
        modalResVO.setFingerprint(modalResPO.getFingerprint());
        modalResVO.setPinpai(modalResPO.getPinpai());
        modalResVO.setCpuProcessNum(modalResPO.getCpuProcessNum());
        modalResVO.setXinghao(modalResPO.getXinghao());
        modalResVO.setNetHostName(modalResPO.getNetHostName());
        modalResVO.setProcVersion(modalResPO.getProcVersion());
        return modalResVO;
    }
}
package com.zfs.server.controller;

import com.zfs.common.dto.PhoneInfoVerifyDTO;
import com.zfs.common.dto.VerifyDTO;
import com.zfs.common.pojo.PhoneInfoPO;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.service.IPhoneModelService;
import com.zfs.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2020/1/9 14:47
 * @description: 手机型号
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class PhoneModelController {
    @Autowired
    private IPhoneModelService service;

    @PostMapping("phonemodel")
    public ResultVO checkPlugin(@RequestBody VerifyDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return ResultVO.varifyDevice();
        }

        return service.phoneModel();
    }

    /**
     * @author:ly
     * @data:20/4/9/10
     * */

    @PostMapping("addInfo")
    public ResultVO addInfoInfoPlugin(@RequestBody PhoneInfoPO modalResPO){
        if(modalResPO!=null&&modalResPO.getModelId()!=null&&modalResPO.getModelId()>0){
            service.addModalInfo(modalResPO);
            return new ResultVO(2001);
        }

        return new ResultVO(2000);
    }


    @PostMapping("phoneinfo")
    public ResultVO getInfoPlugin(@RequestBody PhoneInfoVerifyDTO dto){
        if (!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return  service.queryPhone(dto.getModelId());
    }
}

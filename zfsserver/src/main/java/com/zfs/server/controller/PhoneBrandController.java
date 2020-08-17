package com.zfs.server.controller;

import com.zfs.common.dto.VerifyDTO;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.service.IPhoneBrandService;
import com.zfs.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-17 16:05
 */
@RestController
@RequestMapping("v1.0")
public class PhoneBrandController {
    @Autowired
    private IPhoneBrandService phoneBrandService;
    @PostMapping("getPhoneInfo")
    public ResultVO getPhoneInfo(VerifyDTO verifyDTO){
        if (!VerifyUtil.checkDeviceId(verifyDTO)) {
            return ResultVO.varifyDevice();
        }
        return phoneBrandService.phoneBrand();
    }
}

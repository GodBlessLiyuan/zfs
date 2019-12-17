package com.rpa.server.service.impl;

import com.rpa.common.mapper.ExceptionMapper;
import com.rpa.common.pojo.ExceptionPO;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.ExceptionDTO;
import com.rpa.server.service.IExceptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 8:51
 * @description: 上传异常log
 * @version: 1.0
 */
@Service
public class ExceptionServiceImpl implements IExceptionService {
    @Resource
    private ExceptionMapper exceptionMapper;

    @Override
    public ResultVO insert(ExceptionDTO dto) {
        ExceptionPO po = new ExceptionPO();
        po.setDeviceId(dto.getId());
        po.setError(dto.getException());
        po.setBuildversion(dto.getOsv());
        po.setBuildrelease(dto.getOsre());
        po.setVersioncode(dto.getSoftv());
        po.setAndroidmodel(dto.getModel());
        po.setPkg(dto.getPkg());
        po.setCreateTime(new Date());
        exceptionMapper.insert(po);

        return new ResultVO(1000);
    }
}

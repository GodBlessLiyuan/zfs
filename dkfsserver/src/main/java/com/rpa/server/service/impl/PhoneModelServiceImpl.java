package com.rpa.server.service.impl;

import com.rpa.common.bo.PhoneModelBO;
import com.rpa.common.mapper.PhoneModelMapper;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.service.IPhoneModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2020/1/9 14:47
 * @description: 手机型号
 * @version: 1.0
 */
@Service
public class PhoneModelServiceImpl implements IPhoneModelService {

    @Resource
    private PhoneModelMapper phoneModelMapper;

    @Override
    public ResultVO phoneModel() {
        List<PhoneModelBO> bos = phoneModelMapper.queryAll();
        Map<String, List<String>> data = new LinkedHashMap<>();
        for (PhoneModelBO bo : bos) {
            if (data.containsKey(bo.getTypeName())) {
                List<String> list = data.get(bo.getTypeName());
                list.add(bo.getName());
            } else {
                List<String> list = new LinkedList<>();
                list.add(bo.getName());
                data.put(bo.getTypeName(), list);
            }
        }

        return new ResultVO<>(1000, data);
    }
}

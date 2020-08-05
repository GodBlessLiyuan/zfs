package com.rpa.server.service.impl;

import com.rpa.common.bo.PhoneModelBO;
import com.rpa.common.mapper.PhoneInfoMapper;
import com.rpa.common.mapper.PhoneModelMapper;
import com.rpa.common.pojo.PhoneInfoPO;
import com.rpa.common.pojo.PhoneModelPO;
import com.rpa.common.pojo.PhoneTypePO;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.PhoneModelDTO;
import com.rpa.server.service.IPhoneModelService;
import com.rpa.server.vo.PhoneInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
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
        Map<Integer, PhoneModelDTO> map = new LinkedHashMap<>();
        for(PhoneModelBO bo: bos) {
            if(map.containsKey(bo.getTypeId())) {
                PhoneModelDTO dto = map.get(bo.getTypeId());
                List<PhoneModelPO> pos = dto.getModels();
                PhoneModelPO po = new PhoneModelPO();
                po.setModelId(bo.getModelId());
                po.setName(bo.getName());
                pos.add(po);
            }else {
                PhoneModelDTO dto = new PhoneModelDTO();

                PhoneTypePO typePO = new PhoneTypePO();
                typePO.setName(bo.getTypeName());
                dto.setType(typePO);

                List<PhoneModelPO> pos = new LinkedList<>();
                PhoneModelPO po = new PhoneModelPO();
                po.setModelId(bo.getModelId());
                po.setName(bo.getName());
                pos.add(po);
                dto.setModels(pos);

                map.put(bo.getTypeId(), dto);
            }
        }

        return new ResultVO<>(1000, map.values());
    }
    @Autowired
    private PhoneInfoMapper phoneInfoMapper;
    @Override
    public ResultVO queryPhone(Long modelId) {
        PhoneInfoPO modalResPO=phoneInfoMapper.queryByModal(modelId);
        PhoneInfoVO modalResVO = PhoneInfoVO.convert(modalResPO);
        return new ResultVO(1000,modalResVO);
    }

    @Override
    public void addModalInfo(PhoneInfoPO phoneInfoPO) {
        phoneInfoMapper.insert(phoneInfoPO);
    }

}

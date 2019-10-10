package com.rpa.web.service.impl;

import com.rpa.web.dto.KeyValueDTO;
import com.rpa.web.mapper.KeyValueMapper;
import com.rpa.web.pojo.KeyValuePO;
import com.rpa.web.service.AccountTutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.rpa.web.common.Constant.TUTORIAL_URL;

/**
 * @author: dangyi
 * @date: Created in 16:38 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@Service
public class AccountTutorialServiceImpl implements AccountTutorialService {

    @Autowired
    private KeyValueMapper keyValueMapper;

    /**
     * 查询
     * @param tutorial_url
     * @return
     */
    @Override
    public KeyValueDTO query(int tutorial_url) {

        // 从t_key_value表中查出数据
        KeyValuePO keyValuePO = this.keyValueMapper.selectByPrimaryKey(tutorial_url);

        // 将查出的 KeyValuePO 数据转换为 KeyValueDTO
        KeyValueDTO keyValueDTO = new KeyValueDTO();
        keyValueDTO.setKeyName(keyValuePO.getKeyName());
        keyValueDTO.setValue(keyValuePO.getValue());

        return keyValueDTO;
    }

    /**
     * 插入或修改
     * @param url
     * @return
     */
    @Override
    public int insert(String url) {

        KeyValuePO po = new KeyValuePO();
        po.setKeyName(TUTORIAL_URL);
        po.setValue(url);

        // 从t_key_value表中查出数据
        KeyValuePO keyValuePO = this.keyValueMapper.selectByPrimaryKey(TUTORIAL_URL);

        if (keyValuePO == null) {
            return this.keyValueMapper.insert(po);
        }
        return this.keyValueMapper.updateByPrimaryKey(po);
    }


}

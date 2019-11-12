package com.rpa.web.service.impl;

import com.rpa.web.common.Constant;
import com.rpa.web.dto.KeyValueDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.feign.CacheFeignClient;
import com.rpa.web.mapper.KeyValueMapper;
import com.rpa.web.pojo.KeyValuePO;
import com.rpa.web.service.AccountTutorialService;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.rpa.web.common.Constant.REDIS_KEY;
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
    @Autowired
    private CacheFeignClient cacheFeignClient;

    /**
     * 查询
     *
     * @param tutorial_url
     * @return
     */
    @Override
    public ResultVO query(int tutorial_url) {

        // 从t_key_value表中查出数据
        KeyValuePO po = this.keyValueMapper.selectByPrimaryKey(tutorial_url);

        if (null == po) {
            return ResultVOUtil.success("");
        }

        // 将查出的 PO 数据转换为 DTO
        KeyValueDTO dto = new KeyValueDTO();
        dto.setKeyName(po.getKeyName());
        dto.setValue(po.getValue());

        return ResultVOUtil.success(dto);
    }

    /**
     * 插入或修改
     *
     * @param url
     * @return
     */
    @Override
    public ResultVO insert(String url) {

        // 先准备好养好教程数据对象
        KeyValuePO tutorial_po = new KeyValuePO();
        tutorial_po.setKeyName(Constant.TUTORIAL_URL);
        tutorial_po.setValue(url);

        // 尝试着从t_key_value表中查出养号教程数据：无则插入，有则修改
        KeyValuePO po = this.keyValueMapper.selectByPrimaryKey(TUTORIAL_URL);

        int count = 0;
        if (po == null) {
            count = this.keyValueMapper.insert(tutorial_po);
        } else {
            count = this.keyValueMapper.updateByPrimaryKey(tutorial_po);

            // 从t_key_value表中查出INDEX数据，值加1
            KeyValuePO index_po = this.keyValueMapper.selectByPrimaryKey(Constant.INDEX);
            index_po.setValue(String.valueOf(Integer.parseInt(index_po.getValue()) + 1));
            this.keyValueMapper.updateByPrimaryKey(index_po);
        }

        /**
         * @author: xiahui
         * @description: 清除基础信息Redis缓存
         */
        cacheFeignClient.delete(REDIS_KEY);

        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
    }


}

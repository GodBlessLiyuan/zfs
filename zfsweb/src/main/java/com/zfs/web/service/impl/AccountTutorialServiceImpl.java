package com.zfs.web.service.impl;

import com.zfs.common.constant.Constant;
import com.zfs.common.mapper.KeyValueMapper;
import com.zfs.common.pojo.KeyValuePO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.AccountTutorialService;
import com.zfs.web.vo.KeyValueVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.zfs.common.constant.Constant.TUTORIAL_URL;

/**
 * @author: dangyi
 * @date: Created in 16:38 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@Service
public class AccountTutorialServiceImpl implements AccountTutorialService {
    private final static Logger logger = LoggerFactory.getLogger(AccountTutorialServiceImpl.class);

    @Resource
    private KeyValueMapper keyValueMapper;

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
            return new ResultVO(1002);
        }

        // 将查出的 po 数据转换为 vo
        KeyValueVO vo = new KeyValueVO();
        vo.setKeyName(po.getKeyName());
        vo.setValue(po.getValue());

        return new ResultVO(1000, vo);
    }

    /**
     * 插入或修改
     *
     * @param url
     * @return
     */
    @Override
    public ResultVO insert(String url) {

        // 先准备好养号教程数据对象
        KeyValuePO tutorial_po = new KeyValuePO();
        tutorial_po.setKeyName(Constant.TUTORIAL_URL);
        tutorial_po.setValue(url);

        // 尝试着从t_key_value表中查出养号教程数据：无则插入，有则修改
        KeyValuePO po = this.keyValueMapper.selectByPrimaryKey(TUTORIAL_URL);

        int result1;
        if (po == null) {
            result1 = this.keyValueMapper.insert(tutorial_po);
        } else {
            result1 = this.keyValueMapper.updateByPrimaryKey(tutorial_po);

            // 从t_key_value表中查出INDEX数据，值加1
            KeyValuePO index_po = this.keyValueMapper.selectByPrimaryKey(Constant.INDEX);
            index_po.setValue(String.valueOf(Integer.parseInt(index_po.getValue()) + 1));
            int result2 = this.keyValueMapper.updateByPrimaryKey(index_po);
            if (result2 == 0) {
                LogUtil.log(logger, "insert", "养号教程中，index加1失败", index_po);
            }
        }

        if (result1 == 0) {
            LogUtil.log(logger, "insert", "插入或修改失败", tutorial_po);
        }


        return new ResultVO(1000);
    }


}

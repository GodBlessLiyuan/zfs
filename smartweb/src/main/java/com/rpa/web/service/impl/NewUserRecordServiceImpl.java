package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.NewUserRecordDTO;
import com.rpa.web.mapper.NewUserRecordMapper;
import com.rpa.web.pojo.NewUserRecordPO;
import com.rpa.web.service.INewUserRecordService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/3 17:59
 * @description: 新用户赠送记录
 * @version: 1.0
 */
@Service
public class NewUserRecordServiceImpl implements INewUserRecordService {

    @Resource
    private NewUserRecordMapper newUserRecordMapper;

    @Override
    public DTPageInfo<NewUserRecordDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<NewUserRecordPO> page = PageHelper.startPage(pageNum, pageSize);
        List<NewUserRecordPO> pos = newUserRecordMapper.query(reqData);

        return new DTPageInfo<>(draw, page.getTotal(), NewUserRecordDTO.convert(pos));
    }
}

package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.NewUserRecordBO;
import com.zfs.common.mapper.NewUserRecordMapper;
import com.zfs.web.common.PageHelper;
import com.zfs.web.vo.NewUserRecordVO;
import com.zfs.web.service.INewUserRecordService;
import com.zfs.web.utils.DTPageInfo;
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
    public DTPageInfo<NewUserRecordVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<NewUserRecordBO> page = PageHelper.startPage(pageNum, pageSize);
        List<NewUserRecordBO> pos = newUserRecordMapper.query(reqData);

        return new DTPageInfo<>(draw, page.getTotal(), NewUserRecordVO.convert(pos));
    }
}
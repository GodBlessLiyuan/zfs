package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.UserGiftsDTO;
import com.rpa.web.mapper.ComTypeMapper;
import com.rpa.web.mapper.UserGiftsMapper;
import com.rpa.web.pojo.ComTypePO;
import com.rpa.web.pojo.UserGiftsPO;
import com.rpa.web.service.IUserGiftsSercive;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/3 16:53
 * @description: TODO
 * @version: 1.0
 */
@Service
public class UserGiftsServiceImpl implements IUserGiftsSercive {

    @Resource
    private UserGiftsMapper userGiftsMapper;

    @Resource
    private ComTypeMapper comTypeMapper;

    @Override
    public DTPageInfo<UserGiftsDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<UserGiftsPO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserGiftsPO> pos = userGiftsMapper.query(reqData);

        return new DTPageInfo<>(draw, page.getTotal(), UserGiftsDTO.convert(pos));
    }

    @Override
    public ResultVO insert(int comTypeId, int aId) {
        ComTypePO comTypePO = comTypeMapper.selectByPrimaryKey(comTypeId);

        UserGiftsPO userGiftsPO = new UserGiftsPO();
        userGiftsPO.setComTypeId(comTypeId);
        userGiftsPO.setComTypeName(comTypePO.getName());
        userGiftsPO.setDays(comTypePO.getDays());

        userGiftsPO.setStatus((byte) 1);
        userGiftsPO.setCreateTime(new Date());
        userGiftsPO.setaId(aId);

        userGiftsMapper.insert(userGiftsPO);

        return ResultVOUtil.success();
    }

    @Override
    public int updateStatus(int nugId, byte status) {
        UserGiftsPO po = userGiftsMapper.selectByPrimaryKey(nugId);

        po.setStatus(status);

        return userGiftsMapper.updateByPrimaryKey(po);
    }

    @Override
    public int delete(int nugId) {
        return userGiftsMapper.deleteByPrimaryKey(nugId);
    }
}

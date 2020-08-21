package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.mapper.ServiceNumberMapper;
import com.zfs.common.pojo.ServiceNumberPO;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.dto.AdminUserDTO;
import com.zfs.web.service.ICustomerService;
import com.zfs.web.utils.OperatorUtil;
import com.zfs.web.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-06-15 10:49
 */
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ServiceNumberMapper serviceNumberMapper;
    @Override
    public ResultVO insert(HttpSession session, String kf, Byte type) {
        ServiceNumberPO numberPO=new ServiceNumberPO();
        numberPO.setKf(kf);
        numberPO.setCreateTime(new Date());
        numberPO.setUpdateTime(new Date());
        AdminUserDTO operatorPO = OperatorUtil.getOperatorPO(session);
        numberPO.setUsername(operatorPO.getUsername());
        numberPO.setaId((long)operatorPO.getaId());
        numberPO.setDr((byte) 1);
        numberPO.setType(type);
        serviceNumberMapper.insert(numberPO);
        return new ResultVO(1000);
    }

    @Override
    public ResultVO query(int pageNum, int pageSize, Map<String, Object> map) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<ServiceNumberPO> pos = serviceNumberMapper.query(map);
        return new ResultVO(1000, new PageInfoVO<>(page.getTotal(), CustomerVO.convert(pos)));
    }

    @Override
    public ResultVO delete(Long nID) {
        ServiceNumberPO serviceNumberPO = serviceNumberMapper.selectByPrimaryKey(nID);
        if(serviceNumberPO==null){
            return new ResultVO(3002);
        }
        serviceNumberPO.setDr((byte)2);
        serviceNumberPO.setUpdateTime(new Date());
        serviceNumberMapper.updateByPrimaryKey(serviceNumberPO);
        return new ResultVO(1000);
    }
}

package com.rpa.web.service.impl;


import com.rpa.common.bo.UserBO;
import com.rpa.common.mapper.RegisterUserMapper;
import com.rpa.common.mapper.UserMapper;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.service.IRegisterUserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @author: liyuan
 * @data 2020-06-08 08:44
 */
@Service
public class RegisterUserHistoryServiceImpl implements IRegisterUserHistoryService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RegisterUserMapper registerUserMapper;

    @Override
    public ResultVO<Integer> createUserHistory() {
        List<UserBO> tmpList=userMapper.selUserDeivce();
        List<String> destList=registerUserMapper.queryAllPhone();
        if(tmpList==null||tmpList.size()==0){
            return new ResultVO(1000,"没有查到关于用戶设备的历史数据");
        }
        if(destList==null){
            destList=new ArrayList<>();
        }
        Map<String,UserBO> origMap=new LinkedHashMap<>();
        for(int i=0;i<tmpList.size();i++){
            UserBO userBO = tmpList.get(i);
            String phone=userBO.getPhone();
            //目标（m_register_user）没有手机号且待写入的数据（origMap）中没有手机号
            if(!destList.contains(phone)&&!origMap.containsKey(phone)){
                origMap.put(phone,userBO);
            }
        }
        if(origMap.size()>0){
            registerUserMapper.batchInsert(origMap.values());
            return new ResultVO(1000,"用戶设备的历史数据已写入到注册用户表（m_register_user）");
        }
        return new ResultVO(1000,"没有用戶设备的历史数据须写入到注册用户表（m_register_user）");

    }


}

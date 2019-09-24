package com.rpa.web.mapper;

import com.rpa.web.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 10:00
 * @description: TODO
 * @version: 1.0
 */
@Mapper
public interface IUserMapper {

    /**
     * 分页查询
     * @return
     */
    List<User> list();
}

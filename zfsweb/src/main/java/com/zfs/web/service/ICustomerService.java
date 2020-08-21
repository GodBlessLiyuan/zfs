package com.zfs.web.service;


import com.zfs.common.vo.ResultVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ICustomerService {
    ResultVO insert(HttpSession session, String kf ,Byte type);

    ResultVO query(int pageNum, int pageSize, Map<String, Object> map);

    ResultVO delete(Long nID);
}

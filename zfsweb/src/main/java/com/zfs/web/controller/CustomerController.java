package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 在线客户服务控制器
 * @author: liyuan
 * @data 2020-06-15 10:46
 */
@RestController
@RequestMapping("online_service")
public class CustomerController {

    @Autowired
    private ICustomerService service;
    @PostMapping("insert")
    public ResultVO insert(
            @RequestParam(value = "kf1")String kf1,
            @RequestParam(value = "type")Byte type,
            HttpServletRequest request){
         return service.insert(request.getSession(),kf1,type);
    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param startDate
     * @param endDate
     * @param operator 操作人
     * @param number 账号
     * @return
     */
    @RequestMapping("query")
    public ResultVO query(
                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                @RequestParam(value = "startDate", required = false) String startDate,
                @RequestParam(value = "endDate", required = false) String endDate,
                @RequestParam(value = "operator", required = false) String operator,
                @RequestParam(value = "number",required = false)String number) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        if(!StringUtils.isEmpty(operator)){
            map.put("operator", operator);
        }
        if(!StringUtils.isEmpty(number)){
            map.put("number",number);
        }
        return service.query(pageNum, pageSize, map);
    }
    @PostMapping("delete")
    public ResultVO delete(@RequestParam("online_service") Long online_service){
        return service.delete(online_service);
    }
}

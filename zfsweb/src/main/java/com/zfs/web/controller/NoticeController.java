package com.zfs.web.controller;

import com.alibaba.fastjson.JSON;
import com.zfs.web.dto.NoticeDTO;
import com.zfs.web.service.NoticeService;
import com.zfs.common.vo.ResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 18:17 2019/9/26
 * @version: 1.0.0
 * @description: 通知管理
 */

@RequestMapping("notice")
@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 查询
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param status
     * @param type
     * @param title
     * @return
     */
    @PostMapping("query")
    public ResultVO query(
          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
          @RequestParam(value = "startTime", required = false) String startTime,
          @RequestParam(value = "endTime", required = false) String endTime,
          @RequestParam(value = "status", required = false) Integer status,
          @RequestParam(value = "type", required = false) Byte type,
          @RequestParam(value = "title", required = false) String title
    ) {
        // 调用业务层，返回页面结果
        return noticeService.query(pageNum, pageSize, startTime, endTime, status, type, title);
    }


    /**
     * 插入
     * @param type
     * @param text
     * @param picurl
     * @param title
     * @param url
     * @param menbers:1,2,3,是个数组
     * @param httpSession
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(@RequestBody NoticeDTO noticeDTO,
                           HttpSession httpSession) {
        if(noticeDTO.getNotificationperiod()==null||noticeDTO.getNotificationperiod().length!=2){
            return new ResultVO(1003);
        }
        if(noticeDTO.getEffectivetime()==null||noticeDTO.getEffectivetime().length!=2){
            return new ResultVO(1003);
        }
        if(noticeDTO.getMenbers()==null&&noticeDTO.getMenbers().length==0){
            return new ResultVO(1003);
        }
        String showTime=noticeDTO.getNotificationperiod()[0];
        String endShowTime=noticeDTO.getNotificationperiod()[1];
        String startTime=noticeDTO.getEffectivetime()[0];
        String endTime=noticeDTO.getEffectivetime()[1];
        String menber = JSON.toJSONString(noticeDTO.getMenbers());
        menber=menber.substring(1,menber.length()-1);
        return this.noticeService.insert(noticeDTO.getType(),noticeDTO.getText(),noticeDTO.getPicurl(),noticeDTO.getTitle(),
                noticeDTO.getUrl(),endShowTime, showTime, startTime, endTime,menber, httpSession);
    }


    /**
     * 更改状态
     * @param noticeId
     * @param status
     * @param httpSession
     * @return
     */
    @PostMapping("/update/status")
    public ResultVO updateStatus(@RequestParam(value = "noticeId")Integer noticeId,
                                 @RequestParam(value = "status")Integer status,
                                 HttpSession httpSession
                                 ) {
        return this.noticeService.updateStatus(noticeId, status, httpSession);

    }


    /**
     * 删除
     * @param noticeId
     * @return
     */
    @PostMapping("delete")
    public ResultVO delete(@RequestParam(value = "noticeId")Integer noticeId) {
        return this.noticeService.delete(noticeId);
    }
    /**
     * 查看详情
     * */
    @PostMapping("queryById")
    public ResultVO queryById(@RequestParam(value = "noticeId") Integer noticeId){
        return this.noticeService.queryById(noticeId);
    }
    @RequestMapping("upload")
    public ResultVO upload(HttpServletRequest request, @Param("file") MultipartFile file) {
        return noticeService.upload(request, file);
    }
}

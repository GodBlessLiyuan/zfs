package com.zfs.web.service;

import com.zfs.web.vo.NoticeVO;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 18:47 2019/9/26
 * @version: 1.0.0
 * @description: TODO
 */
public interface NoticeService {
    ResultVO query(int start, int length, String startTime, String endTime, Integer status, Byte type, String title);


    ResultVO delete(Integer noticeId);

    ResultVO updateStatus(Integer noticeId, Integer status, HttpSession httpSession);

    ResultVO insert(Byte type, String text, MultipartFile picurl, String title, String url,
                    String endShowTime,String showTime, String startTime, String endTime,
                    String menbers,HttpSession httpSession);

    ResultVO queryById(Integer noticeId);
}

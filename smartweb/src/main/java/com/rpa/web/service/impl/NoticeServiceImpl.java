package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.common.constant.Constant;
import com.rpa.web.common.PageHelper;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.web.dto.NoticeDTO;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.web.mapper.NoticeMapper;
import com.rpa.web.pojo.NoticePO;
import com.rpa.web.service.NoticeService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.FileUtil;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 18:48 2019/9/26
 * @version: 1.0.0
 * @description: 通知管理
 */

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private StringRedisTemplate template;

    @Value("${file.iconDir}")
    private String iconDir;

    @Value("${file.publicPath}")
    private String publicPath;

    /**
     * 查询
     *
     * @param draw
     * @param start
     * @param length
     * @param startTime
     * @param endTime
     * @param status
     * @param type
     * @param title
     * @return
     */
    @Override
    public DTPageInfo<NoticeDTO> query(int draw, int start, int length, String startTime, String endTime, Integer status, Byte type, String title) {

        // 分页
        Page<NoticeDTO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(5);
        map.put("startTime", startTime);
        map.put("endTime", strToDate3(endTime));
        map.put("status", status);
        map.put("type", type);
        map.put("title", title);

        // 按照条件查询数据
        List<NoticePO> lists_PO = noticeMapper.query(map);

        // 将查询到的 PO 数据转换为 DTO
        List<NoticeDTO> lists_DTO = new ArrayList<>();
        for (NoticePO po : lists_PO) {
            NoticeDTO dto = new NoticeDTO();
            dto.setNoticeId(po.getNoticeId());
            dto.setType(po.getType());
            dto.setTitle(po.getTitle());
            dto.setCreateTime(po.getCreateTime());
            dto.setShowTime(po.getShowTime());
            dto.setStartTime(po.getStartTime());
            dto.setEndTime(po.getEndTime());
            dto.setUrl(po.getUrl());
            dto.setText(po.getText());
            if (null == po.getPicurl()) {
                dto.setPicurl(po.getPicurl());
            } else {
                dto.setPicurl(publicPath + po.getPicurl());
            }
            dto.setStatus(po.getStatus());
            dto.setOperator(queryUsernameByAid(po.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 插入
     *
     * @param type
     * @param text
     * @param picurl
     * @param title
     * @param url
     * @param showTime
     * @param startTime
     * @param endTime
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO insert(Byte type, String text, MultipartFile picurl, String title, String url, String showTime,
                           String startTime, String endTime, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 把 noticeDTO 转换为 noticePO
        NoticePO po = new NoticePO();
        po.setType(type);
        po.setText(text);
        if (null == picurl) {
            po.setPicurl(null);
        } else {
            po.setPicurl(FileUtil.uploadFile(picurl, iconDir, "notice"));
        }
        po.setTitle(title);
        po.setUrl(url);
        po.setShowTime(strToTime(showTime));
        po.setStartTime(strToDate1(startTime));
        po.setEndTime(strToDate2(endTime));
        po.setUpdateTime(new Date());
        po.setaId(aId);
        po.setStatus(1);

        this.noticeMapper.insert(po);

        //删除Redis
        this.deleteRedis();

        return new ResultVO(1000);
    }


    /**
     * 修改状态
     *
     * @param noticeId
     * @param status
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO updateStatus(Integer noticeId, Integer status, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 查询出要修改的数据
        NoticePO po = this.noticeMapper.selectByPrimaryKey(noticeId);
        po.setStatus(status);
        po.setUpdateTime(new Date());
        po.setaId(aId);

        this.noticeMapper.updateByPrimaryKey(po);

        //删除Redis
        this.deleteRedis();

        return new ResultVO(1000);
    }


    /**
     * 删除
     *
     * @param noticeId
     * @return
     */
    @Override
    public ResultVO delete(Integer noticeId) {
        this.noticeMapper.deleteByPrimaryKey(noticeId);
        this.deleteRedis();
        return new ResultVO(1000);
    }


    /**
     * 根据aId，从t_admin_user表中查询username
     *
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.adminUserMapper.queryUsernameByAid(aId);
    }


    /**
     * 类型转换：将字符串类型的时间，转换为日期类型
     *
     * @param strDate
     * @return
     */
    private Date strToDate1(String strDate) {
        if (null == strDate || strDate.length() < 1) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }


    /**
     * 类型转换：将字符串类型的时间，转换为日期类型，并设置为当天最后一刻
     *
     * @param strDate
     * @return
     */
    private Date strToDate2(String strDate) {

        if (null == strDate || strDate.length() < 1) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        date = calendar.getTime();

        return date;
    }


    /**
     * 类型转换：将字符串类型的时间，转换为日期类型，设置为当天最后一刻后，再转回字符串
     *
     * @param strDate
     * @return
     */
    private String strToDate3(String strDate) {
        if (null == strDate || "".equals(strDate)) {
            return null;
        } else {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(this.strToDate2(strDate));
        }


    }


    /**
     * 类型转换：将字符串类型的时间，转换为时间类型
     *
     * @param strTime
     * @return
     */
    private Date strToTime(String strTime) {
        if (null == strTime || strTime.length() < 1) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("HH:mm");
        Date date = null;

        try {
            date = format.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }


    /**
     * 删除Redis
     */
    private void deleteRedis() {
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //Redis中的key
        String key = RedisKeyUtil.genNoticeRedisKey() + current_date;
        if (template.hasKey(key)) {
            template.delete(key);
        }
    }
}

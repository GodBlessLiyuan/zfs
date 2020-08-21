package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.mapper.NoticeMapper;
import com.zfs.common.utils.DateUtilCard;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.utils.DateUtil;
import com.zfs.web.utils.OperatorUtil;
import com.zfs.web.vo.NoticeVO;
import com.zfs.common.mapper.AdminUserMapper;
import com.zfs.common.pojo.NoticePO;
import com.zfs.web.service.NoticeService;
import com.zfs.common.vo.ResultVO;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
    private final static Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Resource
    private NoticeMapper noticeMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private StringRedisTemplate template;

    @Value("${file.iconDir}")
    private String iconDir;

    @Value("${file.publicPath}")
    private String publicPath;
    /**
     * 查询
     *
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
    public ResultVO query(int start, int length, String startTime, String endTime, Integer status, Byte type, String title) {

        // 分页
        Page<NoticeVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(5);
        map.put("startTime", startTime);
        map.put("endTime", DateUtil.str2str2(endTime));
        map.put("status", status);
        map.put("type", type);
        map.put("title", title);

        // 按照条件查询数据
        List<NoticePO> pos = noticeMapper.query(map);
        // 将查询到的 PO 数据转换为 VO
        List<NoticeVO> vos = new ArrayList<>();
        this.pos2vos(pos,vos);
        //根据分页查询的结果，封装最终的返回结果
        return new ResultVO(1000,new PageInfoVO<>(page.getTotal(), vos));
    }

    private void pos2vos(List<NoticePO> pos, List<NoticeVO> vos) {
        if(pos==null||pos.size()==0){
            return;
        }
        for (NoticePO po : pos) {
            NoticeVO vo = new NoticeVO();
            this.po2vo(po,vo);
            vos.add(vo);
        }
    }

    private void po2vo(NoticePO po, NoticeVO vo) {
        vo.setNoticeId(po.getNoticeId());
        vo.setType(po.getType());
        vo.setTitle(po.getTitle());
        vo.setCreateTime(po.getCreateTime());
        vo.setShowTime(DateUtilCard.date2Str(po.getShowTime(),DateUtilCard.HM));
        vo.setEndShowTime(DateUtilCard.date2Str(po.getEndShowTime(),DateUtilCard.HM));
        //通知时间段
        vo.setNoticeShowTime(vo.getShowTime()+"-"+vo.getEndShowTime());
        vo.setStartTime(DateUtilCard.date2Str(po.getStartTime(),DateUtilCard.YMD));
        vo.setEndTime(DateUtilCard.date2Str(po.getEndTime(),DateUtilCard.YMD));
        vo.setUrl(po.getUrl());
        vo.setText(po.getText());
        if (null == po.getPicurl()) {
            vo.setPicurl(po.getPicurl());
        } else {
            vo.setPicurl(publicPath + po.getPicurl());
        }
        vo.setStatus(po.getStatus());
        vo.setOperator(queryUsernameByAid(po.getaId()));
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
    public ResultVO insert(Byte type, String text, String picurl, String title, String url,
                           String showTime ,String endShowTime, String startTime, String endTime,String menbers, HttpSession httpSession) {

        NoticePO po = new NoticePO();
        po.setType(type);
        po.setText(text);
        if(!StringUtils.isEmpty(picurl)){
            po.setPicurl(picurl.split(publicPath)[1]);
        }
        po.setTitle(title);
        po.setUrl(url);
        po.setShowTime(DateUtil.str2time(showTime));
        po.setEndShowTime(DateUtil.str2time(endShowTime));
        po.setStartTime(DateUtil.str2date1(startTime));
        po.setEndTime(DateUtil.str2date2(endTime));
        po.setUpdateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));
        po.setStatus(1);
        //会员类别数组
        po.setMenbers(menbers);
        int result = this.noticeMapper.insert(po);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", po);
        }
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

        // 查询出要修改的数据
        NoticePO po = this.noticeMapper.selectByPrimaryKey(noticeId);
        po.setStatus(status);
        po.setUpdateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        int result = this.noticeMapper.updateByPrimaryKey(po);
        if (result == 0) {
            LogUtil.log(logger, "updateStatus", "更新状态失败", po);
        }

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
        int result = this.noticeMapper.deleteByPrimaryKey(noticeId);
        if (result == 0) {
            LogUtil.log(logger, "delete", "删除失败", noticeId);
        }
        this.deleteRedis();
        return new ResultVO(1000);
    }

    @Override
    public ResultVO queryById(Integer noticeId) {
        NoticePO po = this.noticeMapper.selectByPrimaryKey(noticeId);
        if(po==null){
            return new ResultVO(3010);
        }
        NoticeVO vo = new NoticeVO();
        this.po2vo(po,vo);
        if(!StringUtils.isEmpty(po.getMenbers())){
            vo.setMenbers(po.getMenbers().split(","));
        }
        return new ResultVO(1000,vo);
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
     * 删除Redis
     */
    private void deleteRedis() {
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //Redis中的key
        String key = RedisKeyUtil.genNoticeRedisKey(current_date);
        if (template.hasKey(key)) {
            template.delete(key);
        }
    }
}

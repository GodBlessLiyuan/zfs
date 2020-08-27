package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.*;
import com.zfs.common.mapper.*;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.common.UserVipConstant;
import com.zfs.web.utils.DateUtil;
import com.zfs.web.utils.ExcelUtil;
import com.zfs.web.vo.UserVipVO;
import com.zfs.web.vo.UserVipDetailsVO;
import com.zfs.web.service.IUserVipService;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 14:29
 * @description: 用户会员数据
 * @version: 1.0
 */
@Service
public class UserVipServiceImpl implements IUserVipService {

    @Resource
    private UserVipMapper userVipMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserActivityMapper userActivityMapper;

    @Resource
    private NewUserRecordMapper newUserRecordMapper;

    @Resource
    private BatchInfoMapper infoMapper;

    @Override
    public ResultVO query(int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<UserVipBO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserVipBO> dos = userVipMapper.query(reqData);
        return new ResultVO(1000, new PageInfoVO<>(page.getTotal(), UserVipVO.convert(dos)));
    }

    @Override
    public ResultVO queryDetails(long userId) {
        List<UserVipDetailsVO> detailsVOs = new ArrayList<>();
        // 购买
        List<OrderBO> orderBOs = orderMapper.queryByUserId(userId);
        for (OrderBO bo : orderBOs) {
            UserVipDetailsVO dto = new UserVipDetailsVO();
            dto.setVipType(UserVipConstant.USER_VIP_ORDER);
            dto.setUserChanName(bo.getUserChanName());
            if (bo.getCommAttr() == null || bo.getCommAttr() == 1) {
                dto.setCommAttr("独立商品");
            } else if (bo.getCommAttr() == 2) {
                dto.setCommAttr("通用商品");
            }
            dto.setSaleChanName(bo.getSaleChanName());
            dto.setType(bo.getType());
            dto.setCreateTime(bo.getCreateTime());
            dto.setComTypeName(bo.getComTypeName());
            dto.setDays(bo.getDays());
            detailsVOs.add(dto);
        }
        // 好评活动赠送
        List<UserActivityBO> userActivityBOs = userActivityMapper.queryByUserId(userId);
        for (UserActivityBO bo : userActivityBOs) {
            UserVipDetailsVO dto = new UserVipDetailsVO();
            dto.setVipType(UserVipConstant.USER_VIP_ACTIVITY);
            dto.setUserChanName(bo.getUserChanName());
            dto.setCommAttr("无");
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(bo.getCreateTime());
            dto.setComTypeName(bo.getComTypeName());
            dto.setDays(bo.getDays());
            detailsVOs.add(dto);
        }
        // 新用户赠送
        List<NewUserRecordBO> newUserRecordBOs = newUserRecordMapper.queryByUserId(userId);
        for (NewUserRecordBO bo : newUserRecordBOs) {
            UserVipDetailsVO dto = new UserVipDetailsVO();
            dto.setVipType(UserVipConstant.USER_VIP_GIFTS);
            dto.setUserChanName(bo.getUserChanName());
            dto.setCommAttr("无");
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(bo.getCreateTime());
            dto.setComTypeName(bo.getComTypeName());
            dto.setDays(bo.getDays());
            detailsVOs.add(dto);
        }

        // 卡密激活
        List<BatchInfoBO> batchInfoBOS = infoMapper.queryByUserId(userId);
        for (BatchInfoBO batchInfoBO : batchInfoBOS) {
            UserVipDetailsVO dto = new UserVipDetailsVO();
            dto.setVipType(UserVipConstant.USER_VIP_BATCH);
            dto.setUserChanName(batchInfoBO.getUserChanName());
            dto.setCommAttr("无");
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(batchInfoBO.getUpdateTime());
            dto.setComTypeName(batchInfoBO.getComTypeName());
            dto.setDays(batchInfoBO.getDays());
            detailsVOs.add(dto);
        }

        // 根据购买时间降序排序
        Collections.sort(detailsVOs);

        return new ResultVO(1000, detailsVOs);
    }

    @Override
    public ResultVO export(HttpServletResponse response) {
        List<UserVipBO> bos = userVipMapper.query(new HashMap<>());
        List<UserVipVO> vos = UserVipVO.convert(bos);
        HSSFWorkbook wb = this.toExcel(vos);
        ExcelUtil.sendToClient(wb, response);
        return new ResultVO(1000);
    }

    /**
     * 生成Excel表格
     *
     * @param vos
     */
    private HSSFWorkbook toExcel(List<UserVipVO> vos) {
        //表头
        String[] title = {"序号", "手机号", "注册时间", "首次购买时间", "是否付费用户", "会员到期时间", "最近付费时间"};
        //sheet表名
        String sheetname = "用户会员信息";

        String[][] content = new String[vos.size()][title.length];
        for (int i = 0; i < vos.size(); i++) {
            UserVipVO vo = vos.get(i);
            content[i][0] = String.valueOf(i + 1);
            content[i][1] = vo.getPhone();
            content[i][2] = DateUtil.date2str(vo.getCreateTime());
            content[i][3] = vo.getFirstTime() == null ? null : DateUtil.date2str(vo.getFirstTime());

            switch (vo.getIsPay()) {
                case 1:
                    content[i][4] = "是";
                    break;
                case 2:
                    content[i][4] = "否";
                    break;
            }
            content[i][5] = vo.getVendTime() == null ? null : DateUtil.date2str(vo.getEndTime());
            content[i][6] = vo.getLastTime() == null ? null : DateUtil.date2str(vo.getLastTime());
        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetname, title, content, null);
        return wb;
    }
}

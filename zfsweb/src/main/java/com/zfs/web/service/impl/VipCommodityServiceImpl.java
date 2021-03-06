package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.OrderBO;
import com.zfs.common.bo.VipcommodityBO;
import com.zfs.common.mapper.ComTypeMapper;
import com.zfs.common.mapper.SoftChannelMapper;
import com.zfs.common.mapper.VipcommodityMapper;
import com.zfs.common.pojo.ComTypePO;
import com.zfs.common.pojo.SoftChannelPO;
import com.zfs.common.pojo.VipcommodityPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.common.VipCommodityConstant;
import com.zfs.web.service.IVipCommodityService;
import com.zfs.web.utils.DateUtil;
import com.zfs.web.utils.ExcelUtil;
import com.zfs.web.vo.VipCommodityVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 12:03
 * @description: 商品列表
 * @version: 1.0
 */
@Service
public class VipCommodityServiceImpl implements IVipCommodityService {
    private final static Logger logger = LoggerFactory.getLogger(VipCommodityServiceImpl.class);

    @Resource
    private VipcommodityMapper vipcommodityMapper;
    @Resource
    private ComTypeMapper comTypeMapper;
    @Resource
    private SoftChannelMapper softChannelMapper;
    @Autowired
    private StringRedisTemplate template;

    @Override
    public ResultVO query(Integer pageNum, Integer pageSize, Map<String, Object> reqData) {

        Page<VipcommodityBO> page = PageHelper.startPage(pageNum, pageSize);
        List<VipcommodityBO> data = vipcommodityMapper.query(reqData);
        return new ResultVO(1000, new PageInfoVO<>(page.getTotal(), VipCommodityVO.convert(data)));
    }

    @Override
    public ResultVO queryById(Integer cmdyId) {
        return new ResultVO(1000, VipCommodityVO.convert(vipcommodityMapper.selectByPrimaryKey(cmdyId)));
    }

    @Override
    public ResultVO insert(int commAttr, int channelId, int comTypeId, String comName, String description, String price, String showDiscount,
                           float discount, int aId) {
        List<VipcommodityPO> pos = vipcommodityMapper.queryByChanIdAndComTypeId(channelId, comTypeId, commAttr);
        if (pos != null&&pos.size()>0) {
            // 当前渠道-产品已存在！
            return new ResultVO(3009);
        }

        VipcommodityPO vipCommodityPO = new VipcommodityPO();
        vipCommodityPO.setCommAttr((byte) commAttr);
        vipCommodityPO.setSoftChannelId(channelId);
        vipCommodityPO.setComTypeId(comTypeId);
        vipCommodityPO.setComName(comName);
        vipCommodityPO.setDescription(description);
        vipCommodityPO.setPrice(price);
        vipCommodityPO.setShowDiscount(showDiscount);
        vipCommodityPO.setDiscount((long) (100 * discount));
        vipCommodityPO.setaId(aId);

        // 查询产品信息数据
        ComTypePO comTypePO = comTypeMapper.selectByPrimaryKey(comTypeId);
        /**
         * 产品天数大于年会员规定天数则设置为年会员，否则设置为普通会员
         */
        int vipTypeId = comTypePO.getDays() >= VipCommodityConstant.YEAR_MEMBER_DAY ?
                VipCommodityConstant.YEAR_MEMBER_KEY : VipCommodityConstant.COMM_MEMBER_KEY;
        vipCommodityPO.setViptypeId(vipTypeId);
        vipCommodityPO.setComTypeName(comTypePO.getName());
        vipCommodityPO.setDays(comTypePO.getDays());

        // 查询渠道信息数据
        SoftChannelPO softChannelPO = softChannelMapper.selectByPrimaryKey(channelId);
        vipCommodityPO.setName(softChannelPO.getName());

        vipCommodityPO.setCreateTime(new Date());
        vipCommodityPO.setStatus((byte) 1);
        vipCommodityPO.setIstop((byte) 1);

        int result = vipcommodityMapper.insert(vipCommodityPO);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", vipCommodityPO);
            return ResultVO.serverInnerError();
        }

        this.deleteRedis();
        return new ResultVO(1000);
    }

    @Override
    public ResultVO update(Integer cmdyId, String comName, String description, String price, String showDiscount, Float discount) {
        VipcommodityPO vipCommodityPO = vipcommodityMapper.selectByPrimaryKey(cmdyId);
        vipCommodityPO.setComName(comName);
        //备注
        if(!StringUtils.isEmpty(description)){
            vipCommodityPO.setDescription(description);
        }
        //原价
        if(!StringUtils.isEmpty(price)){
            vipCommodityPO.setPrice(price);
        }
        //折扣价
        if(!StringUtils.isEmpty(showDiscount)){
            vipCommodityPO.setShowDiscount(showDiscount);
        }
        //售价
        BigDecimal bd = new BigDecimal(Float.toString(discount));
        vipCommodityPO.setDiscount(bd.multiply(new BigDecimal("100")).longValue());
        vipCommodityPO.setUpdateTime(new Date());
        int first = vipcommodityMapper.updateByPrimaryKey(vipCommodityPO);
        if (first == 0) {
            LogUtil.log(logger, "update", "更新失败", vipCommodityPO);
            return ResultVO.serverInnerError();
        }
        this.deleteRedis();
        return new ResultVO(1000);
    }

    @Override
    public ResultVO updateStatus(Integer cmdyId, Byte status) {
        VipcommodityPO vipCommodityPO = vipcommodityMapper.selectByPrimaryKey(cmdyId);
        vipCommodityPO.setStatus(status);
        int first = vipcommodityMapper.updateByPrimaryKey(vipCommodityPO);
        if (first == 0) {
            LogUtil.log(logger, "updateStatus", "更新失败", vipCommodityPO);
            return ResultVO.serverInnerError();
        }
        this.deleteRedis();
        return new ResultVO(1000);
    }

    @Override
    public ResultVO updateIsTop(Integer cmdyId, Byte isTop) {
        VipcommodityPO vipCommodityPO = vipcommodityMapper.selectByPrimaryKey(cmdyId);
        vipCommodityPO.setIstop(isTop);
        int first = vipcommodityMapper.updateByPrimaryKey(vipCommodityPO);
        if (first == 0) {
            LogUtil.log(logger, "updateIsTop", "更新失败", vipCommodityPO);
            return ResultVO.serverInnerError();
        }
        this.deleteRedis();
        return new ResultVO(1000);
    }

    /**
     * 删除对应的Redis
     */
    private void deleteRedis() {
        Set<String> redisKeys = template.keys(RedisKeyUtil.genVipCommodityRedisKey("*"));
        if (!CollectionUtils.isEmpty(redisKeys)) {
            template.delete(redisKeys);
        }
    }

    /**
     * 导出
     * @param response
     * @return
     */
    @Override
    public ResultVO export(HttpServletResponse response) {
        List<VipcommodityBO> bos = vipcommodityMapper.query(new HashMap<>());
        List<VipCommodityVO> vos = VipCommodityVO.convert(bos);
        HSSFWorkbook wb = this.toExcel(vos);
        ExcelUtil.sendToClient(wb, response);
        return new ResultVO(1000);
    }

    /**
     * 生成Excel表格
     *
     * @param vos
     */
    private HSSFWorkbook toExcel(List<VipCommodityVO> vos) {
        //表头
        String[] title = {"序号", "产品类型", "会员天数", "商品名称", "原价", "折扣", "售价", "是否上架", "是否置顶", "创建时间", "操作人"};
        //sheet表名
        String sheetname = "商品信息";

        String[][] content = new String[vos.size()][title.length];
        for (int i = 0; i < vos.size(); i++) {
            VipCommodityVO vo = vos.get(i);
            content[i][0] = String.valueOf(i + 1);
            content[i][1] = vo.getComTypeName();
            content[i][2] = String.valueOf(vo.getDays());
            content[i][3] = vo.getComName();
            content[i][4] = vo.getPrice();
            content[i][5] = vo.getShowDiscount();
            content[i][6] = String.valueOf(vo.getDiscount());
            switch (vo.getStatus()) {
                case 1:
                    content[i][7] = "未上架";
                    break;
                case 2:
                    content[i][7] = "已上架";
                    break;
            }

            switch (vo.getIstop()) {
                case 1:
                    content[i][8] = "未置顶";
                    break;
                case 2:
                    content[i][8] = "已置顶";
                    break;
            }
            content[i][9] = DateUtil.date2str(vo.getCreateTime());
            content[i][10] = vo.getUsername();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetname, title, content, null);
        return wb;
    }
}

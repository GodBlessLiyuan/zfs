package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.UserBO;
import com.zfs.common.mapper.DeviceMapper;
import com.zfs.common.pojo.DevicePO;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.utils.DateUtil;
import com.zfs.web.utils.ExcelUtil;
import com.zfs.web.vo.DeviceVO;
import com.zfs.web.service.IUnregisteredService;
import com.zfs.web.utils.DTPageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 11:46
 * @description: TODO
 * @version: 1.0
 */
@Service
public class UnregisteredServiceImpl implements IUnregisteredService {

    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public ResultVO query(Integer pageNum, Integer pageSize, Map<String, Object> reqData) {
        Page<DevicePO> page = PageHelper.startPage(pageNum, pageSize);
        List<DevicePO> pos = deviceMapper.query(reqData);
        return new ResultVO(1000, new PageInfoVO<>(page.getTotal(), DeviceVO.convert(pos)));
    }

    @Override
    public ResultVO export(HttpServletResponse response) {
        List<DevicePO> pos = deviceMapper.query(new HashMap<>());
        HSSFWorkbook wb = this.toExcel(pos);
        ExcelUtil.sendToClient(wb, response);
        return new ResultVO(1000);
    }

    /**
     * 生成Excel表格
     *
     * @param pos
     */
    private HSSFWorkbook toExcel(List<DevicePO> pos) {
        //表头
        String[] title = {"序号", "首次使用时间", "应用渠道", "应用版本", "系统版本", "手机厂商", "手机型号"};
        //sheet表名
        String sheetname = "未注册用户数据";

        String[][] content = new String[pos.size()][title.length];
        for (int i = 0; i < pos.size(); i++) {
            DevicePO po = pos.get(i);
            content[i][0] = String.valueOf(i + 1);
            content[i][1] = DateUtil.date2str(po.getCreateTime());
            content[i][2] = po.getChanName();
            content[i][3] = po.getVersionname();
            content[i][4] = po.getBuildrelease();
            content[i][5] = po.getManufacturer();
            content[i][6] = po.getAndroidmodel();

        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetname, title, content, null);
        return wb;
    }
}

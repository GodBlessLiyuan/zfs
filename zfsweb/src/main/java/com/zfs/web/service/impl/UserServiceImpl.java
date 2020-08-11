package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.UserBO;
import com.zfs.common.mapper.RegisterUserMapper;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.utils.DateUtil;
import com.zfs.web.utils.ExcelUtil;
import com.zfs.web.vo.BatchInfoVO;
import com.zfs.web.vo.UserVO;
import com.zfs.web.service.IUserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 10:03
 * @description: 用户信息
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements IUserService {


    @Resource
    private RegisterUserMapper registerUserMapper;

    @Override
    public ResultVO query(int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<UserBO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserBO> dos = registerUserMapper.query(reqData);
        return new ResultVO(1000, new PageInfoVO<>(page.getTotal(), UserVO.convert(dos)));
    }

    @Override
    public ResultVO export(HttpServletResponse response) {
        //查询数据
        List<UserBO> bos = registerUserMapper.query(new HashMap<>());

        //生成Excel表格
        HSSFWorkbook wb = this.toExcel(bos);
        ExcelUtil.sendToClient(wb, response);
        return new ResultVO(1000);
    }

    /**
     * 生成Excel表格
     *
     * @param bos
     */
    private HSSFWorkbook toExcel(List<UserBO> bos) {
        //表头
        String[] title = {"序号", "手机号", "注册时间", "应用渠道", "应用版本", "系统版本", "手机厂商", "手机型号"};
        //sheet表名
        String sheetname = "用户注册信息";

        String[][] content = new String[bos.size()][title.length];
        for (int i = 0; i < bos.size(); i++) {
            UserBO bo = bos.get(i);
            content[i][0] = String.valueOf(i + 1);
            content[i][1] = bo.getPhone();
            content[i][2] = DateUtil.date2str(bo.getCreateTime());
            content[i][3] = bo.getChanName();
            content[i][4] = bo.getVersionName();
            content[i][5] = bo.getBuildRelease();
            content[i][6] = bo.getManufacturer();
            content[i][7] = bo.getAndroidModel();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetname, title, content, null);
        return wb;
    }
}

package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.NewUserRecordBO;
import com.zfs.common.mapper.NewUserRecordMapper;
import com.zfs.common.pojo.FeedbackPO;
import com.zfs.common.utils.DateUtilCard;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.service.INewUserRecordService;
import com.zfs.web.utils.ExcelUtil;
import com.zfs.web.vo.FeedbackVO;
import com.zfs.web.vo.NewUserRecordVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/3 17:59
 * @description: 新用户赠送记录
 * @version: 1.0
 */
@Service
public class NewUserRecordServiceImpl implements INewUserRecordService {

    @Resource
    private NewUserRecordMapper newUserRecordMapper;

    @Override
    public ResultVO query(Integer pageNum, Integer pageSize, Map<String, Object> reqData) {
        Page<NewUserRecordBO> page = PageHelper.startPage(pageNum, pageSize);
        List<NewUserRecordBO> pos = newUserRecordMapper.query(reqData);
        return new ResultVO(1000, new PageInfoVO<>(page.getTotal(), NewUserRecordVO.convert(pos)));
    }

    @Override
    public ResultVO export(Map<String, Object> reqData, HttpServletResponse response) {
        List<NewUserRecordBO> pos = newUserRecordMapper.query(reqData);
        HSSFWorkbook wb = this.toExcel(pos);
        ExcelUtil.sendToClient(wb, response);
        return new ResultVO(1000);
    }

    private HSSFWorkbook toExcel(List<NewUserRecordBO> pos) {
        //表头
        String[] title = {"序号", "手机号", "赠送时间", "赠送产品类型", "赠送产品天数"};
        //sheet表名
        String sheetname = "新用户赠送记录";

        String[][] content = new String[pos.size()][title.length];
        for (int i = 0; i < pos.size(); i++) {
            NewUserRecordBO vo = pos.get(i);
            content[i][0] = String.valueOf(i + 1);
            content[i][1] = vo.getPhone();
            content[i][2] = DateUtilCard.date2Str(vo.getCreateTime(),DateUtilCard.YMD_HMS);
            content[i][3] = vo.getComTypeName();
            content[i][4] = vo.getDays()==null?"0":vo.getDays().toString();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetname, title, content, null);
        return wb;
    }
}
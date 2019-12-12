package com.rpa.web.service;

import com.rpa.web.vo.InviteDetailVO;
import com.rpa.web.vo.InviteUserVO;
import com.rpa.web.vo.RevenueUserVO;
import com.rpa.web.utils.DTPageInfo;

/**
 * @author: dangyi
 * @date: Created in 10:52 2019/10/12
 * @version: 1.0.0
 * @description:
 */
public interface RevenueUserService {
    DTPageInfo<RevenueUserVO> query(int draw, int start, int length, String phone, int orderby);

    DTPageInfo<InviteUserVO> queryInviteduser(int draw, int start, int length, Integer userId, String invitePhone);

    DTPageInfo<InviteDetailVO> queryInviteduserDetail(int draw, int start, int length, Integer userId, Integer viptypeId, String startTime, String endTime);
}

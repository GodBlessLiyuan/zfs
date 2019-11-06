package com.rpa.web.service;

import com.rpa.web.dto.InviteDetailDTO;
import com.rpa.web.dto.InviteUserDTO;
import com.rpa.web.dto.RevenueUserDTO;
import com.rpa.web.utils.DTPageInfo;

import java.util.Date;

/**
 * @author: dangyi
 * @date: Created in 10:52 2019/10/12
 * @version: 1.0.0
 * @description:
 */
public interface RevenueUserService {
    DTPageInfo<RevenueUserDTO> query(int draw, int start, int length, String phone, int orderby);

    DTPageInfo<InviteUserDTO> queryInviteduser(int draw, int start, int length, Integer userId, String invitePhone);

    DTPageInfo<InviteDetailDTO> queryInviteduserDetail(int draw, int start, int length, Integer userId, Integer viptypeId, Date startTime, Date endTime);
}

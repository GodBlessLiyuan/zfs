package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.bo.UserToBO;
import com.zfs.web.dto.BatchSycInfoDTO;

public interface IBatchInfoRestService {
    ResultVO activateSync(BatchSycInfoDTO dto);

    ResultVO keyActivateZnzj(BatchSycInfoDTO dto);

    ResultVO bugZJDouOrder(UserToBO dto);
}

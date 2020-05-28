package com.rpa.web.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.web.dto.BatchInfoDTO;
import com.rpa.web.dto.BatchSycInfoDTO;

public interface IBatchInfoRestService {
    ResultVO activateSync(BatchSycInfoDTO dto);

    ResultVO keyActivateZnzj(BatchSycInfoDTO dto);
}

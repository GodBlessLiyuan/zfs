package com.rpa.web.service;

import com.rpa.common.dto.BatchSycInfoDTO;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.dto.BatchInfoDTO;

public interface IBatchInfoRestService {
    ResultVO activateSync(BatchSycInfoDTO dto);

    ResultVO keyactivate2(BatchInfoDTO dto);
}

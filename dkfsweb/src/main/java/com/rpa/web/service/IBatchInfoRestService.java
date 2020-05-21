package com.rpa.web.service;

import com.rpa.common.dto.BatchSycInfoDTO;
import com.rpa.common.vo.ResultVO;

public interface IBatchInfoRestService {
    ResultVO activateSync(BatchSycInfoDTO dto);
}

package com.rpa.make.service;

import com.rpa.common.dto.MakeDTO;
import com.rpa.common.vo.ResultVO;

/**
 * @author: xiahui
 * @date: Created in 2020/1/11 17:06
 * @description: TODO
 * @version: 1.0
 */
public interface IMakeService {

    /**
     * 分身制作
     *
     * @param dto
     * @return
     */
    ResultVO make(MakeDTO dto);
}

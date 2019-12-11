package com.rpa.web.service;

import com.rpa.common.vo.ResultVO;

/**
 * @author: dangyi
 * @date: Created in 16:38 2019/10/10
 * @version: 1.0.0
 * @description:
 */
public interface AccountTutorialService {
    ResultVO insert(String url);

    ResultVO query(int tutorial_url);
}

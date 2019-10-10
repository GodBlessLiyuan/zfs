package com.rpa.web.service;

import com.rpa.web.dto.KeyValueDTO;

/**
 * @author: dangyi
 * @date: Created in 16:38 2019/10/10
 * @version: 1.0.0
 * @description:
 */
public interface AccountTutorialService {
    int insert(String url);

    KeyValueDTO query(int tutorial_url);
}

package com.zfs.front.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xiahui
 * @date: Created in 2020/5/13 9:39
 * @description: 运营需求
 * @version: 1.0
 */
public interface IOMService {
    String findMaxVersionAPP(HttpServletResponse res) throws IOException;
}

package com.rpa.common.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author: xiahui
 * @date: Created in 2019/12/30 9:02
 * @description: 修改应用分神信息
 * @version: 1.0
 */
public interface Clibrary extends Library {
    Clibrary INSTANTCE = (Clibrary) Native.synchronizedLibrary(
            // ali: /data/smarthelper/dkfsbin/dkfsserver/tools/modXml.so
            Native.loadLibrary(
                    "/data/project/dkfsbin/dkfsserver/tools/modXml.so"
                    , Clibrary.class
            )
    );

    int modifyname(char[] rongrongtemp, int naonaoSize, String axmlpath);
}

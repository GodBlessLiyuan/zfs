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
            Native.load(
                    Clibrary.class.getResource("/modXml.so").getPath()
                    , Clibrary.class
            )
    );

    int modifyname(byte[] rongrongtemp, int naonaoSize, String axmlpath);
}

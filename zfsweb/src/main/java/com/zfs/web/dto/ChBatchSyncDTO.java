package com.zfs.web.dto;

import lombok.Data;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-05-25 10:39
 */
@Data
public class ChBatchSyncDTO extends ChBatchDTO{
    private byte activeSync;
}

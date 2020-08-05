package com.zfs.web.utils;

import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 19:46
 * @description: 适配前端DataTables分页结构
 * @version: 1.0
 */
public class DTPageInfo<T> {
    private int draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data;

    public DTPageInfo() {
    }

    public DTPageInfo(int draw) {
        this.draw = draw;
    }

    public DTPageInfo(int draw, long recordsTotal) {
        this(draw);
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsTotal;
    }

    public DTPageInfo(int draw, long recordsTotal, List<T> data) {
        this(draw, recordsTotal);
        this.data = data;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

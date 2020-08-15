package com.zzl.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 分页结果封装对象
 * Created by 乍暖还寒 on 2020/8/13 18:47
 * Version 1.0
 */
public class PageResult implements Serializable {
    private Long total;//总记录数
    private List rows;//当前页结果
    public PageResult(Long total, List rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
}

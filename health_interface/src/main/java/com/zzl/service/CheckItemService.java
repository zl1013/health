package com.zzl.service;

import com.zzl.entity.PageResult;
import com.zzl.entity.QueryPageBean;
import com.zzl.entity.Result;
import com.zzl.pojo.CheckItem;

import java.util.List;

/**
 * Description: CheckItemService接口
 * Created by 乍暖还寒 on 2020/8/13 21:14
 * Version 1.0
 */
//服务接口
public interface CheckItemService {
    void add(CheckItem checkItem);
    PageResult findPage(QueryPageBean queryPageBean);
    void deleteCheckItemById(Integer id);
    void editCheckItemById(CheckItem checkItem);
    CheckItem findById(Integer id);
    List<CheckItem> findAll();
}

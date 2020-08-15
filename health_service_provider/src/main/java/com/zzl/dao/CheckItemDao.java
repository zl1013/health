package com.zzl.dao;

import com.github.pagehelper.Page;
import com.zzl.entity.PageResult;
import com.zzl.entity.QueryPageBean;
import com.zzl.entity.Result;
import com.zzl.pojo.CheckItem;

import java.util.List;

/**
 * Description: 持久层dao
 * Created by 乍暖还寒 on 2020/8/13 21:12
 * Version 1.0
 */
public interface CheckItemDao {
    void add(CheckItem checkItem);
    Page<CheckItem> selectByCondition(String queryString);
    void deleteCheckItemById(Integer id);
    long findCountByCheckItemId(Integer id);
    void editCheckItemById(CheckItem checkItem);
    CheckItem findById(Integer id);
    List<CheckItem> findAll();
}

package com.zzl.dao;

import com.github.pagehelper.Page;
import com.zzl.entity.PageResult;
import com.zzl.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/14 21:43
 * Version 1.0
 */
public interface CheckGroupDao {
    void add(CheckGroup checkGroup);
    void setCheckGroupAndCheckItem(Map map);
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    Page<CheckGroup> findByCondition(String queryString);
    CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    void edit(CheckGroup checkGroup);

    void deleteAssoication(Integer id);
}

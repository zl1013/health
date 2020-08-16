package com.zzl.service;

import com.zzl.entity.PageResult;
import com.zzl.pojo.CheckGroup;

import java.util.List;

/**
 * Description: CheckGroupService接口
 * Created by 乍暖还寒 on 2020/8/14 21:37
 * Version 1.0
 */
public interface CheckGroupService {
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    List<CheckGroup> findAll();
}

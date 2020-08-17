package com.zzl.dao;

import com.github.pagehelper.Page;
import com.zzl.entity.PageResult;
import com.zzl.pojo.CheckGroup;
import com.zzl.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/16 15:09
 * Version 1.0
 */
public interface SetmealDAO {
    void add(Setmeal setmeal);
    void setSetmealAndCheckGroup(Map map);
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    Page<Setmeal> findByCondition(String queryString);
    List<Setmeal> findAll();

    Setmeal findById(int id);
}

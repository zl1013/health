package com.zzl.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzl.entity.PageResult;
import com.zzl.pojo.Setmeal;

import java.util.List;


/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/16 15:08
 * Version 1.0
 */

public interface SetmealService {
    void add(Setmeal setmeal,Integer[] checkgroupIds);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    public List<Setmeal> findAll();

    Setmeal findById(int id);
}

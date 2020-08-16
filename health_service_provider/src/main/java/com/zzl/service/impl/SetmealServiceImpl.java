package com.zzl.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzl.constant.RedisConstant;
import com.zzl.dao.SetmealDAO;
import com.zzl.entity.PageResult;
import com.zzl.pojo.CheckGroup;
import com.zzl.pojo.Setmeal;
import com.zzl.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/16 15:09
 * Version 1.0
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDAO setmealDAO;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //新增检查套餐，操作t_setmeal表
        setmealDAO.add(setmeal);
        //设置检查套餐与检查组多对多关系，操作t_setmeal_checkgroup表
        Integer id = setmeal.getId();
        this.setSetmealAndCheckGroup(id,checkgroupIds);
        //将图片保存到Redis集合中
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
    }

    //分页查询
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {

        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setmealDAO.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    //设置检查套餐与检查组多对多关系，操作t_setmeal_checkgroup表
    public void setSetmealAndCheckGroup(Integer setmealId,Integer[] checkgroupIds){
        Map<String,Integer> map = new HashMap<>();
        if (checkgroupIds != null && checkgroupIds.length != 0){
            for (Integer checkgroupId : checkgroupIds) {
                map.put("setmeal_id",setmealId);
                map.put("checkgroup_id",checkgroupId);
                setmealDAO.setSetmealAndCheckGroup(map);
            }
        }
    }

}

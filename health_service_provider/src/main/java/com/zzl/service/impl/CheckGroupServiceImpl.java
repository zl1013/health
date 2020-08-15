package com.zzl.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzl.dao.CheckGroupDao;
import com.zzl.entity.PageResult;
import com.zzl.pojo.CheckGroup;
import com.zzl.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/14 21:44
 * Version 1.0
 */
@Service( interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    //新增检查组，同时需要让检查组关联检查项
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {

        //新增检查组，操作t_checkgroup表
        checkGroupDao.add(checkGroup);
        //设置检查组和检查项的多对多关系，操作t_checkgroup_checkitem表
        this.setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }

    //分页查询
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {

        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public CheckGroup findById(Integer id) {
        CheckGroup byId = checkGroupDao.findById(id);
        return byId;
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //修改检查表基本信息，操作检查组t_checkgroup表
        checkGroupDao.edit(checkGroup);
        //清理当前检查组关联的检查项，操作中间关系表t_checkgroup_checkitem
        checkGroupDao.deleteAssoication(checkGroup.getId());
        //重新建立当前检查组和检查项的关联关系
        this.setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);

    }

    //建立检查组和检查项多对多关系
    public void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds){
        Map<String,Integer> map = new HashMap<>();
        if (checkitemIds != null && checkitemIds.length != 0){
            for (Integer checkitemsId : checkitemIds) {
                map.put("checkGroupId",checkGroupId);
                map.put("checkitemsId",checkitemsId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}

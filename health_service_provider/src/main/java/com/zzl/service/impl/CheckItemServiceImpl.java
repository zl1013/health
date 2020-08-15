package com.zzl.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzl.dao.CheckItemDao;
import com.zzl.entity.PageResult;
import com.zzl.entity.QueryPageBean;
import com.zzl.entity.Result;
import com.zzl.pojo.CheckItem;
import com.zzl.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: CheckItemService实现类
 * Created by 乍暖还寒 on 2020/8/13 21:04
 * Version 1.0
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    //注入dao
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    //分页查询
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        Page<CheckItem> page = PageHelper.startPage(currentPage, pageSize);
        //select * from t_checkitem limit 0 , 10
        checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();

        return new PageResult(total,rows);
    }

    //根据id删除检查项
    @Override
    public void deleteCheckItemById(Integer id) {
        //判断当前检查项是否已经关联到检查组
        long count = checkItemDao.findCountByCheckItemId(id);
        if ( count > 0 ){
            //当前检查项已经被关联到检查组，不允许删除
            throw new RuntimeException();
        }
            //执行删除
        checkItemDao.deleteCheckItemById(id);
    }

    @Override
    public void editCheckItemById(CheckItem checkItem) {
        checkItemDao.editCheckItemById(checkItem);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}

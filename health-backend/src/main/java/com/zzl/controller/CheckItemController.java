package com.zzl.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zzl.constant.MessageConstant;
import com.zzl.entity.PageResult;
import com.zzl.entity.QueryPageBean;
import com.zzl.entity.Result;
import com.zzl.pojo.CheckItem;
import com.zzl.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description: 检查项管理
 * Created by 乍暖还寒 on 2020/8/13 20:49
 * Version 1.0
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    //新增检查项
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
            return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    //检查项分页查询
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult page = checkItemService.findPage(queryPageBean);
        return  page;
    }

    //删除检查项
    @DeleteMapping("/deleteCheckItemById")
    public Result deleteCheckItemById(Integer id){

        Result result = null;
        try {
            checkItemService.deleteCheckItemById(id);
            return  new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }
    //编辑检查项
    @PutMapping("/editCheckItemById")
    public Result editCheckItemById(@RequestBody CheckItem checkItem){

        Result result = null;
        try {
            checkItemService.editCheckItemById(checkItem);
            return  new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    //查询检查项
    @GetMapping("/findById")
    public Result findById(Integer id){
        CheckItem checkItem = null;
        try {
            checkItem = checkItemService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    //查询所有检查项
    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckItem> allCheckItem = null;
        try {
            allCheckItem = checkItemService.findAll();
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,allCheckItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }


}

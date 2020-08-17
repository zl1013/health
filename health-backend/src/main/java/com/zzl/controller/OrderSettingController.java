package com.zzl.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zzl.constant.MessageConstant;
import com.zzl.entity.Result;
import com.zzl.pojo.OrderSetting;
import com.zzl.service.OrderSettingService;
import com.zzl.utils.POIUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/16 23:59
 * Version 1.0
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    //文件上传，实现预约设置数据批量导入
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){
        try {
            List<String[]> readExcel = POIUtils.readExcel(excelFile);//使用POI解析表格数据
            List<OrderSetting> data = new ArrayList<>();
            for (String[] strings : readExcel) {
                data.add(new OrderSetting(new Date(strings[0]),Integer.parseInt(strings[1])));
            }
            //通过dubbo远程调用服务实现数据批量导入到数据库
            orderSettingService.add(data);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    //查询预约数据
    @PostMapping("/getOrderSettingByMonth")
    public Result query(@RequestParam("date")String date){//date格式 yyyy-mm
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            System.out.println(list);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    //预约设置
    @PostMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try{
            orderSettingService.editNumberByDate(orderSetting);
            //预约设置成功
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //预约设置失败
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }
}

package com.zzl.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzl.dao.OrderSettingDAO;
import com.zzl.pojo.OrderSetting;
import com.zzl.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/17 0:10
 * Version 1.0
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDAO orderSettingDAO;
    //批量导入预约设置
    @Override
    public void add(List<OrderSetting> list) {

        if (list != null && list.size() > 0){
            for (OrderSetting orderSetting : list) {
                //判断日期是否已经设置可预约人数
                long countByOrderDate = orderSettingDAO.findCountByOrderDate(orderSetting.getOrderDate());
                if (countByOrderDate > 0){
                    //已经设置可预约人数，执行更新操作
                    orderSettingDAO.editNumberByOrderDate(orderSetting);
                }else {
                    //没有设置预约人数，执行插入操作
                    orderSettingDAO.add(orderSetting);
                }
            }
        }
    }

    //根据月份查询预约设置数据   格式yyyy-mm
    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String start = date + "-1";
        String end = date + "-31";
        Map dateMap = new HashMap();
        dateMap.put("start",start);
        dateMap.put("end",end);
        List<OrderSetting> orderSettingByMonth = orderSettingDAO.getOrderSettingByMonth(dateMap);

        List<Map> resultList = new ArrayList<>();
        //date: 1, number: 120, reservations: 1
        if (orderSettingByMonth != null && orderSettingByMonth.size() >0){
            for (OrderSetting orderSetting : orderSettingByMonth) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(orderSetting.getOrderDate());
//                calendar.get(Calendar.DATE);
                Map<String,String> map = new HashMap<>();
                map.put("date",orderSetting.getOrderDate().getDate()+"");
                map.put("number",orderSetting.getNumber()+"");
                map.put("reservations",orderSetting.getReservations()+"");
                resultList.add(map);
            }
        }

        return resultList;
    }

    //根据日期修改可预约人数
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDAO.findCountByOrderDate(orderSetting.getOrderDate());
        if(count > 0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDAO.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDAO.add(orderSetting);
        }
    }
}

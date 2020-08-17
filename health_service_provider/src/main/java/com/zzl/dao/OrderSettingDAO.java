package com.zzl.dao;

import com.zzl.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/17 9:37
 * Version 1.0
 */
public interface OrderSettingDAO {
    void add(OrderSetting orderSetting);
    void editNumberByOrderDate(OrderSetting orderSetting);
    long findCountByOrderDate(Date orderDate);
    List<OrderSetting> getOrderSettingByMonth(Map map);
}

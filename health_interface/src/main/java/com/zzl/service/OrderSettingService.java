package com.zzl.service;

import com.zzl.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/17 0:05
 * Version 1.0
 */
public interface OrderSettingService {
    void add(List<OrderSetting> list);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}

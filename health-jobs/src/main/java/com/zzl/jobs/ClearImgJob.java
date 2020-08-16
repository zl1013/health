package com.zzl.jobs;

import com.zzl.constant.RedisConstant;
import com.zzl.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * Description: 自定义job，定时清理垃圾图片
 * Created by 乍暖还寒 on 2020/8/16 22:18
 * Version 1.0
 */
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;
    public void clearImg(){
        System.out.println("自定义任务执行");
        //根据Redis中保存的两个set集合进行差值计算，获得垃圾图片名称集合
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (set != null){
            for (String s : set) {
                //删除七牛云上的图片
                QiniuUtils.deleteFileFromQiniu(s);
                //从Redis集合中删除图片名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,s);

            }
        }
    }
}

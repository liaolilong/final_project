package com.wlaq.staservice.schedule;

import com.wlaq.staservice.entity.StatisticsDaily;
import com.wlaq.staservice.service.StatisticsDailyService;
import com.wlaq.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService staService;
    //每隔5秒钟执行一次这个方法
    @Scheduled(cron = "0/5 * * * * ?")
    public void task1() {
        System.out.println("*********++++++++++++*****task1执行了..");
    }
    //在每天凌晨一点，执行方法，把数据查询进行添加
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        //获取上一天的日期
       staService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}

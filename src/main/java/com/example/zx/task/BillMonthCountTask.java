package com.example.zx.task;

import com.example.zx.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Slf4j
@Component
public class BillMonthCountTask {

    @Value("${stat.month:}")
    private String statMonth;

    @Autowired
    private SysUserMapper sysUserMapper;

    // 每天凌晨 1 点执行
    @Scheduled(cron = "0 40 19 * * ?")
    public void rebuildDailyStats() {
        log.info("开始执行每日统计重算任务");
        // TODO: 调用你的统计重算服务

        LocalDate start;

        if (StringUtils.hasText(statMonth)) {
            start = LocalDate.parse(statMonth + "-01");
        } else {
            start = LocalDate.now().withDayOfMonth(1);
        }

        LocalDate end = start.plusMonths(1);
        sysUserMapper.insertMonthStat(start.toString(), end.toString(), "徐克彪");

        log.info("每日统计重算任务结束");
    }
}

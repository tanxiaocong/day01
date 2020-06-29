package com.vsc.website;

import com.vsc.website.service.AttachmentService;
import com.vsc.website.service.LoginTokensService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Configuration       //1.主要用于标记配置类，兼备Component的效果
@EnableScheduling    // 2.开启定时任务
@Transactional(isolation = Isolation.READ_COMMITTED)
public class StaticScheduleTask {
    private static Logger logger = LoggerFactory.getLogger(StaticScheduleTask.class);

//    @Resource
//    private UsersService usersService;
    @Resource
    private AttachmentService attachmentService;

    @Resource
    private LoginTokensService loginTokensService;


//    /**
//     * 定时任务--自动更新用户数据
//     * 时间：每天0时0分10秒执行
//     */
//    @Scheduled(cron = "10 0 0 * * ?")
//    public void configureTasks() {
//        logger.info("===============自动更新用户数据定时任务开始================");
//        usersService.updateUsers();
//        logger.info("===============自动更新用户数据定时任务结束================");
//    }

    /**
     * 定时任务--自动清除临时文件
     * 时间：每天15分15秒执行
     */
    @Scheduled(cron = "15 15 * * * ?")
    public void txClearTmpFiles() {
        logger.info("===============自动清除临时文件定时任务开始================");
        attachmentService.clearTmpFiles();
        logger.info("===============自动清除临时文件定时任务开始================");
//      logger.info("===============清楚过期token开始================");
//      loginTokensService.clearOverdueTokens();
//      logger.info("===============清楚过期token结束================");

    }
}

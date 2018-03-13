package com.redis.queue.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.redis.queue.domain.MessageEntity;
import com.redis.queue.service.ISendControlCenterService;

/**
 * @author yin.huang
 * @date 2018年3月13日 上午10:24:41
 */
public class TimingHandleMessageService {

  private ExecutorService           handleMessageExecutor = null;

  private int                       threadNum             = 5;

  private ScheduledExecutorService  scheduledExecutor     = null;

  private ISendControlCenterService sendControlCenterService;

  private int                       handleFreeInterval    = 5;

  public TimingHandleMessageService(int threadNum) {
    this.threadNum = threadNum;
    handleMessageExecutor = Executors.newFixedThreadPool(threadNum);
    scheduledExecutor = Executors.newScheduledThreadPool(threadNum);
  }

  public void start() {// 初始化通知线程池

    for (int index = 0; index < threadNum; index++) {
      startThread();
    }
  }

  private void startThread() {

    handleMessageExecutor.submit(new Runnable() {

      public void run() {
        dealWithMessage();
      }
    });
  }

  private void dealWithMessage() {
    while (true) {// 循环获取信息

      MessageEntity messageEntity = sendControlCenterService.takeMessage();
      if (messageEntity == null) {// 当前队列为空
        System.out.println("message is null");
        break;
      }

      try {
        // 处理信息
        System.out.println("deal message name=" + messageEntity.getName());

      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(">>> Process message happen an exception. messageEntity=" + messageEntity);
      }
    }

    // 队列为空，停止一段时间继续处理
    scheduledExecutor.schedule(new Runnable() {

      public void run() {
        startThread();
      }
    }, handleFreeInterval, TimeUnit.SECONDS);
  }

  public void stop() {
    handleMessageExecutor.shutdownNow();
    scheduledExecutor.shutdownNow();
  }

  public void setSendControlCenterService(ISendControlCenterService sendControlCenterService) {
    this.sendControlCenterService = sendControlCenterService;
  }

  public void setHandleFreeInterval(int handleFreeInterval) {
    this.handleFreeInterval = handleFreeInterval;
  }

}

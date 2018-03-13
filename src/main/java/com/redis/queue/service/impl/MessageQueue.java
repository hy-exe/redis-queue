package com.redis.queue.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

import com.redis.queue.domain.CacheKey;
import com.redis.queue.domain.MessageEntity;

/**
 * @author yin.huang
 * @date 2018年3月13日 上午10:04:12
 */
public class MessageQueue {

  private RedisTemplate<String, MessageEntity> messageRedis;

  private int                                  messageQueueTimeOut = 7200;

  public long poll(MessageEntity messageEntity) {// 左近

    try {
      Long index = messageRedis.boundListOps(CacheKey.getMessageQueueRedisKey()).leftPush(messageEntity);
      if (index == null) {
        System.out.println(">>> Push message info to queue is failed. messageEntity={}" + messageEntity);
        return -1;
      }

      messageRedis.boundListOps(CacheKey.getMessageQueueRedisKey()).expire(messageQueueTimeOut, TimeUnit.SECONDS);
      return index;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(">>> Poll message into queue is failed. messageEntity={}" + messageEntity);
    }

    return -1;
  }

  public MessageEntity take() {// 右出

    try {
      MessageEntity smsMrNotify = messageRedis.boundListOps(CacheKey.getMessageQueueRedisKey()).rightPop();
      if (smsMrNotify == null) {
        System.out.println(">>> Not find message info from queue.");
      }
      return smsMrNotify;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(">>> Get message from queue is failed.");
    }

    return null;
  }

  public void setMessageRedis(RedisTemplate<String, MessageEntity> messageRedis) {
    this.messageRedis = messageRedis;
  }

  public void setMessageQueueTimeOut(int messageQueueTimeOut) {
    this.messageQueueTimeOut = messageQueueTimeOut;
  }

}

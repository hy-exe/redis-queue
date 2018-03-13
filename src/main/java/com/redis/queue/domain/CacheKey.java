package com.redis.queue.domain;

/**
 * @author yin.huang
 * @date 2018年3月13日 上午10:16:20
 */
public class CacheKey {

  private static String MESSAGE_QUEUE_REDIS_KEY = "MESSAGE_QUEUE_REDIS_KEY";

  public static String getMessageQueueRedisKey() {
    return MESSAGE_QUEUE_REDIS_KEY;
  }

}

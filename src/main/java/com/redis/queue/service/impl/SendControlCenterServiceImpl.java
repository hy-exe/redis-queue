package com.redis.queue.service.impl;

import com.redis.queue.domain.MessageEntity;
import com.redis.queue.service.ISendControlCenterService;

/**
 * @author yin.huang
 * @date 2018年3月13日 上午10:01:04
 */
public class SendControlCenterServiceImpl implements ISendControlCenterService {

  private MessageQueue messageQueue;

  public MessageEntity takeMessage() {
    return messageQueue.take();
  }

  public void pollMessage(MessageEntity messageEntity) {
    long result = messageQueue.poll(messageEntity);
    if (result < 0) {
      // 数据放入失败
      System.out.println("poll message into redis is fail.message=" + messageEntity);
    }

  }

  public MessageQueue getMessageQueue() {
    return messageQueue;
  }

  public void setMessageQueue(MessageQueue messageQueue) {
    this.messageQueue = messageQueue;
  }

}

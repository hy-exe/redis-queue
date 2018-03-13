package com.redis.queue.service;

import com.redis.queue.domain.MessageEntity;

/**
 * @author yin.huang
 * @date 2018年3月13日 上午9:54:26
 */
public interface ISendControlCenterService {

  public MessageEntity takeMessage();

  public void pollMessage(MessageEntity messageEntity);

}

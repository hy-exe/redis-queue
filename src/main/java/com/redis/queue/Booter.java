package com.redis.queue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.redis.queue.domain.MessageEntity;
import com.redis.queue.service.ISendControlCenterService;

/**
 * @author yin.huang
 * @date 2018年3月13日 上午10:47:16
 */
public class Booter {

  @SuppressWarnings("resource")
  public static void main(String[] args) {
    String[] sources = new String[] { "spring/applicationContext-redis.xml", "spring/applicationContext-service.xml" };
    ApplicationContext ctx = new ClassPathXmlApplicationContext(sources);
    ISendControlCenterService sendControlCenterService = (ISendControlCenterService) ctx.getBean("sendControlCenterService");
    MessageEntity messageEntity = new MessageEntity();
    messageEntity.setName("test");
    sendControlCenterService.pollMessage(messageEntity);
    System.out.println("Server Started... ");
  }
}

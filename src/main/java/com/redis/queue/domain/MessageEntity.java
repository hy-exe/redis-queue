package com.redis.queue.domain;

/**
 * @author yin.huang
 * @date 2018年3月13日 上午9:59:10
 */
public class MessageEntity extends AbstractEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 2157591779050611392L;

  private int               sendResult;

  private String            name;

  public int getSendResult() {
    return sendResult;
  }

  public void setSendResult(int sendResult) {
    this.sendResult = sendResult;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}

package com.redis.queue.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author yin.huang
 * @date 2018年3月13日 上午9:56:56
 *
 */
public class AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 4678018003400893700L;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

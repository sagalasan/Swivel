package com.sagalasan.swivel.message.codec;

import com.sagalasan.swivel.message.Time;

/**
 * Created by christiaan on 6/19/16.
 */
public class TimeCodec extends CustomCodec<Time>
{
  public TimeCodec(Class<Time> timeClass)
  {
    super(timeClass);
  }
}

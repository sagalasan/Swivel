package com.sagalasan.swivel.message.codec;

import com.sagalasan.swivel.message.Ping;

/**
 * Created by christiaan on 6/19/16.
 */
public class PingCodec extends CustomCodec<Ping>
{
  public PingCodec(Class<Ping> classReference)
  {
    super(classReference);
  }
}

package com.sagalasan.swivel.message.codec;

import com.sagalasan.swivel.message.JsonObjectSerializable;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;

/**
 * Created by christiaan on 6/19/16.
 */
public class CustomCodec<T extends JsonObjectSerializable> implements MessageCodec<T, T>
{
  private Class<T> classReference;

  public CustomCodec(Class<T> classReference)
  {
    this.classReference = classReference;
  }

  @Override
  public void encodeToWire(Buffer buffer, T t)
  {
    String jsonString = t.toJson().encode();
    int length = jsonString.getBytes().length;

    buffer.appendInt(length);
    buffer.appendString(jsonString);
  }

  @Override
  public T decodeFromWire(int initPosition, Buffer buffer)
  {
    int position = initPosition;

    int length = buffer.getInt(position);
    position += Integer.BYTES;

    JsonObject jsonObject = new JsonObject(buffer.getString(position, position + length));

    T object;
    try
    {
      object = classReference.newInstance();
      object.decodeJson(jsonObject);
    }
    catch (IllegalAccessException | InstantiationException e)
    {
      throw new RuntimeException(e);
    }

    return object;
  }

  @Override
  public T transform(T t)
  {
    return t;
  }

  @Override
  public String name()
  {
    return this.getClass().getSimpleName();
  }

  @Override
  public byte systemCodecID()
  {
    return -1;
  }
}

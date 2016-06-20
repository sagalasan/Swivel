package com.sagalasan.swivel.message;

import io.vertx.core.json.JsonObject;

import java.io.Serializable;

/**
 * Created by christiaan on 6/19/16.
 */
public class Time implements JsonObjectSerializable
{
  private static final String TIME = "time";
  private long time;

  public long getTime()
  {
    return time;
  }

  public Time setTime(long time)
  {
    this.time = time;
    return this;
  }

  @Override
  public JsonObject toJson()
  {
    return new JsonObject().put(TIME, time);
  }

  @Override
  public void decodeJson(JsonObject jsonObject)
  {
    this.time = jsonObject.getLong(TIME);
  }
}

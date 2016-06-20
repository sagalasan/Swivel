package com.sagalasan.swivel.message;

import io.vertx.core.json.JsonObject;

/**
 * Created by christiaan on 6/19/16.
 */
public class Ping implements JsonObjectSerializable
{
  @Override
  public JsonObject toJson()
  {
    return new JsonObject();
  }

  @Override
  public void decodeJson(JsonObject jsonObject)
  {
  }

  @Override
  public String toString()
  {
    return toJson().encodePrettily();
  }
}

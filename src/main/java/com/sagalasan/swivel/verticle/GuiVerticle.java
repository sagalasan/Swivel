package com.sagalasan.swivel.verticle;

import io.vertx.core.AbstractVerticle;

/**
 * Created by christi3 on 6/17/2016.
 */
public class GuiVerticle extends AbstractVerticle
{
  @Override
  public void start()
  {
    vertx.setTimer(2000, (handler) ->
    {
      System.out.println("Gui verticle");
    });
  }
}

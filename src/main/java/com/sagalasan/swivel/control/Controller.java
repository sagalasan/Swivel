/* Copyright 2016 Christiaan Martinez
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at

* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.sagalasan.swivel.control;

import com.google.inject.Inject;
import com.sagalasan.swivel.message.Ping;
import com.sagalasan.swivel.message.Time;
import com.sagalasan.swivel.message.codec.PingCodec;
import com.sagalasan.swivel.message.codec.TimeCodec;
import com.sagalasan.swivel.service.CurrentTimeService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;

/**
 * @author Christiaan Martinez
 */
public abstract class Controller extends AbstractVerticle
{
  private Vertx vertx;
  private String deploymentID;

  @Inject
  public void setVertx(Vertx vertx)
  {
    this.vertx = vertx;
    vertx.deployVerticle(this, res ->
    {
      deploymentID = res.result();
      System.out.println(this.getClass().getSimpleName() + " deployed");
      vertx.eventBus().send(CurrentTimeService.RECEIVE, new Ping(), reply ->
      {
        if(reply.succeeded())
        {
          System.out.println("Reply successful");
          Time time = (Time) reply.result().body();
          onCurrentTimeReceived(time.getTime());
        }
      });
      onDeploy();
    });
  }

  @Override
  public void start(Future<Void> future)
  {
    vertx.eventBus().consumer(CurrentTimeService.class.getName(), this::handleTimeReceived);
    future.complete();
  }

  public void handleTimeReceived(Message message)
  {
    Time time = (Time) message.body();
    onCurrentTimeReceived(time.getTime());
  }

  public void onCurrentTimeReceived(long time) { }

  public void undeploy()
  {
    vertx.undeploy(deploymentID);
  }

  public abstract void onDeploy();
}

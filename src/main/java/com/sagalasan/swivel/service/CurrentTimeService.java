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
package com.sagalasan.swivel.service;

import com.sagalasan.swivel.message.Time;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;

/**
 * @author Christiaan Martinez
 */
public class CurrentTimeService extends MicroVerticle
{
  public static final String RECEIVE = CurrentTimeService.class.getName() + "broadcast";
  private long timerId;

  @Override
  public void start(Future<Void> future)
  {
    timerId = vertx.setPeriodic(1000, this::sendTime);
    vertx.eventBus().consumer(RECEIVE, this::sendTime);
    complete(future);
  }

  @Override
  public void stop()
  {
    vertx.cancelTimer(timerId);
  }

  private void sendTime(Message message)
  {
    message.reply(new Time().setTime(System.currentTimeMillis()));
  }

  private void sendTime(Long event)
  {
    publishTime();
  }

  private void publishTime()
  {
    vertx.eventBus().publish(this.getClass().getName(), new Time().setTime(System.currentTimeMillis()));
  }
}

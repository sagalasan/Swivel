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

package com.sagalasan.swivel.verticle;

import com.sagalasan.swivel.control.Controller;
import com.sagalasan.swivel.service.CurrentTimeService;
import io.vertx.core.AbstractVerticle;

/**
 * @author Christiaan Martinez
 */
public class GuiVerticle extends AbstractVerticle
{
  private Controller controller;

  @Override
  public void start()
  {
    vertx.setTimer(2000, (handler) ->
    {
      System.out.println("Gui verticle");
    });

    vertx.eventBus().consumer(CurrentTimeService.class.getName(), handler ->
    {

    });
  }
}

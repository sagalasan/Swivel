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

import io.vertx.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christiaan Martinez
 */
public class ServiceVerticle extends AbstractVerticle
{
  private List<MicroVerticle> verticles = new ArrayList<>();

  private CurrentTimeService currentTimeService = new CurrentTimeService();

  @Override
  public void start(Future<Void> future)
  {
    verticles.add(currentTimeService);

    vertx.deployVerticle(currentTimeService, handler -> handleDeploy(future));
  }

  public void handleDeploy(Future<Void> future)
  {
    if(isDeployed()) future.complete();
  }

  public boolean isDeployed()
  {
    for(MicroVerticle verticle : verticles)
    {
      if(!verticle.isDeployed()) return false;
    }
    return true;
  }
}

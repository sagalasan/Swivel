/*
* Copyright 2016 Christiaan Martinez
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
package com.sagalasan.swivel;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sagalasan.swivel.injection.SwivelModule;
import com.sagalasan.swivel.verticle.GuiVerticle;
import io.vertx.core.Vertx;
import javafx.stage.Stage;

/**
 * @author Christiaan Martinez
 */
public class AppController
{
  private Stage stage;
  private Injector injector;

  private Vertx vertx;
  private GuiVerticle guiVerticle;


  public AppController()
  {
    injector = Guice.createInjector(new SwivelModule());
    vertx = Vertx.vertx();
    guiVerticle = new GuiVerticle();
    vertx.deployVerticle(guiVerticle, (res) ->
    {
      System.out.println("guiVerticle deployed");
    });
  }

  public void start(Stage stage) throws Exception
  {
    this.stage = stage;
  }

}

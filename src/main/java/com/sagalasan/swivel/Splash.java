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

package com.sagalasan.swivel;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Christiaan Martinez
 */
public class Splash extends Preloader
{
  private Stage stage;

  private Scene createPreloaderScene()
  {
    BorderPane p = new BorderPane();
    p.setCenter(new Label("Splash"));
    return new Scene(p, 300, 150);
  }

  public void start(Stage stage) throws Exception
  {
    this.stage = stage;
    stage.setScene(createPreloaderScene());
    stage.show();
  }

  @Override
  public void handleProgressNotification(ProgressNotification pn)
  {
  }

  @Override
  public void handleStateChangeNotification(StateChangeNotification evt)
  {
    if (evt.getType() == StateChangeNotification.Type.BEFORE_START)
    {
      stage.hide();
    }
  }
}

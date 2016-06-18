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

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.text.SimpleDateFormat;

/**
 * @author Christiaan Martinez
 */
public class MainController extends Controller
{
  @FXML
  private Label clockLabel;

  @FXML
  private StackPane contentHolder;

  public void setContentHolder(Parent parent)
  {
    contentHolder.getChildren().setAll(parent);
  }

  @Override
  public void onCurrentTimeReceived(long time)
  {
    Platform.runLater(() -> clockLabel.setText(new SimpleDateFormat("HH:mm").format(time)));
  }
}

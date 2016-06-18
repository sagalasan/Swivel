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
import com.sagalasan.swivel.control.Controller;
import com.sagalasan.swivel.control.MainController;
import com.sagalasan.swivel.injection.SwivelModule;
import com.sagalasan.swivel.service.ServiceVerticle;
import com.sagalasan.swivel.view.FxmlManager;
import io.vertx.core.Vertx;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Christiaan Martinez
 */
public class AppController
{
  private Stage stage;
  private Injector injector;

  private Vertx vertx;

  private ServiceVerticle serviceVerticle;

  private Scene currentScene;
  private Controller currentController;
  private MainController mainController;

  public AppController()
  {
    injector = Guice.createInjector(new SwivelModule());
    vertx = injector.getInstance(Vertx.class);
  }

  public void start(Stage stage) throws Exception
  {
    this.stage = stage;
    stage.setOnCloseRequest(e -> close());
    serviceVerticle = new ServiceVerticle();

    onPreStart();

    vertx.deployVerticle(serviceVerticle, res ->
    {
      System.out.println("serviceVerticle deployed");
      onStart();
    });
  }

  private void switchScene(Scene scene)
  {
    currentScene = scene;
    stage.setScene(scene);
    stage.show();
  }

  private void onPreStart()
  {
    Platform.runLater(() ->
    {
      BorderPane p = new BorderPane();
      p.setCenter(new Label("Splash"));
      Scene scene = new Scene(p, 800, 600);
      switchScene(scene);
    });
  }

  private void onStart()
  {
    Platform.runLater(() ->
    {
      stage.setTitle("Swivel");
      FXMLLoader loader = new FXMLLoader();
      Parent parent = FxmlManager.loadFxml(loader, FxmlManager.MAIN_FXML);
      mainController = loader.getController();
      injector.injectMembers(mainController);

      Scene scene = new Scene(parent, 800, 600);
      switchScene(scene);
      showTimeScene();
    });
  }

  private void showScene(String path)
  {
    FXMLLoader loader = new FXMLLoader();
    Parent node = FxmlManager.loadFxml(loader, path);
    Controller controller = loader.getController();
    injector.injectMembers(controller);
    if(currentController != null) ;
    mainController.setContentHolder(node);
    currentController = controller;
  }

  private void close()
  {
    vertx.close(handler ->
    {
      Platform.exit();
      System.exit(0);
    });
  }

  private void showTimeScene()
  {
    showScene(FxmlManager.TIME_SCENE_FXML);
  }
}

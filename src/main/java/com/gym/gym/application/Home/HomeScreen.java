package com.gym.gym.application.Home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class HomeScreen extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
        Parent root = (Parent) loader.load();
        HomeController homeController = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("GYM");
        stage.setResizable(true);
        stage.setMinHeight(screenBounds.getHeight());
        stage.setMinWidth(screenBounds.getWidth());
        stage.setMaximized(true);
        homeController.main();
        stage.show();
    }



}

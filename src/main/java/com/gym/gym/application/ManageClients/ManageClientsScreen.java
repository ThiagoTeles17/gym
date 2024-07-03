package com.gym.gym.application.ManageClients;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.MouseEvent;
import java.beans.EventHandler;

public class ManageClientsScreen extends Application {

    private static Stage stage;

    @FXML
    Button avaliationBtn;
    private ManageClientsController controller;


    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageClients.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        ManageClientsScreen.stage = stage;

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Alunos");
        stage.setResizable(false);
        controller.main();
        stage.show();

    }


    public Stage getStage(){
        return ManageClientsScreen.stage;
    }



    public ManageClientsController getController(){
        return this.controller;
    }
}

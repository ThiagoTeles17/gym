package com.gym.gym.application.ManageClients;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ManageClientsScreen extends Application {


    private static Stage stage;



    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageClients.fxml"));
        Parent root = loader.load();
        ManageClientsController controller = loader.getController();
        ManageClientsScreen.stage = stage;

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Alunos");
        stage.setResizable(false);
        controller.main();
        stage.show();

    }
}

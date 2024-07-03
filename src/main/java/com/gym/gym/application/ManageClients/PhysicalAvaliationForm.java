package com.gym.gym.application.ManageClients;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PhysicalAvaliationForm extends Application {

    FXMLLoader loader;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        loader = new FXMLLoader(getClass().getResource("PhysicalAvaliationForm.fxml"));
        Parent root = loader.load();
        PhysicalAvaliationFormController controller = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Avaliação Fisica");
        stage.setResizable(false);
        stage.show();

    }

    public PhysicalAvaliationFormController getController(){
        return loader.getController();
    }


}

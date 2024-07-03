package com.gym.gym.application.ManagePlans;

import com.gym.gym.application.ManageClients.ManageClientsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManagePlansScreen extends Application {


    private static Stage stage;
    private ManagePlansController controller;


    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagePlans.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        ManagePlansScreen.stage = stage;

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Planos");
        stage.setResizable(false);
        controller.main();
        stage.show();

    }

    public ManagePlansController getController(){
        return this.controller;
    }
}

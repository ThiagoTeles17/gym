package com.gym.gym.application.Create;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientScreen extends Application {

    public static Stage stage;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        ClientScreen.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Client.fxml"));
        Parent root = loader.load();
        ClientController controller = loader.getController();
        controller.main();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cadastrar Aluno");
        stage.show();
        stage.setResizable(false);
    }

    public static Stage getStage(){
        return stage;
    }

}

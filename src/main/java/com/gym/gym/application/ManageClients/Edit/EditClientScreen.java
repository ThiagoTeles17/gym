package com.gym.gym.application.ManageClients.Edit;

import com.gym.gym.application.ManageClients.Create.ClientController;
import com.gym.gym.model.ClientModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class EditClientScreen extends Application {

    FXMLLoader loader;

    public static Stage stage;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        EditClientScreen.stage = stage;
        loader = new FXMLLoader(getClass().getResource("EditClient.fxml"));
        Parent root = loader.load();
        EditClientController controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Editar Aluno");
        stage.show();
        stage.setResizable(false);
        controller.main();
    }
    public EditClientController getController() throws IOException {
        return loader.getController();
    }
    public static Stage getStage(){
        return stage;
    }

}

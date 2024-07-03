package com.gym.gym.application.ManagePlans.Create;

import com.gym.gym.application.ManageClients.Create.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreatePlanScreen extends Application {

    public static Stage stage;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        CreatePlanScreen.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreatePlan.fxml"));
        Parent root = loader.load();
        CreatePlanController controller = loader.getController();
        controller.main();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cadastrar Plano");
        stage.show();
        stage.setResizable(false);
    }

    public static Stage getStage(){
        return stage;
    }

}

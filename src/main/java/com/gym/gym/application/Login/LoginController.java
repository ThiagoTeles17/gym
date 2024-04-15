package com.gym.gym.application.Login;

import com.gym.gym.application.Home.HomeScreen;
import com.masked.maskedtextfield.MaskedTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginController{

    @FXML
    AnchorPane windowContainer;
    @FXML
    MaskedTextField loginText;
    @FXML
    PasswordField passText;
    @FXML
    Label msgInvalidLogin;


    public void main(){
        initListeners();
    }

    public void initListeners(){
            windowContainer.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.ENTER)
                        login();
                }
            });

    }

    public void closeApp(){
        System.exit(0);
    }

    public void login(){
        try {
            if(loginText.getText().equals("109.174.649-40") && passText.getText().equals("admin")){
                new HomeScreen().start(new Stage());
                LoginScreen.getStage().close();
                msgInvalidLogin.setVisible(false);
            }
            else {
                msgInvalidLogin.setVisible(true);
                System.out.println("Login Inválido");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}

package com.gym.gym.application.Home;

import com.gym.gym.DAO.ClientDAO;
import com.gym.gym.application.Create.ClientScreen;
import com.gym.gym.application.ManageClients.ManageClientsScreen;
import javafx.stage.Stage;

public class HomeController {

    public void newClient(){
        try {
            new ClientScreen().start(new Stage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ManageClients(){
        try {
            new ManageClientsScreen().start(new Stage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listClients(){
        new ClientDAO().getClients();
    }


}

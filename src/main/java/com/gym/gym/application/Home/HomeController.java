package com.gym.gym.application.Home;

import com.gym.gym.DAO.ClientDAO;
import com.gym.gym.application.ManageClients.Create.ClientScreen;
import com.gym.gym.application.ManageClients.ManageClientsScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Label alunosAtivosDash;

    @FXML
    private Label alunosInativosDash;
    @FXML
            private Label alunosCadastradosDash;

    ClientDAO clientDAO = new ClientDAO();

    public void main(){
        getAlunosToDashboard();
    }
    public void getAlunosToDashboard(){
        alunosAtivosDash.setText(String.valueOf(clientDAO.getClientsAtivosLength()));
        alunosInativosDash.setText(String.valueOf(clientDAO.getClientsLength() - clientDAO.getClientsAtivosLength()));
        alunosCadastradosDash.setText(String.valueOf(clientDAO.getClientsLength()));
    }

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

package com.gym.gym.application.ManageClients;

import com.gym.gym.DAO.PlanDAO;
import com.gym.gym.model.ClientModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PhysicalAvaliationFormController {

    @FXML
    private TextField altura;

    @FXML
    private TextField atividadePraticada;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private CheckBox consomeBebidaAlcoolica;

    @FXML
    private DatePicker dataAvaliacao;

    @FXML
    private TextField freqCardiaca;

    @FXML
    private CheckBox fumante;

    @FXML
    private TextField matricula;

    @FXML
    private TextField nome;

    @FXML
    private TextArea objetivo;

    @FXML
    private TextArea observacoes;

    @FXML
    private TextField peso;

    @FXML
    private TextField plano;

    @FXML
    private CheckBox praticaAtividadeFisica;

    @FXML
    private TextField tempoPraticaAtividadeFisica;


    ClientModel client;


    public void main(){
        initListeners();
    }

    public void initListeners(){

    }

    public void setClient(ClientModel client){
        this.client = client;
        matricula.setText(String.valueOf(client.getNumMatricula()));
        nome.setText(String.valueOf(client.getNome() + " " + client.getSobrenome()));
        plano.setText(String.valueOf(new PlanDAO().getPlanById(client.getIdPlano()).getNome()));
    }

    private void savePhysicalAvaliation(){


    }

}

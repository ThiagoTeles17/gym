package com.gym.gym.application.ManageClients;

import com.gym.gym.DAO.ClientDAO;
import com.gym.gym.application.Create.ClientScreen;
import com.gym.gym.model.ClientModel;
import com.gym.gym.util.DBUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageClientsController {

    @FXML
    private ImageView profilePicture;

    @FXML
    private TableView<ClientModel> clientsTable;

    @FXML
    private TableColumn<ClientModel, String> columnNome;

    @FXML
    private TableColumn<ClientModel, String> columnSituacao;

    @FXML
    private TableColumn<ClientModel, String> columnDataNascimento;

    @FXML
    private TableColumn<ClientModel, String> columnMatricula;

    @FXML
    private TableColumn<ClientModel, String> columnDataMatricula;

    private ObservableList<ClientModel> clientsObservableList;

    private List<ClientModel> clientsList = new ArrayList<>();

    private ClientModel currentClient;

    @FXML
    private Label clientesLength;

    @FXML
    private Label clientesAtivosLength;

    @FXML
    private Label clientesInativosLength;

    ClientDAO clientDAO = new ClientDAO();

    public void main(){
        getClientsAndSetList();
        tableListener();

        //change rows height to 50
        resizeRowHeight(50);

    }

    public void getClientsAndSetList()
    {
        //Format dataNascimento to pattern dd/MM/yyyy
        columnDataMatricula.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClientModel, String> param) {
                Date dataDeMatricula = param.getValue().getDataMatricula();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return new SimpleStringProperty(String.valueOf(simpleDateFormat.format(dataDeMatricula)));
            }
        });

        //concat name and last name
        columnNome.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ClientModel, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ClientModel, String> param) {
                        return new SimpleStringProperty(param.getValue().getNome() + " " + param.getValue().getSobrenome());
                    }
                }
        );


        //Format dataNascimento to pattern dd/MM/yyyy
        columnDataNascimento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClientModel, String> param) {
                Date dataDeNascimento = param.getValue().getDataNascimento();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return new SimpleStringProperty(String.valueOf(simpleDateFormat.format(dataDeNascimento)));
            }
        });
        columnMatricula.setCellValueFactory(new PropertyValueFactory<ClientModel, String>("numMatricula"));
        columnSituacao.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClientModel, String> param) {
                return new SimpleStringProperty(param.getValue().getAtivo() ? "Ativo" : "Inativo");
            }
        });


        clientDAO.getClients();
        clientesLength.setText(String.valueOf(clientDAO.getClientsLength()));
        clientesAtivosLength.setText(String.valueOf(clientDAO.getClientsAtivosLength()));
        clientesInativosLength.setText(String.valueOf(clientDAO.getClientsLength() - clientDAO.getClientsAtivosLength()));


        clientsObservableList = FXCollections.observableArrayList(clientDAO.getClientsList());
        clientsTable.setItems(clientsObservableList);

    };


    public void addNewClient() throws Exception{
        try {
            new ClientScreen().start(new Stage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleRemoveClient(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Excluir Aluno");
        alert.setHeaderText("Atenção! Essa operação é irreversível.");
        alert.setContentText("Deseja realmente excluir o aluno " + currentClient.getNome() + " " + currentClient.getSobrenome() + "?");


        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            clientDAO.removeClient(currentClient.getNumMatricula(), currentClient.getNome());

            getClientsAndSetList();
        }


    }

    public void tableListener(){

        clientsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ClientModel>() {
            @Override
            public void changed(ObservableValue<? extends ClientModel> observable, ClientModel oldValue, ClientModel newValue) {
                if(clientsTable.getSelectionModel().getSelectedItem() != null){
                    currentClient = clientsTable.getSelectionModel().getSelectedItem();

                    if(currentClient.getProfilePicImg() != null){
                        profilePicture.setImage(currentClient.getProfilePicImg());


                    }

                }
            }
        });

    }

    private void resizeRowHeight(double newHeight){
        clientsTable.setRowFactory(new Callback<TableView<ClientModel>, TableRow<ClientModel>>() {
            @Override
            public TableRow<ClientModel> call(TableView<ClientModel> param) {
                return new TableRow<ClientModel>() {
                    @Override
                    protected double computePrefHeight(double width) {
                        return newHeight;
                    }

                    ;
                };
            };
        });
    }

}

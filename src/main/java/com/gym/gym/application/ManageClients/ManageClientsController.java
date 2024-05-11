package com.gym.gym.application.ManageClients;

import com.gym.gym.DAO.ClientDAO;
import com.gym.gym.application.ManageClients.Create.ClientScreen;
import com.gym.gym.application.ManageClients.Edit.EditClientController;
import com.gym.gym.application.ManageClients.Edit.EditClientScreen;
import com.gym.gym.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageClientsController {

    public Stage filterScreenStage = new Stage();

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


    @FXML
    AnchorPane filterAp;





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

        clientsTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    try {
                        handleEditClient();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
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


    public void handleFilterClients() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FilterDataScreen.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        filterScreenStage.setScene(scene);
        filterScreenStage.setTitle("Filtrar");
        filterScreenStage.setResizable(false);
        filterScreenStage.setMaximized(false);
        filterScreenStage.show();

    }
    public void cancelFilterClients(){

        if(filterAp != null) {
            filterScreenStage = (Stage) filterAp.getScene().getWindow();
            filterScreenStage.close();
        }

    }
    public void handleEditClient() throws Exception{

        try {
            EditClientScreen editClientScreen = new EditClientScreen();
            editClientScreen.start(new Stage());
            EditClientController controller = editClientScreen.getController();
            controller.setClient(clientsTable.getSelectionModel().getSelectedItem());
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

}


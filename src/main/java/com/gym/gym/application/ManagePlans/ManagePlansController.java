package com.gym.gym.application.ManagePlans;

import com.gym.gym.DAO.PlanDAO;
import com.gym.gym.application.ManageClients.Create.ClientScreen;
import com.gym.gym.application.ManageClients.Edit.EditClientController;
import com.gym.gym.application.ManageClients.Edit.EditClientScreen;
import com.gym.gym.application.ManagePlans.Create.CreatePlanScreen;
import com.gym.gym.model.ClientModel;
import com.gym.gym.model.PlanModel;
import com.gym.gym.model.PlanModel;
import javafx.beans.property.SimpleIntegerProperty;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ManagePlansController {


    @FXML
    private TableView<PlanModel> plansTable;

    @FXML
    private TableColumn<PlanModel, String> columnNome;

    @FXML
    private TableColumn<PlanModel, Integer> columnID;
    
    @FXML
    private TableColumn<PlanModel, Integer> columnAlunosAtivos;

    @FXML
    private TableColumn<PlanModel, Double> columnValor;

    private ObservableList<PlanModel> plansObservableList;

    private List<PlanModel> plansList = new ArrayList<>();

    private PlanModel currentPlan;

    @FXML
    private Label planosLength;
    
    
    PlanDAO planDAO = new PlanDAO();

    public void main(){
        getPlansAndSetList();
        tableListener();

        //change rows height to 50
        resizeRowHeight(50);

    }

    public void getPlansAndSetList()
    {
        columnID.setCellValueFactory(new PropertyValueFactory<PlanModel, Integer>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<PlanModel, String>("nome"));

        columnAlunosAtivos.setCellValueFactory(new PropertyValueFactory<PlanModel, Integer>("alunosAtivos"));

        columnValor.setCellValueFactory(new PropertyValueFactory<PlanModel, Double>("valor"));

        planDAO.getPlans();

        planosLength.setText(String.valueOf(planDAO.getPlansLength()));
   

        plansObservableList = FXCollections.observableArrayList(planDAO.getPlansList());
        plansTable.setItems(plansObservableList);

    };


    public void addNewPlan() throws Exception{
        try {
            new CreatePlanScreen().start(new Stage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleRemovePlan(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Excluir Plano");
        alert.setHeaderText("Atenção! Essa operação é irreversível.");
        alert.setContentText("Deseja realmente excluir o plano " + currentPlan.getNome() + "?");


        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            planDAO.removePlan(currentPlan.getId(), currentPlan.getNome());
            getPlansAndSetList();
        }


    }

    public void tableListener(){

        plansTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlanModel>() {
            @Override
            public void changed(ObservableValue<? extends PlanModel> observable, PlanModel oldValue, PlanModel newValue) {
                if(plansTable.getSelectionModel().getSelectedItem() != null){
                    currentPlan = plansTable.getSelectionModel().getSelectedItem();

                }
            }
        });

        plansTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    try {
                        handleEditPlan();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void resizeRowHeight(double newHeight){
        plansTable.setRowFactory(new Callback<TableView<PlanModel>, TableRow<PlanModel>>() {
            @Override
            public TableRow<PlanModel> call(TableView<PlanModel> param) {
                return new TableRow<PlanModel>() {
                    @Override
                    protected double computePrefHeight(double width) {
                        return newHeight;
                    }


                };
            };
        });
    }


    public void handleEditPlan() throws Exception{
        /*TODO Criar tela de edição dos planos
        try {
            EditClientScreen editClientScreen = new EditClientScreen();
            editClientScreen.start(new Stage());
            EditClientController controller = editClientScreen.getController();
            controller.setClient(plansTable.getSelectionModel().getSelectedItem());
        }
        catch (Exception e){
            e.printStackTrace();
        }
         */
    }

}


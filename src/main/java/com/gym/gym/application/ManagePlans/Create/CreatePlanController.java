package com.gym.gym.application.ManagePlans.Create;

import com.gym.gym.DAO.ClientDAO;
import com.gym.gym.DAO.PlanDAO;
import com.gym.gym.DAO.UfCidadesDAO;
import com.gym.gym.model.ClientModel;
import com.gym.gym.model.PlanModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

public class CreatePlanController {

    @FXML
    private AnchorPane ap;

    @FXML
    private TextArea descricao;

    @FXML
    private TextField identificador;

    @FXML
    private TextField nome;

    @FXML
    private TextField valor;

    @FXML
    private ComboBox modalidadePagamento;
    Stage stage;

    PlanDAO planDAO = new PlanDAO();

    public void main(){
        identificador.setText(String.valueOf(planDAO.getLastId() + 1));
        modalidadePagamento.setItems(FXCollections.observableArrayList("Anual", "Mensal", "Quinzenal", "Diário"));
    }


    public void handleSavePlan(){

        PlanModel plan = new PlanModel();

        String errors = validate();

        if(errors.equals("")){

            plan.setId(Integer.valueOf(identificador.getText()));
            plan.setNome(nome.getText());
            plan.setDescricao(descricao.getText());
            plan.setValor(Double.valueOf(valor.getText()));
            plan.setModalidadePagamento(String.valueOf(modalidadePagamento.getValue()));

            planDAO.RegisterPlan(plan);

            if (ap != null) {
                stage = (Stage) ap.getScene().getWindow();
                stage.close();
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos Obrigatórios:");
            alert.setHeaderText("Os seguintes campos são obrigatórios");
            alert.setContentText(errors);
            alert.showAndWait();
        }

    }

    private String validate(){

        String errorMsg = "";


        if(nome.getText().equals("")){
            errorMsg = errorMsg + "O campo \"Nome\" é obrigatório\n\n";
        }
        if(descricao.getText().equals("")){
            errorMsg = errorMsg + "O campo \"Descrição\" é obrigatório\n\n";
        }
        if(valor.getText().equals("")){
            errorMsg = errorMsg + "O campo \"Valor\" é obrigatório\n\n";
        }

        return errorMsg;
    }


    public void cancelRegister(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancelar operação");
        alert.setHeaderText("Deseja cancelar a operação?");
        alert.setContentText("Dados não salvos serão perdidos");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            if (ap != null) {
                stage= (Stage) ap.getScene().getWindow();
                stage.close();
            }
        }

    }

}

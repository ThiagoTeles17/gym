package com.gym.gym.application.ManageClients.Edit;

import com.gym.gym.DAO.ClientDAO;
import com.gym.gym.DAO.UfCidadesDAO;
import com.gym.gym.application.ManageClients.ManageClientsController;
import com.gym.gym.model.ClientModel;
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
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class EditClientController {

    @FXML
    ImageView profilePicture;
    @FXML
    AnchorPane ap;
    @FXML
    TextField numMatricula;
    @FXML
    DatePicker dataMatricula;
    @FXML
    ComboBox tipoPlano;
    @FXML
            TextField nome;
    @FXML
            TextField sobrenome;
    @FXML
            TextField rg;
    @FXML
            TextField cpf;
    @FXML
            DatePicker dataNascimento;
    @FXML
            ComboBox sexo;
    @FXML
            ComboBox ufNaturalidade;
    @FXML
    ComboBox cidadeNaturalidade;
    @FXML
    ComboBox cidadeEndereco;
    @FXML
            TextField nomeMae;
    @FXML
            TextField nomePai;
    @FXML
            TextField logradouro;
    @FXML
            TextField numEndereco;
    @FXML
            TextField bairroEndereco;
    @FXML
            TextField complementoEndereco;
    @FXML
            ComboBox ufEndereco;
    @FXML
            TextField telefone;
    @FXML
            TextField celular;
    @FXML
            TextField telefoneEmergencia;
    @FXML
            TextField idade;

    @FXML
    ComboBox selectAtivo;


    private List<String> cidadesEnderecoList;
    private List<String> cidadesNaturalidadeList;
    private List<String> ufList;
    private FileInputStream profilePicIS;
    private ClientModel client;

    Stage stage;

    public void main(){
        initListeners();
        sexo.setItems(FXCollections.observableArrayList("Masculino", "Feminino"));
    }

    public void setClient(ClientModel client){

        this.client = client;

        numMatricula.setText(String.valueOf(client.getNumMatricula()));
        nome.setText(client.getNome());
        sobrenome.setText(client.getSobrenome());
        sexo.setValue(client.getSexo());

        ufList = new UfCidadesDAO().getUFs();

        ufEndereco.setItems(FXCollections.observableArrayList(ufList));
        ufNaturalidade.setItems(FXCollections.observableArrayList(ufList));

        ufEndereco.setValue(client.getUfEndereco());
        ufNaturalidade.setValue(client.getUfNaturalidade());


        cidadeEndereco.setItems(FXCollections.observableArrayList(new UfCidadesDAO().getCidadesByUf(String.valueOf(ufEndereco.getValue()))));
        cidadeEndereco.setValue(client.getCidadeEndereco());

        if(ufNaturalidade.getValue() != ""){
            cidadeNaturalidade.setItems(FXCollections.observableArrayList(new UfCidadesDAO().getCidadesByUf(String.valueOf(ufNaturalidade.getValue()))));
            cidadeNaturalidade.setValue(client.getCidadeNaturalidade());
        }


        dataMatricula.setValue(client.getDataMatricula().toLocalDate());

        //Calculate client age
        try{
            dataNascimento.setValue(client.getDataNascimento().toLocalDate());
            calculateAge();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        cpf.setText(client.getCpf());
        rg.setText(client.getRg());
        bairroEndereco.setText(client.getBairroEndereco());
        logradouro.setText(client.getLogradouro());
        nomeMae.setText(client.getNomeMae());
        nomePai.setText(client.getNomePai());
        numEndereco.setText(client.getNumEndereco());
        telefone.setText(client.getTelefone());
        celular.setText(client.getCelular());
        telefoneEmergencia.setText(client.getTelefoneEmergencia());
        complementoEndereco.setText(client.getComplementoEndereco());
        selectAtivo.setItems(FXCollections.observableArrayList("Ativo", "Inativo"));
        selectAtivo.setValue(client.getAtivo() ? "Ativo" : "Inativo");

        //Verify if image exists
        if(client.getProfilePicImg() != null){
            if(client.getProfilePicImg().getWidth() != 0)
                    profilePicture.setImage(client.getProfilePicImg());
        }


    }

    public void changePicture() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma imagem");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imagem PNG", "*.png"),
            new FileChooser.ExtensionFilter("Imagem JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("Imagem BMP", "*.bmp")
        );
        File selectedFile = fileChooser.showOpenDialog(ap.getScene().getWindow());


        if(selectedFile != null){
            profilePicIS = new FileInputStream(selectedFile);
            profilePicture.setImage(new Image(selectedFile.toURI().toString()));
        }
    }

    public void handleSaveClient(){

        String errors = validate();

        if(errors.equals("")){
            client.setNumMatricula(numMatricula.getText());
            client.setDataMatricula(java.sql.Date.valueOf(dataMatricula.getValue()));
            client.setIdPlano(1);
            client.setNome(nome.getText());
            client.setSobrenome(sobrenome.getText());
            client.setRg(rg.getText());
            client.setEmissorRg("");
            client.setCpf(cpf.getText());
            client.setDataNascimento(java.sql.Date.valueOf(dataNascimento.getValue()));
            client.setSexo(String.valueOf(sexo.getValue()));
            client.setCidadeNaturalidade(String.valueOf(cidadeNaturalidade.getValue()));
            client.setUfNaturalidade(String.valueOf(ufNaturalidade.getValue()));
            client.setCidadeEndereco(String.valueOf(cidadeEndereco.getValue()));
            client.setUfEndereco(String.valueOf(ufEndereco.getValue()));
            client.setNomeMae(nomeMae.getText());
            client.setNomePai(nomePai.getText());
            client.setLogradouro(logradouro.getText());
            client.setNumEndereco(numEndereco.getText());
            client.setBairroEndereco(bairroEndereco.getText());
            client.setComplementoEndereco(complementoEndereco.getText());
            client.setTelefone(telefone.getText());
            client.setCelular(celular.getText());
            client.setTelefoneEmergencia(telefoneEmergencia.getText());
            client.setAtivo(selectAtivo.getValue().equals("Ativo") ? true : false);
            client.setProfilePic(profilePicIS);

            try{
                new ClientDAO().EditClient(client);
            }
            catch (Exception e){
                e.printStackTrace();
            }

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

    public void calculateAge(){
        if(dataNascimento.getValue() != null) {
            LocalDate dataDeNascimento = dataNascimento.getValue();
            Period period = Period.between(dataDeNascimento, LocalDate.now());
            idade.setText(String.valueOf(period.getYears()));
        }
    }


    private String validate(){

        String errorMsg = "";


        if(dataMatricula.getValue() == null){
            errorMsg = errorMsg + "O campo \"Data da Matricula\" é obrigatório\n\n";
        }
        if(nome.getText().equals("")){
            errorMsg = errorMsg + "O campo \"Nome\" é obrigatório\n\n";
        }
        if(sobrenome.getText().equals("")){
            errorMsg = errorMsg + "O campo \"Sobrenome\" é obrigatório\n\n";
        }
        if(sexo.getValue() == null){
            errorMsg = errorMsg + "O campo \"Sexo\" é obrigatório\n\n";
        }
        if(cpf.getText().equals("")){
            errorMsg = errorMsg + "O campo \"CPF\" é obrigatório\n\n";
        }
        if(dataNascimento.getValue() == null){
            errorMsg = errorMsg + "O campo \"Data de Nascimento\" é obrigatório\n\n";
        }
        if(nomeMae.getText().equals("")){
            errorMsg = errorMsg + "O campo \"Nome da Mãe\" é obrigatório\n\n";
        }
        if(cidadeEndereco.getValue() == null){
            errorMsg = errorMsg + "O campo \"Cidade\" em endereço residencial é obrigatório\n\n";
        }
        if(ufEndereco.getValue() == null){
            errorMsg = errorMsg + "O campo \"UF\" em endereço residencial é obrigatório\n\n";
        }
        if(celular.getText().equals("") && telefone.getText().equals("") && telefoneEmergencia.getText().equals("")){
            errorMsg = errorMsg + "É necessário informar pelo menos um número de telefone ou celular\n\n";
        }

        return errorMsg;
    }







    public void initListeners(){
        dataNascimento.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal)
                calculateAge();
        });
        ufEndereco.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal && ufEndereco.getValue() != null) {
                cidadesEnderecoList = new UfCidadesDAO().getCidadesByUf(String.valueOf(ufEndereco.getValue()));
                cidadeEndereco.setItems(FXCollections.observableArrayList(cidadesEnderecoList));
            }
        });
        ufNaturalidade.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal && ufNaturalidade.getValue() != null) {
                cidadesNaturalidadeList = new UfCidadesDAO().getCidadesByUf(String.valueOf(ufNaturalidade.getValue()));
                cidadeNaturalidade.setItems(FXCollections.observableArrayList(cidadesNaturalidadeList));
            }
        });



    }



    public void cancelRegister(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancelar operação");
        alert.setHeaderText("Deseja cancelar a operação?");
        alert.setContentText("Dados não salvos serão perdidos");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            if (ap != null) {
                stage = (Stage) ap.getScene().getWindow();
                stage.close();
            }
        }

    }

}

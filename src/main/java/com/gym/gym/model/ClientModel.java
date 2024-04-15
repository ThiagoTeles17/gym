package com.gym.gym.model;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ClientModel {

    Image profilePicImg;
    FileInputStream profilePic;
    String numMatricula;
    java.sql.Date dataMatricula;
    int idPlano;
    String nome;
    String sobrenome;
    String rg;
    String emissorRg;
    String cpf;
    java.sql.Date dataNascimento;
    String sexo;
    String cidadeNaturalidade;
    String ufNaturalidade;
    String nomeMae;
    String nomePai;
    String logradouro;
    String numEndereco;
    String bairroEndereco;
    String complementoEndereco;
    String cidadeEndereco;
    String ufEndereco;
    String telefone;
    String celular;
    String telefoneEmergencia;
    int idade;
    Boolean ativo;

    public FileInputStream getProfilePic() {
        return profilePic;
    }

    public Image getProfilePicImg() {
        return profilePicImg;
    }

    public void setProfilePicImg(Image profilePicImg) {
        this.profilePicImg = profilePicImg;
    }

    public void setProfilePic(FileInputStream profilePic) {
        this.profilePic = profilePic;
    }

    public String getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(String numMatricula) {
        this.numMatricula = numMatricula;
    }

    public java.sql.Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(java.sql.Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmissorRg(){
        return emissorRg;
    }

    public void setEmissorRg(String emissorRg) {
        this.emissorRg = emissorRg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public java.sql.Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(java.sql.Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCidadeNaturalidade() {
        return cidadeNaturalidade;
    }

    public void setCidadeNaturalidade(String cidadeNaturalidade) {
        this.cidadeNaturalidade = cidadeNaturalidade;
    }

    public String getUfNaturalidade() {
        return ufNaturalidade;
    }

    public void setUfNaturalidade(String ufNaturalidade) {
        this.ufNaturalidade = ufNaturalidade;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getBairroEndereco() {
        return bairroEndereco;
    }

    public void setBairroEndereco(String bairroEndereco) {
        this.bairroEndereco = bairroEndereco;
    }

    public String getCidadeEndereco() {
        return cidadeEndereco;
    }

    public void setCidadeEndereco(String cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    public String getNumEndereco() {
        return numEndereco;
    }

    public void setNumEndereco(String numEndereco) {
        this.numEndereco = numEndereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefoneEmergencia() {
        return telefoneEmergencia;
    }

    public void setTelefoneEmergencia(String telefoneEmergencia) {
        this.telefoneEmergencia = telefoneEmergencia;
    }

    public String getUfEndereco() {
        return ufEndereco;
    }

    public void setUfEndereco(String ufEndereco) {
        this.ufEndereco = ufEndereco;
    }


    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}

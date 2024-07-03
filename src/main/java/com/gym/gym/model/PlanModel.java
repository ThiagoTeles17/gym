package com.gym.gym.model;

public class PlanModel {
    private Integer id;
    private String nome;
    private String descricao;
    private Double valor;
    private int alunosAtivos;


    private String modalidadePagamento; //Payments types: Anual, Mensal or Diário

    public String getModalidadePagamento(){
        return modalidadePagamento;
    }
    public void setModalidadePagamento(String modalidadePagamento){
        this.modalidadePagamento = modalidadePagamento;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public Double getValor(){
        return valor;
    }
    public void setValor(Double valor){
        this.valor = valor;
    }
    public int getAlunosAtivos(){
        return alunosAtivos;
    }
    public void setAlunosAtivos(int alunosAtivos){
        this.alunosAtivos = alunosAtivos;
    }





}

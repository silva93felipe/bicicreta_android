package com.app.bicicreta.app.model;

public class Peca {
    private int id;
    private String dataCompra;
    private double valor;
    private int quilometros;
    private int bicicletaId;
    private String nomePeca;
    private String modeloBicicleta;
    public Peca(String descricao, String dataCompra, double valor,int bicicletaId) {
        this.nomePeca = descricao.toUpperCase();
        this.dataCompra = dataCompra;
        this.valor = valor;
        this.bicicletaId = bicicletaId;
    }

    public Peca(int id, String descricao, String dataCompra, double valor, int quilometros, int bicicletaId, String modeloBicicleta) {
        this(descricao, dataCompra, valor, bicicletaId);
        this.id = id;
        this.quilometros = quilometros;
        this.modeloBicicleta = modeloBicicleta;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuilometros() {
        return quilometros;
    }

    public void setQuilometros(int quilometros) {
        this.quilometros = quilometros;
    }

    public int getBicicletaId() {
        return bicicletaId;
    }

    public void setBicicletaId(int bicicletaId) {
        this.bicicletaId = bicicletaId;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModeloBicicleta() {
        return modeloBicicleta;
    }

    public void setModeloBicicleta(String modeloBicicleta) {
        this.modeloBicicleta = modeloBicicleta;
    }
}

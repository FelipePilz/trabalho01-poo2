package dev.trabalho.dao.imovel;

import java.sql.Timestamp;

public class Imovel {

    private int id;
    private String endereco;
    private String descricao;
    private boolean garagem = false;
    private boolean disponivel = false;
    private Timestamp dataCadastro;

    public Imovel(String endereco, String descricao, boolean garagem) {
        this.endereco = endereco;
        this.descricao = descricao;
        this.garagem = garagem;
    }

    public Imovel(int id, String endereco, String descricao, boolean garagem, boolean disponivel, Timestamp dataCadastro) {
        this.id = id;
        this.endereco = endereco;
        this.descricao = descricao;
        this.garagem = garagem;
        this.disponivel = disponivel;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isGaragem() {
        return garagem;
    }

    public void setGaragem(boolean garagem) {
        this.garagem = garagem;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", endereco='" + endereco + '\'' +
                ", descricao='" + descricao + '\'' +
                ", garagem=" + garagem +
                ", disponivel=" + disponivel +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}

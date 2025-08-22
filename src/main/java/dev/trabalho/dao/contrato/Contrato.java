package dev.trabalho.dao.contrato;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;

public class Contrato {

    private int id;
    private int idImovel;
    private int idCliente;
    private Date dataInicio;
    private Date dataFim;
    private BigDecimal valor;
    private boolean ativo;
    private String observacoes;
    private Timestamp dataCadastro;

    public Contrato(int idImovel, int idCliente, Date dataInicio, Date dataFim, BigDecimal valor, boolean ativo, String observacoes) {
        this.idImovel = idImovel;
        this.idCliente = idCliente;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valor = valor;
        this.ativo = ativo;
        this.observacoes = observacoes;
    }

    public Contrato(int id, int idImovel, int idCliente, Date dataInicio, Date dataFim, BigDecimal valor, boolean ativo, String observacoes, Timestamp dataCadastro) {
        this.id = id;
        this.idImovel = idImovel;
        this.idCliente = idCliente;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valor = valor;
        this.ativo = ativo;
        this.observacoes = observacoes;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(int idImovel) {
        this.idImovel = idImovel;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
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
                ", idImovel=" + idImovel +
                ", idCliente=" + idCliente +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", valor=" + valor +
                ", ativo=" + ativo +
                ", observacoes='" + observacoes + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}

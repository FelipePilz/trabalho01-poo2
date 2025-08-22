package dev.trabalho.dao.contrato;

import dev.trabalho.dao.BaseDAO;
import dev.trabalho.dao.cliente.ClienteDAO;
import dev.trabalho.dao.imovel.ImovelDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO extends BaseDAO {

    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static ImovelDAO imovelDao = new ImovelDAO();

    public void create(Contrato contrato) {
        validateCreate(contrato);

        String sql = "INSERT INTO contratos (id_imovel, id_cliente, data_inicio, data_fim, valor, ativo, observacoes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, contrato.getIdImovel());
            pre.setInt(2, contrato.getIdCliente());
            pre.setDate(3, contrato.getDataInicio());
            pre.setDate(4, contrato.getDataFim());
            pre.setBigDecimal(5, contrato.getValor());
            pre.setBoolean(6, contrato.isAtivo());
            pre.setString(7, contrato.getObservacoes());

            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir contrato", e);
        }
    }

    public List<Contrato> find() {
        String sql = "SELECT * FROM contratos";

        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {

            ResultSet rs = pre.executeQuery();

            List<Contrato> contratos = new ArrayList<>();
            while (rs.next()) {
                contratos.add(mapRow(rs));
            }
            return contratos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar contratos", e);
        }
    }

    private void validateCreate(Contrato contrato) {
        if (contrato == null) {
            throw new RuntimeException("Contrato não pode ser nulo");
        }

        if (contrato.getIdImovel() <= 0 || imovelDao.findById(contrato.getIdImovel()) == null) {
            throw new RuntimeException("ID do imóvel inválido");
        }

        if (contrato.getIdCliente() <= 0 || clienteDAO.findById(contrato.getIdCliente()) == null) {
            throw new RuntimeException("ID do cliente inválido");
        }

        if (contrato.getDataInicio() == null) {
            throw new RuntimeException("Data de início do contrato é obrigatória");
        }

        if (contrato.getDataFim() == null) {
            throw new RuntimeException("Data de fim do contrato é obrigatória");
        }

        if (contrato.getDataFim().before(contrato.getDataInicio())) {
            throw new RuntimeException("Data de fim não pode ser anterior à data de início");
        }

        if (contrato.getValor() == null || contrato.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor do contrato deve ser maior que 0");
        }

    }

    private Contrato mapRow(ResultSet rs) throws SQLException {
        return new Contrato(
                rs.getInt("id"),
                rs.getInt("id_imovel"),
                rs.getInt("id_cliente"),
                rs.getDate("data_inicio"),
                rs.getDate("data_fim"),
                rs.getBigDecimal("valor"),
                rs.getBoolean("ativo"),
                rs.getString("observacoes"),
                rs.getTimestamp("data_cadastro")
        );
    }
}

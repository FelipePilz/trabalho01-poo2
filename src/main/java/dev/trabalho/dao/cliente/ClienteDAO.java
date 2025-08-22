package dev.trabalho.dao.cliente;

import dev.trabalho.dao.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends BaseDAO {

    public void create(Cliente cliente) {
        validateCreate(cliente);

        String sql = "INSERT INTO clientes (nome, cpf, telefone, email, endereco) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setString(1, cliente.getNome());
            pre.setString(2, cliente.getCpf());
            pre.setString(3, cliente.getTelefone());
            pre.setString(4, cliente.getEmail());
            pre.setString(5, cliente.getEndereco());

            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente", e);
        }
    }

    public List<Cliente> find() {
        String sql = "SELECT * FROM clientes";

        try (Connection con = con()) {
            PreparedStatement pre = con.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();


            List<Cliente> users = new ArrayList<>();
            while (rs.next()) {
                users.add(mapRow(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente findById(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, id);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            } else {
                return null; // ou lançar uma exceção se preferir
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID", e);
        }
    }

    public Cliente findClienteComMaisContratos() {
        String sql = "SELECT c.*, COUNT(ct.id) AS total_contratos " +
                "FROM clientes c " +
                "JOIN contratos ct ON c.id = ct.id_cliente " +
                "GROUP BY c.id " +
                "ORDER BY total_contratos DESC " +
                "LIMIT 1";

        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente com mais contratos", e);
        }
    }


    private void validateCreate(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new RuntimeException("Nome do cliente não pode ser vazio");
        }

        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            throw new RuntimeException("CPF do cliente não pode ser vazio");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            throw new RuntimeException("Telefone do cliente não pode ser vazio");
        }
    }

    private Cliente mapRow(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("endereco"),
                rs.getTimestamp("data_cadastro")
        );
    }

}

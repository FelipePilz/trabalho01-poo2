package dev.trabalho.dao.imovel;

import dev.trabalho.dao.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImovelDAO extends BaseDAO {

    public void create(Imovel imovel) {
        validateCreate(imovel);

        String sql = "INSERT INTO imoveis (endereco,descricao, garagem) " +
                "VALUES (?, ?, ?)";

        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setString(1, imovel.getEndereco());
            pre.setString(2, imovel.getDescricao());
            pre.setBoolean(3, imovel.isGaragem());

            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir imóvel", e);
        }
    }

    public List<Imovel> find() {
        String sql = "SELECT * FROM imoveis";

        try (Connection con = con()) {
            PreparedStatement pre = con.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();

            List<Imovel> imoveis = new ArrayList<>();
            while (rs.next()) {
                imoveis.add(mapRow(rs));
            }
            return imoveis;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Imovel findById(int id) {
        String sql = "SELECT * FROM imoveis WHERE id = ?";

        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, id);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            } else {
                return null; // ou lançar exceção, dependendo da regra de negócio
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar imóvel por ID", e);
        }
    }

    private void validateCreate(Imovel imovel) {
        if (imovel.getEndereco() == null && imovel.getEndereco().isEmpty()) {
            throw new RuntimeException("Endereço do imóvel não pode estar vazio");
        }

        if (imovel.getDescricao() == null && imovel.getDescricao().isEmpty()) {
            throw new RuntimeException("Descrição do imóvel não pode estar vazio");
        }
    }

    private Imovel mapRow(ResultSet rs) throws SQLException {
        return new Imovel(
                rs.getInt("id"),
                rs.getString("endereco"),
                rs.getString("descricao"),
                rs.getBoolean("garagem"),
                rs.getBoolean("disponivel"),
                rs.getTimestamp("data_cadastro")
        );
    }

}

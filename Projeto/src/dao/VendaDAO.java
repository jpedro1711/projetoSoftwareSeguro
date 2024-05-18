package dao;
import models.ItemVenda;
import models.Venda;
import utils.ExceptionsLogger;

import java.sql.*;

public class VendaDAO {
    private Conexao conexao;

    public VendaDAO() {
        conexao = Conexao.getInstance();
    }

    public void cadastrarVenda(Venda venda) {
        Connection connection = conexao.getConexao();
        String insertVendaQuery = "INSERT INTO venda (dataVenda, valorTotal) VALUES (?, ?)";
        String insertItemVenda = "INSERT INTO items_venda (venda_id, produto_id, quantidade) VALUES (?, ?, ?)";
        String updateQtdEstoque = "UPDATE produto SET quantidadeEstoque = ? WHERE id = ?";

        try (
                PreparedStatement ps = connection.prepareStatement(insertVendaQuery, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement psItem = connection.prepareStatement(insertItemVenda);
                PreparedStatement updateQtdEstoquePs = connection.prepareStatement(updateQtdEstoque)
        ) {


            ps.setDate(1, new java.sql.Date(venda.getDataVenda().getTime()));
            ps.setDouble(2, venda.getTotalVenda());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int vendaId = rs.getInt(1);
                venda.setVendaId(vendaId);

                for (ItemVenda item : venda.getProdutos()) {
                    psItem.setInt(1, vendaId);
                    psItem.setInt(2, item.getProduto().getProdutoId());
                    psItem.setInt(3, item.getQuantidade());

                    updateQtdEstoquePs.setInt(1, item.getProduto().getQuantidadeEstoque() - item.getQuantidade());
                    updateQtdEstoquePs.setInt(2, item.getProduto().getProdutoId());
                    psItem.executeUpdate();
                    updateQtdEstoquePs.executeUpdate();
                }

            }

            rs.close();
        } catch (SQLException e) {
            ExceptionsLogger.log(e);
        }


    }
}

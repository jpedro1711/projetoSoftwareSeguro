package dao;
import exceptions.VendaException;
import models.ItemVenda;
import models.Venda;

import java.sql.*;

public class VendaDAO {
    private Conexao conexao;

    public VendaDAO() {
        conexao = Conexao.getInstance();
    }

    public void cadastrarVenda(Venda venda) throws VendaException {
        Connection connection = conexao.getConexao();
        String insertVendaQuery = "INSERT INTO venda (dataVenda, valorTotal) VALUES (?, ?)";
        String insertItemVenda = "INSERT INTO items_venda (venda_id, produto_id, quantidade) VALUES (?, ?, ?)";
        String updateQtdEstoque = "UPDATE produto SET quantidadeEstoque = ? WHERE id = ?";

        PreparedStatement ps = null;
        PreparedStatement psItem = null;
        PreparedStatement updateQtdEstoquePs = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(insertVendaQuery, Statement.RETURN_GENERATED_KEYS);
            psItem = connection.prepareStatement(insertItemVenda);
            updateQtdEstoquePs = connection.prepareStatement(updateQtdEstoque);

            ps.setDate(1, new java.sql.Date(venda.getDataVenda().getTime()));
            ps.setDouble(2, venda.getTotalVenda());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

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
            throw new VendaException("Erro ao cadastrar venda");
        } finally {
            fecharConexoes(connection, ps, rs);
        }


    }

    public double getValorTotalVendido() throws VendaException {
        String query = "select sum(valorTotal) from venda";
        Connection conn = conexao.getConexao();
        double valor = 0;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query);;
            rs = ps.executeQuery(query);

            while (rs.next()) {
                valor = rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new VendaException("Erro ao buscar valor vendido");
        }
        finally {
            fecharConexoes(conn, ps, rs);
        }

        return valor;
    }

    private void fecharConexoes(Connection conn, PreparedStatement ps, ResultSet rs) throws VendaException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new VendaException("Erro ao fechar conexão");
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new VendaException("Erro ao fechar conexão");
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new VendaException("Erro ao fechar conexão");
            }
        }
    }
}

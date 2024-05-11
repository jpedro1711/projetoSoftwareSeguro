package dao;

import models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {
    private Conexao conexao;

    public ProdutoDAO() {
        conexao = Conexao.getInstance();
    }

    public void cadastrar(Produto produto) {
        String query = "INSERT INTO PRODUTO (nomeProduto, valorVenda, custoUnitario, quantidadeEstoque, quantidadeMinimaEstoque) values (?, ?, ?, ?, ?)";
        Connection conn = conexao.getConexao();

        try {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, produto.getNomeProduto());
            ps.setDouble(2, produto.getValorVenda());
            ps.setDouble(3, produto.getCustoUnitario());
            ps.setInt(4, produto.getQuantidadeEstoque());
            ps.setInt(5, produto.getQuantidadeMinimaEstoque());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

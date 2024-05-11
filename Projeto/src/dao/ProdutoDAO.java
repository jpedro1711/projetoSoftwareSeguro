package dao;

import models.Produto;
import utils.ExceptionsLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Conexao conexao;

    public ProdutoDAO() {
        conexao = Conexao.getInstance();
    }

    public void cadastrar(Produto produto) {
        String query = "INSERT INTO PRODUTO (nomeProduto, valorVenda, custoUnitario, quantidadeEstoque, quantidadeMinimaEstoque) values (?, ?, ?, ?, ?)";
        Connection conn = conexao.getConexao();

        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(query);

                ps.setString(1, produto.getNomeProduto());
                ps.setDouble(2, produto.getValorVenda());
                ps.setDouble(3, produto.getCustoUnitario());
                ps.setInt(4, produto.getQuantidadeEstoque());
                ps.setInt(5, produto.getQuantidadeMinimaEstoque());

                ps.executeUpdate();

                ps.close();
            } catch (SQLException e) {
                ExceptionsLogger.log(e);
            }
            catch (Exception e) {
                ExceptionsLogger.log(e);
            }
            finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    ExceptionsLogger.log(e);
                }
            }
        }
    }

    public List<Produto> listar() {
        String query = "SELECT * FROM produto";
        Connection conn = conexao.getConexao();
        List<Produto> produtos = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                double valorVenda = rs.getDouble(3);
                double custoUnitario = rs.getDouble(4);
                int qtdEstoque = rs.getInt(5);
                int qtdMinEstoque = rs.getInt(6);

                produtos.add(new Produto(id, nome, valorVenda, custoUnitario, qtdEstoque, qtdMinEstoque));
            }
        } catch (SQLException e) {
            ExceptionsLogger.log(e);
        }
        catch (Exception e) {
            ExceptionsLogger.log(e);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    ExceptionsLogger.log(e);
                }
            }
            return produtos;
        }
    }
}

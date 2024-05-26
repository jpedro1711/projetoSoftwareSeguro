package dao;

import exceptions.ProdutoException;
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
            try(PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, produto.getNomeProduto());
                ps.setDouble(2, produto.getValorVenda());
                ps.setDouble(3, produto.getCustoUnitario());
                ps.setInt(4, produto.getQuantidadeEstoque());
                ps.setInt(5, produto.getQuantidadeMinimaEstoque());

                ps.executeUpdate();

            } catch (SQLException e) {
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

        try(PreparedStatement ps = conn.prepareStatement(query)) {


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
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                ExceptionsLogger.log(e);
            }
        }
        return produtos;
    }

    public List<Produto> listarAbaixoEstoqueMinimo() {
        String query = "SELECT * FROM produto where quantidadeEstoque < quantidadeMinimaEstoque";
        Connection conn = conexao.getConexao();
        List<Produto> produtos = new ArrayList<>();

        try(PreparedStatement ps = conn.prepareStatement(query)) {


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
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                ExceptionsLogger.log(e);
            }
        }
        return produtos;
    }

    public List<Produto> getTop3MaisVendidos() {
        String query = "SELECT produto.id, produto.NomeProduto, produto.valorVenda, produto.custoUnitario, produto.quantidadeEstoque, produto.quantidadeMinimaEstoque, COUNT(*) as contagem " +
                "FROM produto " +
                "INNER JOIN items_venda AS a ON a.produto_id = produto.id " +
                "INNER JOIN venda AS b ON b.id = a.venda_id " +
                "GROUP BY produto.id, produto.NomeProduto, produto.valorVenda, produto.custoUnitario, produto.quantidadeEstoque, produto.quantidadeMinimaEstoque " +
                "ORDER BY contagem DESC " +
                "LIMIT 3";

        Connection conn = conexao.getConexao();
        List<Produto> produtos = new ArrayList<>();

        try(PreparedStatement ps = conn.prepareStatement(query)) {

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
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                ExceptionsLogger.log(e);
            }
        }
        return produtos;
    }

    public Produto buscarProdutoPorId(int id) {
        String query = "SELECT * FROM produto WHERE id = ?";
        Connection conn = conexao.getConexao();
        Produto produto = null;

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nomeProduto");
                double valorVenda = rs.getDouble("valorVenda");
                double custoUnitario = rs.getDouble("custoUnitario");
                int qtdEstoque = rs.getInt("quantidadeEstoque");
                int qtdMinEstoque = rs.getInt("quantidadeMinimaEstoque");

                produto = new Produto(id, nome, valorVenda, custoUnitario, qtdEstoque, qtdMinEstoque);
            }
        } catch (SQLException e) {
            ExceptionsLogger.log(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                ExceptionsLogger.log(e);
            }

        }

        return produto;
    }


    public void atualizarQuantidadeEstoque(int id, int novaQuantidade) throws ProdutoException {
        if (buscarProdutoPorId(id) == null) {
            throw new ProdutoException("Produto não encontrado");
        }

        String query = "UPDATE produto SET quantidadeEstoque = ? WHERE id = ?";
        Connection conn = conexao.getConexao();

        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, novaQuantidade);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (SQLException e) {
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

    public void atualizarQuantidadeMinimaEstoque(int id, int novaQuantidade) throws ProdutoException {
        if (buscarProdutoPorId(id) == null) {
            throw new ProdutoException("Produto não encontrado");
        }

        String query = "UPDATE produto SET quantidadeMinimaEstoque = ? WHERE id = ?";
        Connection conn = conexao.getConexao();

        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, novaQuantidade);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (SQLException e) {
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

    public Produto atualizarProduto(int id, Produto produto) throws ProdutoException {
        if (buscarProdutoPorId(id) == null) {
            throw new ProdutoException("Produto não encontrado");
        }

        String query = "UPDATE produto SET nomeProduto = ?, valorVenda = ?, custoUnitario = ?, quantidadeEstoque = ?, quantidadeMinimaEstoque = ? WHERE id = ?;";
        Connection conn = conexao.getConexao();
        Produto p = null;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, produto.getNomeProduto());
            ps.setDouble(2, produto.getValorVenda());
            ps.setDouble(3, produto.getCustoUnitario());
            ps.setInt(4, produto.getQuantidadeEstoque());
            ps.setInt(5, produto.getQuantidadeMinimaEstoque());
            ps.setInt(6, id);

            ps.executeUpdate();

            p = buscarProdutoPorId(id);

        } catch (SQLException e) {
            ExceptionsLogger.log(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                ExceptionsLogger.log(e);
            }
            return p;
        }

    }
}

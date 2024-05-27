package dao;

import exceptions.ProdutoException;
import models.Produto;

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
        String query = "INSERT INTO PRODUTO (nomeProduto, valorVenda, custoUnitario, quantidadeEstoque, quantidadeMinimaEstoque) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = conexao.getConexao();
            ps = conn.prepareStatement(query);
            ps.setString(1, produto.getNomeProduto());
            ps.setDouble(2, produto.getValorVenda());
            ps.setDouble(3, produto.getCustoUnitario());
            ps.setInt(4, produto.getQuantidadeEstoque());
            ps.setInt(5, produto.getQuantidadeMinimaEstoque());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexoes(conn, ps, null);
        }
    }

    public List<Produto> listar() {
        String query = "SELECT * FROM produto";
        List<Produto> produtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = conexao.getConexao();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nomeProduto"),
                        rs.getDouble("valorVenda"),
                        rs.getDouble("custoUnitario"),
                        rs.getInt("quantidadeEstoque"),
                        rs.getInt("quantidadeMinimaEstoque")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexoes(conn, ps, rs);
        }

        return produtos;
    }

    public List<Produto> listarAbaixoEstoqueMinimo() {
        String query = "SELECT * FROM produto WHERE quantidadeEstoque < quantidadeMinimaEstoque";
        List<Produto> produtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = conexao.getConexao();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nomeProduto"),
                        rs.getDouble("valorVenda"),
                        rs.getDouble("custoUnitario"),
                        rs.getInt("quantidadeEstoque"),
                        rs.getInt("quantidadeMinimaEstoque")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexoes(conn, ps, rs);
        }

        return produtos;
    }

    public List<Produto> getTop3MaisVendidos() {
        String query = "SELECT produto.id, produto.nomeProduto, produto.valorVenda, produto.custoUnitario, produto.quantidadeEstoque, produto.quantidadeMinimaEstoque, COUNT(*) as contagem " +
                "FROM produto " +
                "INNER JOIN items_venda AS a ON a.produto_id = produto.id " +
                "INNER JOIN venda AS b ON b.id = a.venda_id " +
                "GROUP BY produto.id, produto.nomeProduto, produto.valorVenda, produto.custoUnitario, produto.quantidadeEstoque, produto.quantidadeMinimaEstoque " +
                "ORDER BY contagem DESC " +
                "LIMIT 3";
        List<Produto> produtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = conexao.getConexao();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nomeProduto"),
                        rs.getDouble("valorVenda"),
                        rs.getDouble("custoUnitario"),
                        rs.getInt("quantidadeEstoque"),
                        rs.getInt("quantidadeMinimaEstoque")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexoes(conn, ps, rs);
        }

        return produtos;
    }

    public Produto buscarProdutoPorId(int id) {
        String query = "SELECT * FROM produto WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Produto produto = null;

        try {
            conn = conexao.getConexao();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nomeProduto"),
                        rs.getDouble("valorVenda"),
                        rs.getDouble("custoUnitario"),
                        rs.getInt("quantidadeEstoque"),
                        rs.getInt("quantidadeMinimaEstoque")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexoes(conn, ps, rs);
        }

        return produto;
    }

    public void atualizarQuantidadeEstoque(int id, int novaQuantidade) throws ProdutoException {
        if (buscarProdutoPorId(id) == null) {
            throw new ProdutoException("Produto não encontrado");
        }

        String query = "UPDATE produto SET quantidadeEstoque = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = conexao.getConexao();
            ps = conn.prepareStatement(query);
            ps.setInt(1, novaQuantidade);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexoes(conn, ps, null);
        }
    }

    public void atualizarQuantidadeMinimaEstoque(int id, int novaQuantidade) throws ProdutoException {
        if (buscarProdutoPorId(id) == null) {
            throw new ProdutoException("Produto não encontrado");
        }

        String query = "UPDATE produto SET quantidadeMinimaEstoque = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = conexao.getConexao();
            ps = conn.prepareStatement(query);
            ps.setInt(1, novaQuantidade);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexoes(conn, ps, null);
        }
    }

    public Produto atualizarProduto(int id, Produto produto) throws ProdutoException {
        if (buscarProdutoPorId(id) == null) {
            throw new ProdutoException("Produto não encontrado");
        }

        String query = "UPDATE produto SET nomeProduto = ?, valorVenda = ?, custoUnitario = ?, quantidadeEstoque = ?, quantidadeMinimaEstoque = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        Produto p = null;

        try {
            conn = conexao.getConexao();
            ps = conn.prepareStatement(query);
            ps.setString(1, produto.getNomeProduto());
            ps.setDouble(2, produto.getValorVenda());
            ps.setDouble(3, produto.getCustoUnitario());
            ps.setInt(4, produto.getQuantidadeEstoque());
            ps.setInt(5, produto.getQuantidadeMinimaEstoque());
            ps.setInt(6, id);
            ps.executeUpdate();

            p = buscarProdutoPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexoes(conn, ps, null);
        }

        return p;
    }

    private void fecharConexoes(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

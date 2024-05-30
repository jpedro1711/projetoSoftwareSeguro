package tests;

import dao.ProdutoDAO;
import exceptions.ProdutoException;
import models.Produto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProdutoDaoTests {

    @Test
    public void buscarProdutoPorId_deve_retornar_null_quando_nao_existe_produto_com_o_id(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        int id = 999;

        Produto result = produtoDAO.buscarProdutoPorId(id);

        assertNull(result);
    }

    @Test
    public void buscarProdutoPorId_deve_retornar_produto_quando_existe_produto_com_id() {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        int id = 1;

        Produto result = produtoDAO.buscarProdutoPorId(id);

        assertNotNull(result);
        assertEquals("Produto Exemplo", result.getNomeProduto());
    }

    @Test
    public void listarProdutos_deve_retornar_lista_de_Produtos() {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        List<Produto> produtos = produtoDAO.listar();

        assertTrue(produtos.size() > 0);
    }

    @Test
    public void getTOP3maisVendidos_deve_retornar_lista_com_3_produtos() {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        List<Produto> produtos = produtoDAO.getTop3MaisVendidos();

        assertTrue(produtos.size() == 3);
    }

    @Test
    public void atualizar_Quantidade_Em_Estoque_Deve_atualizar_Quantidade() throws ProdutoException {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        Produto produto = produtoDAO.buscarProdutoPorId(1);

        int quantidadeAntiga = produto.getQuantidadeEstoque();

        int novaQuantidade = 4231;

        produtoDAO.atualizarQuantidadeEstoque(produto.getProdutoId(), novaQuantidade);

        produto = produtoDAO.buscarProdutoPorId(1);

        assertTrue(produto.getQuantidadeEstoque() == novaQuantidade);
    }
}

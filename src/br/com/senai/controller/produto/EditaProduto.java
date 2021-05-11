package br.com.senai.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class EditaProduto {

	ListaProduto listaProduto = new ListaProduto();
	Scanner entrada = new Scanner(System.in);
	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;
		
		if (produtos.size() <= 0) {
			System.out.println("Não possui produtos para serem editados");
			return null;
		}
		
		listaProduto.listarProdutos();
		
		System.out.println("--- EDITAR DADOS DE PRODUTO ---");
		System.out.print("Informe o Id do produto: ");
		idDoProduto =  entrada.nextInt() - 1;
		if(idDoProduto >= produtos.size()) {
			System.out.println("Este produto não existe");
			return null;
		}

		System.out.println("--- Campos ---");
		System.out.println("1) Nome do produto\n2) Preço unitário\n3) Quantidade");
		System.out.print("Informe o campo que deseja editar: ");
		indexDoCampo = entrada.nextInt();
		
		switch(indexDoCampo) {
		case 1:
			editarNomeDoProduto(produtos, produto, idDoProduto);
			break;
		case 2:
			editarPrecoDoProduto(produtos, produto, idDoProduto);
			break;
		case 3:
			editarQuantidadeDoProduto(produtos, produto, idDoProduto);
			break;
		default:
			System.out.println("Opção inválida");
			break;
			}
		return produto;
	}
	private ProdutoModel editarNomeDoProduto(List<ProdutoModel> produtos, ProdutoModel produto, int idDoProduto) {
		System.out.print("Informe o novo nome para o produto: ");
		produto.setNomeDoProduto(entrada.next());
		
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
		
		produtos.set(idDoProduto, produto);
		return produto;
	}
	private ProdutoModel editarQuantidadeDoProduto(List<ProdutoModel> produtos, ProdutoModel produto, int idDoProduto) {
		System.out.print("Informe a nova quantidade para o produto: ");
		produto.setQuantidadeDeProduto(entrada.nextInt());
		
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());
		
		produtos.set(idDoProduto, produto);
		return produto;
	}
	private ProdutoModel editarPrecoDoProduto(List<ProdutoModel> produtos, ProdutoModel produto, int idDoProduto) {
		System.out.print("Informe o novo preço para o produto: ");
		produto.setPrecoDoProduto(entrada.nextDouble());
		
		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getQuantidadeDeProduto() * produto.getPrecoDoProduto());
		
		produtos.set(idDoProduto, produto);
		return produto;
	}
	public List<ProdutoModel> atualizarQuantidadeEValorTotal(List<ProdutoModel> produtos, int quantidade, int idDoProduto ) {
		ProdutoModel produto = new ProdutoModel();
		produto.setQuantidadeDeProduto(
				produtos.get(idDoProduto).getQuantidadeDeProduto() - quantidade
				);
		produto.setSaldoEmEstoque(
				produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto()
				);
		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		produtos.set(idDoProduto, produto);
		
		return produtos;
	}
}

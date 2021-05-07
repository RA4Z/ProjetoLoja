package br.com.senai.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class AdicionaItemNoCarrinho {
	Scanner entrada = new Scanner(System.in);
	ListaProduto listaProduto = new ListaProduto();

	public CarrinhoModel cadastrarItemNoCarrinho(List<ProdutoModel> produtos) {
		CarrinhoModel carrinhoModel = new CarrinhoModel();
		if (produtos.size() <= 0) {
			System.out.println("Não há produtos cadastrados.");
			return null;
		}

		listaProduto.listarProdutos(produtos);
		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.print("Informe o ID do produto: ");
		carrinhoModel.setIdDoProduto(entrada.nextInt() - 1);
		int idDoProduto = carrinhoModel.getIdDoProduto();

		if (carrinhoModel.getIdDoProduto() > produtos.size()) {
			System.out.println("Esse produto não está cadastrado.");
			return null;
		}
		System.out.println("Informe a quantidade desejada: ");
		carrinhoModel.setQuantidadeDeItensNoCarrinho(entrada.nextInt());

		if (carrinhoModel.getQuantidadeDeItensNoCarrinho() > produtos.get(idDoProduto).getQuantidadeDeProduto()) {
			System.out.println("este produto não possui toda essa quantidade.");
			return null;
		}

		carrinhoModel.setProdutoModel(produtos.get(idDoProduto));
		carrinhoModel.setValorTotalPorItem(
				carrinhoModel.getQuantidadeDeItensNoCarrinho() * produtos.get(idDoProduto).getPrecoDoProduto());
		produtos.get(idDoProduto).setQuantidadeDeProduto(
				produtos.get(idDoProduto).getQuantidadeDeProduto() - carrinhoModel.getQuantidadeDeItensNoCarrinho());

		return carrinhoModel;
	}
}

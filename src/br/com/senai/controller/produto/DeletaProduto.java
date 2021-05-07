package br.com.senai.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class DeletaProduto {
	ListaProduto listaProduto = new ListaProduto();
	Scanner entrada = new Scanner(System.in);
	public void removerProduto(List<ProdutoModel> produtos) {
		System.out.println("--- REMOVER PRODUTO ---");
		if (produtos.size() <= 0) {
			System.out.println("Não possui produtos para serem removidos.");
			return;
		}
		listaProduto.listarProdutos(produtos);
		System.out.println("Informe o ID do produto a ser removido: ");
		int idDoProduto = entrada.nextInt() - 1;
		
		if(idDoProduto > produtos.size()) {
			System.out.println("Este produto não foi cadastrado.");
			return;
		}
		
		produtos.remove(idDoProduto);
	}
}

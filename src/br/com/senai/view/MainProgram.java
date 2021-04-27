package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.controller.ProdutoController;
import br.com.senai.model.ProdutoModel;

public class MainProgram {
	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();

		ProdutoController produtoController = new ProdutoController();

		boolean sair = false;

		do {
			produtoController.menu();
			int opc = produtoController.opcao();

			switch (opc) {
			case 1:
				produtos.add(produtoController.cadastrarProduto());
				break;
			case 2:
				produtoController.consultarProdutos(produtos);
				break;
			case 3:
				produtoController.editarProduto(produtos);
				break;
			case 4:
				produtoController.removerProduto(produtos);
				break;
			case 5:
				produtoController.adicionar(produtos);
				break;
			case 6:
				produtoController.mostrar(produtos);
				break;
			case 9:
				sair = true;
				break;

			default:
				System.out.println("Opção inválida!!!");
				break;
			}
		} while (!sair);

		System.out.println("Sistema encerrado!!!");
	}
}

package br.com.senai.controller.produto;

import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class CadastraProduto {
	Scanner entrada = new Scanner(System.in);
	
	public ProdutoModel cadastrarProduto() {
		ProdutoModel produtoModel = new ProdutoModel();

		System.out.println("\n--- CADASTRAR ITENS ---\n");
		System.out.print("Produto: ");
		produtoModel.setNomeDoProduto(entrada.next());
		System.out.print("Preço: ");
		produtoModel.setPrecoDoProduto(entrada.nextDouble());
		System.out.print("Quantidade:");
		produtoModel.setQuantidadeDeProduto(entrada.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());
		
		return produtoModel;
	}
}

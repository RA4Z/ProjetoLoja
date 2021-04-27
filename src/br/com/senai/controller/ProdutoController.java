package br.com.senai.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;
import hakunamatata.Atributos;

public class ProdutoController {
	ArrayList<Carrinho> carrinho = new ArrayList<Carrinho>();

	private Scanner sc;
	public ProdutoController() {
		sc = new Scanner(System.in);
	}

	public int opcao() {
		System.out.print("> ");
		return sc.nextInt();
	}

	public void menu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover item");
		System.out.println("5) Adicionar ao carrinho");
		System.out.println("6) Visualizar o carrinho");
		System.out.println("9) Sair do sistema");
		System.out.println("--------------------");
	}

	public ProdutoModel cadastrarProduto() {
		ProdutoModel produtoModel = new ProdutoModel();

		System.out.println("\n--- CADASTRAR ITENS ---\n");
		System.out.print("Produto: ");
		produtoModel.setNomeDoProduto(sc.next());
		System.out.print("Preço: ");
		produtoModel.setPrecoDoProduto(sc.nextDouble());
		System.out.print("Quantidade:");
		produtoModel.setQuantidadeDeProduto(sc.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());

		return produtoModel;
	}

	public List<ProdutoModel> consultarProdutos(List<ProdutoModel> produtos) {
		System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto ", "Preço", "Qtd", "R$ Total");
		//
		// for (ProdutoModel produtoModel : produtos) {
		// System.out.printf(" | %10s | %8s | %4s | %9s
		// |\n",produtoModel.getNomdeDoProduto(),
		// produtoModel.getPrecoDoProduto(),produtoModel.getQuantidadeDeProduto(),
		// produtoModel.getSaldoEmEstoque());
		//
		// }

		// produtos.forEach(produto -> {
		// int id = 1;
		// System.out.printf("| %10s | R$%6.2f | %4s | %9s |\n",
		// id,
		// produto.getNomeDoProduto(),
		// produto.getPrecoDoProduto(),
		// produto.getQuantidadeDeProduto(),
		// produto.getSaldoEmEstoque());
		// id++;
		// });

		for (int i = 0; i < produtos.size(); i++) {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | %9s |\n", i + 1, produtos.get(i).getNomeDoProduto(),
					produtos.get(i).getPrecoDoProduto(), produtos.get(i).getQuantidadeDeProduto(),
					produtos.get(i).getSaldoEmEstoque());
		}

		return produtos;
	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;

		if (produtos.size() > 0) {

			System.out.println("--- EDITAR DADOS DE PRODUTO ---");
			System.out.print("Informe o ID do produto: ");
			idDoProduto = sc.nextInt() - 1;

			if (idDoProduto >= produtos.size()) {
				System.out.println("Esse Produto não existe!!");
				return null;
			}
			System.out.println("--- CAMPOS ---");
			System.out.println("1 - Nome do Produto;");
			System.out.println("2 - Preço Unitário;");
			System.out.println("3 - Quantidade;");
			System.out.print("Informe o campo que deseja editar: ");
			indexDoCampo = sc.nextInt();

			switch (indexDoCampo) {
			case 1:
				produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
				produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
				produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());

				System.out.print("Informe o novo nome para o produto: ");
				produto.setNomeDoProduto(sc.next());

				produtos.set(idDoProduto, produto);

				break;

			case 2:
				produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
				produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());

				System.out.print("Informe o novo preço para o produto: ");
				produto.setPrecoDoProduto(sc.nextDouble());

				produto.setSaldoEmEstoque(
						produtos.get(idDoProduto).getQuantidadeDeProduto() * produto.getPrecoDoProduto());

				produtos.set(idDoProduto, produto);

				break;

			case 3:
				produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
				produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());

				System.out.print("Informe a nova quantidade para o produto: ");
				produto.setQuantidadeDeProduto(sc.nextInt());

				produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
				produto.setSaldoEmEstoque(
						produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());

				produtos.set(idDoProduto, produto);

				break;

			default:
				System.out.println("Opção inválida!!!");
				break;
			}
		} else {
			System.out.println("Não possui produtos pra serem editados.");
		}
		return produto;
	}
	public void removerProduto(List<ProdutoModel> produtos) {
		System.out.println("--- REMOVER PRODUTO ---");
		if(produtos.size()<=0) {
			System.out.println("Não possui produtos para serem removidos.");
			return;
		}
		listarProdutos(produtos);
		System.out.print("Informe o ID do produto a ser removido:");
		int idDoProduto = sc.nextInt();
		if(idDoProduto > produtos.size()) {
			System.out.println("Este produto não foi cadastrado.");
			return;
		}
		produtos.remove(idDoProduto - 1);
	}
	// for(int i = 0;i < produtos.size(); i++) {
	// System.out.printf("| %10s | %8s | %4s | %9s |\n",
	// produtos.get(i).getNomdeDoProduto(),
	// produtos.get(i).getPrecoDoProduto(),
	// produtos.get(i).getQuantidadeDeProduto(),
	// produtos.get(i).getSaldoEmEstoque());
	// }

	private void listarProdutos(List<ProdutoModel> produtos) {
		
	}
	
	public void adicionar(List<ProdutoModel> produtos) {
		int id,quantidade=0;
		System.out.print("Insira o ID do produto que deseja comprar: ");
		id = sc.nextInt();
		if(id >= produtos.size()) {
			System.out.println("\nEsse produto não existe!!");
			return;
		}else {
			System.out.print("\nInsira a quantidade desejada: ");
			quantidade = sc.nextInt();
			carrinho.add(new Carrinho(id,quantidade));
		}		
	}
	public void mostrar(List<ProdutoModel> produtos) {
		for (int i = 0; i < produtos.size(); i++) {
			if(carrinho.get(i))
		}
	}
	public int obterConta(int indice) {	
		for(int i = 0; i < carrinho.size(); i++) {
			if(carrinho.get(i).getId() == indice) {
				return i;
			}
		}
		return -1;
	}
}
package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.controller.Controller;
import br.com.senai.controller.carrinho.AdicionaItemNoCarrinho;
import br.com.senai.controller.carrinho.DevolveItemDoCarrinho;
import br.com.senai.controller.carrinho.ListaCarrinho;
import br.com.senai.controller.cliente.AdicionaCliente;
import br.com.senai.controller.cliente.MostraHistorico;
import br.com.senai.controller.produto.CadastraProduto;
import br.com.senai.controller.produto.DeletaProduto;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.ProdutoModel;
import br.com.senai.model.CarrinhoModel;

public class MainProgram {
	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<CarrinhoModel> itensNoCarrinho = new ArrayList<CarrinhoModel>();

		
		Controller produtoController = new Controller();
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		AdicionaItemNoCarrinho adicionaItemNoCarrinho = new AdicionaItemNoCarrinho();
		CadastraProduto cadastraProduto = new CadastraProduto();
		ListaProduto listaProduto = new ListaProduto();
		EditaProduto editaProduto = new EditaProduto();
		DeletaProduto deletaProduto = new DeletaProduto();
		AdicionaCliente adicionaCliente = new AdicionaCliente();
		DevolveItemDoCarrinho devolveItem = new DevolveItemDoCarrinho();
		MostraHistorico mostraHistorico = new MostraHistorico();
		
		boolean sair = false;
		listaCarrinho.resetar();
		String cliente = adicionaCliente.definirCliente();
		do {
			produtoController.menu();
			int opc = produtoController.opcao();

			switch (opc) {
			case 0:
				mostraHistorico.mostrarHistorico();
				break;
			case 1:
				produtos.add(cadastraProduto.cadastrarProduto());
				break;
			case 2:
				listaProduto.listarProdutos();
				break;
			case 3:
				editaProduto.editarProduto();
				break;
			case 4:
				deletaProduto.removerProduto();
				break;
			case 5:
				itensNoCarrinho.add(adicionaItemNoCarrinho.cadastrarItemNoCarrinho());
				break;
			case 6:
				listaCarrinho.listarItensNoCarrinho();
				break;
			case 7:
				listaCarrinho.gerarCupom(cliente);
				break;
			case 8:
				devolveItem.devolverItemDoCarrinho();
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

package br.com.senai.controller;

import java.util.Scanner;
import br.com.senai.controller.produto.ListaProduto;

public class Controller {
	ListaProduto listaProduto = new ListaProduto();

	private Scanner entrada;

	public Controller() {
		entrada = new Scanner(System.in);
	}

	public int opcao() {
		System.out.print("> ");
		return entrada.nextInt();
	}
	

	public void menu() {
		System.out.println("\n------ MENU ------");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover item");
		System.out.println("5) Adicionar ao carrinho");
		System.out.println("6) Listar Itens no carrinho");
		System.out.println("7) Gerar cupom");
		System.out.println("8) Devolver itens");
		System.out.println("9) Sair do sistema");
		System.out.println("--------------------");
	}
	
}


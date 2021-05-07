package br.com.senai.controller.cliente;

import java.util.Scanner;

public class AdicionaCliente {
	Scanner entrada = new Scanner(System.in);

	public String definirCliente() {
		String nome;
		System.out.println("Informe o nome do cliente: ");
		nome = entrada.next();
		return nome;
	}

}

package br.com.senai.controller;

import br.com.senai.model.ProdutoModel;

public class Carrinho extends ProdutoModel{
	int id,quantidade;
	
	public Carrinho(int id, int quantidade) {
	this.id = id;
	this.quantidade = quantidade;
 }
}

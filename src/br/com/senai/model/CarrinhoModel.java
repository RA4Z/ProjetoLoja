package br.com.senai.model;

public class CarrinhoModel {
	private int quantidadeDeItensNoCarrinho;
	private int idDoProduto;
	private ProdutoModel produtoModel;
	private double valorTotalPorItem;

	public CarrinhoModel() {
	}

	public CarrinhoModel(int quantidadeDeItensNoCarrinho, int idDoProduto, ProdutoModel produtoModel,
			double valorTotalNoCarrinho) {
		super();
		this.quantidadeDeItensNoCarrinho = quantidadeDeItensNoCarrinho;
		this.idDoProduto = idDoProduto;
		this.produtoModel = produtoModel;
		this.valorTotalPorItem = valorTotalNoCarrinho;
	}

	public int getQuantidadeDeItensNoCarrinho() {
		return quantidadeDeItensNoCarrinho;
	}

	public void setQuantidadeDeItensNoCarrinho(int quantidadeDeItensNoCarrinho) {
		this.quantidadeDeItensNoCarrinho = quantidadeDeItensNoCarrinho;
	}

	public int getIdDoProduto() {
		return idDoProduto;
	}

	public void setIdDoProduto(int idDoProduto) {
		this.idDoProduto = idDoProduto;
	}

	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}

	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}

	public double getValorTotalPorItem() {
		return valorTotalPorItem;
	}

	public void setValorTotalPorItem(double valorTotalPorItem) {
		this.valorTotalPorItem = valorTotalPorItem;
	}

}

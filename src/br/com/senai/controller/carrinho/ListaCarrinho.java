package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.CarrinhoModel;

public class ListaCarrinho {

	private Connection connection;
	
	public ListaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ResultSet listarProdutos() {

		PreparedStatement preparedStatement;
		try {
			String sql = "select * from carrinho order by idDoProduto";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Não possui dados cadastrados.");
				return null;
			}
			
			System.out.println("\n----------- PRODUTOS CADASTRADOS -----------\n");
			System.out.printf("| %2s | %15s | %4s | %9s |\n", "ID", "Produto", "Qtd", "R$ Total");

			
			resultSet.previous();
			
			while (resultSet.next()) {
				System.out.printf("| %2s | %15s | %4s | %9s |\n", resultSet.getInt("idDoProduto"),
						resultSet.getString("nomeDoProduto"),resultSet.getInt("quantidadeDoProduto"), 
						resultSet.getDouble("valorTotal"));
			}
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	public List<CarrinhoModel> listarItensNoCarrinho(List<CarrinhoModel> itensNoCarrinho) {
		System.out.println("--- ITENS NO CARRINHO ---");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n",
				"ID", "Produto", "Preço", "Qtd", "R$ Total");
		
		if(itensNoCarrinho.size() <= 0 ) {
		return null;	
		}
		
		itensNoCarrinho.forEach(item -> {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n",
					item.getIdDoProduto(),
					item.getProdutoModel().getNomeDoProduto(),
					item.getProdutoModel().getPrecoDoProduto(),
					item.getQuantidadeDeItensNoCarrinho(),
					item.getValorTotalPorItem()
					);
		});
		
		double valorTotalDoCarrinho = itensNoCarrinho.stream()
				.mapToDouble(CarrinhoModel::getValorTotalPorItem).sum();
		System.out.println("Valor Total: R$" + valorTotalDoCarrinho);
		
		
		return itensNoCarrinho;
	}
	public void gerarCupom(List<CarrinhoModel> itensNoCarrinho, String cliente) {
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		if(itensNoCarrinho.size()<= 0) {
			System.out.println("Lista vazia.");
			return;
		}
		listaCarrinho.listarItensNoCarrinho(itensNoCarrinho);
		System.out.println("Cliente: " + cliente);
	}
}

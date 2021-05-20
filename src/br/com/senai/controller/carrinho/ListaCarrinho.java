package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class ListaCarrinho {

	private Connection connection;
	
	public ListaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ResultSet listarItensNoCarrinho() {
		PreparedStatement preparedStatement;
		try {
			String sql = "select * from carrinho order by idDoProduto;";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Não possui produtos no carrinho.");
				return null;
			}
			
			System.out.println("\n----------- PRODUTOS NO CARRINHO -----------\n");
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

	//=========================================================================================================
	
	public ResultSet gerarCupom(String cliente) {
		PreparedStatement preparedStatement;		
		
		try {
			String sql = "select * from carrinho order by idDoProduto";

			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			if(!resultSet.next()) {
				System.out.println("Não possui produtos no carrinho.");
				return null;
			} 			
					
			sql = "insert into historico values (" + 
					"	(?)," + 
					"    (select sum(quantidadeDoProduto) as quantidadeTotal from carrinho)," + 
					"	(select sum(valorTotal) as valorTotal from carrinho)" + 
					");";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cliente);
			
			preparedStatement.execute();
			
			listarItensNoCarrinho();
			System.out.println("Cliente: " + cliente);
			
			sql = "DELETE FROM carrinho;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			
			return resultSet;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}

package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.CarrinhoModel;

public class DevolveItemDoCarrinho {
	Scanner entrada = new Scanner(System.in);
	private Connection connection;
	
	public DevolveItemDoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public CarrinhoModel devolverItemDoCarrinho() {
		CarrinhoModel carrinhoModel = new CarrinhoModel();
		PreparedStatement preparedStatement;
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		int quantidade = 0;
		try {
			if (listaCarrinho.listarItensNoCarrinho() == null) {
				return null;
			}
			System.out.println("Qual o ID do item a ser devolvido? ");
			int id = entrada.nextInt();
			
			String sql = "SELECT * FROM carrinho WHERE idDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Esse produto não está no carrinho!");
				return null;
			} else {
				System.out.println("Informe a quantidade desejada: ");
				quantidade = entrada.nextInt();

				if (quantidade > resultSet.getInt("quantidadeDoProduto")) {
					System.out.println("Este produto não possui toda essa quantidade.");
					return null;
				}
			}
			
			sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			double preco = resultSet.getDouble("precoDoProduto");
			
			sql = "UPDATE carrinho SET quantidadeDoProduto = ?, saldoEmEstoque = ? "
					+ " WHERE idDoProduto = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, resultSet.getInt("quantidadeDoProduto") - quantidade);
			preparedStatement.setDouble(2, (resultSet.getInt("quantidadeDoProduto") - quantidade) * preco);
			preparedStatement.setInt(3, id);
			preparedStatement.execute();	
			
			return carrinhoModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

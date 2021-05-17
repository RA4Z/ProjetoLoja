package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;

public class AdicionaItemNoCarrinho {
	Scanner entrada = new Scanner(System.in);
	private ListaProduto listaProduto;
	private Connection connection;

	public AdicionaItemNoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	@SuppressWarnings("all")
	public CarrinhoModel cadastrarItemNoCarrinho() {
		PreparedStatement preparedStatement;
		double preco = 0;
		listaProduto = new ListaProduto();	
		
		CarrinhoModel carrinhoModel = new CarrinhoModel();
		
		if (listaProduto.listarProdutos() == null) {
			return null;
		}
				
		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.print("Informe o ID do produto: ");
		
		carrinhoModel.setIdDoProduto(entrada.nextInt());
		int idDoProduto = carrinhoModel.getIdDoProduto();

		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			
			ResultSet resultSet = preparedStatement.executeQuery();
		
			if(!resultSet.next()) {
				System.out.println("Esse produto não foi cadastrado.");
				return null;
			} else {
				System.out.println("Informe a quantidade desejada: ");
				carrinhoModel.setQuantidadeDeItensNoCarrinho(entrada.nextInt());

				if (carrinhoModel.getQuantidadeDeItensNoCarrinho() > resultSet.getInt("quantidadeDoProduto")) {
					System.out.println("Este produto não possui toda essa quantidade.");
					return null;
				}
			}
				carrinhoModel.setIdDoProduto(idDoProduto);
				carrinhoModel.setValorTotalPorItem(
						carrinhoModel.getQuantidadeDeItensNoCarrinho() * resultSet.getDouble("precoDoProduto"));
				
				preco = resultSet.getDouble("precoDoProduto");
				double tudo = (resultSet.getInt("quantidadeDoProduto") 
						- carrinhoModel.getQuantidadeDeItensNoCarrinho()) * resultSet.getDouble("precoDoProduto");
				
				sql = "UPDATE produto SET quantidadeDoProduto = ?, saldoEmEstoque = ? "
						+ " WHERE codigoDoProduto = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, resultSet.getInt("quantidadeDoProduto") - carrinhoModel.getQuantidadeDeItensNoCarrinho());
				preparedStatement.setDouble(2, tudo);
				preparedStatement.setInt(3, idDoProduto);
				
				preparedStatement.execute();
				resultSet.previous();
				
				// ======================================================================================================			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} try {
			String sql = "SELECT * FROM carrinho WHERE idDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			preparedStatement.execute();
			if(!resultSet.next()) {
				sql = "INSERT INTO carrinho (idDoProduto, quantidadeDoProduto, valorTotal)"
						+ " VALUES (?, ?, ?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, idDoProduto);
				preparedStatement.setInt(2, carrinhoModel.getQuantidadeDeItensNoCarrinho());
				preparedStatement.setDouble(3, carrinhoModel.getQuantidadeDeItensNoCarrinho() * preco);
				preparedStatement.execute();
				return carrinhoModel;
			} else {
				sql = "UPDATE carrinho SET idDoProduto = ?, quantidadeDoProduto = ?, valorTotal = ? ";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, idDoProduto);
				preparedStatement.setInt(2, resultSet.getInt("quantidadeDoProduto") + carrinhoModel.getQuantidadeDeItensNoCarrinho());
				preparedStatement.setDouble(3, (resultSet.getInt("quantidadeDoProduto") + carrinhoModel.getQuantidadeDeItensNoCarrinho()) * preco);
				preparedStatement.execute();
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return carrinhoModel;
	}
}

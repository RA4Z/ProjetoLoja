package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class MostraHistorico {

	private Connection connection;

	public MostraHistorico() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	public ResultSet mostrarHistorico(int tipo, String cliente) {
		PreparedStatement preparedStatement;
		try {
			String sql = "select * from historico order by nomeDoCliente;";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("N?o possui hist?rico de clientes");
				return null;
			}
			if(tipo == 1) {
			System.out.println("\n----------- HIST?RICO DE CLIENTES -----------\n");
			System.out.printf("| %15s | %4s | %9s |\n", "Nome", "Qtd", "R$ Total");
			
			resultSet.previous();
			
			while (resultSet.next()) {
				System.out.printf("| %15s | %4s | %9s |\n",resultSet.getString("nomeDoCliente"),
						resultSet.getInt("quantidadeDoProduto"), 
						resultSet.getDouble("valorTotal"));
			}
			}else {
				sql = "select * from historico where nomeDoCliente = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, cliente);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				preparedStatement.execute();
				System.out.printf("| %15s | %4s | %9s |\n", "Nome", "Qtd", "R$ Total");
				System.out.printf("| %15s | %4s | %9s |\n",resultSet.getString("nomeDoCliente"),
						resultSet.getInt("quantidadeDoProduto"), 
						resultSet.getDouble("valorTotal"));
			}
			
			sql = "DELETE FROM historico";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
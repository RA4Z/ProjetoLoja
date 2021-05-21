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
	public ResultSet mostrarHistorico() {
		PreparedStatement preparedStatement;
		try {
			String sql = "select * from historico order by nomeDoCliente;";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Não possui histórico de clientes");
				return null;
			}
			
			System.out.println("\n----------- HISTÓRICO DE CLIENTES -----------\n");
			System.out.printf("| %15s | %4s | %9s |\n", "Nome", "Qtd", "R$ Total");
			
			resultSet.previous();
			
			while (resultSet.next()) {
				System.out.printf("| %15s | %4s | %9s |\n",resultSet.getString("nomeDoCliente"),
						resultSet.getInt("quantidadeDoProduto"), 
						resultSet.getDouble("valorTotal"));
			}
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}

package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class EditaProduto {

	private Scanner entrada = new Scanner(System.in);
	private ListaProduto listaProduto;
	private ProdutoModel produto;
	private Connection connection;

	public EditaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ProdutoModel editarProduto() {
		PreparedStatement preparedStatement;
		
		produto = new ProdutoModel();
		listaProduto = new ListaProduto();		
		
		int idDoProduto, indexDoCampo;

		if (listaProduto.listarProdutos() == null) {
			return null;
		}

		System.out.println("--- EDITAR DADOS DE PRODUTO ---");
		System.out.print("Informe o Id do produto: ");
		idDoProduto = entrada.nextInt();
		
		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Esse produto não existe.");
				return null;
			} else {
				produto.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
				produto.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				produto.setQuantidadeDeProduto(resultSet.getInt("quantidadeDoProduto"));
			}
			resultSet.previous();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		System.out.println("--- Campos ---");
		System.out.println("1) Nome do produto\n2) Preço unitário\n3) Quantidade");
		System.out.print("Informe o campo que deseja editar: ");
		indexDoCampo = entrada.nextInt();

		switch (indexDoCampo) {
		case 1:
			editarNomeDoProduto(idDoProduto);
			break;
		case 2:
			editarPrecoDoProduto(idDoProduto);
			break;
		case 3:
			editarQuantidadeDoProduto(idDoProduto);
			break;
		default:
			System.out.println("Opção inválida");
			break;
		}
		return produto;
	}

	private ProdutoModel editarNomeDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.print("Informe o novo nome para o produto: ");
		produto.setNomeDoProduto(entrada.next());
		
		try {
			String sql = "UPDATE produto SET nomeDoProduto = ?"
					   + " WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, produto.getNomeDoProduto());
			preparedStatement.setInt(2, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		return produto;
	}

	private ProdutoModel editarPrecoDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.print("Informe o novo preço para o produto: ");
		
		produto.setPrecoDoProduto(entrada.nextDouble());
		
		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());

		try {
			String sql = "UPDATE produto SET precoDoProduto = ?, saldoEmEstoque = ? "
					+ " WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, produto.getPrecoDoProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
		return produto;
	}

	private ProdutoModel editarQuantidadeDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.print("Informe a nova quantidade para o produto: ");
		produto.setQuantidadeDeProduto(entrada.nextInt());

		produto.setSaldoEmEstoque(produto.getQuantidadeDeProduto() * produto.getPrecoDoProduto());
		
		try {
			String sql = "UPDATE produto SET quantidadeDoProduto = ?, saldoEmEstoque = ? "
					+ " WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, produto.getQuantidadeDeProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return produto;
	}

	public List<ProdutoModel> atualizarQuantidadeEValorTotal(List<ProdutoModel> produtos, int quantidade,
			int idDoProduto) {
		ProdutoModel produto = new ProdutoModel();
		produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto() - quantidade);
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());
		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		produtos.set(idDoProduto, produto);

		return produtos;
	}
}

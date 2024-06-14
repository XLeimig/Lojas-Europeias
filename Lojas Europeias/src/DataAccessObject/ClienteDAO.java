package DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Cliente;
import Utilitarios.ConexaoBanco;

public class ClienteDAO {
	
	public List<Cliente> Listar(){
		String sql = "SELECT * FROM pessoas WHERE cargo IS NULL";
		List<Cliente> clientes = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		ResultSet rset = null;
		
		try {
			conn = ConexaoBanco.conectar();
			pgstmt = conn.prepareStatement(sql);
			rset = pgstmt.executeQuery();
			
			while(rset.next()) {
				String id = rset.getString("id"), nome = rset.getString("nome");
				String email = rset.getString("email"), cpf = rset.getString("cpf");
				String endereco = rset.getString("endereco"), numeroCasa = rset.getString("numero_casa");
				String cep = rset.getString("cep"), senha = rset.getString("senha");
				int idade = rset.getInt("idade");
				
				Cliente cliente = new Cliente(nome, email, cpf, idade, senha, endereco, cep, numeroCasa);
				cliente.setId(id);
				
				clientes.add(cliente);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pgstmt != null) {
					pgstmt.close();
				}
				if(rset != null) {
					pgstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return clientes;
	}
	
	public Cliente ListarPorCpf (String cpf) {
		String sql = "SELECT * FROM pessoas WHERE cpf ILIKE ? AND cargo IS NULL";
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		ResultSet rset = null;
		
		try {
			conn = ConexaoBanco.conectar();
			pgstmt = conn.prepareStatement(sql);
			pgstmt.setString(1, cpf);
			rset = pgstmt.executeQuery();
			
			if(rset.next()) {
				String id = rset.getString("id"), nome = rset.getString("nome");
				String email = rset.getString("email"), cpfCliente = rset.getString("cpf");
				String endereco = rset.getString("endereco"), cep = rset.getString("cep");
				String numeroCasa = rset.getString("numero_casa"), senha = rset.getString("senha");
				int idade = rset.getInt("idade");
				
				Cliente cliente = new Cliente(nome, email, cpfCliente, idade, senha, endereco, cep, numeroCasa);
				cliente.setId(id);
				
				return cliente;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pgstmt != null) {
					pgstmt.close();
				}
				if(rset != null) {
					pgstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void Salvar(Cliente cliente) {
		String sql = "INSERT INTO pessoas (id, nome, email, cpf, idade, senha, endereco, cep, numero_casa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		
		try {
			conn = ConexaoBanco.conectar();
			pgstmt = conn.prepareStatement(sql);
			
			pgstmt.setString(1, cliente.getId());
			pgstmt.setString(2, cliente.getNome());
			pgstmt.setString(3, cliente.getEmail());
			pgstmt.setString(4, cliente.getCpf());
			pgstmt.setInt(5, cliente.getIdade());
			pgstmt.setString(6, cliente.getSenha());
			pgstmt.setString(7, cliente.getEndereco());
			pgstmt.setString(8, cliente.getCep());
			pgstmt.setString(9, cliente.getNumeroCasa());
			
			pgstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Atualizar (Cliente cliente) {
		String sql = "UPDATE pessoas SET nome = ?, email = ?, cpf = ?, idade = ?, senha = ?, endereco = ?, cep = ?, numero_casa = ? " +
					 "WHERE id ILIKE ?";
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		
		try {
			conn = ConexaoBanco.conectar();
			pgstmt = conn.prepareStatement(sql);
			
			if(cliente != null) {
				pgstmt.setString(1, cliente.getNome());
				pgstmt.setString(2, cliente.getEmail());
				pgstmt.setString(3, cliente.getCpf());
				pgstmt.setInt(4, cliente.getIdade());
				pgstmt.setString(5, cliente.getSenha());
				pgstmt.setString(6, cliente.getEndereco());
				pgstmt.setString(7, cliente.getCep());
				pgstmt.setString(8, cliente.getNumeroCasa());
				pgstmt.setString(9, cliente.getId());
			
				int arrowsAffected = pgstmt.executeUpdate();
				if(arrowsAffected == 0) {
					System.err.println("Nenhum registro atualizado.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pgstmt != null) {
					pgstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void Deletar(Cliente cliente) {
		String sql = "DELETE FROM pessoas WHERE id ILIKE ? AND cargo != 'gerente' AND cargo != 'vendedor'";
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		
		try {
			conn = ConexaoBanco.conectar();
			pgstmt = conn.prepareStatement(sql);
			
			if(cliente != null) {
				pgstmt.setString(1, cliente.getId());
			
				pgstmt.execute();
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pgstmt != null) {
					pgstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

package DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Pedido;
import Utilitarios.ConexaoBanco;

public class PedidoDAO {
	
	public List<Pedido> Listar(){
		String sql = "SELECT * FROM pedidos";
		List<Pedido> pedidos = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		ResultSet rset = null;
		
		try {
			conn = ConexaoBanco.conectar();
			pgstmt = conn.prepareStatement(sql);
			rset = pgstmt.executeQuery();
			
			while(rset.next()) {
				String id = rset.getString("id"), pessoaId = rset.getString("pessoa_id");
				String descricao = rset.getString("descricao");
				
				Pedido pedido = new Pedido(pessoaId, descricao);
				pedido.setId(id);
				
				pedidos.add(pedido);
				
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
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return pedidos;
	}
	
	public List<Pedido> ListarPorCliente(String clienteId) {
		String sql = "SELECT * FROM pedidos WHERE pessoa_id = ?";
		List<Pedido> pedidosCliente = new ArrayList<>(); 
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		ResultSet rset = null;
		
		try {
			conn = ConexaoBanco.conectar();
			pgstmt = conn.prepareStatement(sql);
			pgstmt.setString(1, clienteId);
			rset = pgstmt.executeQuery();
			
			while(rset.next()) {
				String id = rset.getString("id"), pessoaId = rset.getString("pessoa_id");
				String descricao = rset.getString("descricao");
				
				Pedido pedido = new Pedido(pessoaId, descricao);
				pedido.setId(id);
				pedidosCliente.add(pedido);			
			}
			return pedidosCliente;	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(rset != null) {
					pgstmt.close();
				}
				if(pgstmt != null) {
					pgstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void Salvar(Pedido pedido) {
		String sql = "INSERT INTO pedidos (id, pessoa_id, descricao, valor_total) VALUES (?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		
		try {
			conn = ConexaoBanco.conectar();
			pgstmt = conn.prepareStatement(sql);
			
			if(pedido != null) {
				pgstmt.setString(1, pedido.getId());
				pgstmt.setString(2, pedido.getPessoaId());
				pgstmt.setString(3, pedido.getDescricao());
				pgstmt.setDouble(4, pedido.getValorTotal());
			
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
	
	public void Atualizar(Pedido pedido) {
		String sql = "UPDATE pedidos SET pessoa_id = ?, descricao = ?, valor_total = ? " +
					 "WHERE id ILIKE ?";
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		
		try {
			conn = ConexaoBanco.conectar();
			pgstmt = conn.prepareStatement(sql);
			
			if(pedido != null) {
				pgstmt.setString(1, pedido.getPessoaId());
				pgstmt.setString(2, pedido.getDescricao());
				pgstmt.setDouble(3, pedido.getValorTotal());
				pgstmt.setString(4, pedido.getId());
			}
			int arrowsAffected = pgstmt.executeUpdate();
			if(arrowsAffected == 0) {
				System.err.println("Nenhum pedido foi atualizado.");
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
	
	public void Deletar(Pedido pedido) {
		String sql = "DELETE FROM pedidos WHERE id ILIKE ?";
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		
		try {
			conn = ConexaoBanco.conectar();
			pgstmt = conn.prepareStatement(sql);
			
			if(pedido != null) {
				pgstmt.setString(1, pedido.getId());
				
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

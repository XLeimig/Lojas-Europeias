package DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.PedidoProduto;
import Utilitarios.ConexaoBanco;

public class PedidoProdutoDAO {
	
	public List<PedidoProduto> Listar(){
		String sql = "SELECT * FROM pedido_produtos";
		List<PedidoProduto> pedidoProdutos = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		ResultSet rset = null;
		
		try {
			conn = ConexaoBanco.conectar();
			pgstmt = conn.prepareStatement(sql);
			rset = pgstmt.executeQuery();
			
			while(rset.next()) {
				String id = rset.getString("id"), pedidoId = rset.getString("pedido_id");
				String produtoId = rset.getString("produto_id");
				int quantidadeProduto = rset.getInt("quantidade_produto");
				double valorProduto = rset.getDouble("valor_produto");
				
				PedidoProduto produto = new PedidoProduto(pedidoId, produtoId, quantidadeProduto, valorProduto);
				produto.setId(id);
				
				pedidoProdutos.add(produto);
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
		return pedidoProdutos;
	}
	
	public List<PedidoProduto> ListarPorPedido(String pedido){
		String sql = "SELECT * FROM pedido_produtos WHERE pedido_id = ?";
		List<PedidoProduto> produtosPorPedido = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		ResultSet rset = null;
		
		try {
			if(pedido != null) {
				conn = ConexaoBanco.conectar();
				pgstmt = conn.prepareStatement(sql);
				pgstmt.setString(1, pedido);
				rset = pgstmt.executeQuery();
			
				while(rset.next()) {
					String id = rset.getString("id"), pedidoId = rset.getString("pedido_id");
					String produtoId = rset.getString("produto_id");
					int quantidadeProduto = rset.getInt("quantidade_produto");
					double valorProduto = rset.getDouble("valor_produto");
				
					PedidoProduto produto = new PedidoProduto(pedidoId, produtoId, quantidadeProduto, valorProduto);
					produto.setId(id);
					produtosPorPedido.add(produto);
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
				if(rset != null) {
					pgstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return produtosPorPedido;
	}

	public void Salvar(PedidoProduto pedidoProduto) {
		String sql = "INSERT INTO pedido_produtos (id, pedido_id, produto_id, quantidade_produto, valor_produto)" + 
					 "VALUES (?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		
		try {
			if(pedidoProduto != null) {
				conn = ConexaoBanco.conectar();
				pgstmt = conn.prepareStatement(sql);
			
				pgstmt.setString(1, pedidoProduto.getId());
				pgstmt.setString(2, pedidoProduto.getPedidoId());
				pgstmt.setString(3, pedidoProduto.getProdutoId());
				pgstmt.setInt(4, pedidoProduto.getQuantidadeProduto());
				pgstmt.setDouble(5, pedidoProduto.getValorProduto());
			
				pgstmt.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Atualizar(PedidoProduto pedidoProduto) {
		String sql = "UPDATE pedido_produtos SET pedido_id = ?, produto_id = ?, quantidade_produto = ?, valor_produto = ? " + 
					 "WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		
		try {
			if(pedidoProduto != null) {
				conn = ConexaoBanco.conectar();
				pgstmt = conn.prepareStatement(sql);
			
				pgstmt.setString(1, pedidoProduto.getPedidoId());
				pgstmt.setString(2, pedidoProduto.getProdutoId());
				pgstmt.setInt(3, pedidoProduto.getQuantidadeProduto());
				pgstmt.setDouble(4, pedidoProduto.getValorProduto());
				pgstmt.setString(5, pedidoProduto.getId());
			
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
	
	public void Deletar(PedidoProduto pedidoProduto) {
		String sql = "DELETE FROM pedido_produtos WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pgstmt = null;
		
		try {
			if(pedidoProduto != null) {
				conn = ConexaoBanco.conectar();
				pgstmt = conn.prepareStatement(sql);
				
				pgstmt.setString(1, pedidoProduto.getId());
				
				pgstmt.execute();
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

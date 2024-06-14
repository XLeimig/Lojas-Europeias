package Aplicacao;

import DataAccessObject.GerenteDAO;
import DataAccessObject.PedidoDAO;
import DataAccessObject.PedidoProdutoDAO;
import DataAccessObject.ProdutoDAO;
import DataAccessObject.VendedorDAO;
import DataAccessObject.ClienteDAO;
import Model.Gerente;
import Model.Pedido;
import Model.PedidoProduto;
import Model.Pessoa;
import Model.Produto;
import Model.Vendedor;
import Model.Cliente;

public class Main {

	public static void main(String[] args) {
		Pessoa gerente = new Gerente("gerenteNome", "gerenteEmail", "777", 44, "gerenteSenha", "gerente", 2111.54, 124.92);
		Pessoa func = new Vendedor("felix", "felixemail", "999", 27, "55555", "vendedor", 1414.0);
		Produto produto = new Produto("Air Fryer Mondial", "Forno elétrico multifunções Mondial", 300, 899.00, "Eletrônicos");
		PedidoDAO pedidoDao = new PedidoDAO();
		PedidoProdutoDAO pedidoProdutoDao = new PedidoProdutoDAO();
		VendedorDAO vendedorDao = new VendedorDAO();
		GerenteDAO gerenteDao = new GerenteDAO();
		ProdutoDAO produtoDao = new ProdutoDAO();
		ClienteDAO clienteDao = new ClienteDAO();
		
		/*cadastrar pedido
		Cliente pessoaId = clienteDao.ListarPorCpf("06142024123");
		Pedido pedido = new Pedido(pessoaId.getId(), "teste mavericks", 469.74);
		pedidoDao.Salvar(pedido);
		*/
		
		/*Cadastrar pedidoprodutos = id(auto), pedidoid(pedido), produtoid(produto), quantidade, valor//
		Cliente cliente = clienteDao.ListarPorCpf("06142024123");
		
		if(cliente != null) {
			String clienteId = cliente.getId();
		
			Pedido pedidoInsert = new Pedido(clienteId, "segundo teste");
			pedidoDao.Salvar(pedidoInsert);
			String ultimoPedidoId = pedidoInsert.getId();
		
			Produto produto1 = produtoDao.ListarPorNome("helicoptero");
			Produto produto2 = produtoDao.ListarPorNome("basketball");
			String produto1Id = produto1.getId();
			String produto2Id = produto2.getId();
		
			PedidoProduto pedidoProduto1 = new PedidoProduto(ultimoPedidoId, produto1Id, 2, produto1.getValor());
			PedidoProduto pedidoProduto2 = new PedidoProduto(ultimoPedidoId, produto2Id, 4, produto2.getValor());
			pedidoProdutoDao.Salvar(pedidoProduto1);
			pedidoProdutoDao.Salvar(pedidoProduto2);
		
			double valorTotalProduto1 = pedidoProduto1.getValorProduto() * pedidoProduto1.getQuantidadeProduto();
			double valorTotalProduto2 = pedidoProduto2.getValorProduto() * pedidoProduto2.getQuantidadeProduto();
			double valorTotalPedido = valorTotalProduto1 + valorTotalProduto2;
			pedidoInsert.setValor_total(valorTotalPedido);
			pedidoDao.Atualizar(pedidoInsert);
		}
		*/

		/*listarTodos
		for(int i = 0; i < vendedorDao.Listar().size(); i++) {
			Vendedor vendedores = vendedorDao.Listar().get(i);
			System.out.println(vendedores);
		}
		for(int i = 0; i < produtoDao.Listar().size(); i++) {
			Produto produtos = produtoDao.Listar().get(i); //apenas para merito de teste **RETIRAR
			System.out.println(produtos);
		}
		for(int i = 0; i < gerenteDao.Listar().size(); i++) {
			Gerente gerentes = gerenteDao.Listar().get(i);
			System.out.println(gerentes);
		}*/
		
		/*for(int i = 0; i < clienteDao.Listar().size(); i++) {
			Cliente clientes = clienteDao.Listar().get(i);
			System.out.println(clientes);
		}
		for(int i = 0; i < pedidoDao.Listar().size(); i++) {
			Pedido pedidos = pedidoDao.Listar().get(i);
			System.out.println(pedidos);
		}*/
		
		/*listar todos os pedidoProdutos
		for(int i = 0; i < pedidoProdutoDao.Listar().size(); i ++) {
			PedidoProduto pedidoProdutos = pedidoProdutoDao.Listar().get(i);
			System.out.println(pedidoProdutos);
		}
		*/
		
		/*listar todos os produtos por pedido
		Cliente cliente = clienteDao.ListarPorCpf("06142024123");
		String clienteId = cliente.getId();
		Pedido pedido = pedidoDao.ListarPorCliente(clienteId).getLast();
		
		for(int i = 0; i < pedidoProdutoDao.ListarPorPedido(pedido.getId()).size(); i++) {
			PedidoProduto produtos = pedidoProdutoDao.ListarPorPedido(pedido.getId()).get(i);
			
			System.out.println(produtos);
		}
		*/
		
		/* listar todos os pedidos do cliente
		Cliente cliente = clienteDao.ListarPorCpf("06142024123");
		String clienteId = cliente.getId();
		
		for(int i = 0; i < pedidoDao.ListarPorCliente(clienteId).size(); i++) {
			Pedido pedidosCliente = pedidoDao.ListarPorCliente(clienteId).get(i);
			System.out.println(pedidosCliente);
		}
		*/
		
		
		/*update
		Vendedor vendedorUpdate = vendedorDao.ListarPorCpf("654");
		vendedorUpdate.setSalario(1414.00);
		vendedorUpdate.setNome("Nikola Jokic");
		vendedorDao.Atualizar(vendedorUpdate);
		
		Gerente gerenteUpdate = gerenteDao.ListarPorCpf("123");
		gerenteUpdate.setCargo("gerente");
		gerenteUpdate.setSalario(2111.54);
		gerenteDao.Atualizar(gerenteUpdate);

		Produto produtoUpdate = produtoDao.ListarPorNome("produtoNome");
		produtoUpdate.setNome("Basketball");
		produtoDao.Atualizar(produtoUpdate);
		
		Cliente clienteUpdate = clienteDao.ListarPorCpf("5695");
		clienteUpdate.setNome("Primeiro cliente");
		clienteUpdate.setEmail("emailDoCliente");
		clienteUpdate.setEndereco("Endereco um");
		clienteUpdate.setCep("Cep um");
		clienteUpdate.setNumeroCasa("89");
		clienteUpdate.setSenha("senhaCliente");
		clienteDao.Atualizar(clienteUpdate);
		*/
		
		/* atualizando pedido
		Cliente cliente = clienteDao.ListarPorCpf("06142024123");
		String clienteId = cliente.getId();
		
		Pedido ultimoPedido = (Pedido) pedidoDao.ListarPorCliente(clienteId).getLast();
		ultimoPedido.setDescricao("luka e irving contra boston");
		pedidoDao.Atualizar(ultimoPedido);
		
		System.out.println(ultimoPedido);
		*/
		
		/*atualizando pedido_produtos
		Cliente cliente = clienteDao.ListarPorCpf("06142024123");
		String clienteId = cliente.getId();
		
		Pedido pedido = pedidoDao.ListarPorCliente(clienteId).getLast();
		String pedidoId = pedido.getId();
		System.out.println(pedido);
		
		//.get(itemQueSeraAlterado) --esperar entrada do usuario
		PedidoProduto pedidoProduto1 = pedidoProdutoDao.ListarPorPedido(pedidoId).get(1);
		PedidoProduto pedidoProduto2 = pedidoProdutoDao.ListarPorPedido(pedidoId).get(0); 
		pedidoProduto1.setQuantidadeProduto(3);
		pedidoProduto2.setQuantidadeProduto(3);
		
		double valorTotalProd1 = pedidoProduto1.getValorProduto() * pedidoProduto1.getQuantidadeProduto();
		double valorTotalProd2 = pedidoProduto2.getValorProduto() * pedidoProduto2.getQuantidadeProduto();
		double valorTotalPedido = valorTotalProd1 + valorTotalProd2;
		pedido.setValor_total(valorTotalPedido);
		
		pedidoDao.Atualizar(pedido);
		pedidoProdutoDao.Atualizar(pedidoProduto1);
		pedidoProdutoDao.Atualizar(pedidoProduto2);
		*/
		
		/*delete
		Cliente clienteDelete = clienteDao.ListarPorCpf("5695");
		clienteDao.Deletar(clienteDelete);
		
		Produto produtoDelete = produtoDao.ListarPorNome("novoNome");
		produtoDao.Deletar(produtoDelete);
		
		Vendedor vendedorDelete = vendedorDao.ListarPorCpf("999");
		vendedorDao.Deletar(vendedorDelete);
		
		Gerente gerenteDelete = gerenteDao.ListarPorCpf("123");
		gerenteDao.Deletar(gerenteDelete);
		*/
		
		/*deletando ultimo pedido realizado
		Cliente cliente = clienteDao.ListarPorCpf("06142024123");
		String clienteId = cliente.getId();
		
		Pedido pedidoDelete = pedidoDao.ListarPorCliente(clienteId).getLast();
		pedidoDao.Deletar(pedidoDelete);
		*/
		
		/*deletando ultimo produto adicionado
		Cliente cliente = clienteDao.ListarPorCpf("06142024123");
		String clienteId = cliente.getId();
		
		Pedido pedido = pedidoDao.ListarPorCliente(clienteId).getLast();
		String pedidoId = pedido.getId();
		
		//.get(itemQueSeraExcluido) --esperar entrada do usuario
		PedidoProduto pedidoProduto = pedidoProdutoDao.ListarPorPedido(pedidoId).get(1);
		pedidoProdutoDao.Deletar(pedidoProduto);
		
		//TODO codigo pra atualizar valor total do pedido
		*/
		
		//System.out.println(vendedorDao.ListarPorCpf("999"));
		//System.out.println(gerenteDao.ListarPorCpf("123"));
		//System.out.println(produtoDao.ListarPorNome("novoNome"));
		//System.out.println(clienteDao.ListarPorCpf("5695"));
		
	}

}
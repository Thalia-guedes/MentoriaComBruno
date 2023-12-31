package org.example;

import org.example.modelo.Cliente;
import org.example.modelo.Produto;
import org.example.modelo.Vendedor;
import org.example.regras.CadastroCliente;
import org.example.regras.CadastroVendedor;
import org.example.repositorio.ClienteDB;
import org.example.repositorio.DBConnection;
import org.example.repositorio.ProdutoDB;
import org.example.repositorio.VendedorDB;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CadastroCliente cadastroCliente = new CadastroCliente(new ClienteDB(DBConnection.getConnection()));
        VendedorDB vendedorDB = new VendedorDB(DBConnection.getConnection());
        CadastroVendedor cadastroVendedor = new CadastroVendedor(vendedorDB);
        ProdutoDB produtoDB = new ProdutoDB(DBConnection.getConnection());
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("Menu do Banco de Dados:");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Listar Todos os Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Deletar Cliente");
            System.out.println("5. Buscar Cliente por CPF");
            System.out.println("6. Adicionar Vendedor");
            System.out.println("7. Listar Todos os Vendedores");
            System.out.println("8. Atualizar Vendedor");
            System.out.println("9. Deletar Vendedor");
            System.out.println("10. Buscar Vendedor por CPF");
            System.out.println("11. Adicionar Produto");
            System.out.println("12. Listar todos os produtos");
            System.out.println("13. Deletar produto por Id");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("Digite o nome: ");
                    String nomeCliente = scanner.next();
                    System.out.println("Digite o cpf: ");
                    String cpfCliente = scanner.next();
                    System.out.println("Digite o email: ");
                    String emailCliente = scanner.next();
                    Cliente adicionarCliente = new Cliente(nomeCliente,  emailCliente, cpfCliente);
                    cadastroCliente.adicionar(adicionarCliente);
                    break;
                case 2:
                    List<Cliente> clientes = cadastroCliente.listarTodos();
                    for (Cliente cliente : clientes) {
                        System.out.println(cliente);
                    }
                    break;
                case 3:
                    System.out.println("Digite o nome: ");
                    String nomeNovo = scanner.next();
                    System.out.println("Digite o cpf: ");
                    String cpfNovo = scanner.next();
                    System.out.println("Digite o email: ");
                    String emailNovo = scanner.next();
                    Cliente clienteNovo = new Cliente(nomeNovo, cpfNovo, emailNovo);
                    cadastroCliente.atualizar(clienteNovo);
                    System.out.println(clienteNovo);
                    break;
                case 4:
                    System.out.println("Digite o cpf para remover: ");
                    String cpfRemover = scanner.next();
                    cadastroCliente.remover(cpfRemover);
                    break;
                case 5:
                    System.out.println("Digite o cpf que deseja buscar: ");
                    String cpfBuscar = scanner.next();
                    cadastroCliente.buscarPorcpf(cpfBuscar);
                    break;
                case 6:
                    System.out.println("Digite o nome: ");
                    String nomeVendedor = scanner.next();
                    System.out.println("Digite o cpf: ");
                    String cpfVendedor = scanner.next();
                    System.out.println("Digite o email: ");
                    String emailVendedor = scanner.next();
                    Vendedor adicionarVendedor = new Vendedor(nomeVendedor, emailVendedor, cpfVendedor);
                    cadastroVendedor.adicionar(adicionarVendedor);
                    break;
                case 7:
                    List<Vendedor> vendedores = cadastroVendedor.listarTodos();
                    for (Vendedor vendedor : vendedores) {
                        System.out.println(vendedor);
                    }
                    break;
                case 8:
                    System.out.println("Digite o nome: ");
                    String nomeNovoVendedor = scanner.next();
                    System.out.println("Digite o cpf: ");
                    String cpfNovoVendedor = scanner.next();
                    System.out.println("Digite o email: ");
                    String emailNovoVendedor = scanner.next();
                    Vendedor vendedorNovo = new Vendedor(nomeNovoVendedor, emailNovoVendedor, cpfNovoVendedor);
                    cadastroVendedor.atualizar(vendedorNovo);
                    System.out.println(vendedorNovo);
                    break;
                case 9:
                    System.out.println("Digite o cpf para remover: ");
                    String removerCPFVendedor = scanner.next();
                    cadastroVendedor.remover(removerCPFVendedor);
                    break;
                case 10:
                System.out.println("Digite o cpf que deseja buscar: ");
                String cpfBuscarVendedor= scanner.next();
                cadastroVendedor.buscarPorcpf(cpfBuscarVendedor);
                break;
                case 11:
                    System.out.println("Digite o nome do produto: ");
                    String nomeProduto = scanner.next();
                    System.out.println("Digite o preco do produto: ");
                    Double preco = scanner.nextDouble();
                    System.out.println("Digite a quantidade do produto: ");
                    int quantidade = scanner.nextInt();
                    Double total = preco * quantidade;
                    System.out.println("O valor total da compra é: " +total);
                    Produto produto = new Produto(nomeProduto, preco, quantidade, total);
                    produtoDB.cadastrarProduto(produto);
                    break;
                case 12:
                    List<Produto> produtos = produtoDB.listarTodasAsVendas();
                    for (Produto produto1 : produtos) {
                        System.out.println(produto1);
                    }
                    break;
                case 13:
                    System.out.println("Digite o id do produto: ");
                    int id = scanner.nextInt();
                    produtoDB.deletar(id);
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}

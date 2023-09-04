package org.example;

import org.example.modelo.Cliente;
import org.example.regras.CadastroCliente;
import org.example.repositorio.ClienteDB;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CadastroCliente cadastroCliente = new CadastroCliente();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome: ");
        String nome = scanner.next();

        System.out.println("Digite Cpf: ");
        String cpf = scanner.next();

        System.out.println("Digite seu email: ");
        String email = scanner.next();
        cadastroCliente.adicionar(new Cliente(nome, email, cpf));

        List<Cliente> clientes = cadastroCliente.listarTodos();

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

        System.out.println("Digite o cpf que deseja remover: ");
        String cpfCliente = scanner.next();

        cadastroCliente.remover(cpfCliente);

        System.out.println("Digite o novo nome: ");
        String novoNome = scanner.next();
        System.out.println("Digite o email: ");
        String emailNovo = scanner.next();
        System.out.println("Digite o id : ");
        int idCliente = scanner.nextInt();

        System.out.println("Digite o cpf que deseja atualizar: ");
        String cpfAtualizado = scanner.next();

        Cliente novoCliente = new Cliente(idCliente, novoNome, emailNovo, cpfAtualizado);
       //System.out.println(novoCliente);
        cadastroCliente.atualizar(novoCliente);
    }
}

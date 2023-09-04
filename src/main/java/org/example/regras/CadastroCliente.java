package org.example.regras;

import org.example.modelo.Cliente;
import org.example.repositorio.ClienteDB;
import org.example.repositorio.DBConnection;

import java.util.ArrayList;
import java.util.List;

public class CadastroCliente {
    private final ClienteDB clienteDB;

    public CadastroCliente() {
        clienteDB = new ClienteDB(DBConnection.getConnection());
    }

    public List<Cliente> listarTodos() {
        return clienteDB.listarTodos();
    }

    public Cliente adicionar(Cliente cliente) {
        if (!cliente.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email sem @.");
        }
        Cliente clienteComCpf = clienteDB.buscarPorCpf(cliente.getCpf());
        if (clienteComCpf != null) {
            throw new IllegalArgumentException("CPF ja existente");
        }
        Cliente emailValido = validarEmail(cliente.getEmail());
        if (emailValido != null) {
            throw new IllegalArgumentException("Email ja cadastrado");
        }
        clienteDB.salvar(cliente);
        return cliente;
    }

    public Cliente remover(String cpf) {
        Cliente cliente = clienteDB.buscarPorCpf(cpf);
        if (cliente != null) {
            clienteDB.deletar(cliente.getId());
        }
        return cliente;
    }


    public Cliente atualizar(Cliente cliente) {
        Cliente clienteAtualizado = clienteDB.atualizar(cliente);
        return clienteAtualizado;
    }
    public Cliente validarEmail(String email) {
        for (Cliente cliente : clienteDB.listarTodos()) {
            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null;
    }
}

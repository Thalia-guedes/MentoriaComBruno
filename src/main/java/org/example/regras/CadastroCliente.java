package org.example.regras;

import org.example.modelo.Cliente;
import org.example.repositorio.ClienteDB;
import org.example.repositorio.DBConnection;

import java.util.ArrayList;
import java.util.List;

public class CadastroCliente {
    private final ClienteDB clienteDB;
    private List<Cliente> clientes = new ArrayList<>();

    public CadastroCliente() {
        clienteDB = new ClienteDB(DBConnection.getConnection());
    }

    public List<Cliente> listarTodos(){
        return clienteDB.listarTodos();
    }

    public Cliente pesquisarPorCpf(String cpf){
        for (Cliente cliente : clientes) {
            if (cliente.possuiCpf(cpf)) {
                return cliente;
            }
        }
        return null;
    }
    public Cliente adicionar(Cliente cliente){
        if (!cliente.getEmail().contains("@")){
            throw new IllegalArgumentException("Email sem @.");
        }
        Cliente clienteComCpf = pesquisarPorCpf(cliente.getCpf());
        if (clienteComCpf != null){
            throw new IllegalArgumentException("CPF ja existente");
        }
        Cliente emailValido = validarEmail(cliente.getEmail());
        if (emailValido != null){
            throw new IllegalArgumentException("Email ja cadastrado");
        }
        clienteDB.salvar(cliente);
        return cliente;
    }

    public Cliente remover(String cpf){
        Cliente cliente = pesquisarPorCpf(cpf);
        if (cliente != null){
            clientes.remove(cliente);
        }
        return cliente;
    }

    public Cliente atualizar(Cliente cliente){
        remover(cliente.getCpf());
        return adicionar(cliente);
    }

    public Cliente validarEmail(String email){
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)){
                return cliente;
            }
        }
        return null;
    }
}

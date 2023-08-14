package org.example.regras;

import org.example.modelo.Cliente;

import java.util.List;

public class CadastroCliente {
    private List<Cliente> clientes;
    public List<Cliente> listarTodos(){
        return clientes;
    }
    public Cliente pesquisarPorCpf(String cpf){
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
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
        clientes.add(cliente);
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
}

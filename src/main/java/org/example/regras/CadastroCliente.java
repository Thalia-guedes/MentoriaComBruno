package org.example.regras;

import org.example.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class CadastroCliente {
    private List<Cliente> clientes = new ArrayList<>();
    public List<Cliente> listarTodos(){
        return clientes;
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

    public Cliente validarEmail(String email){
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)){
                return cliente;
            }
        }
        return null;
    }
}

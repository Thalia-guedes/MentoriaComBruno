package org.example.regras;

import org.example.modelo.Cliente;

import java.util.List;

public class CadastroCliente {
    private List<Cliente> clientes;
    public List<Cliente> listarTodos(){
        return clientes;
    }
    public Cliente pesquisarPorCpf(String cpf){
        int tamanho = clientes.size();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
}

package org.example.regras;

import org.example.modelo.Vendedor;
import java.util.List;

public class CadastroVendedor {
    private List<Vendedor> vendedores;

    public List<Vendedor> listarTodos() {
        return vendedores;
    }
    public Vendedor pesquisarPorEmail(String email) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getEmail().equals(email)) {
                return vendedor;
            }
        }
        return null;
    }
    public Vendedor adicionar(Vendedor vendedor) {
        if (!vendedor.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email precisa conter @!");
        }
        Vendedor emailRepetido = pesquisarPorEmail(vendedor.getEmail());
        if (emailRepetido != null) {
            throw new IllegalArgumentException("Email ja cadastrado");
        }
        Vendedor vendedorCpf = pegarCpf(vendedor.getCpf());
        if (vendedorCpf != null) {
            throw new IllegalArgumentException("CPF ja cadastrado");
        }
        vendedores.add(vendedor);
        return vendedor;
    }

    public Vendedor pegarCpf(String cpf) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getCpf().equals(cpf)) {
                return vendedor;
            }
        }
        return null;
    }
}

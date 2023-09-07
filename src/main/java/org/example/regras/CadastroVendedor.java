package org.example.regras;

import org.example.modelo.Cliente;
import org.example.modelo.Vendedor;
import org.example.repositorio.ClienteDB;
import org.example.repositorio.DBConnection;
import org.example.repositorio.VendedorDB;

import java.util.List;

public class CadastroVendedor {
    private final VendedorDB vendedorDB;

    public CadastroVendedor(VendedorDB vendedorDB) {
        this.vendedorDB = vendedorDB;
    }

    public List<Vendedor> listarTodos() {
        return vendedorDB.listarTodos();
    }
    public Vendedor buscarPorcpf(String cpf){
        Vendedor vendedor = vendedorDB.buscarPorCpf(cpf);
        if (vendedor != null){
            vendedorDB.buscarPorCpf(vendedor.getCpf());
        }
        return vendedor;
    }
    public Vendedor adicionar(Vendedor vendedor) {
        if (!vendedor.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email sem @.");
        }
        Vendedor vendedorComCpf = vendedorDB.buscarPorCpf(vendedor.getCpf());
        if (vendedorComCpf != null) {
            throw new IllegalArgumentException("CPF ja existente");
        }
        Vendedor emailValido = validarEmail(vendedor.getEmail());
        if (emailValido != null) {
            throw new IllegalArgumentException("Email ja cadastrado");
        }
        vendedorDB.salvar(vendedor);
        return vendedor;
    }
    public Vendedor remover(String cpf) {
        Vendedor vendedor = vendedorDB.buscarPorCpf(cpf);
        if (vendedor != null) {
            vendedorDB.deletar(vendedor.getId());
        }
        return vendedor;
    }
    public Vendedor atualizar(Vendedor vendedor) {
        Vendedor vendedorAtualizado = vendedorDB.atualizar(vendedor);
        return vendedorAtualizado;
    }
    public Vendedor validarEmail(String email) {
        for (Vendedor vendedor : vendedorDB.listarTodos()) {
            if (vendedor.getEmail().equals(email)) {
                return vendedor;
            }
        }
        return null;
    }

}

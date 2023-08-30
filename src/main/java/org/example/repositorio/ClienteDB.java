package org.example.repositorio;

import org.example.modelo.Cliente;

import java.sql.ClientInfoStatus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDB {

    private final Connection connection;

    public ClienteDB(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Cliente cliente){

        try {
            connection.createStatement().
                    execute(
                            "insert into cliente(nome, cpf, email) values ('"+cliente.getNome()+"', '"+cliente.getCpf()+"', '"+cliente.getEmail()+"')"
                    );
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Cliente> listarTodos(){
        List<Cliente> clientes = new ArrayList<>();
        try {
           ResultSet resultSet = connection.createStatement().executeQuery("select * from cliente");
           while (resultSet.next()){
               Integer id = resultSet.getInt(1);
               String nome = resultSet.getString(2);
               String cpf = resultSet.getString(3);
               String email = resultSet.getString(4);
               Cliente cliente = new Cliente(id, nome, cpf, email);

               clientes.add(cliente);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
}

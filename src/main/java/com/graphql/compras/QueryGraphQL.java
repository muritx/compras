package com.graphql.compras;

//import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class QueryGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private ClienteRepository clienteRep;

    public String hello() {
        return "Hello GraphQL!";
    }

    public int soma(int a, int b) {
        return a+b;
    }

    // public Cliente cliente() {
    //     return new Cliente("Ricardo", "r@gmail.com");
    // }

    public Cliente cliente(Long id) {
        return clienteRep.findById(id).orElse(null);
    }

    public List<Cliente> clientes() {
        return clienteRep.findAll();
    }

    @Transactional
    public Cliente saveCliente(Long id, String nome, String email) {
        Cliente c = new Cliente();
        c.setId(id);
        c.setNome(nome);
        c.setEmail(email);
        return clienteRep.save(c);
    }

    @Transactional
    public Boolean deleteCliente(Long id) {
        if(clienteRep.findById(id).isPresent()) {
            clienteRep.deleteById(id);
            return true;
        }
        return false;
    }

    // public List<Cliente> clientes() {
    //     List<Cliente> list = new ArrayList<>();
    //     for (int i = 0; i < 10; i++) {
    //         list.add(new Cliente("Cliente " + i, "r"+i+"@gmail.com"));
    //     }

    //     return list;

    // }
    
}

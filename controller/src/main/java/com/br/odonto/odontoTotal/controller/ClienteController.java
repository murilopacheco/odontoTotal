package com.br.odonto.odontoTotal.controller;


import com.br.odonto.odontoTotal.dao.ClienteDao;
import com.br.odonto.odontoTotal.dominio.Cliente;
import java.util.List;

/**
 *
 * @author Danillo
 */
public class ClienteController {

    private final ClienteDao dao;

    public ClienteController() {
        dao = new ClienteDao();
    }

    public List<Cliente> consultar(String sqlFiltro, int limite) {
        return dao.consultar(sqlFiltro, limite);
    }

    public List<String> atualizar(Cliente cliente) {
        List<String> inconsistencias = cliente.validar();
        if (inconsistencias.isEmpty()) {
            dao.salvar(cliente);
        }
        return inconsistencias;
    }
    public Cliente buscar(Long id) {
        Cliente cliente = new Cliente();
        cliente = dao.buscar(id);
        return cliente;
    }
    public List<Cliente> pesquisarPorCliente(String cliente) {
        return dao.pesquisarPorCliente(cliente);
    }
}

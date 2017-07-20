package com.br.odonto.odontoTotal.dao;

import com.br.odonto.odontoTotal.dominio.Cliente;
import java.util.List;

/**
 *
 * @author Danillo
 */
public class ClienteDao extends GenericDao<Cliente> implements IEntityDao<Cliente> {

    public List<Cliente> consultar(String sqlFiltro, int limite) {
        StringBuilder sb = new StringBuilder();

        sb.append("select c.* ").
                append("from Cliente c ").
                
                append("where c.`DTYPE` like 'CLI'");
        if (sqlFiltro != null && !sqlFiltro.isEmpty()) {
            sb.append(" and ").append(sqlFiltro);
        }
        return super.pesquisarSQLCustomizada(Cliente.class, sb.toString());
    }

    @Override
    public Cliente buscar(Long id) {
        return super.buscar(Cliente.class, id);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return super.buscarTodos(Cliente.class);
    }

}

package com.br.odonto.odontoTotal.dao;

import com.br.odonto.odontoTotal.dominio.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author Murilo
 */
public class ClienteDao extends GenericDao<Cliente> implements IEntityDao<Cliente> {

    public List<Cliente> consultar(String sqlFiltro, int limite) {
        StringBuilder sb = new StringBuilder();

        sb.append("select c.* ").
                append("from Cliente c ").
                
                append("where c.id > 0");
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

    public List<Cliente> pesquisarPorCliente(String cliente) {
        if(cliente==null || cliente.isEmpty()){
            return buscarTodos();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("select c ").
                append("from Cliente c ").
                append("where c.nome like '").
                append(cliente).
                append("%'");
        EntityManager em = ConnectionFactory.obterManager();
        Query query = em.createQuery(sb.toString());
        return query.getResultList();
    }

}

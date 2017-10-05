package com.br.odonto.odontoTotal.dao;

import com.br.odonto.odontoTotal.dominio.Profissional;
import com.br.odonto.odontoTotal.dominio.Profissional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author Murilo
 */
public class ProfissionalDao extends GenericDao<Profissional> implements IEntityDao<Profissional> {

    public List<Profissional> consultar() {
        StringBuilder sb = new StringBuilder();

        sb.append("select p.* ").
                append("from Profissional p ").
                
                append("where p.id > 0");

        return super.pesquisarSQLCustomizada(Profissional.class, sb.toString());
    }

    @Override
    public Profissional buscar(Long id) {
        return super.buscar(Profissional.class, id);
    }

    @Override
    public List<Profissional> buscarTodos() {
        return super.buscarTodos(Profissional.class);
    }

    public List<Profissional> pesquisarPorCliente(String profissional) {
        if(profissional==null || profissional.isEmpty()){
            return buscarTodos();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("select p ").
                append("from Profissional p ").
                append("where p.nome like '").
                append(profissional).
                append("%'");
        EntityManager em = ConnectionFactory.obterManager();
        Query query = em.createQuery(sb.toString());
        return query.getResultList();
    }

}

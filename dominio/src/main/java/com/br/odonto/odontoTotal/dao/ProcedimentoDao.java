package com.br.odonto.odontoTotal.dao;

import com.br.odonto.odontoTotal.dominio.Procedimento;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author Danillo
 */
public class ProcedimentoDao extends GenericDao<Procedimento> implements IEntityDao<Procedimento> {

    public List<Procedimento> consultar() {
        StringBuilder sb = new StringBuilder();

        sb.append("select con.* ").
                append("from Procedimento pro ").
                
                append("where pro.id > 0");
      
        return super.pesquisarSQLCustomizada(Procedimento.class, sb.toString());
    }

    @Override
    public Procedimento buscar(Long id) {
        return super.buscar(Procedimento.class, id);
    }

    @Override
    public List<Procedimento> buscarTodos() {
        return super.buscarTodos(Procedimento.class);
    }
    
    public List<Procedimento> buscarPorNome(String nome){
           if(nome==null || nome.isEmpty()){
            return buscarTodos();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("select con ").
                append("from Procedimento pro ").
                append("where pro.nome like '").
                append(nome).
                append("%'");
        EntityManager em = ConnectionFactory.obterManager();
        Query query = em.createQuery(sb.toString());
        return query.getResultList();
    }

    public List<Procedimento> pesquisarPorProcedimento(String procedimento) {
        if(procedimento==null || procedimento.isEmpty()){
            return buscarTodos();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("select p ").
                append("from Procedimento p ").
                append("where p.nome like '").
                append(procedimento).
                append("%'");
        EntityManager em = ConnectionFactory.obterManager();
        Query query = em.createQuery(sb.toString());
        return query.getResultList();
    }

}

package com.br.odonto.odontoTotal.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Murilo
 * @param <T>
 */
public class GenericDao<T> implements IDao<T> {

    @Override
    public T salvar(T entidade) {
        EntityManager em = ConnectionFactory.obterManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(entidade);
            tx.commit();
            return entidade;
        } catch (Exception e) {
            System.out.println("Não foi possível salvar entidade :" + entidade.getClass().toString());
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, "Não foi possível salvar entidade ", e);
            return null;
        } finally {
            em.close();
        }

    }

    @Override
    public void remover(T entidade) {
        EntityManager em = ConnectionFactory.obterManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(entidade);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Não foi possível remover entidade :" + entidade.getClass().toString());
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, "Não foi possível remover entidade ", e);
        } finally {
            em.close();
        }
    }

    @Override
    public T buscar(Class klass, Long id) {
        EntityManager em = ConnectionFactory.obterManager();
        try {
            return (T) em.find(klass, id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar entidade :" + klass.toString());
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, "Erro ao buscar entidade! ", e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> buscarTodos(Class klass
    ) {
        EntityManager em = ConnectionFactory.obterManager();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select e from ").
                    append(klass.getSimpleName()).
                    append(" e");
            TypedQuery query = em.createQuery(sb.toString(), klass);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar todos da entidade :" + klass.toString());
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, "Erro ao buscar todos da entidade! ", e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> filtrarPorUnicoAtributo(Class klass, String filtroChaveValor
    ) {
        EntityManager em = ConnectionFactory.obterManager();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select e from ").
                    append(klass.getSimpleName()).
                    append(" e where e.").
                    append(filtroChaveValor);
            TypedQuery query = em.createQuery(sb.toString(), klass);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao executar filtro na entidade :" + klass.toString());
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, "Erro ao executar filtro na entidade! ", e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> pesquisarJPQLCustomizada(Class klass, String jpql
    ) {
        EntityManager em = ConnectionFactory.obterManager();
        try {
            TypedQuery<T> query = em.createQuery(jpql, klass);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao executar pesquisa JPQL customizada na entidade :" + klass.toString());
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, "Erro ao executar pesquisa JPQL customizada na entidade! ", e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> pesquisarSQLCustomizada(Class klass, String sql
    ) {
        EntityManager em = ConnectionFactory.obterManager();
        try {
            Query query = em.createNativeQuery(sql, klass);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao executar pesquisa SQL customizada na entidade :" + klass.toString());
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, "Erro ao executar pesquisa SQL customizada na entidade! ", e);
            return null;
        } finally {
            em.close();
        }
    }

}

package com.br.odonto.odontoTotal.dao;

import java.util.List;

/**
 *
 * @author Murilo
 * @param <T>
 */
public interface IDao <T>{

    /**
     * 
     * @param entidade
     * @return 
     */
    public T salvar(T entidade);
    
    /**
     * 
     * @param entidade 
     */
    public void remover(T entidade);
    
    /**
     * 
     * @param klass
     * @param id
     * @return 
     */
    public T buscar(Class klass, Long id);
    
    /**
     * 
     * @param klass
     * @return 
     */
    public List<T> buscarTodos(Class klass);
    
    /**
     * 
     * @param klass
     * @param filtroChaveValor: representa um atributo com respectivo valor, 
     * ex.: "nomeOficial like 'ARTUR%'"
     * @return 
     */
    public List<T> filtrarPorUnicoAtributo(Class klass, String filtroChaveValor);

    /**
     *
     * @param klass
     * @param jpql: 
     * @return
     */
    public List<T> pesquisarJPQLCustomizada(Class klass, String jpql);
    
    /**
     * 
     * @param klass
     * @param sql
     * @return 
     */
    public List<T> pesquisarSQLCustomizada(Class klass, String sql);
}

package com.br.odonto.odontoTotal.dao;

import java.util.List;

/**
 *
 * @author Murilo
 * @param <T>
 */
public interface IEntityDao<T> {

    /**
     *
     * @param id
     * @return
     */
    public T buscar(Long id);

    /**
     *
     * @return
     */
    public List<T> buscarTodos();
    
}

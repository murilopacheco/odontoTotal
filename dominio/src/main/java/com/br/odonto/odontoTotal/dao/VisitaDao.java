package com.br.odonto.odontoTotal.dao;

import com.br.odonto.odontoTotal.dominio.Visita;

import java.util.List;

/**
 *
 * @author Murilo
 */
public class VisitaDao extends GenericDao<Visita> implements IEntityDao<Visita> {

    public List<Visita> consultar(String sqlFiltro, int limite) {
        StringBuilder sb = new StringBuilder();

        sb.append("select v.* ").
                append("from Visita v ").
                
                append("where v.id > 0");
        if (sqlFiltro != null && !sqlFiltro.isEmpty()) {
            sb.append(" and ").append(sqlFiltro);
        }
        return super.pesquisarSQLCustomizada(Visita.class, sb.toString());
    }

    @Override
    public Visita buscar(Long id) {
        return super.buscar(Visita.class, id);
    }

    @Override
    public List<Visita> buscarTodos() {
        return super.buscarTodos(Visita.class);
    }

}

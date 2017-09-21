package com.br.odonto.odontoTotal.dao;

import com.br.odonto.odontoTotal.dominio.ItensVisita;
import com.br.odonto.odontoTotal.dominio.ItensVisita;

import java.util.List;

/**
 *
 * @author Murilo
 */
public class ItensVisitaDao extends GenericDao<ItensVisita> implements IEntityDao<ItensVisita> {

    public List<ItensVisita> consultar(String sqlFiltro, int limite) {
        StringBuilder sb = new StringBuilder();

        sb.append("select iv.* ").
                append("from ItensVisita iv ").
                
                append("where iv.id > 0");
        if (sqlFiltro != null && !sqlFiltro.isEmpty()) {
            sb.append(" and ").append(sqlFiltro);
        }
        return super.pesquisarSQLCustomizada(ItensVisita.class, sb.toString());
    }

    @Override
    public ItensVisita buscar(Long id) {
        return super.buscar(ItensVisita.class, id);
    }

    @Override
    public List<ItensVisita> buscarTodos() {
        return super.buscarTodos(ItensVisita.class);
    }

}

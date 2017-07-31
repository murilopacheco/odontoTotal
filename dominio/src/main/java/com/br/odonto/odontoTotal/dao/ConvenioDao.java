package com.br.odonto.odontoTotal.dao;

import com.br.odonto.odontoTotal.dominio.Convenio;
import java.util.List;

/**
 *
 * @author Danillo
 */
public class ConvenioDao extends GenericDao<Convenio> implements IEntityDao<Convenio> {

    public List<Convenio> consultar() {
        StringBuilder sb = new StringBuilder();

        sb.append("select con.* ").
                append("from Convenio con ").
                
                append("where con.id > 0");
      
        return super.pesquisarSQLCustomizada(Convenio.class, sb.toString());
    }

    @Override
    public Convenio buscar(Long id) {
        return super.buscar(Convenio.class, id);
    }

    @Override
    public List<Convenio> buscarTodos() {
        return super.buscarTodos(Convenio.class);
    }

}

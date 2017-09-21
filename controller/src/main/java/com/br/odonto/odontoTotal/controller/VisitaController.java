package com.br.odonto.odontoTotal.controller;


import com.br.odonto.odontoTotal.dao.ClienteDao;
import com.br.odonto.odontoTotal.dao.ItensVisitaDao;
import com.br.odonto.odontoTotal.dao.VisitaDao;
import com.br.odonto.odontoTotal.dominio.Cliente;
import com.br.odonto.odontoTotal.dominio.ItensVisita;
import com.br.odonto.odontoTotal.dominio.Visita;
import jdk.nashorn.internal.objects.NativeArray;

import java.util.List;

/**
 *
 * @author Murilo
 */
public class VisitaController {

    private final VisitaDao dao;
    private final ItensVisitaDao itensDao;

    public VisitaController() {

        dao = new VisitaDao();
        itensDao = new ItensVisitaDao();
    }

    public List<Visita> consultar(String sqlFiltro, int limite) {
        return dao.consultar(sqlFiltro, limite);
    }

    public List<String> atualizar(Visita visita) {
        List<String> inconsistencias = visita.validar();
        if (inconsistencias.isEmpty()) {
            dao.salvar(visita);
        }
        return inconsistencias;
    }
}

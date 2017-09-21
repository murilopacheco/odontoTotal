package com.br.odonto.odontoTotal.controller;


import com.br.odonto.odontoTotal.dao.ClienteDao;
import com.br.odonto.odontoTotal.dao.VisitaDao;
import com.br.odonto.odontoTotal.dominio.Cliente;
import com.br.odonto.odontoTotal.dominio.Visita;

import java.util.List;

/**
 *
 * @author Murilo
 */
public class VisitaController {

    private final VisitaDao dao;

    public VisitaController() {
        dao = new VisitaDao();
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

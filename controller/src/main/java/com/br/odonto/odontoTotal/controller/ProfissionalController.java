package com.br.odonto.odontoTotal.controller;


import com.br.odonto.odontoTotal.dao.ClienteDao;
import com.br.odonto.odontoTotal.dao.ProfissionalDao;
import com.br.odonto.odontoTotal.dominio.Profissional;
import com.br.odonto.odontoTotal.dominio.Profissional;

import java.util.List;

/**
 *
 * @author Danillo
 */
public class ProfissionalController {

    private final ProfissionalDao dao;

    public ProfissionalController() {
        dao = new ProfissionalDao();
    }

    public List<Profissional> consultar() {
        return dao.consultar();
    }

    public List<String> atualizar(Profissional profissional) {
        List<String> inconsistencias = profissional.validar();
        if (inconsistencias.isEmpty()) {
            dao.salvar(profissional);
        }
        return inconsistencias;
    }
    public Profissional buscar(Long id) {
        Profissional profissional = new Profissional();
        profissional = dao.buscar(id);
        return profissional;
    }
    public List<Profissional> pesquisarPorCliente(String cliente) {
        return dao.pesquisarPorCliente(cliente);
    }
}

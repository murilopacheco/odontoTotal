package com.br.odonto.odontoTotal.controller;

import com.br.odonto.odontoTotal.dao.ConvenioDao;
import com.br.odonto.odontoTotal.dominio.Convenio;
import java.util.List;

/**
 *
 * @author Danillo
 */
public class ConvenioController {

    private final ConvenioDao dao;

    public ConvenioController() {
        dao = new ConvenioDao();
    }

    public List<Convenio> consultar() {
        return dao.consultar();
    }

    public List<String> atualizar(Convenio convenio) {
        List<String> inconsistencias = convenio.validar();
        if (inconsistencias.isEmpty()) {
            dao.salvar(convenio);
        }
        return inconsistencias;
    }

    public Convenio buscar(Long id) {
        Convenio convenio;
        convenio = new Convenio();
        convenio = dao.buscar(id);
        return convenio;
    }
    
    public List<Convenio> buscarPorNome(String nome){
       return dao.buscarPorNome(nome);
       }
}

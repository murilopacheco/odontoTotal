package com.br.odonto.odontoTotal.controller;

import com.br.odonto.odontoTotal.dao.ProcedimentoDao;
import com.br.odonto.odontoTotal.dominio.Procedimento;

import java.util.List;

/**
 *
 * @author Murilo
 */
public class ProcedimentoController {

    private final ProcedimentoDao dao;

    public ProcedimentoController() {
        dao = new ProcedimentoDao();
    }

    public List<Procedimento> consultar() {
        return dao.consultar();
    }

    public List<String> atualizar(Procedimento procedimento) {
        List<String> inconsistencias = procedimento.validar();
        if (inconsistencias.isEmpty()) {
            dao.salvar(procedimento);
        }
        return inconsistencias;
    }

    public Procedimento buscar(Long id) {
        Procedimento procedimento;
        procedimento = new Procedimento();
        procedimento = dao.buscar(id);
        return procedimento;
    }
    
    public List<Procedimento> buscarPorNome(String nome){
       return dao.buscarPorNome(nome);
       }

    public List<Procedimento> pesquisarPorProcedimento(String procedimento) {
        return dao.pesquisarPorProcedimento(procedimento);
    }
}

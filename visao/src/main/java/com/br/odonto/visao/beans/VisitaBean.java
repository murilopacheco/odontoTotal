/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.odonto.visao.beans;

import com.br.odonto.odontoTotal.controller.ProcedimentoController;
import com.br.odonto.odontoTotal.dominio.Procedimento;
import com.br.odonto.odontoTotal.dominio.Visita;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author muril
 */
@Named(value = "visitaBean")
@SessionScoped
public class VisitaBean implements Serializable{
    
    @Inject
    private ClienteBean clienteBean;
    private Visita visita = new Visita();
    private List<Visita> visitas;
    private Procedimento procedimento;
    private List<Procedimento> procedimentos = new ArrayList<>();


    @PostConstruct
    public void init(){

        visita.setDataVisita(LocalDate.now());
        procedimentos = new ArrayList<Procedimento>();
    }

    public void visualizarDetalhesVisita(Visita v){
        //To-Do metodo de detalhes visita.
    }

    public List<Procedimento> listaProcedimentos(String procedimento){
        ProcedimentoController pro = new ProcedimentoController();
        return pro.pesquisarPorProcedimento(procedimento);

    }

    public void adicionaProcedimento(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
        Procedimento procedimento = new Procedimento();
        procedimento = (Procedimento) event.getObject();
        if(procedimentos == null){
            procedimentos = new ArrayList<Procedimento>();
        }
        procedimentos.add(procedimento);
    }

    public ClienteBean getClienteBean() {
        return clienteBean;
    }

    public void setClienteBean(ClienteBean clienteBean) {
        this.clienteBean = clienteBean;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }
}

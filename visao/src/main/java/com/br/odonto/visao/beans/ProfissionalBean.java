/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.odonto.visao.beans;

import com.br.odonto.odontoTotal.controller.ProfissionalController;
import com.br.odonto.odontoTotal.dominio.Profissional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author murilo
 */
@Named(value = "profissionalBean")
@SessionScoped
public class ProfissionalBean implements Serializable {

    private Profissional profissional;

    private final ProfissionalController controller;

    private List<Profissional> profissionais;

    public ProfissionalBean() {

        controller = new ProfissionalController();
    }

    @PostConstruct
    public void init() {
        consultar();
    }

    public String atualizar() {
        List<String> inconsistencias = controller.atualizar(profissional);
        if (inconsistencias.isEmpty()) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização realizada com sucesso", "");
            contexto.addMessage("/profissionais/listagemProfissionais", msg);
            contexto.getExternalContext().getFlash().setKeepMessages(true);
            return "/profissionais/listagemProfissionais";
        }
        inconsistencias.forEach((inconsistencia) -> {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(inconsistencia));
        });
        return "/profissionais/listagemProfissionais";

    }

    public void consultar() {
        this.profissionais = controller.consultar();
        if (!profissionais.isEmpty() && profissional != null) {
            this.profissional = profissionais.get(0);
        }
    }


    public void editar(Profissional p){
        this.profissional = p;
    }

    public void novoProfissional(){
        this.profissional = new Profissional();
    }


    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public ProfissionalController getController() {
        return controller;
    }

    public List<Profissional> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }
}

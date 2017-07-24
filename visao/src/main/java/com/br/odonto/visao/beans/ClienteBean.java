/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.odonto.visao.beans;

import com.br.odonto.odontoTotal.controller.ClienteController;
import com.br.odonto.odontoTotal.dominio.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author murilo
 */
@Named(value = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private final ClienteController controller;

    private String sqlFiltro;

    private String sqlContent;

    private int limiteRegistros;
    
    private Cliente cliente;
    
    private List<Cliente> clientes;
    
     public ClienteBean() {
        this.cliente = new Cliente();
        controller = new ClienteController();
        StringBuilder sb = new StringBuilder();
        sb.append("select c.* ").
                append("from Cliente c ").
                append("where c.`DTYPE` like 'CLI' ");
        sqlContent = sb.toString();
    }
     
    @PostConstruct
    public void init(){
        consultar();
    }
     
     public String atualizar() {
        List<String> inconsistencias = controller.atualizar(cliente);
        if (inconsistencias.isEmpty()) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização realizada com sucesso", "");
            contexto.addMessage("/clientes/atualizaDadosPessoais", msg);
            contexto.getExternalContext().getFlash().setKeepMessages(true);
            return "/clientes/atualizaDadosPessoais";
        }
        for (String inconsistencia : inconsistencias) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(inconsistencia));
        }
        return "/clientes/atualizaDadosPessoais";

    }
     
     public void consultar() {
        this.clientes = controller.consultar(sqlFiltro, limiteRegistros);
    }
     
     public void prepararAtualizacaoEgresso(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getSqlFiltro() {
        return sqlFiltro;
    }

    public void setSqlFiltro(String sqlFiltro) {
        this.sqlFiltro = sqlFiltro;
    }

    public String getSqlContent() {
        return sqlContent;
    }

    public void setSqlContent(String sqlContent) {
        this.sqlContent = sqlContent;
    }

    public int getLimiteRegistros() {
        return limiteRegistros;
    }

    public void setLimiteRegistros(int limiteRegistros) {
        this.limiteRegistros = limiteRegistros;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.odonto.visao.beans;

import com.br.odonto.odontoTotal.controller.ClienteController;
import com.br.odonto.odontoTotal.controller.ConvenioController;
import com.br.odonto.odontoTotal.dominio.Cliente;
import com.br.odonto.odontoTotal.dominio.Convenio;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author murilo
 */
@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private final ClienteController controller;

    private String sqlFiltro;

    private String sqlContent;

    private int limiteRegistros;

    private Cliente cliente;

    private List<Cliente> clientes;
    
    private String idade;
    
    private Convenio convenio;
    
    private List<Convenio> convenios;

    public ClienteBean() {
        cliente = new Cliente();
        controller = new ClienteController();
        StringBuilder sb = new StringBuilder();
        sb.append("select c.* ").
                append("from Cliente c ").
                append("where c.`DTYPE` like 'CLI' ");
        sqlContent = sb.toString();
    }

    @PostConstruct
    public void init() {
        consultar();
        calculaIdade();
        listarConvenios();
    }

    public String atualizar() {
        List<String> inconsistencias = controller.atualizar(cliente);
        if (inconsistencias.isEmpty()) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização realizada com sucesso", "");
            contexto.addMessage("/clientes/listagemClientes", msg);
            contexto.getExternalContext().getFlash().setKeepMessages(true);
            return "/clientes/listagemClientes";
        }
        inconsistencias.forEach((inconsistencia) -> {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(inconsistencia));
        });
        return "/clientes/atualizaDadosPessoais";

    }

    public void consultar() {
        this.clientes = controller.consultar(sqlFiltro, limiteRegistros);
        if (!clientes.isEmpty()) {
            this.cliente = clientes.get(0);
        }
    }
    
    public void consultarConvenios(String nome){
        ConvenioController con = new ConvenioController();
        convenios = con.buscarPorNome(nome);
    }
    
    public void listarConvenios(){
        ConvenioController con = new ConvenioController();
        convenios = con.consultar();
    }
    
    public void calculaIdade(){
        if(cliente.getDataNascimento() != null){
        final LocalDate dataAtual = LocalDate.now();
        final Period periodo = Period.between(cliente.getDataNascimento(), dataAtual);
        idade = String.valueOf(periodo.getYears());
        }else{
            idade = "";
        }
    }

    public String abrirVisita(Cliente cliente){
        this.cliente = cliente;
        return "/visitas/listagemVisitas";
    }

    public void prepararAtualizacaoCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void novoCliente(){
        cliente = new Cliente();
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

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public List<Convenio> getConvenios() {
        return convenios;
    }

    public void setConvenios(List<Convenio> convenios) {
        this.convenios = convenios;
    }

    
}

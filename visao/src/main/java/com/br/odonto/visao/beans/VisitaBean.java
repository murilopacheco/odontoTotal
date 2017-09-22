/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.odonto.visao.beans;

import com.br.odonto.odontoTotal.controller.ProcedimentoController;
import com.br.odonto.odontoTotal.controller.VisitaController;
import com.br.odonto.odontoTotal.dominio.ItensVisita;
import com.br.odonto.odontoTotal.dominio.Procedimento;
import com.br.odonto.odontoTotal.dominio.Visita;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
    private List<ItensVisita> itens = new ArrayList<ItensVisita>();
    private List<Procedimento> procedimentos = new ArrayList<>();
    private List<Procedimento> procedimentosSelecionados = new ArrayList<Procedimento>();
    VisitaController controller = new VisitaController();


    @PostConstruct
    public void init(){

        visita.setDataVisita(LocalDate.now());
        procedimentos = new ArrayList<Procedimento>();
        consultar();
    }

    public void visualizarDetalhesVisita(Visita v){
        this.visita = v;
        if(v != null){
            procedimentos = new ArrayList<Procedimento>();
        for (int i = 0; i < v.getItens().size(); i++) {
            Procedimento p = new Procedimento();
            p = v.getItens().get(i).getProcedimento();
            p.setPreco(v.getItens().get(i).getValorPago());
            procedimentos.add(p);
            }
        }
    }

    public List<Procedimento> listaProcedimentos(String procedimento){
        ProcedimentoController pro = new ProcedimentoController();
        return pro.pesquisarPorProcedimento(procedimento);

    }

    public void adicionaProcedimento(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
        Procedimento pro = new Procedimento();
        pro = (Procedimento) event.getObject();
        if(procedimentos == null){
            procedimentos = new ArrayList<Procedimento>();
        }
        procedimentos.add(pro);
        this.procedimento = new Procedimento();
    }

    public void removeProcedimento(){
        this.procedimentos.removeAll(this.procedimentosSelecionados);
    }

    public void consultar() {
        String sqlFiltro = "";
        int limiteRegistros = 0;
        this.visitas = controller.consultar(sqlFiltro, limiteRegistros);
        if (!visitas.isEmpty()) {
            this.visita = visitas.get(0);
        }
    }

    public String salvarVisita(){
        if(procedimentos != null) {
            visita.setCliente(clienteBean.getCliente());
            itens = new ArrayList<ItensVisita>();
            for (Iterator<Procedimento> iter = procedimentos.iterator(); iter.hasNext(); ) {
                Procedimento procedimento = iter.next();
                ItensVisita itensVisita = new ItensVisita();
                itensVisita.setProcedimento(procedimento);
                itensVisita.setVisita(visita);
                itensVisita.setValorPago(procedimento.getPreco());
                itens.add(itensVisita);
            }

            visita.setItens(itens);
            List<String> inconsistencias = controller.atualizar(visita);
            return "/clientes/listagemVisitas";
        }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Visita não salva falta adicionar procedimentos"));
            return"/clientes/listagemVisitas";
        }
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

    public List<Procedimento> getProcedimentosSelecionados() {
        return procedimentosSelecionados;
    }

    public void setProcedimentosSelecionados(List<Procedimento> procedimentosSelecionados) {
        this.procedimentosSelecionados = procedimentosSelecionados;
    }

    public List<ItensVisita> getItens() {
        return itens;
    }

    public void setItens(List<ItensVisita> itens) {
        this.itens = itens;
    }
}

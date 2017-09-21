/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.odonto.odontoTotal.dominio;

import com.br.odonto.odontoTotal.dao.validadores.ValidadorGenerico;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author murilo
 */
@Entity
@DiscriminatorValue("VIS")
public class Visita extends ValidadorGenerico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dataVisita;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "visita", cascade = CascadeType.ALL)
    private List<ItensVisita> itens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(LocalDate dataVisita) {
        this.dataVisita = dataVisita;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItensVisita> getItens() {
        return itens;
    }

    public void setItens(List<ItensVisita> itens) {
        this.itens = itens;
    }

    @Override
    public List<String> validar() {
        validarNaoNulo(dataVisita, "dataVisita");
        return inconsistencias;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.odonto.odontoTotal.dominio;

import com.br.odonto.odontoTotal.dao.validadores.ValidadorGenerico;
import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author murilo
 */
@Entity
@DiscriminatorValue("CON")
public class Convenio extends ValidadorGenerico implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String registroAns;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistroAns() {
        return registroAns;
    }

    public void setRegistroAns(String registroAns) {
        this.registroAns = registroAns;
    }
    
    

    @Override
    public List<String> validar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

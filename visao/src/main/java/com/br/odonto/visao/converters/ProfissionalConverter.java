/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.odonto.visao.converters;

import com.br.odonto.odontoTotal.controller.ProfissionalController;
import com.br.odonto.odontoTotal.controller.ProfissionalController;
import com.br.odonto.odontoTotal.dominio.Profissional;
import com.br.odonto.odontoTotal.dominio.Profissional;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author murilo
 */
@FacesConverter("jsfProfissionalConverter")
public class ProfissionalConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                Long id = Long.parseLong(value);
                ProfissionalController con  = new ProfissionalController();
                Profissional p = con.buscar(id);
                return p;
                        
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na conversão", "Não é um convênio válido."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(((Profissional) o).getId() != null){
            return ((Profissional) o).getId().toString();
        }else{
            return "";
        }
    }
    
}

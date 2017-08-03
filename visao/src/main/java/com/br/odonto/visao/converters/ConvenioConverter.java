/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.odonto.visao.converters;

import com.br.odonto.odontoTotal.controller.ConvenioController;
import com.br.odonto.odontoTotal.dominio.Convenio;
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
@FacesConverter("jsfConvenioConverter")
public class ConvenioConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                Long id = Long.parseLong(value);
                ConvenioController con  = new ConvenioController();
                Convenio c = con.buscar(id);
                return c.getId();
                        
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
        if(((Convenio) o).getId() != null){
            return ((Convenio) o).getId().toString();
        }else{
            return "";
        }
    }
    
}

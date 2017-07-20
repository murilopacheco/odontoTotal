package com.br.odonto.odontoTotal.dao.validadores;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Murilo
 * @param <T>
 */
public abstract class ValidadorGenerico implements IValidador{

    protected final List<String> inconsistencias = new ArrayList<>();
    
    @Override
    public abstract List<String> validar();
    
    /**
     * *******************************************************************
     * *******************************************************************
     * VALIDAÇÃO GERAL
     * *******************************************************************
     * *******************************************************************
     */
    public void validarNaoNulo(Object value, String nomeCampo){
        if(value==null){
            inconsistencias.add(nomeCampo + " não informado(a)");
        }
    }
    
    /**
     * *******************************************************************
     * *******************************************************************
     * VALIDAÇÃO DE ATRIBUTOS STRING
     * *******************************************************************
     * *******************************************************************
     */
    public void validarNaoNuloEVazio(String value, String nomeCampo){
        if(value==null || value.isEmpty()){
            inconsistencias.add(nomeCampo + " não informado(a)");
        }
    }
    
    public void garantirTamanhoMinimo(String value, String nomeCampo, int tamanhoMinimo){
        if(value.length()<tamanhoMinimo){
            inconsistencias.add(nomeCampo + " menor que " + tamanhoMinimo + " caracteres");
        }
    }
    
    /**
     * *******************************************************************
     * *******************************************************************
     * VALIDAÇÃO DE ATRIBUTOS NUMÉRICOS
     * *******************************************************************
     * *******************************************************************
     */
    
    /**
     * *******************************************************************
     * *******************************************************************
     * VALIDAÇÃO DE ATRIBUTOS DATA
     * *******************************************************************
     * *******************************************************************
     */
    
    /**
     * *******************************************************************
     * *******************************************************************
     * VALIDAÇÃO DE ATRIBUTOS DE RELACIONAMETNO 1:N
     * *******************************************************************
     * *******************************************************************
     */
    
    /**
     * *******************************************************************
     * *******************************************************************
     * VALIDAÇÃO DE ATRIBUTOS DE RELACIONAMETNO N:1
     * *******************************************************************
     * *******************************************************************
     */
    
}

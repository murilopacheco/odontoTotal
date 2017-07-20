package com.br.odonto.odontoTotal.dao.validadores;

import java.util.List;

/**
 *
 * @author Murilo
 */
public interface IValidador{
    
    /**
     * Validar restrições de domínio
     * @return 
     */
    public List<String> validar();
    
    
}

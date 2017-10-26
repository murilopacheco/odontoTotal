package com.br.odonto.odontoTotal.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Murilo
 */
public class ConnectionFactory {

    private static EntityManager em;
    private static EntityManagerFactory emf;

    public static EntityManager obterManager() {
        try {

                emf = Persistence.createEntityManagerFactory("odontoAtualPU");
            if(em == null || !em.isOpen())
                em = emf.createEntityManager();
        } catch (Exception e) {
            System.out.println("Não foi possível criar Entidade de Persistência");
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, "Não foi possível criar Entidade de Persistência!", e);

        }
        return em;
    }
}

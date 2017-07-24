package com.br.odonto.odontoTotal.dominio;


import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Danillo
 */
public class DataGenerator {

   
    public static Cliente constroi(EntityManager em) {
     
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setCPF("673.780.581-73");
        cliente.setEndereco("Rua 1 numero 1 quadra 1 lote 1 Setor Teste CEP 74000-000");
        cliente.setDataNascimento(LocalDate.now());
        em.persist(cliente);

        return cliente;
    }

   

   

    public static void main(String[] args) throws IOException {
        executar();
        System.exit(0);
    }

    public static void executar() throws IOException {
       try{         
        EntityManager em = getEM();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Cliente cli = constroi(em);
        transaction.commit();
        System.out.println("Dados cadastrados com suscesso");
        em.close();
       }finally{
          
       }
        
    }
    
    private static EntityManager getEM() {
        //Cadastrar os cursos na UFG
        EntityManager em
                = Persistence.createEntityManagerFactory("odontoAtualPU").
                        createEntityManager();
        return em;
    }
        
}

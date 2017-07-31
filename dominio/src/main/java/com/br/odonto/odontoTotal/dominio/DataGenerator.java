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
        Convenio convenio =  new Convenio();
        
        convenio.setNome("Particular");
        convenio.setRegistroAns("123");
        
       em.persist(convenio);
       
       convenio =  new Convenio();
        
        convenio.setNome("Uniodonto Goiânia");
        convenio.setRegistroAns("358436");
        
        em.persist(convenio);
        
        cliente.setNome("Cliente Teste");
        cliente.setCPF("673.780.581-73");
        cliente.setEndereco("Rua 1 numero 1 quadra 1 lote 1 Setor Teste CEP 74000-000");
        cliente.setDataNascimento(LocalDate.now());
        cliente.setTelefone("62 98444-7430");
        cliente.setRg("123456");
        cliente.setCEP("74000-000");
        cliente.setCidade("Goiânia");
        cliente.setEstado("Goiás");
        cliente.setProfissao("Estudante");
        cliente.setResponsavel("Maria");
        cliente.setConvenio(convenio);
        cliente.setPlano("básico");
        
        
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

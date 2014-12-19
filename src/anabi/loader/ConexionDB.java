package anabi.loader;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionDB {
	
	private EntityManagerFactory emf;
    private EntityManager em;
	
	 public EntityManager conectDB() {
	        
	        if (emf == null) {
	            emf = Persistence.createEntityManagerFactory("AnabiPU");
	            em = emf.createEntityManager();
	        }
	        return em;
	    }

}

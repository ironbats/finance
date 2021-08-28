package br.com.homeoffice.util;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class JPAUtil {
	
	private static EntityManagerFactory manager = Persistence.createEntityManagerFactory("homeOffice");
	
	@PersistenceContext
	public static  EntityManager getEntityManager(){
		return manager.createEntityManager();
	}
	
	public void  close (EntityManager manager){
		manager.close();
	}

}

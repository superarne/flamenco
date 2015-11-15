package com.flamenco.server.user;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/user")
public class UserServlet{
	
	private static final Logger log = LoggerFactory.getLogger(UserServlet.class);
	
	@PersistenceUnit(unitName = "flamenco")
	private EntityManagerFactory entityManagerFactory;
	
//	@POST
//	@Path("/create")
//    @Consumes("application/json")
//	public void createUser(User user){
//		EntityManager em = entityManagerFactory.createEntityManager();
//		em.persist(user);
//	}
//	
//	@GET
//	@Path("/read")
//	@Produces("application/json")
//	public User read(long id){
//		EntityManager em = entityManagerFactory.createEntityManager();
//		return em.find(User.class, id);
//	}
//	
//	@PUT
//	@Path("/update")
//	@Consumes("application/json")
//	public void updateUser(String user){
//		EntityManager em = entityManagerFactory.createEntityManager();
//		em.merge(user);
//	}
//	
//	@DELETE
//	@Path("/delete")
//	@Consumes("application/json")
//	public void deleteUser(User user){
//		EntityManager em = entityManagerFactory.createEntityManager();
//		em.remove(user);
//	}	
	
	@GET
	@Path("/all")
	@Produces("application/json")
    public Collection<User> getUsers() {  
		if(entityManagerFactory == null){
			try {
				throw new Exception("EntityManagerFactory is null");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		EntityManager em = entityManagerFactory.createEntityManager();	
		Query query = em.createQuery("SELECT u FROM User u");
		return (Collection<User>) query.getResultList();
    }
    

}

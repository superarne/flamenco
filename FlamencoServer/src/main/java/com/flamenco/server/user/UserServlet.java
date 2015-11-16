package com.flamenco.server.user;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.ws.rs.*;

import com.flamenco.server.domain.UserService;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/user")
public class UserServlet {

    private static final Logger log = LoggerFactory.getLogger(UserServlet.class);

    private final EntityManager entityManager;

    private final UserService userService;

    @Inject
    public UserServlet(EntityManager entityManager, UserService userService) {
        this.entityManager = entityManager;
        this.userService = userService;
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    @Transactional
    public void createUser(User user) {
        log.debug("persist user: {}", user);
        try {
            entityManager.persist(user);
            log.debug("created user with Id: {}", user.getId());

            User user1 = entityManager.find(User.class, user.getId());
            log.debug("loaded user: {}", user1);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }
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
    @Path("all")
    @Produces("application/json")
    public Collection<User> getUsers() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return (Collection<User>) query.getResultList();
    }
}

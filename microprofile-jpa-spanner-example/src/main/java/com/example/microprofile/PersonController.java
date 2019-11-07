package com.example.microprofile;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/person")
@Singleton
public class PersonController {
	@Inject
	EntityManager em;

    @GET
    public Person create(@QueryParam("name") String name) {
    	Person p = new Person();
    	p.setName(name);
    	em.persist(p);

    	return p;
    }
}


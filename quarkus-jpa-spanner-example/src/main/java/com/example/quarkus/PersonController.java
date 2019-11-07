package com.example.quarkus;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/hello")
public class PersonController {

    @Inject EntityManager em;
    @Inject PersonRepository personRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/")
    @Transactional
    public Person create(@QueryParam("name") String name) {
        Person p = new Person();
        p.setName(name);

        personRepository.persist(p);

        return p;
    }
}
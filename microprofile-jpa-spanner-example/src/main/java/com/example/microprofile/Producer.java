package com.example.microprofile;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@ApplicationScoped
public class Producer {
	@Produces EntityManager entityManager() {
		return Persistence.createEntityManagerFactory("spanner-example")
				.createEntityManager();
	}

	public void close(EntityManager entityManager) {
		entityManager.close();
	}
}

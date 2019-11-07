package com.example.quarkus;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class PersonRepository implements PanacheRepositoryBase<Person, UUID> {
}

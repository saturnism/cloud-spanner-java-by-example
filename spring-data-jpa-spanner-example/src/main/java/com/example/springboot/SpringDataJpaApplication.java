package com.example.springboot;

import org.hibernate.annotations.Type;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

}

@Entity
class Person {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type="uuid-char")
	@Id
	private UUID id;

	private String name;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

@RepositoryRestResource
interface PersonRepository extends PagingAndSortingRepository<Person, UUID> {

}

@RestController
class PersonController {
	private final PersonRepository personRepository;

	PersonController(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@GetMapping("/person")
	Person create(String name) {
		Person p = new Person();

		p.setName(name);
		personRepository.save(p);

		return p;
	}
}



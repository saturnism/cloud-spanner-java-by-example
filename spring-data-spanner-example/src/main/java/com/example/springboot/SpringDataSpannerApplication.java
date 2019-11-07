package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
public class SpringDataSpannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataSpannerApplication.class, args);
	}

}

@Table
class Person {
	@PrimaryKey
	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
interface PersonRepository extends PagingAndSortingRepository<Person, String> {

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
		p.setId(UUID.randomUUID().toString());
		p.setName(name);

		personRepository.save(p);

		return p;
	}
}
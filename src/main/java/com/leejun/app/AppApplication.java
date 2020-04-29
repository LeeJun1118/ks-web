package com.leejun.app;

import com.leejun.app.domain.Person;
import com.leejun.app.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	private final PersonRepository personRepository;

	public AppApplication(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		personRepository.save(new Person("홀길동","데이터분석","hong@test.com","010-1111-2222"	));
	}
}

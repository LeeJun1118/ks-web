package com.leejun.app;

import com.leejun.app.domain.*;
import com.leejun.app.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	private final PersonRepository personRepository;
	private final SocialMediaRepository socialMediaRepository;
	private final InterestsRepository interestsRepository;
	private final SkillRepository skillRepository;
	private final WorkRepository workRepository;

	public AppApplication(PersonRepository personRepository, SocialMediaRepository socialMediaRepository, InterestsRepository interestsRepository, SkillRepository skillRepository, WorkRepository workRepository) {
		this.personRepository = personRepository;
		this.socialMediaRepository = socialMediaRepository;
		this.interestsRepository = interestsRepository;
		this.skillRepository = skillRepository;
		this.workRepository = workRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		personRepository.save(new Person("이준","백엔드","hello@test.com"," 010-1234-5678"	));

		socialMediaRepository.save(new SocialMedia("anonymous","anonymous",null,null));

		interestsRepository.save(new Interests("LOL"));
		interestsRepository.save(new Interests("음악 감상"));

		skillRepository.save(new Skill("JAVA",80));
		skillRepository.save(new Skill("C",60));
		skillRepository.save(new Skill("C++",50));

		workRepository.save(new Work("Backend","Line","2021.04.01 ~ ","평생 직장"));
	}
}

package dev.lusungu.salamapesa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.resilience.annotation.EnableResilientMethods;

@SpringBootApplication
@EnableResilientMethods
@EnableJpaAuditing
public class SalamapesaApplication {

	 static void main(String[] args) {
		SpringApplication.run(SalamapesaApplication.class, args);
	}

}

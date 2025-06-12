package io.github.enzopavani.orcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OrcatApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrcatApplication.class, args);
	}

}

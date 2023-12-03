package br.com.luizvictor.springemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringemailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringemailApplication.class, args);
	}

}

package org.example;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinSprintApplication {

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(FinSprintApplication.class, args);
	}

	@PostConstruct
	public void logConnectionDetails() {
		System.out.println("Using DB user: " + username);
		System.out.println("Using DB password: " + password);
	}
}
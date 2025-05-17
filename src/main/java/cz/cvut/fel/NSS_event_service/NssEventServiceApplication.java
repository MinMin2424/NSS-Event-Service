package cz.cvut.fel.NSS_event_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class NssEventServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NssEventServiceApplication.class, args);
	}

}

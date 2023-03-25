package com.ameen;

import com.ameen.service.model.Ticket;
import com.ameen.service.repository.TicketsRepository;
import com.ameen.service.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SecurityApplication {


	public static void main(String[] args) {


		SpringApplication.run(SecurityApplication.class, args);


	}

}

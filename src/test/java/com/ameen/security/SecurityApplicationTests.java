package com.ameen.security;


import com.ameen.security.user.User;
import com.ameen.security.user.UserRepository;
import com.ameen.service.model.Ticket;
import com.ameen.service.repository.TicketsRepository;
import com.ameen.service.services.TicketsService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class SecurityApplicationTests {

    public static Logger log = LoggerFactory.getLogger(SecurityApplicationTests.class);
    @Autowired
    TicketsRepository repo ;

    @Autowired
    TicketsService ticketsService;


    @Test
    public void insertExampleTicket(){

        Ticket ticket= TicketGenerator.generateRandomTicket("md.ameen1234@gmail.com");

        log.info(ticket.toString());
//        Ticket ticket = this.ticketsService.createExampleTicket("Example");
        repo.save(ticket);
    }
    @Test
    public void insert10RandomTickets(){
        for (int i = 0;i<10;i++) {

            Ticket ticket= TicketGenerator.generateRandomTicket("md.ameen1234@gmail.com");

            log.info(ticket.toString());

            repo.save(ticket);
        }

    }

}

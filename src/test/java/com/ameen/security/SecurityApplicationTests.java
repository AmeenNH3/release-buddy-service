package com.ameen.security;


import com.ameen.security.user.User;
import com.ameen.security.user.UserRepository;
import com.ameen.service.model.Ticket;
import com.ameen.service.repository.TicketsRepository;
import com.ameen.service.services.TicketsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class SecurityApplicationTests {

    @Autowired
    TicketsRepository repo ;

    @Autowired
    TicketsService ticketsService;


    @Test
    public void insertExampleTicket(){
        Ticket ticket = this.ticketsService.createExampleTicket();

        repo.save(ticket);
    }


}

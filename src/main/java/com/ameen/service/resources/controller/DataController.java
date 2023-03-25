package com.ameen.service.resources.controller;

import com.ameen.security.config.JwtService;
import com.ameen.service.model.Ticket;
import com.ameen.service.repository.TicketsRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
@Slf4j
public class DataController {


    @Autowired
    JwtService jwtService;

    @Autowired
    TicketsRepository ticketsRepository;

    @GetMapping("/allTickets")
    public List<Ticket> getAllTicketsOftheUserName(@RequestHeader(name = "Authorization") String token) {
        String username = jwtService.extractUsername(token.substring(7));
        log.info("username: {}",username);
        return ticketsRepository.findByCreatedBy(username);
    }
}

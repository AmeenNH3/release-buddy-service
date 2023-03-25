package com.ameen.service.resources.controller;

import com.ameen.security.config.JwtService;
import com.ameen.service.model.Ticket;
import com.ameen.service.repository.TicketsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/data")
@Slf4j
public class DataController {


    @Autowired
    MongoOperations mongoOperations;
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

    @PostMapping("/saveTickets")
    public ResponseEntity<String> saveAllTicketsOftheUserName(@RequestHeader(name ="Authorization") String token, @RequestBody List<Ticket> tickets){
        String username = jwtService.extractUsername(token.substring(7));
        log.info("username: {}",username);

        try{
            for (Ticket ticket: tickets) {
                Optional<Ticket> findById = ticketsRepository.findById(ticket.getId());
                log.info(findById.toString());
                if(findById.isPresent()) {
                    Query query = new Query();
                    query.addCriteria(Criteria.where("id").is(ticket.getId()));
                    query.fields().include("id");
                    Update update = new Update();
                    update.set("stacks", ticket.getStacks());
                    mongoOperations.updateFirst(query, update, Ticket.class);
                }
                else{
                    ticketsRepository.save(ticket);
                }
            }


            List<UUID> ids = tickets.stream().map(Ticket::getId).collect(Collectors.toList());

            List<Ticket> ticketsFromDb = ticketsRepository.findByCreatedBy(username);

            for (Ticket ticket:ticketsFromDb){
                if(!ids.contains(ticket.getId())){
                   ticketsRepository.deleteById(ticket.getId());
                }
            }



        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error occurred when saving tickets");
        }


        return ResponseEntity.ok("Tickets saved successfully");
    }
}

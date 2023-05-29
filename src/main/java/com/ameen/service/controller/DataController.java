package com.ameen.service.controller;

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

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/data")
@Slf4j
@CrossOrigin(origins = {"http://localhost:5173"})
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
    public ResponseEntity<String> saveAllTickets(@RequestHeader(name = "Authorization") String token, @RequestBody List<Ticket> tickets) {
        String username = jwtService.extractUsername(token.substring(7));
        log.info("username: {}", username);

        for (Ticket receivedTicket : tickets) {
            UUID ticketId = receivedTicket.getId();



            Optional<Ticket> existingTicketOptional = ticketsRepository.findById(ticketId);
            if (existingTicketOptional.isPresent()) {
                Ticket existingTicket = existingTicketOptional.get();
                // Update existing ticket fields
                existingTicket.setName(receivedTicket.getName());
                existingTicket.setDescription(receivedTicket.getDescription());
                existingTicket.setOwner(receivedTicket.getOwner());
                existingTicket.setTicketStatus(receivedTicket.getTicketStatus());
                existingTicket.setChangeTicketNumber(receivedTicket.getChangeTicketNumber());
                existingTicket.setReleaseDate(receivedTicket.getReleaseDate());
                existingTicket.setWorkingTeams(receivedTicket.getWorkingTeams());
                existingTicket.setStacks(receivedTicket.getStacks());
                // Save the updated ticket
                ticketsRepository.save(existingTicket);
            } else {
                // Add new ticket
                receivedTicket.setCreatedBy(username);
                receivedTicket.setCreatedDate(new Date());
                ticketsRepository.save(receivedTicket);
            }
        }

        // Delete any tickets not present in the received list
        List<UUID> receivedTicketIds = tickets.stream().map(Ticket::getId).collect(Collectors.toList());
        List<Ticket> ticketsToDelete = ticketsRepository.findAllByIdNotIn(receivedTicketIds);
        if (!ticketsToDelete.isEmpty()) {
            ticketsRepository.deleteAll(ticketsToDelete);
        }

        return ResponseEntity.ok("Tickets saved successfully");
    }
//    @PostMapping("/saveTickets")
//    public ResponseEntity<String> saveAllTickets(@RequestHeader(name = "Authorization") String token, @RequestBody List<Ticket> tickets) {
//        String username = jwtService.extractUsername(token.substring(7));
//        log.info("username: {}", username);
//
//        List<Ticket> existingTickets = ticketsRepository.findAll();
//        Set<UUID> receivedTicketIds = new HashSet<>();
//        Set<UUID> existingTicketIds = new HashSet<>();
//
//        // Update or delete existing tickets
//        for (Ticket existingTicket : existingTickets) {
//            UUID existingTicketId = existingTicket.getId();
//            existingTicketIds.add(existingTicketId);
//
//            boolean found = false;
//            for (Ticket receivedTicket : tickets) {
//                if (existingTicketId.equals(receivedTicket.getId())) {
//                    // Update existing ticket
//                    receivedTicket.setCreatedBy(username);
//                    ticketsRepository.save(receivedTicket);
//                    found = true;
//                    break;
//                }
//            }
//
//            if (!found) {
//                // Delete existing ticket not present in the received list
//                ticketsRepository.deleteById(existingTicketId);
//            }
//        }
//
//        // Add new tickets
//        for (Ticket receivedTicket : tickets) {
//            UUID receivedTicketId = receivedTicket.getId();
//            receivedTicket.setCreatedBy(username);
//            receivedTicket.setCreatedDate(new Date());
//            receivedTicket.setLasModifiedDate(new Date());
//
//            if (!existingTicketIds.contains(receivedTicketId)) {
//                // Add new ticket
//                ticketsRepository.save(receivedTicket);
//            }
//            receivedTicketIds.add(receivedTicketId);
//        }
//
//        // Delete any tickets not present in the received list
//        for (UUID existingTicketId : existingTicketIds) {
//            if (!receivedTicketIds.contains(existingTicketId)) {
//                ticketsRepository.deleteById(existingTicketId);
//            }
//        }
//
//        return ResponseEntity.ok("Tickets saved successfully");
//    }
//    @PostMapping("/saveTickets")
//    public ResponseEntity<String> saveAllTickets(@RequestHeader(name ="Authorization") String token, @RequestBody List<Ticket> tickets){
//        String username = jwtService.extractUsername(token.substring(7));
//        log.info("username: {}",username);
//
//        try{
//            for (Ticket ticket: tickets) {
//                Optional<Ticket> findById = ticketsRepository.findById(ticket.getId());
//                log.info(findById.toString());
//                if(findById.isPresent()) {
//                    Query query = new Query();
//                    query.addCriteria(Criteria.where("id").is(ticket.getId()));
//                    query.fields().include("id");
//                    Update update = new Update();
//                    update.set("stacks", ticket.getStacks());
//                    mongoOperations.updateFirst(query, update, Ticket.class);
//                }
//                else{
//                    ticketsRepository.save(ticket);
//                }
//            }
//
//            List<UUID> ids = tickets.stream().map(Ticket::getId).collect(Collectors.toList());
//
//            List<Ticket> ticketsFromDb = ticketsRepository.findByCreatedBy(username);
//
//            for (Ticket ticket:ticketsFromDb){
//                if(!ids.contains(ticket.getId())){
//                   ticketsRepository.deleteById(ticket.getId());
//                }
//            }
//
//        }catch (Exception e){
//            return ResponseEntity.internalServerError().body("Error occurred when saving tickets");
//        }
//
//
//        return ResponseEntity.ok("Tickets saved successfully");
//    }
}

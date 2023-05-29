package com.ameen.service.repository;

import com.ameen.service.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface TicketsRepository extends MongoRepository<Ticket, UUID> {
    List<Ticket> findByCreatedBy(String username);
    void deleteById(UUID id);

    List<Ticket> findAllByIdNotIn(List<UUID> receivedTicketIds);
}

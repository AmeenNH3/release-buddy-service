package com.ameen.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Document(collection = "tickets")
@Data
public class Ticket {
    @Id
    private UUID id;
    private String name;

    private String description;
    private String owner;
    private TicketStatus ticketStatus;
    private String changeTicketNumber;
    private Date releaseDate;

    private String workingTeams;
    private List<Stack> stacks;
    private String createdBy;

    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lasModifiedDate;

    @Version
    @JsonIgnore
    private Long version;

    private List<UUID> stackOrder;
    private String ticketNotes;
    public Ticket(){

    }

    public Ticket(UUID id, String name, String description, String owner, TicketStatus ticketStatus, String changeTicketNumber, Date releaseDate, String workingTeams, List<Stack> stacks, String createdBy,
                  List<UUID> stackOrder, String ticketNotes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.ticketStatus = ticketStatus;
        this.changeTicketNumber = changeTicketNumber;
        this.releaseDate = releaseDate;
        this.workingTeams = workingTeams;
        this.stacks = stacks;
        this.createdBy = createdBy;
        this.ticketNotes = ticketNotes;
        this.stackOrder = stackOrder;
    }
}

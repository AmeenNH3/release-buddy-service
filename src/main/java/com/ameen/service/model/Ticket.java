package com.ameen.service.model;

import lombok.AllArgsConstructor;
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
public class Ticket {
    @Id
    private UUID id;
    private String name;
    private List<Stack> stacks;
    private String createdBy;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lasModifiedDate;

    @Version
    private Long version;

    public Ticket(){

    }

    public Ticket(UUID id, String name, List<Stack> stacks, String createdBy) {
        this.id = id;
        this.name = name;
        this.stacks = stacks;
        this.createdBy = createdBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stack> getStacks() {
        return stacks;
    }

    public void setStacks(List<Stack> stacks) {
        this.stacks = stacks;
    }
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }
    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedOn() {
        return createdDate;
    }

    public Date getLasModifiedDate() {
        return lasModifiedDate;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stacks=" + stacks +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdDate +
                ", lasModifiedDate=" + lasModifiedDate +
                '}';
    }
}

package com.ameen.service.services;

import com.ameen.service.model.Stack;
import com.ameen.service.model.Status;
import com.ameen.service.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketsService {

    public Ticket createExampleTicket(){
        Stack stack1 = new Stack(UUID.randomUUID(),"release-buddy-ui","issue-fix",null,
                Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED);
        Stack stack2 = new Stack(UUID.randomUUID(),"release-buddy-backend","local-fix",null,
                Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED);
        Stack stack3 = new Stack(UUID.randomUUID(),"release-buddy-fronend","update-mongo",null,
                Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED);
        Stack stack4 = new Stack(UUID.randomUUID(),"release-buddy-risk-managment","ppi-removal",null,
                Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED, Status.NOT_STARTED);
        List<Stack> stacks = new ArrayList<>();
        stacks.add(stack1);
        stacks.add(stack2);
        stacks.add(stack3);
        stacks.add(stack4);

        return new Ticket( UUID.randomUUID(), "Example", stacks,"md.ameen@gmail.com");
    }
}

package com.ameen.security;

import com.ameen.service.model.Stack;
import com.ameen.service.model.Status;
import com.ameen.service.model.Ticket;
import com.ameen.service.model.TicketStatus;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


public class TicketGenerator {
    public static Ticket generateRandomTicket(String createdByName) {
        UUID id = UUID.randomUUID();
        String name = generateRandomString(10);
        String description = generateRandomString(50);
        String owner = generateRandomString(8);
        TicketStatus status = generateRandomTicketStatus();
        String changeTicketNumber = generateRandomString(6);
        Date releaseDate = generateRandomDate();
        String workingTeams = generateRandomString(20);
        List<Stack> stacks = generateRandomStacks();
        String createdBy = createdByName;

        List<UUID> stackOrder = generateRandomStackId(stacks);
        String ticketNotes = generateRandomString(100);



        return new Ticket(id, name, description, owner, status, changeTicketNumber, releaseDate, workingTeams, stacks, createdBy,stackOrder, ticketNotes);
    }

    private static List<UUID> generateRandomStackId(List<Stack> stacks) {
        List<UUID> stackOrder = new ArrayList<>();
        CopyOnWriteArrayList<Stack> data = new CopyOnWriteArrayList<>(stacks);
        Random random = new Random();
        while(!data.isEmpty()){
            Stack stack = data.get(random.nextInt(data.size()));
          stackOrder.add(stack.getId()) ;
          data.remove(stack);
        }
         return stackOrder;
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
    private static TicketStatus generateRandomTicketStatus() {
        TicketStatus[] statuses = TicketStatus.values();
        Random random = new Random();
        int index = random.nextInt(statuses.length);
        return statuses[index];
    }
    private static Status generateRandomStatus() {
        Status[] statuses = Status.values();
        Random random = new Random();
        int index = random.nextInt(statuses.length);
        return statuses[index];
    }

    private static Date generateRandomDate() {
        long minTimestamp = 1_000_000_000_000L; // 2001-09-09 01:46:40 UTC
        long maxTimestamp = System.currentTimeMillis();
        long randomTimestamp = minTimestamp + (long) (Math.random() * (maxTimestamp - minTimestamp));

        return new Date(randomTimestamp);
    }

    private static List<Stack> generateRandomStacks() {
        List<Stack> stacks = new ArrayList<>();
        Random random = new Random();
        int stackCount = random.nextInt(5) + 1; // Generate between 1 and 5 stacks

        for (int i = 0; i < stackCount; i++) {
            UUID id = UUID.randomUUID();
            String stackName = generateRandomString(8);
            String localBranch = generateRandomString(10);
            Status testedLB = generateRandomStatus();
            Status mergedToD = generateRandomStatus();
            Status testedD = generateRandomStatus();
            Status mergedToM = generateRandomStatus();
            Status testedM = generateRandomStatus();
            Integer bundleNo = random.nextInt(100) + 1; // Generate between 1 and 100
            Status status = generateRandomStatus();
            String owner = generateRandomString(8);
            String oldProductionBundle = generateRandomString(15);
            String newProductionBundle = generateRandomString(15);

            Stack stack = new Stack(id, stackName, localBranch, testedLB, mergedToD, testedD, mergedToM, testedM, bundleNo, status, owner,
                    oldProductionBundle,newProductionBundle);
            stacks.add(stack);
        }

        return stacks;
    }

    private static Long generateRandomVersion() {
        Random random = new Random();
        return random.nextLong();
    }
}
package com.ameen.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stack {
    private UUID id;
    private String stackName;
    private String localBranch;
    private Integer bundleNo;
    private Status testedLB;
    private Status mergedToD;
    private Status testedD;
    private Status mergedToM;
    private Status testedM;
    private Status status;

}

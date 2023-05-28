package com.ameen.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stack {
    private UUID id;
    private String stackName;
    private String localBranch;
    private Status testedLB;
    private Status mergedToD;
    private Status testedD;
    private Status mergedToM;
    private Status testedM;
    private Integer bundleNo;
    private Status status;
    private String owner;

    @CreatedDate
    @JsonIgnore
    private Date createdDate;

    @LastModifiedDate
    @JsonIgnore
    private Date lastModifiedDate;

    public Stack(UUID id, String stackName, String localBranch, Status testedLB, Status mergedToD, Status testedD, Status mergedToM, Status testedM, Integer bundleNo, Status status, String owner) {
        this.id = id;
        this.stackName = stackName;
        this.localBranch = localBranch;
        this.testedLB = testedLB;
        this.mergedToD = mergedToD;
        this.testedD = testedD;
        this.mergedToM = mergedToM;
        this.testedM = testedM;
        this.bundleNo = bundleNo;
        this.status = status;
        this.owner = owner;
    }


}

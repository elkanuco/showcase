package com.github.elkanuco.fund_transfer.dtos;

import com.github.elkanuco.fund_transfer.enums.SubjectType;

import lombok.Data;

@Data
public class SubjectDTO {
    private Long id;
    private SubjectType type;
    private String name;
}
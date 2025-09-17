package com.github.elkanuco.fund_transfer.mappers;

import org.mapstruct.Mapper;

import com.github.elkanuco.fund_transfer.dtos.SubjectDTO;
import com.github.elkanuco.fund_transfer.entities.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectDTO toDto(Subject subject);
    Subject toEntity(SubjectDTO dto);
}
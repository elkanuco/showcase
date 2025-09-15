package com.github.elkanuco.fund_transfer.dtos;

import lombok.Data;

@Data
public class BankDTO {
    private Long id;
    private String name;
    private String bic;
}

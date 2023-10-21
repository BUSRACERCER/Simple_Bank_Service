package com.works.bankservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class AccountDTO {
    @NotEmpty(message = "Owner can not be null or empty !!")
    private String owner;

    @NotEmpty(message = "accountNumber can not be null or empty !!")
    private String accountNumber;
}

package com.works.bankservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class DepositTransaction extends Transaction {
    private String approvalCode;
}

package com.works.bankservice.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class WithdrawalTransaction extends Transaction {
    private String approvalCode;
}

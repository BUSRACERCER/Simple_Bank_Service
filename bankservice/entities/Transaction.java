package com.works.bankservice.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private double amount;
    private LocalDateTime date;
    private String approvalCode;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount account;
}

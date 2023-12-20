package com.storedprocexample.storedproceduresexample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    private int id;
    private int accountNumber;
    private String accountHolderName;
    private Double balance;
}

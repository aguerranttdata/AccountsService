package com.group7.accountsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan extends Credit {
    private double fullPayment;
    private int numberInstallments;
}

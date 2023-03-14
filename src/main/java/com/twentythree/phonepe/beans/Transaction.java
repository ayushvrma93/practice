package com.twentythree.phonepe.beans;

import com.twentythree.phonepe.beans.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

    private int id;
    private Float amount;
    TransactionType transactionType;

    User sourceUser;

    String destinationId;

    public Transaction copy(){
        Transaction copyTxn = new Transaction(this.id, this.amount, this.transactionType, this.sourceUser, this.destinationId);
        return copyTxn;
    }

}

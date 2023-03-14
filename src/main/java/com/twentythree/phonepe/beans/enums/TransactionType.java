package com.twentythree.phonepe.beans.enums;

public enum TransactionType {

    GROUP_TRANSACTION("GROUP_TRANSACTION"),
    INDIVIDUAL_TRANSACTION("INDIVIDUAL_TRANSACTION");

    private String value;
    TransactionType(String value){
        this.value = value;
    }

    private String getValue(){
        return this.value;
    }
}

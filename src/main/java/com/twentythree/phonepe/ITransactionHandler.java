package com.twentythree.phonepe;

import com.twentythree.phonepe.beans.Transaction;
import com.twentythree.phonepe.beans.User;
import com.twentythree.phonepe.beans.enums.TransactionType;
import com.twentythree.phonepe.exceptions.BusinessException;

import java.util.List;

public interface ITransactionHandler {

    Transaction handle(Transaction transaction);

    List<Transaction>[][] getTransactionsForGroup(TransactionType transactionType, String id);

    List<Transaction> getTransactionsBetweenUsers(User sourceUser, User destinationUser) throws BusinessException;
}

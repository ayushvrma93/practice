package com.twentythree.phonepe;

import com.twentythree.phonepe.beans.Transaction;
import com.twentythree.phonepe.beans.User;
import com.twentythree.phonepe.exceptions.BusinessException;

import java.util.List;

public interface IUserManager {
    User create(User user) throws BusinessException;
    User modify(int id, User user) throws BusinessException;

    Transaction handleTransaction(Transaction transaction);

    List<Transaction> showTransactions(User sourceUser, User destinationUser) throws BusinessException;
}

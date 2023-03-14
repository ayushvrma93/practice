package com.twentythree.phonepe;

import com.twentythree.phonepe.beans.Group;
import com.twentythree.phonepe.beans.Transaction;
import com.twentythree.phonepe.exceptions.BusinessException;

import java.util.List;

public interface IGroupManager {

    Group create (Group group) throws BusinessException;
    Group modify(String id, Group group) throws BusinessException;

    Group delete(String id) throws BusinessException;

    Group get(String id);

    Transaction handleTransaction(Transaction transaction);

    List<Transaction>[][] showTransactions(String groupId);
}

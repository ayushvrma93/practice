package com.twentythree.phonepe;

import com.twentythree.phonepe.beans.Transaction;
import com.twentythree.phonepe.beans.User;
import com.twentythree.phonepe.beans.enums.TransactionType;
import com.twentythree.phonepe.exceptions.BusinessException;

import java.util.List;

public class TransactionHandlerImpl implements ITransactionHandler{

    private static final ITransactionHandler INSTANCE = new TransactionHandlerImpl();

    private static IGroupManager groupManager = GroupManagerImpl.getINSTANCE();
    private static IUserManager userManager = UserManagerImpl.getINSTANCE();

    public static ITransactionHandler getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Transaction handle(Transaction transaction) {

        if(TransactionType.GROUP_TRANSACTION.equals(transaction.getTransactionType())){
            return groupManager.handleTransaction(transaction);
        }

        else{
            return userManager.handleTransaction(transaction);
        }
    }

    @Override
    public List<Transaction>[][] getTransactionsForGroup(TransactionType transactionType, String id){
        return groupManager.showTransactions(id);
    }

    @Override
    public List<Transaction> getTransactionsBetweenUsers(User sourceUser, User destinationUser) throws BusinessException {
        return userManager.showTransactions(sourceUser, destinationUser);
    }
}

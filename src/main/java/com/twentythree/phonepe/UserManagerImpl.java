package com.twentythree.phonepe;

import com.twentythree.phonepe.beans.Transaction;
import com.twentythree.phonepe.beans.User;
import com.twentythree.phonepe.exceptions.BusinessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagerImpl implements IUserManager{

    private static final IUserManager INSTANCE = new UserManagerImpl();
    private static final ISplittingStrategies splittingStrategies = EqualSplittingStrategy.getInstance();
    private Map<User, Map<User, List<Transaction>>> userTransaction = new HashMap<>();

    public UserManagerImpl(){}

    public static IUserManager getINSTANCE() {
        return INSTANCE;
    }

    Map<String, User> users = new HashMap<>();


    @Override
    public User create(User user) throws BusinessException {

        if(users.containsKey(user.getEmail())){
            throw new BusinessException("User already exists");
        }
        users.put(user.getEmail(), user);
        return users.get(user.getEmail());
    }

    @Override
    public User modify(int id, User user) throws BusinessException {
        throw new BusinessException("Not yet implemented");
    }

    @Override
    public Transaction handleTransaction(Transaction transaction) {

        User sourceUser = transaction.getSourceUser();
        User destUser = users.get(transaction.getDestinationId());

        List<User> users = new ArrayList<>();
        users.add(sourceUser);
        users.add(destUser);

        Map<User, List<Transaction>> userTransactions = userTransaction.get(sourceUser);
        transaction.setAmount(splittingStrategies.handle(users, transaction.getAmount()));

        if(userTransactions == null){
            userTransactions = new HashMap<>();
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);
            userTransactions.put(destUser, transactions);
        } else {
            List<Transaction> transactions = userTransactions.get(destUser);
            if(transactions == null) {
                transactions = new ArrayList<>();
            }
            transactions.add(transaction);
            userTransactions.put(destUser, transactions);
        }
        userTransaction.put(sourceUser, userTransactions);
        return transaction;
    }

    @Override
    public List<Transaction> showTransactions(User sourceUser, User destinationUser) throws BusinessException {

        Map<User, List<Transaction>> transactionsBetweenUser = userTransaction.get(sourceUser);

        if(transactionsBetweenUser == null || transactionsBetweenUser.isEmpty()){
            throw new BusinessException("No transactions available between users");
        }

        List<Transaction> transactions = transactionsBetweenUser.get(destinationUser);

        if(transactions == null || transactions.isEmpty()){
            throw new BusinessException("No transactions available between users");
        }
        return transactions;
    }
}

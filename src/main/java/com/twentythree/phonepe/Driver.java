package com.twentythree.phonepe;

import com.twentythree.phonepe.beans.Group;
import com.twentythree.phonepe.beans.Transaction;
import com.twentythree.phonepe.beans.User;
import com.twentythree.phonepe.beans.enums.TransactionType;
import com.twentythree.phonepe.exceptions.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static void main(String[] args) {
        ITransactionHandler transactionHandler = TransactionHandlerImpl.getINSTANCE();

        List<User> users1 = new ArrayList<>();

        User user1 = new User("user1", "user1@gmail.com");
        User user2 = new User("user2", "user2@gmail.com");
        User user3 = new User("user3", "user3@gmail.com");

        IUserManager userManager = UserManagerImpl.getINSTANCE();
        try {
            userManager.create(user1);
            userManager.create(user2);
            userManager.create(user3);
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }


        users1.add(user1);
        users1.add(user2);
        users1.add(user3);

        Group group1 = new Group("group1", users1);
        IGroupManager groupManager = GroupManagerImpl.getINSTANCE();

        try {
            Group createdGroup =  groupManager.create(group1);
            System.out.println(createdGroup);
        } catch (BusinessException e) {
            throw new RuntimeException(e.toString());
        }

        Transaction transaction1 = new Transaction(1, 100.0f, TransactionType.INDIVIDUAL_TRANSACTION, user1, user2.getEmail());
        Transaction transaction2 = new Transaction(2, 80.0f, TransactionType.INDIVIDUAL_TRANSACTION, user2, user1.getEmail());
        System.out.println(transactionHandler.handle(transaction1));
        System.out.println(transactionHandler.handle(transaction2));

        Transaction transaction3 = new Transaction(3, 100.0f, TransactionType.GROUP_TRANSACTION, user1, group1.getId());
        Transaction transaction4 = new Transaction(4, 10.0f, TransactionType.GROUP_TRANSACTION, user2, group1.getId());
        Transaction transaction5 = new Transaction(5, 80.0f, TransactionType.GROUP_TRANSACTION, user3, group1.getId());

        System.out.println(groupManager.handleTransaction(transaction3));
        System.out.println(groupManager.handleTransaction(transaction4));
        System.out.println(groupManager.handleTransaction(transaction5));

        List<Transaction>[][] transaction = groupManager.showTransactions("group1");
        System.out.println("Transactions for group: ");

        for (List<Transaction>[] lists : transaction) {
            System.out.println();
            for (int j = 0; j < lists.length; j++) {
                System.out.println(lists[j] + " ");
            }
        }

        System.out.println("Transactions for users: ");

        try {
            List<Transaction> transactionsForUser = userManager.showTransactions(user1, user2);
            System.out.println(transactionsForUser);
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }
}

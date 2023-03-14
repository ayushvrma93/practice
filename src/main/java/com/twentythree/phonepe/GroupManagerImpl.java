package com.twentythree.phonepe;

import com.twentythree.phonepe.beans.Group;
import com.twentythree.phonepe.beans.Transaction;
import com.twentythree.phonepe.beans.User;
import com.twentythree.phonepe.exceptions.BusinessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupManagerImpl implements IGroupManager{

    private static final IGroupManager INSTANCE = new GroupManagerImpl();
    private static ISplittingStrategies splittingStrategies= EqualSplittingStrategy.getInstance();

    public GroupManagerImpl(){

    }

    public static IGroupManager getINSTANCE() {
        return INSTANCE;
    }

    private Map<String, Group> groups = new HashMap<>();
    private Map<Group, List<User>> groupAndUsers = new HashMap<>();
    //

    @Override
    public Group create(Group group) throws BusinessException {

        if(groups.containsKey(group.getId())){
            throw new BusinessException("Group id already exists");
        }

        Map<User, Integer> userVsIndices = new HashMap<>();
        int userIndex = 0;

        for(User user : group.getUsers()){
            userVsIndices.put(user, userIndex);
            userIndex++;
        }

        group.setUserVsIndices(userVsIndices);
        int size = group.getUsers().size();
        List<Transaction>[][] transactionMatrix = new List[size][size];
        group.setTransactionsAndUsers(transactionMatrix);

        groups.put(group.getId(), group);
        return groups.get(group.getId());
    }

    @Override
    public Group modify(String id, Group group) throws BusinessException {
            throw new BusinessException("Not yet implemented");
    }

    @Override
    public Group delete(String id) throws BusinessException {

        if(!groups.containsKey(id)){
            throw new BusinessException("Invalid group id provided");
        }
        Group toBeDeleted = groups.get(id);
        groups.remove(id);
        return toBeDeleted;
    }

    @Override
    public Group get(String id) {
        return groups.get(id);
    }

    //
    @Override
    public Transaction handleTransaction(Transaction transaction) {

        Group currGroup = groups.get(transaction.getDestinationId());
        Float amountPerUser = splittingStrategies.handle(currGroup.getUsers(), transaction.getAmount());
        transaction.setAmount(amountPerUser);

        int sourceIndex = currGroup.getUserVsIndices().get(transaction.getSourceUser());

        List<Transaction>[][] transactionsAndUsers = currGroup.getTransactionsAndUsers();

        List<Transaction>[] transactionsForUser = transactionsAndUsers[sourceIndex];

        for(int i=0; i<transactionsForUser.length; i++){

            if(i == sourceIndex) continue;

            List<Transaction> transactions = transactionsForUser[i];
            Transaction copy = transaction.copy();

            if(transactions == null){
                transactions = new ArrayList<>();
            }
            transactions.add(copy);
            transactionsForUser[i] = transactions;
        }
        return transaction;
    }

    @Override
    public List<Transaction>[][] showTransactions(String groupId) {

        Group group = groups.get(groupId);

        List<Transaction>[][] transactionsAndUsers = group.getTransactionsAndUsers();

        return transactionsAndUsers;
    }
}

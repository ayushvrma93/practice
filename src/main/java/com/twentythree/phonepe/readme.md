Assumptions:

Although, this had quite a larger scope, I have tried to make some assumptions, stating some of them:

1. There is only 1-1 transaction for users. Only, the user who is owed can add the transaction.
2. Modifications to groups and users are very limited or are not allowed at all.
3. For any group-transactions, the only split equally has been implemented yet.
4. The show transactions facility is a 2D matrix of User X User which holds a list of transactions for each user pair. The row index denotes the user who has paid and all corresponding cells in that row
    denotes how much he is owed by the current user.
5. 2D matrix was chosen so that it could easily be extended to add "simplify-debts" functionality.
6. In real-world scenario, the addition of amount for each usr should be the responsibility of the classes extending the "ISplittingHandler" and not the "GroupManagerImpl" or "UserManagerImpl". This has been
    done, just to save sometime as it is already a very long assignment for the given time.

In case I have missed some assumptions, I would explain later.

Points to add:

1. Since each cell in the matrix represents the transactions owed by the column user to the row user, to get the total amount owed by
   someone for that person is the sum of amounts in the list of transactions. Could not add this, right now, it just shows add the 
   transactions, but not summation.

Thanks!!
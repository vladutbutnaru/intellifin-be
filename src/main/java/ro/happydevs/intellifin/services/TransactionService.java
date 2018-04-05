package ro.happydevs.intellifin.services;

import ro.happydevs.intellifin.models.Transaction;
import ro.happydevs.intellifin.repositories.TransactionRepository;
import ro.happydevs.intellifin.repositories.UserRepository;

public class TransactionService {

    private TransactionRepository transactionRepository = new TransactionRepository();

    public void createTransaction(Transaction transaction, String token){
        UserService userService = new UserService();

        transaction.setUserId(userService.getUserForToken(token).getId());

        transactionRepository.create(transaction);



    }
}

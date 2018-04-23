package ro.happydevs.intellifin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.Transaction;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.repositories.TokenRepository;
import ro.happydevs.intellifin.repositories.TransactionRepository;
import ro.happydevs.intellifin.repositories.UserRepository;

import java.util.ArrayList;

public class TransactionService {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);
    private TransactionRepository transactionRepository = new TransactionRepository();
    private TokenRepository tokenRepository = new TokenRepository();

    public boolean createTransaction(Transaction transaction, String token) {

        UserService userService = new UserService();

        logger.info("[Transaction Service Create] - Called");

        transaction.setUserId(userService.getUserForToken(token).getId());
        return transactionRepository.create(transaction);

    }

}

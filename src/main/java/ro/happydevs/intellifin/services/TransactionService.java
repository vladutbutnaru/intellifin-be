package ro.happydevs.intellifin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.happydevs.intellifin.models.Account;
import ro.happydevs.intellifin.models.Transaction;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.repositories.AccountRepository;
import ro.happydevs.intellifin.repositories.TokenRepository;
import ro.happydevs.intellifin.repositories.TransactionRepository;


import java.util.ArrayList;

import java.sql.Date;
import java.util.List;

public class TransactionService {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);
    private TransactionRepository transactionRepository = new TransactionRepository();
    private TokenRepository tokenRepository = new TokenRepository();
    private AccountRepository accountRepository = new AccountRepository();

    public boolean createTransaction(Transaction transaction, String token) {

        User u = tokenRepository.getUserByToken(token);

        logger.info("[Transaction Service Create] - Called");

        for (int i = 0; i < accountRepository.getAccountsForUserId(u.getId()).size(); i++)
            if (transaction.getAccountId() == accountRepository.getAccountsForUserId(u.getId()).get(i).getId()) {
                if (transaction.getType() == 0)
                    if (transaction.getAmount() <= accountRepository.getAccountsForUserId(u.getId()).get(i).getSold()) {
                        transaction.setAmount(-transaction.getAmount());
                }

                    else {
                        logger.error("[Transaction Service Create] - Failure: Insufficient Funds");
                        return false;
                    }

                transaction.setUserId(u.getId());
                return transactionRepository.create(transaction);


            }
        return false;


    }

    public ArrayList<Transaction> getAllEarnings(String token, Date startDate) {

        User u = tokenRepository.getUserByToken(token);

        for (Transaction trans : transactionRepository.getAll()) {

            if (trans.getUserId() == u.getId())

                return transactionRepository.getAllEarnings(u.getId(), startDate);
        }
    return null;
    }


    public ArrayList<Transaction> getAllExpenses(String token, Date startDate) {

        User u = tokenRepository.getUserByToken(token);

        for (Transaction trans : transactionRepository.getAll()) {

            if (trans.getUserId() == u.getId())

                return transactionRepository.getAllExpenses(u.getId(),startDate);
        }
        return null;
    }
}

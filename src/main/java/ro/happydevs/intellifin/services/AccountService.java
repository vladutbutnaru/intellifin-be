package ro.happydevs.intellifin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.Account;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.repositories.AccountRepository;
import ro.happydevs.intellifin.repositories.TokenRepository;

import java.util.ArrayList;

public class AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);
    private TokenRepository tokenRepository = new TokenRepository();

    private AccountRepository accountRepository = new AccountRepository();

    public boolean createAccount(Account account, String token) {
        User u = tokenRepository.getUserByToken(token);

        logger.info("[Account Service Create] - Called");

        if (account.getUserId() == u.getId()) {
                account.setUserId(u.getId());
                return accountRepository.create(account); }

        return false;

    }

    public boolean deleteAccount(int id, String token) {
        User u = tokenRepository.getUserByToken(token);

        logger.info("[Account Service Delete] - Called");

        for (int i = 0; i < accountRepository.getAccountsForUserId(u.getId()).size(); i++)
             if (accountRepository.getAccountsForUserId(u.getId()).get(i).getId() == id)
                return accountRepository.delete(id);

        return false;

    }

    public ArrayList<Account> getAccountsForUser(String token) {
        User u = tokenRepository.getUserByToken(token);

        return accountRepository.getAccountsForUserId(u.getId());


    }

    public Account getAccountById(int accountId, String token) {
        User u = tokenRepository.getUserByToken(token);

        logger.info("[Account Service GetById] - Called");

        for (int i = 0; i < accountRepository.getAccountsForUserId(u.getId()).size(); i++)
            if (accountRepository.getAccountsForUserId(u.getId()).get(i).getId() == accountId)
                return (Account) accountRepository.getById(accountId);

        return null;

    }

    public boolean updateAccount(Account newAccount, String token) {
        User u = tokenRepository.getUserByToken(token);

        logger.info("[Account Service Update] - Called");

        for (int i = 0; i < accountRepository.getAccountsForUserId(u.getId()).size(); i++)
            if (accountRepository.getAccountsForUserId(u.getId()).get(i).getId() == newAccount.getId())
                return accountRepository.update(newAccount);

        return false;
    }

}

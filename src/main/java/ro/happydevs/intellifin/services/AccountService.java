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

        //test
        account.setUserId(u.getId());

        logger.info("[Account Service Create] - Called");

        return accountRepository.create(account);


    }

    public ArrayList<Account> getAccountsForUser(String token) {
        User u = tokenRepository.getUserByToken(token);

        return accountRepository.getAccountsForUserId(u.getId());


    }

    public Account getAccountById(int accountId) {
        return (Account) accountRepository.getById(accountId);

    }

}

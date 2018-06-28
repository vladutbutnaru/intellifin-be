package ro.happydevs.intellifin.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.Account;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.repositories.AccountRepository;
import ro.happydevs.intellifin.repositories.TokenRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TokenService tokenService;

    public boolean createAccount(Account account, String token) {
        User u = tokenService.getUserByToken(token);

        account.setUserId(u.getId());

        logger.info("[Account Service Create] - Called");

        accountRepository.save(account);

        return true;

    }

    public List<Account> getAccountsForUser(String token) {
        User u = tokenService.getUserByToken(token);


        return accountRepository.findAllAccountsForUser(u.getId());


    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).get();

    }

}

package ro.happydevs.intellifin.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.Account;
import ro.happydevs.intellifin.models.business.User;
import ro.happydevs.intellifin.repositories.AccountRepository;
import ro.happydevs.intellifin.repositories.TokenRepository;
import ro.happydevs.intellifin.utils.reporting.IntelliLogger;

import java.util.List;

@Service
public class AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    IntelliLogger intelliLogger;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TokenService tokenService;

    /**
     * Creates an account for a user based on a token
     * received from front-end
     *
     * @param account
     * @param token
     * @return boolean
     */
    public boolean createAccount(Account account, String token) {
        User u = tokenService.getUserByToken(token);

        account.setUserId(u.getId());

        logger.info("[Account Service Create] - Called");

        accountRepository.save(account);

        return true;

    }

    /**
     * List all accounts for a given user
     *
     * @param token
     * @return List<Account>
     */
    public List<Account> getAccountsForUser(String token) {
        User u = tokenService.getUserByToken(token);


        return accountRepository.findAllAccountsForUser(u.getId());


    }

    /**
     * Gets an account by ID
     *
     * @param accountId
     * @return Account
     */
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).get();

    }

}

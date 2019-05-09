package ro.happydevs.intellifin.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.*;
import ro.happydevs.intellifin.models.dto.GenericMessageDTO;
import ro.happydevs.intellifin.models.dto.business.accounts.CreateAccountDTO;
import ro.happydevs.intellifin.models.reporting.LogLine;
import ro.happydevs.intellifin.repositories.AccountRepository;
import ro.happydevs.intellifin.repositories.HouseholdMemberRepository;
import ro.happydevs.intellifin.repositories.TokenRepository;
import ro.happydevs.intellifin.utils.reporting.IntelliLogger;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    HouseholdService householdService;

    @Autowired
    HouseholdMemberRepository householdMemberRepository;

    @Autowired
    TransactionService transactionService;

    /**
     * Creates an account for a user based on a token
     * received from front-end
     *
     * @param createAccountDTO
     * @param token
     * @return boolean
     */
    public boolean createAccount(CreateAccountDTO createAccountDTO, String token) {
        User u = tokenService.getUserByToken(token);

        Account account = new Account();
        account.setDeleted(false);
        account.setCreditCard(createAccountDTO.isCreditCard());
        account.setCreatedAt(new Date());
        account.setCreditCardLimit(createAccountDTO.getCreditCardLimit());
        account.setCurrency(createAccountDTO.getAccountCurrency());
        account.setDescription(createAccountDTO.getAccountDescription());
        account.setName(createAccountDTO.getAccountName());
        account.setIban(createAccountDTO.getIban());
        account.setSharedWithHousehold(createAccountDTO.isShareWithHousehold());
        account.setSold(createAccountDTO.getInitialSold());
        account.setType(createAccountDTO.getType());
        account.setUserId(u.getId());

        account = accountRepository.save(account);

        intelliLogger.createAccountLog(account.getName(), u.getId(), account.getId());

        return true;

    }


    public List<Account> getOwnAccountsForUser(String token) {
        User u = tokenService.getUserByToken(token);

        intelliLogger.createLog(new LogLine(u.getId(), "LIST_OWN_ACCOUNTS"));
        return accountRepository.findAllAccountsForUser(u.getId());

    }

    public List<Account> getOwnAccountsForUser(Long userId) {
        intelliLogger.createLog(new LogLine(userId, "LIST_OWN_ACCOUNTS"));
        return accountRepository.findAllAccountsForUser(userId);

    }

    /**
     * List all accounts for a given user including the ones shared within household
     *
     * @param token
     * @return List<Account>
     */

    public List<Account> getAccountsForUser(String token) {
        User u = tokenService.getUserByToken(token);

        List<Account> listOfAccounts = new ArrayList<>();

        //own accounts
        listOfAccounts.addAll(accountRepository.findAllAccountsForUser(u.getId()));

        //check if the user has any household associated
        if (householdService.getOwnHousehold(token) != null)
            for (Household household : householdService.getOwnHousehold(token)) {
                for (HouseholdMember householdMember : householdMemberRepository.findHouseholdMembersForHouseholdId(household.getId())) {
                    for (Account account : getOwnAccountsForUser(householdMember.getId())) {
                        if (account.isSharedWithHousehold()) {
                            //prefix the household shared accounts
                            account.setName("[" + householdService.householdRepository.findById(account.getSharedHouseholdId()).get().getName() + "] " + account.getName());
                            listOfAccounts.add(account);
                        }
                    }
                }
            }
        intelliLogger.createLog(new LogLine(u.getId(), "LIST_ALL_ACCOUNTS"));

        return listOfAccounts;

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

    /**
     * Updates account information by account ID
     *
     * @param account
     * @param token
     * @return nothing
     */
    public void updateAccount(Account account, String token) {
        intelliLogger.updateAccountLog(account.getName(), tokenService.getUserByToken(token).getId(), account.getId());

        accountRepository.save(account);

    }

    /**
     * Marks an account as deleted by account ID
     * Marks all transactions for the respective account as deleted
     *
     * @param accountId
     * @param token
     * @return GenericMessageDTO
     */
    public GenericMessageDTO deleteAccount(Long accountId, String token) {
        intelliLogger.createLog(new LogLine(tokenService.getUserByToken(token).getId(), "[ACCOUNT DELETE] - " + accountId));

        Account accountToDelete = accountRepository.findById(accountId).get();
        accountToDelete.setDeleted(true);
        accountRepository.save(accountToDelete);

        List<Transaction> transactionsForTheAccount = transactionService.findAllTransactionsForAccountId(accountToDelete.getId());
        for (Transaction t : transactionsForTheAccount) {

            transactionService.deleteTransaction(t);
        }
        intelliLogger.deleteAccountLog(accountToDelete.getName(), tokenService.getUserByToken(token).getId());
        return new GenericMessageDTO(1, "Account and transactions deleted successfully!", true);


    }

}

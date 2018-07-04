package ro.happydevs.intellifin.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.Account;
import ro.happydevs.intellifin.models.business.Transaction;
import ro.happydevs.intellifin.repositories.AccountRepository;
import ro.happydevs.intellifin.repositories.TransactionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    public void createRegularTransaction(Transaction transaction, String token) {

        transaction.setUserId(tokenService.getUserByToken(token).getId());
        transaction.setRecurring(false);
        transaction.setCreatedAt(new Date());
        transaction.setDeleted(false);


        Account account = accountService.getAccountById(transaction.getAccountId());
        if(transaction.isEarning())
             account.setSold(account.getSold()+transaction.getAmount());
        else
            account.setSold(account.getSold()-transaction.getAmount());

        accountRepository.save(account);

        transactionRepository.save(transaction);


    }

    public ArrayList<Transaction> findAllTransactionsForUser(String token){
      ArrayList<Transaction> transactions = new ArrayList<>();

        for(Account a : accountService.getAccountsForUser(token)){
            transactions.addAll(findAllTransactionsForAccountId(a.getId()));

        }
        return transactions;

    }

    public List<Transaction> findAllTransactionsForAccountId(Long accountId){
        return transactionRepository.findAllForAccount(accountId);

    }
}

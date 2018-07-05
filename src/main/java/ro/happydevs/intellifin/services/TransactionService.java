package ro.happydevs.intellifin.services;


import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.Account;
import ro.happydevs.intellifin.models.business.ProductPrice;
import ro.happydevs.intellifin.models.business.Transaction;
import ro.happydevs.intellifin.models.dto.GenericMessageDTO;
import ro.happydevs.intellifin.models.nonpersistent.TransactionWithProducts;
import ro.happydevs.intellifin.models.reporting.LogLine;
import ro.happydevs.intellifin.repositories.AccountRepository;
import ro.happydevs.intellifin.repositories.ProductPriceRepository;
import ro.happydevs.intellifin.repositories.TransactionRepository;
import ro.happydevs.intellifin.utils.reporting.IntelliLogger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private static Logger logger = LoggerFactory.getLogger(TransactionService.class);
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    IntelliLogger intelliLogger;

    @Autowired
    ProductPriceRepository productPriceRepository;
    @Autowired
    ProductPriceService productPriceService;


    public Transaction createRegularTransaction(Transaction transaction, String token) {

        transaction.setUserId(tokenService.getUserByToken(token).getId());
        transaction.setRecurring(false);
        transaction.setCreatedAt(new Date());
        transaction.setDeleted(false);


        Account account = accountService.getAccountById(transaction.getAccountId());
        if (transaction.isEarning())
            account.setSold(account.getSold() + transaction.getAmount());
        else
            account.setSold(account.getSold() - transaction.getAmount());

        accountRepository.save(account);

        intelliLogger.createLog(new LogLine(tokenService.getUserByToken(token).getId(),"[CREATED] - " + transaction.getId()));

        return transactionRepository.save(transaction);




    }

    public ArrayList<Transaction> findAllTransactionsForUser(String token) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        for (Account a : accountService.getAccountsForUser(token)) {
            transactions.addAll(findAllTransactionsForAccountId(a.getId()));

        }
        return transactions;

    }

    public List<Transaction> findAllTransactionsForAccountId(Long accountId) {
        return transactionRepository.findAllForAccount(accountId);

    }

    public void deleteTransaction(Transaction t){
        t.setDeleted(true);
        transactionRepository.save(t);

    }

    public List<Transaction> getTodayTransactions(String token){
        Date today = new Date();
        Date todayMorning = DateUtils.truncate(today, Calendar.DATE);
        Date todayEvening = DateUtils.addSeconds(DateUtils.addMinutes(DateUtils.addHours(todayMorning, 23), 59), 59);

        List<Transaction> transactions = new ArrayList<Transaction>();
        for(Account a : accountService.getAccountsForUser(token)){
            transactions.addAll(transactionRepository.findAllForAccountBetweenStartEndDates(a.getId(),todayMorning,todayEvening));

        }
        return transactions;

    }

    public List<Transaction> getMonthlyTransactions(String token){
        Date firstOfMonth = new Date();
        firstOfMonth.setDate(0);
        Date lastOfMonth = new Date();
        lastOfMonth = (DateUtils.addMonths(firstOfMonth,1));


        List<Transaction> transactions = new ArrayList<Transaction>();
        for(Account a : accountService.getAccountsForUser(token)){
            transactions.addAll(transactionRepository.findAllForAccountBetweenStartEndDates(a.getId(),firstOfMonth,lastOfMonth));

        }
        return transactions;

    }

    public GenericMessageDTO createTransactionWithProducts(TransactionWithProducts transactionWithProducts, String token){
            // create the transaction as regular one
            Transaction savedTransaction = createRegularTransaction(transactionWithProducts.getTransaction(), token);

            //save the product prices
            for(ProductPrice productPrice : transactionWithProducts.getProductPrices()){
                productPrice.setDeleted(false);
                productPriceRepository.save(productPrice);
            }

            return new GenericMessageDTO(1,"Transaction with products created succesfully", true);
    }



}

package ro.happydevs.intellifin.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.Transaction;
import ro.happydevs.intellifin.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TokenService tokenService;

    public void createTransaction(Transaction transaction, String token) {

        transaction.setUserId(tokenService.getUserByToken(token).getId());

        transactionRepository.save(transaction);


    }
}

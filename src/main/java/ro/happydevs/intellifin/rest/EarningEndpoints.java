package ro.happydevs.intellifin.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.Account;
import ro.happydevs.intellifin.models.Transaction;
import ro.happydevs.intellifin.services.AccountService;
import ro.happydevs.intellifin.services.TokenService;
import ro.happydevs.intellifin.services.TransactionService;
import java.sql.Date;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/rest/transactions")
public class EarningEndpoints {

    private TransactionService transactionService = new TransactionService();

    private TokenService tokenService = new TokenService();
    private AccountService accountService = new AccountService();

    @RequestMapping(value="/earning/add")
    public ResponseEntity<?> addEaerning(
            @RequestHeader("Authentication") String token,
            @RequestBody Transaction transaction
    ){

        if (tokenService.verifyToken(token)) {
            //check if the account belongs to the user
            for(Account account : accountService.getAccountsForUser(token)){
                if(account.getId() == transaction.getAccountId()){
                    transactionService.createTransaction(transaction,token);
                    return ResponseEntity.ok(transaction);

                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");



    }

    @RequestMapping(value="/earning/getAllFromStartDate")
    public ResponseEntity<?> getAllFromStartDate(@RequestHeader("Authentication") String token,
                                    @RequestParam(value = "startDate") Date date){

        if (tokenService.verifyToken(token)) {

                    return ResponseEntity.ok(transactionService.getAllEarnings(token,date));
            }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

    }

}

package ro.happydevs.intellifin.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.Account;
import ro.happydevs.intellifin.models.Transaction;
import ro.happydevs.intellifin.services.AccountService;
import ro.happydevs.intellifin.services.TokenService;
import ro.happydevs.intellifin.services.TransactionService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/rest/transactions")
public class TransactionEndpoints {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/expense/add", method = RequestMethod.POST)
    public ResponseEntity<?> addExpense(
            @RequestHeader("Authentication") String token,
            @RequestBody Transaction transaction
    ) {

        if (tokenService.verifyToken(token)) {
            //check if the account belongs to the user
            for (Account account : accountService.getAccountsForUser(token)) {
                if (account.getId() == transaction.getAccountId()) {
                    transactionService.createTransaction(transaction, token);
                    return ResponseEntity.ok(transaction);

                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }


}

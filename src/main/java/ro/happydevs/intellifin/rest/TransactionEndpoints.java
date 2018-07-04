package ro.happydevs.intellifin.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.business.Account;
import ro.happydevs.intellifin.models.business.Transaction;
import ro.happydevs.intellifin.models.dto.GenericMessageDTO;
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

    @RequestMapping(value = "/transaction/regular/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRegularTransaction(
            @RequestHeader("Authentication") String token,
            @RequestBody Transaction transaction
    ) {

        if (tokenService.verifyToken(token)) {
            //check if the account belongs to the user
            for (Account account : accountService.getAccountsForUser(token)) {
                if (account.getId() == transaction.getAccountId()) {
                    transactionService.createRegularTransaction(transaction, token);
                    return ResponseEntity.ok(new GenericMessageDTO(1,"Transaction created!",true));

                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }
    @RequestMapping(value = "/transaction/list/all", method = RequestMethod.GET)
    public ResponseEntity<?> listAllTransactions(
            @RequestHeader("Authentication") String token
    ) {

        if (tokenService.verifyToken(token)) {
           return ResponseEntity.ok(transactionService.findAllTransactionsForUser(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

    }

    @RequestMapping(value = "/transaction/list/account", method = RequestMethod.GET)
    public ResponseEntity<?> listAllTransactionsForAccount(
            @RequestHeader("Authentication") String token,
            @RequestParam("id") Long accountId
    ) {

        if (tokenService.verifyToken(token)) {
            return ResponseEntity.ok(transactionService.findAllTransactionsForAccountId(accountId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

    }




}

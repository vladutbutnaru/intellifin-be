package ro.happydevs.intellifin.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.Account;
import ro.happydevs.intellifin.services.AccountService;
import ro.happydevs.intellifin.services.TokenService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/rest/accounts")
public class AccountEndpoints {
    private static Logger logger = LoggerFactory.getLogger(AccountEndpoints.class);
    private AccountService accountService = new AccountService();
    private TokenService tokenService = new TokenService();


    @RequestMapping(value = "/create")
    public ResponseEntity<?> createAccount(@RequestBody Account account,
                                           @RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {

            accountService.createAccount(account, token);
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

    }

    @RequestMapping(value = "/list")
    public ResponseEntity<?> listAccounts(@RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(accountService.getAccountsForUser(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

    }

    @RequestMapping(value = "/get")
    public ResponseEntity<?> getAccount(@RequestHeader("Authentication") String token,
                                        @RequestParam(value = "id") int id) {
        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(accountService.getAccountById(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

    }

}

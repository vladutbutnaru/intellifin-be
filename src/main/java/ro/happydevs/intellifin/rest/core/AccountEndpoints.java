package ro.happydevs.intellifin.rest.core;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.business.Account;
import ro.happydevs.intellifin.models.dto.business.accounts.CreateAccountDTO;
import ro.happydevs.intellifin.services.AccountService;
import ro.happydevs.intellifin.services.TokenService;


@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/rest/accounts")
public class AccountEndpoints {
    private static Logger logger = LoggerFactory.getLogger(AccountEndpoints.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private TokenService tokenService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation("Create a new account")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountDTO createAccountDTO,
                                           @RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {

            accountService.createAccount(createAccountDTO, token);
            return ResponseEntity.ok(createAccountDTO);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("Get a list of all user's accounts and shared HouseHold accounts")
    public ResponseEntity<?> listAccounts(@RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(accountService.getAccountsForUser(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ApiOperation("Get information of a User's account based on the ID")
    public ResponseEntity<?> getAccount(@RequestHeader("Authentication") String token,
                                        @RequestParam(value = "id") Long id) {
        if (tokenService.verifyToken(token)) {
            return ResponseEntity.ok(accountService.getAccountById(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation("Update a User's account")
    public ResponseEntity<?> updateAccount(@RequestBody Account account,
                                           @RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {

            accountService.updateAccount(account, token);
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation("Delete a User's account and the corresponding transactions")
    public ResponseEntity<?> deleteAccount(@RequestHeader("Authentication") String token,
                                           @RequestParam(value = "id") Long id) {
        if (tokenService.verifyToken(token)) {
            return ResponseEntity.ok(accountService.deleteAccount(id, token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

}

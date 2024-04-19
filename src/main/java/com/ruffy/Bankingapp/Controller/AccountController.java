package com.ruffy.Bankingapp.Controller;

import com.ruffy.Bankingapp.DTO.AccountDto;
import com.ruffy.Bankingapp.Services.Implementation.AccountServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
//    @Autowired
    private final AccountServiceImplementation accountServiceImplementation;

    public AccountController(AccountServiceImplementation accountServiceImplementation) {
        this.accountServiceImplementation = accountServiceImplementation;
    }

    // Create the Endpoints

    // Create new Account User
    @PostMapping("/addNewAccountUser")
    public ResponseEntity<AccountDto> createAccountUser(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountServiceImplementation.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/allAccounts")
    public ResponseEntity<List<AccountDto>> getAccountUsers(){
        List<AccountDto>accountDtos = accountServiceImplementation.getAllAccounts();
        return ResponseEntity.ok(accountDtos);
//        return new ResponseEntity<>(accountServiceImplementation.getAllAccounts(), HttpStatus.CREATED);
    }



    @GetMapping("/accountUser/{id}")
    public ResponseEntity<AccountDto> getAccountUserById(@PathVariable Long id){
        AccountDto accountDto = accountServiceImplementation.getAccountsById(id);
        return ResponseEntity.ok(accountDto);

//        return new ResponseEntity<>(accountServiceImplementation.getAccountsById(id), HttpStatus.OK);
    }

    // Deposit amount REST API
    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> depositToAccount(@PathVariable Long id, @RequestBody Map<String, Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto = accountServiceImplementation.depositToAccount(id, amount);
        return ResponseEntity.ok(accountDto);
//        return new ResponseEntity<>(accountServiceImplementation.getAccountsById(id), HttpStatus.OK);
    }

    // Withdraw from account REST API
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto> withDrawFromAccount(@PathVariable Long id, @RequestBody Map<String, Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto = accountServiceImplementation.withDrawFromAccount(id, amount);
        return ResponseEntity.ok(accountDto);
//        return new ResponseEntity<>(accountServiceImplementation.getAccountsById(id), HttpStatus.OK);
    }
}

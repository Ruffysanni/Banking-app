package com.ruffy.Bankingapp.mapper;

import com.ruffy.Bankingapp.DTO.AccountDto;
import com.ruffy.Bankingapp.Entity.Account;

public class AccountMapper {

    // Mapping from AccountDto to Account
    public  static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getAccountBalance()

        );
        return account;
    }

    // Mapping from Account to AccountDto
    public static AccountDto mapFromAccountToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getAccountBalance()
        );
        return accountDto;
    }
}

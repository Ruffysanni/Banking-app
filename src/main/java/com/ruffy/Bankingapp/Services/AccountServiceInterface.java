package com.ruffy.Bankingapp.Services;

import com.ruffy.Bankingapp.DTO.AccountDto;

import java.util.List;

public interface AccountServiceInterface {
    AccountDto createAccount(AccountDto account);
    List<AccountDto> getAllAccounts();
    AccountDto getAccountsById(Long id);
    AccountDto updateAccount(Long id, AccountDto accountDto);
    AccountDto deleteAccount(Long id);
    AccountDto depositToAccount(Long id, double amountToDeposit);
    AccountDto withDrawFromAccount(Long id, double amountToWithdraw);
}

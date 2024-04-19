package com.ruffy.Bankingapp.Services.Implementation;

import com.ruffy.Bankingapp.DTO.AccountDto;
import com.ruffy.Bankingapp.Entity.Account;
import com.ruffy.Bankingapp.Repository.AccountRepository;
import com.ruffy.Bankingapp.Services.AccountServiceInterface;
import com.ruffy.Bankingapp.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImplementation implements AccountServiceInterface {
    private AccountRepository accountRepository;

    public AccountServiceImplementation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Create an account
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapFromAccountToAccountDto( savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account -> AccountMapper.mapFromAccountToAccountDto(account))).collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccountsById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
        return AccountMapper.mapFromAccountToAccountDto(account);
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        return null;
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
        accountRepository.delete(account);

    }

    @Override
    public AccountDto depositToAccount(Long id, double amountToDeposit) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
        double totalBalance = account.getAccountBalance() + amountToDeposit;
        account.setAccountBalance(totalBalance);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapFromAccountToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withDrawFromAccount(Long id, double amountToWithdraw) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
        if(amountToWithdraw > account.getAccountBalance()){
            throw new RuntimeException("Insufficient account balance to initiate transaction.");
        }
        double totalBalance = account.getAccountBalance() - amountToWithdraw;
        account.setAccountBalance(totalBalance);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapFromAccountToAccountDto(savedAccount);
    }


}

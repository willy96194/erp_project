package com.example.erp.service;

import com.example.erp.dto.AccountDTO;
import com.example.erp.entity.Account;
import com.example.erp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountDTO> getAccounts() {
        return accountRepository.findAllToDTO();
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}

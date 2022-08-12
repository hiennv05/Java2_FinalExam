package com.vti.rw41.FinalExam.service;

import com.vti.rw41.FinalExam.dto.request.AccountRequest;
import com.vti.rw41.FinalExam.entity.Account;
import com.vti.rw41.FinalExam.form.AccountFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface IAccountService {
    Page<Account> getAllAccounts(Pageable pageable, String search, AccountFilterForm filterForm);

    public Optional<Account> getAccountById(Integer id);

    public Account addAccount(AccountRequest accountRequest);

    Optional<Account> updateAccountById(Integer id, AccountRequest accountRequest);
    public Optional<Account> deleteAccount(Integer id);
}

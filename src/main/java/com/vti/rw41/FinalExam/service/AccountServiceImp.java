package com.vti.rw41.FinalExam.service;

import com.vti.rw41.FinalExam.dto.request.AccountRequest;
import com.vti.rw41.FinalExam.entity.Account;
import com.vti.rw41.FinalExam.form.AccountFilterForm;
import com.vti.rw41.FinalExam.repository.IAccountRepository;
import com.vti.rw41.FinalExam.specification.AccountSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImp implements IAccountService {
    @Autowired
    IAccountRepository repository;

    @Override
    public Page<Account> getAllAccounts(Pageable pageable,
                                        String search,
                                        AccountFilterForm filterForm) {
        Specification<Account> where = AccountSpecification.buildWhere(search, filterForm);
        return repository.findAll(where, pageable);
    }


    @Override
    public Optional<Account> getAccountById(Integer id) {
        return repository.findById(id);
    }
    @Override
    public Account addAccount(AccountRequest accountRequest) {
        Account account = new Account();
        if(repository.isUserNameNotExists(account.getUser_name())) {
            System.out.println("user_name is exists");
        }
        account.setUser_name(accountRequest.getUser_name());
        account.setFull_name(accountRequest.getFirst_name()+" "+accountRequest.getLast_name());
        account.setRole(accountRequest.getRole());
        account.setDepartment(accountRequest.getDepartment());

        return repository.save(account);
    }

    @Override
    public Optional<Account> updateAccountById(Integer id, AccountRequest accountRequest) {
        Optional<Account> oldAccount = repository.findById(id);
        oldAccount.ifPresent(entity -> {
            entity.setUser_name(accountRequest.getUser_name());
            entity.setFull_name(accountRequest.getFirst_name()+" "+accountRequest.getLast_name());
            entity.setRole(accountRequest.getRole());
            entity.setDepartment(accountRequest.getDepartment());
        });
        return oldAccount;
    }

    public Optional<Account> deleteAccount(Integer id) {
        Optional<Account> account = repository.findById(id);
        account.ifPresent(a -> repository.delete(a));
        return account;
    }
}

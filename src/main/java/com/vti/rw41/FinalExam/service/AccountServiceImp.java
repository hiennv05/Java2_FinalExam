package com.vti.rw41.FinalExam.service;

import com.vti.rw41.FinalExam.dto.request.AccountRequest;
import com.vti.rw41.FinalExam.entity.Account;
import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.enumurations.RoleAcccount;
import com.vti.rw41.FinalExam.form.AccountFilterForm;
import com.vti.rw41.FinalExam.repository.IAccountRepository;
import com.vti.rw41.FinalExam.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AccountServiceImp implements IAccountService {
    @Autowired
    IAccountRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

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
    @Transactional
    public Account addAccount(AccountRequest accountRequest) {
        Account account = modelMapper.map(accountRequest, Account.class);
        return repository.save(account);
    }

    @Override
    @Transactional
    public Optional<Account> updateAccountById(Integer id, AccountRequest accountRequest) {
        Optional<Account> oldAccount = repository.findById(id);
        oldAccount.ifPresent(entity -> {
            entity.setUsername(accountRequest.getUsername());
            entity.setFirstname(accountRequest.getFirstname());
            entity.setLastname(accountRequest.getLastname());
            entity.setFullname(accountRequest.getFirstname() + " " + accountRequest.getLastname());
            entity.setRole(RoleAcccount.valueOf(accountRequest.getRole()));
            Department department = new Department();
            department.setId( accountRequest.getDepartmentId());
            entity.setDepartment(department);
        });
        return oldAccount;
    }

    @Transactional
    public Optional<Account> deleteAccount(Integer id) {
        Optional<Account> account = repository.findById(id);
        account.ifPresent(a -> repository.delete(a));
        return account;
    }
}

package com.vti.rw41.FinalExam.controller;

import com.vti.rw41.FinalExam.dto.request.AccountRequest;
import com.vti.rw41.FinalExam.dto.response.AccountDto;
import com.vti.rw41.FinalExam.entity.Account;
import com.vti.rw41.FinalExam.service.AccountServiceImp;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    AccountServiceImp service;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public Page<AccountDto> getAllAccounts(Pageable pageable) {
        Page<Account> entity = service.getAllAccounts(pageable);

        List<AccountDto> dto = modelMapper.map(entity.getContent(),
                new TypeToken<List<AccountDto>>() {
                }.getType());
        Page<AccountDto> dtoPage = new PageImpl<>(dto, pageable, entity.getTotalElements());
        return dtoPage;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Optional<AccountDto> getAccountById(@PathVariable Integer id) {
        Optional<Account> entity = service.getAccountById(id);
        AccountDto accountDto = null;
        if (entity.isPresent()) {
            accountDto = modelMapper.map(entity.get(), AccountDto.class);
        }
        return Optional.ofNullable(accountDto);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Account addAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return service.addAccount(accountRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Optional<Account> updateAccountById(@PathVariable Integer id,
                                               @Valid @RequestBody AccountRequest accountRequest) {
        return service.updateAccountById(id, accountRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Optional<Account> deleteAccount(@PathVariable Integer id) {
        return service.deleteAccount(id);
    }


}

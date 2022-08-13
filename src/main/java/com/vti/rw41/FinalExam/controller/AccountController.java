package com.vti.rw41.FinalExam.controller;

import com.vti.rw41.FinalExam.dto.request.AccountRequest;
import com.vti.rw41.FinalExam.dto.response.AccountDto;
import com.vti.rw41.FinalExam.entity.Account;
import com.vti.rw41.FinalExam.form.AccountFilterForm;
import com.vti.rw41.FinalExam.service.AccountServiceImp;
import com.vti.rw41.FinalExam.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    IAccountService service;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public Page<AccountDto> getAllAccounts(Pageable pageable,
                                           @RequestParam(value = "search", required = false) String search,
                                           AccountFilterForm filterForm) {
        Page<Account> entity = service.getAllAccounts(pageable, search, filterForm);

        List<AccountDto> dto = modelMapper.map(entity.getContent(), new TypeToken<List<AccountDto>>() {
                }.getType());
        Page<AccountDto> dtoPage = new PageImpl<>(dto, pageable, entity.getTotalElements());
        return dtoPage;
    }

//    @GetMapping("/principal")
//    public UserDetails getCurrentAccount(@AuthenticationPrincipal UserDetails principal) {
//        return principal;
//    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Optional<AccountDto> getAccountById(@PathVariable Integer id) {
        Optional<Account> entity = service.getAccountById(id);
        AccountDto accountDto = null;
        if (entity.isPresent()) {
            accountDto = modelMapper.map(entity.get(), AccountDto.class);
        }
        return Optional.ofNullable(accountDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Account addAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return service.addAccount(accountRequest);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Optional<Account> updateAccountById(@PathVariable Integer id,
                                               @Valid @RequestBody AccountRequest accountRequest) {
        return service.updateAccountById(id, accountRequest);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Optional<Account> deleteAccount(@PathVariable Integer id) {
        return service.deleteAccount(id);
    }


}

package com.vti.rw41.FinalExam.controller;

import com.vti.rw41.FinalExam.dto.request.AccountRequest;
import com.vti.rw41.FinalExam.dto.request.LoginRequest;
import com.vti.rw41.FinalExam.dto.request.ResetPasswordRequest;
import com.vti.rw41.FinalExam.dto.response.AccountDto;
import com.vti.rw41.FinalExam.entity.Account;
import com.vti.rw41.FinalExam.form.AccountFilterForm;
import com.vti.rw41.FinalExam.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    IAccountService service;

    @Autowired
    ModelMapper modelMapper;


    @GetMapping("/principal")
    public UserDetails getCurrentAccount(@AuthenticationPrincipal UserDetails principal) {
        return principal;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        return service.login(request);
    }

    @PostMapping("/register")
    public Account registerAccount(@Valid @RequestBody
                                   AccountRequest request) {
        return service.registerAccount(request);
    }

    @PostMapping("/forgot/{userName}")
    public void forgotPassword(@PathVariable String userName) {
        service.forgotPassword(userName);
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        return service.resetPassword(request);
    }

//    @GetMapping
//    public Page<AccountDto> getAllAccounts(Pageable pageable,
//                                           @RequestParam(value = "search", required = false) String search,
//                                           AccountFilterForm filterForm) {
//        Page<Account> entity = service.getAllAccounts(pageable, search, filterForm);
//
//        List<AccountDto> dto = modelMapper.map(entity.getContent(), new TypeToken<List<AccountDto>>() {
//        }.getType());
//        Page<AccountDto> dtoPage = new PageImpl<>(dto, pageable, entity.getTotalElements());
//        return dtoPage;
//    }

    @GetMapping()
    public Page<AccountDto> getAllAccountsV2(Integer departmentId, String role, String search, Pageable pageable) {
        Page<Account> entity = service.getAllAccountsV2(departmentId, role, search, pageable);

        List<AccountDto> dto = modelMapper.map(entity.getContent(), new TypeToken<List<AccountDto>>()
        {}.getType());
       Page<AccountDto> dtoPage= new PageImpl<>(dto, pageable, entity.getTotalElements());
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

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public AccountDto addAccount(@Valid @RequestBody AccountRequest accountRequest) {
        Account account = service.addAccount(accountRequest);
        AccountDto accountDto = null;
        accountDto = modelMapper.map(account, AccountDto.class);
        return accountDto;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Optional<AccountDto> updateAccountById(@PathVariable Integer id,
                                                  @Valid @RequestBody AccountRequest accountRequest) {
        Optional<Account> account = service.updateAccountById(id, accountRequest);
        AccountDto accountDto = null;
        if (account.isPresent()) {
            accountDto = modelMapper.map(account.get(), AccountDto.class);
        }
        return Optional.ofNullable(accountDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable Set<Integer> id) {
         service.deleteByIdIn(id);
    }
}

package com.vti.rw41.FinalExam.service;

import com.vti.rw41.FinalExam.dto.request.AccountRequest;
import com.vti.rw41.FinalExam.dto.request.LoginRequest;
import com.vti.rw41.FinalExam.dto.request.ResetPasswordRequest;
import com.vti.rw41.FinalExam.dto.response.AccountDto;
import com.vti.rw41.FinalExam.entity.Account;
import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.entity.OtpAccount;
import com.vti.rw41.FinalExam.enumurations.RoleAcccount;
import com.vti.rw41.FinalExam.form.AccountFilterForm;
import com.vti.rw41.FinalExam.repository.IAccountRepository;
import com.vti.rw41.FinalExam.repository.IDepartmentRepository;
import com.vti.rw41.FinalExam.repository.OtpAccountRepository;
import com.vti.rw41.FinalExam.security.JwtTokenProvider;
import com.vti.rw41.FinalExam.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class AccountServiceImp implements IAccountService {
    @Autowired
    IAccountRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IDepartmentRepository departmentRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    OtpAccountRepository otpAccountRepository;

    @Autowired
    private SendMailService sendMailService;

    @Override
    public ResponseEntity<String> login(LoginRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        if (passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            String token = jwtTokenProvider.createToken(request.getUsername(), userDetails.getAuthorities());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @Transactional
    public Account registerAccount(AccountRequest request) {

        Account accountEntity = new Account();

        accountEntity.setUsername(request.getUsername());
        accountEntity.setFirstname(request.getFirstname());
        accountEntity.setLastname(request.getLastname());
        accountEntity.setFullname(request.getFirstname() + " " + request.getLastname());

        // Default password 123456A
        String encode = passwordEncoder.encode("123456A");
        accountEntity.setPassword(encode);

        accountEntity.setRole(RoleAcccount.EMPLOYEE);
        repository.save(accountEntity);
        return accountEntity;
    }

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

        Account entity = new Account();
        entity.setUsername(accountRequest.getUsername());
        entity.setFirstname(accountRequest.getFirstname());
        entity.setLastname(accountRequest.getLastname());
        entity.setFullname(accountRequest.getFirstname() + " " + accountRequest.getLastname());

        // Default password 123456A
        String encode = passwordEncoder.encode("123456A");
        entity.setPassword(encode);

        entity.setRole(RoleAcccount.EMPLOYEE);
        Department department1 = departmentRepository.findById(accountRequest.getDepartmentId()).orElse(null);
        entity.setDepartment(department1);

        return repository.save(entity);
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
            entity.setRole(RoleAcccount.EMPLOYEE);
            Department department1 = departmentRepository.findById(accountRequest.getDepartmentId()).orElse(null);
            entity.setDepartment(department1);
        });
        return oldAccount;
    }

//       Set<Account> accounts = repository.findByIdIn(ids);
//       accounts.forEach(account -> {
//           repository.delete(account);

    @Transactional
    public void deleteByIdIn(Set<Integer> ids) {
        repository.deleteByIdIn(ids);
    }

    public void forgotPassword(String userName) {

        Account account = repository.findByUsername(userName);

        if (account != null) {

            Random rd = new SecureRandom();
            int random = rd.nextInt(999_999);// random tu 0 -> 999_999
            String randomStr = String.format("%06d", random);

            OtpAccount otp = new OtpAccount();
            otp.setOtp(randomStr);
            otp.setUserName(userName);
            otp.setExpire(LocalDateTime.now().plusMinutes(30));
            otpAccountRepository.save(otp);

            sendMailService.sendForgotPassword(account.getUsername(), randomStr, account.getLang());
        }
    }

    @Transactional
    public ResponseEntity<?> resetPassword(ResetPasswordRequest request) {
        OtpAccount otp = otpAccountRepository.findByUserNameAndOtp(request.getUserName(), request.getOtp());
        if (otp != null && otp.getExpire().isAfter(LocalDateTime.now())) { //n???u otp t???n t???i v?? ch??a h???t h???n (> now)
            Account accountEntity = repository.findByUsername(request.getUserName());
            accountEntity.setPassword(passwordEncoder.encode(request.getPassword()));
            repository.save(accountEntity);
            otpAccountRepository.delete(otp);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public Page<Account> getAllAccountsV2(Integer departmentId, String role, String search, Pageable pageable) {
         if (search != null) {
             search = "%" + search + "%";
         }
         return repository.getAllAccountsV2(departmentId, role, search, pageable);
    }
}

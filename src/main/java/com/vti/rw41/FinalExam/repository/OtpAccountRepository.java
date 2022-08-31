package com.vti.rw41.FinalExam.repository;

import com.vti.rw41.FinalExam.entity.OtpAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpAccountRepository extends JpaRepository<OtpAccount, Long> {

    OtpAccount findByUserNameAndOtp(String userName, String otp);

}

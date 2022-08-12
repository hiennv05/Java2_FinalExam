package com.vti.rw41.FinalExam.repository;

import com.vti.rw41.FinalExam.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT (count(a)= 0) FROM Account a WHERE a.user_name = ?1")
    boolean isUserNameNotExists(String name);

}

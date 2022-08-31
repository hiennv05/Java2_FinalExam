package com.vti.rw41.FinalExam.repository;

import com.vti.rw41.FinalExam.dto.response.AccountDto;
import com.vti.rw41.FinalExam.entity.Account;
import com.vti.rw41.FinalExam.enumurations.RoleAcccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT (count(a)= 0) FROM Account a WHERE a.username = ?1")
    boolean isUserNameNotExists(String name);

    Page<Account> findAll(Specification<Account> where, Pageable pageable);


    Account findByUsername(String username);
    @Transactional
    @Modifying
    void deleteByIdIn(Set<Integer> ids);

//    @Transactional
//    @Modifying
//    @Query("delete from Account a where a.id in ?1")
//    void deleteByIdIn(Set<Integer> ids);


    @Query(value = "SELECT * FROM account " +
            "WHERE (:search is null OR (user_name like :search)) " +
            "AND (:role is null OR role = :role) " +
            "AND (:departmentId is null OR department_id = :departmentId) " +
            "ORDER BY department_id DESC" , nativeQuery = true)
    Page<Account> getAllAccountsV2(Integer departmentId, String role, String search, Pageable pageable);
}

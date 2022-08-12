package com.vti.rw41.FinalExam.entity;

import com.vti.rw41.FinalExam.enumurations.RoleDepartment;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RoleDepartment type;

    private Integer total_member;

    private LocalDate create_date;

    @OneToMany(mappedBy = "department")
    private List<Account> accounts;



}

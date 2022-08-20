package com.vti.rw41.FinalExam.entity;

import com.vti.rw41.FinalExam.enumurations.RoleDepartment;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Department  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RoleDepartment type;

    @Column(name = "total_member")
    private Integer totalMember;

    @Column(name = "create_date")
    private LocalDate createDate;

    @OneToMany(mappedBy = "department")
    private List<Account> accounts;



}

package com.vti.rw41.FinalExam.entity;

import com.vti.rw41.FinalExam.enumurations.RoleAcccount;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "`Account`")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;

    @Column( nullable = false, unique = false, length= 100)
    private String user_name;

    @Column( nullable = false, unique = false, length= 50)
    private String first_name;

    @Column( nullable = false, unique = false, length= 50)
    private String last_name;

    @Formula(value = "concat(first_name, ' ' , last_name)")
    private String full_name;

    private String password;
    @Enumerated(EnumType.STRING)
    private RoleAcccount role;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}

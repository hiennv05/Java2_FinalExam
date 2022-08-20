package com.vti.rw41.FinalExam.entity;

import com.vti.rw41.FinalExam.enumurations.RoleAcccount;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Getter
@Setter
@Table(name = "`Account`")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;

    @Column( name= "user_name", nullable = false, unique = false, length= 100)
    private String username;

    @Column( name= "first_name",nullable = false, unique = false, length= 50)
    private String firstname;

    @Column(name= "last_name", nullable = false, unique = false, length= 50)
    private String lastname;

    @Formula(value = "concat(first_name, ' ' , last_name)")
    private String fullname;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleAcccount role;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}

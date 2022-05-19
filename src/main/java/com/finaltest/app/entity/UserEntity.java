package com.finaltest.app.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Getter
@Setter
@Entity(name="users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 5486454656775807555L;
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, length = 50)

    private String userId;
    @Column(nullable = false, length = 250)

    private String fistName;
    @Column(nullable = false, length = 250)
    private String lastName;

    @Column(nullable = false, length = 250,unique = true)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    private String emailVerificationToken;

    @Column(nullable = false)
    private Boolean emailVerificationStatus = false;
}

package com.etranzact.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Entity
@Table(name = "customers")
@Setter
@Getter
public class CustomerDetails extends BaseEntity implements Serializable {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "telephone", unique = true, nullable = false)
    private String telephone;

    @Column(name = "pin_no", length = 500)
    private String pin;

    @OneToOne
    @JoinColumn(name = "account_fk")
    private AccountDetails accountDetails;


}

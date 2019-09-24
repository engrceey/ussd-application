package com.etranzact.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Entity
@Table(name = "accounts")
@Setter
@Getter
public class AccountDetails extends BaseEntity implements Serializable {

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

}

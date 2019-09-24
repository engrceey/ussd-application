package com.etranzact.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Entity
@Table(name = "notifier")
@Setter
@Getter
public class Notifier extends BaseEntity implements Serializable {

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    private String message;

    @Column(name = "is_read")
    private boolean isRead = false;

    @ManyToOne
    @JoinColumn(name = "customer_fk")
    private CustomerDetails accountDetails;
}

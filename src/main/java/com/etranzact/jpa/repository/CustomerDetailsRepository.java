package com.etranzact.jpa.repository;

import com.etranzact.jpa.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {

    Optional<CustomerDetails> findByTelephone(String telephone);

    Optional<CustomerDetails> findByAccountDetails_AccountNumber(String accountNumber);

    Optional<CustomerDetails> findByEmail(String email);
}

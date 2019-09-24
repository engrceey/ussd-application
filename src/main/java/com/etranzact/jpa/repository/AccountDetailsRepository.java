package com.etranzact.jpa.repository;

import com.etranzact.jpa.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {

    public Optional<AccountDetails> findByAccountNumber(String accountNo);

}

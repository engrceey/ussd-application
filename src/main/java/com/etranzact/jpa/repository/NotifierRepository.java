package com.etranzact.jpa.repository;

import com.etranzact.jpa.entity.Notifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Repository
public interface NotifierRepository extends JpaRepository<Notifier, Long> {
}

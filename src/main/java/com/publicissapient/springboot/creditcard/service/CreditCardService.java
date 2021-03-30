package com.publicissapient.springboot.creditcard.service;

import com.publicissapient.springboot.creditcard.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardService extends JpaRepository<CreditCard, Integer>{
    Optional<CreditCard> findById(Long id);
}

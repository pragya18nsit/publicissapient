package com.publicissapient.springboot.creditcard.service;

import com.publicissapient.springboot.creditcard.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardService extends JpaRepository<CreditCard, Integer>{
}

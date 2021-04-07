package com.publicissapient.springboot.creditcard.controller;

import com.publicissapient.springboot.creditcard.exception.CreditCardFailedLuhnException;
import com.publicissapient.springboot.creditcard.exception.CreditCardNotFoundException;
import com.publicissapient.springboot.creditcard.model.CreditCard;
import com.publicissapient.springboot.creditcard.service.CreditCardService;
import com.publicissapient.springboot.creditcard.utils.LuhnValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @Value("${spring.creditcard.expiryDays}")
    private Integer expiryDays;

    @Value("${spring.creditcard.defaultBalance}")
    private Integer defaultBalance;

    @Value("${spring.creditcard.cardLimit}")
    private Integer cardLimit;

    // Select, Insert Operations for a Credit CARD
    @GetMapping("/creditcards/{id}")
    CreditCard getCreditCard(@PathVariable Long id){

        Optional<CreditCard> creditCard = creditCardService.findById(id);

        if (!creditCard.isPresent())
            throw new CreditCardNotFoundException("Credit card with id-" + id + " not found");

        return  creditCard.get();
    }

    @PostMapping("/creditcards")
    CreditCard addCreditCard(@Valid @RequestBody CreditCard creditCard){
        //Validate credit card id
        long ltime;
        Date currentDate, expiryDate;

        Long creditCardId = creditCard.getId();
        if(!LuhnValidation.Check(creditCardId.toString())){

            throw new CreditCardFailedLuhnException("Credit card with id- " + creditCardId + " failed luhn check validation");
        }

        //Set current date and expiry dates
        currentDate = new Date();
        creditCard.setIssueDate(currentDate);
        ltime=currentDate.getTime()+expiryDays;
        expiryDate=new Date(ltime);
        creditCard.setExpiryDate(expiryDate);
        creditCard.setBalance(defaultBalance);
        creditCard.setCardLimit(cardLimit);
        return creditCardService.save(creditCard);

    }


    @GetMapping("/creditcards")
    List<CreditCard> getAllCreditCards(){
        return creditCardService.findAll();
    }


}

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

    // Select, Insert Operations for a Credit CARD
    @RequestMapping(value = "/creditcard/{id}", method = RequestMethod.GET)
    CreditCard getCreditCard(@PathVariable Long id){

        Optional<CreditCard> creditCard = creditCardService.findById(id);

        if (!creditCard.isPresent())
            throw new CreditCardNotFoundException("Credit card with id-" + id + " not found");

        return  creditCard.get();
    }

    @RequestMapping(value = "/creditcard", method = RequestMethod.POST)
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
        return creditCardService.save(creditCard);

    }
    // Select, Insert for List of Credit Cards

    @RequestMapping(value = "/creditcards", method = RequestMethod.GET)
    List<CreditCard> getAllCreditCards(){
        return creditCardService.findAll();
    }

    @RequestMapping(value = "/creditcards", method = RequestMethod.POST)
    List<CreditCard>  addAllCreditCards( @RequestBody List<CreditCard> creditCardList){
        long ltime;
        Date currentDate, expiryDate;

        for(CreditCard creditCard: creditCardList){
            log.info("value of id : {} ", creditCard.getId());
            Long creditCardId = creditCard.getId();
            if(!LuhnValidation.Check(creditCardId.toString())){
                throw new CreditCardFailedLuhnException("Credit card with id- " + creditCardId + " failed luhn check validation");
            }

            //Set current date and expiry dates
            currentDate = new Date();
            creditCard.setIssueDate(currentDate);
            ltime = currentDate.getTime()+expiryDays;
            expiryDate = new Date(ltime);
            creditCard.setExpiryDate(expiryDate);
            creditCard.setBalance(defaultBalance);

        }
        return creditCardService.saveAll(creditCardList);
    }

}

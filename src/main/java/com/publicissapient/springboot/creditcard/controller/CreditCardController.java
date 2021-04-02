package com.publicissapient.springboot.creditcard.controller;

import com.publicissapient.springboot.creditcard.exception.CreditCardNotFoundException;
import com.publicissapient.springboot.creditcard.exception.ExceptionResponse;
import com.publicissapient.springboot.creditcard.model.CreditCard;
import com.publicissapient.springboot.creditcard.service.CreditCardService;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    // Select, Insert Operations for a Credit CARD

    @RequestMapping(value = "/creditcard/{id}", method = RequestMethod.GET)
    CreditCard getCreditCard(@PathVariable Long id){

        Optional<CreditCard> creditCard = creditCardService.findById(id);

        if (!creditCard.isPresent())
            throw new CreditCardNotFoundException("id-" + id);

        return  creditCard.get();
    }

    @RequestMapping(value = "/creditcard", method = RequestMethod.POST)
    CreditCard addCreditCard(@Valid @RequestBody CreditCard creditCard){

        //Set current date and expiry dates
        Date currentDate = new Date();
        creditCard.setIssue_date(currentDate);
        long ltime=currentDate.getTime()+3*24*60*60*1000;
        Date expiryDate=new Date(ltime);
        creditCard.setExpiry_date(expiryDate);
        creditCard.setBalance(0);

        return creditCardService.save(creditCard);

    }
    // Select, Insert for List of Credit Cards

    @RequestMapping(value = "/creditcards", method = RequestMethod.GET)
    List<CreditCard> getAllCreditCards(){
        return creditCardService.findAll();
    }

    @RequestMapping(value = "/creditcards", method = RequestMethod.POST)
    List<CreditCard>  addAllCreditCards( @RequestBody List<CreditCard> creditCardList){
        for(CreditCard creditCard: creditCardList){
            log.info("value of id : {} ", creditCard.getId());
            //Set current date and expiry dates
            Date currentDate = new Date();
            creditCard.setIssue_date(currentDate);
            long ltime=currentDate.getTime()+3*24*60*60*1000;
            Date expiryDate=new Date(ltime);
            creditCard.setExpiry_date(expiryDate);
            creditCard.setBalance(0);

        }
        return creditCardService.saveAll(creditCardList);
    }

}

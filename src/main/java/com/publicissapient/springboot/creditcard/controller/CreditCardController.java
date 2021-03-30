package com.publicissapient.springboot.creditcard.controller;

import com.publicissapient.springboot.creditcard.exception.CreditCardNotFoundException;
import com.publicissapient.springboot.creditcard.model.CreditCard;
import com.publicissapient.springboot.creditcard.service.CreditCardService;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    // Select, Insert Operations for a Credit CARD

    @RequestMapping(value = "/creditcard/{id}", method = RequestMethod.GET)
    CreditCard getCreditCard(@PathVariable Long id){
        System.out.print(id);
        Optional<CreditCard> creditCard = creditCardService.findById(id);

        if (!creditCard.isPresent())
            throw new CreditCardNotFoundException("id-" + id);

        return  creditCard.get();
    }

    @RequestMapping(value = "/creditcard", method = RequestMethod.POST)
    CreditCard addCreditCard(@Valid @RequestBody CreditCard creditCard){
        return creditCardService.save(creditCard);

    }
    // Select, Insert for List of Credit Cards

    @RequestMapping(value = "/creditcards", method = RequestMethod.GET)
    List<CreditCard> getAllCreditCards(){
        return creditCardService.findAll();
    }

    @RequestMapping(value = "/creditcards", method = RequestMethod.POST)
    String addAllCreditCards( @RequestBody List<CreditCard> creditCardList){
        creditCardService.saveAll(creditCardList);
        return "SUCCESS";
    }

}

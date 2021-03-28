package com.publicissapient.springboot.creditcard.controller;

import com.publicissapient.springboot.creditcard.model.CreditCard;
import com.publicissapient.springboot.creditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    CreditCard getCreditCard(@PathVariable Integer id){
        return  creditCardService.findById(id).get();
    }

    @RequestMapping(value = "/creditcard", method = RequestMethod.POST)
    String addCreditCard(@RequestBody CreditCard employee){
        CreditCard savedCreditCard = creditCardService.save(employee);
        return "SUCCESS";
    }
    // Select, Insert for List of Credit Cards

    @RequestMapping(value = "/creditcards", method = RequestMethod.GET)
    List<CreditCard> getAllCreditCards(){
        return creditCardService.findAll();
    }

    @RequestMapping(value = "/creditcards", method = RequestMethod.POST)
    String addAllEmployees(@RequestBody List<CreditCard> employeeList){
        creditCardService.saveAll(employeeList);
        return "SUCCESS";
    }

}
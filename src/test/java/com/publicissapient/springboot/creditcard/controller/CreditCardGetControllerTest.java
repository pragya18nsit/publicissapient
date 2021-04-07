package com.publicissapient.springboot.creditcard.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.springboot.creditcard.model.CreditCard;
import com.publicissapient.springboot.creditcard.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class CreditCardGetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CreditCardService creditCardService;

    @Test
    public void testGetCreditCard_Unauthorized() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(7220810501341782203L);


        given(creditCardService.findById(7220810501341782203L)).willReturn(java.util.Optional.of(creditCard));

        this.mockMvc.perform(get("/creditcards/7220810501341782203L")).andExpect(status().isUnauthorized());
    }

    @WithMockUser(username="admin")
    @Test
    public void testGetCreditCard() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(7220810501341782203L);
        creditCard.setBankName("UBS BANK");

        given(creditCardService.findById(7220810501341782203L)).willReturn(java.util.Optional.of(creditCard));
        this.mockMvc.perform(get("/creditcards/7220810501341782203")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("bankName").value("UBS BANK"));

    }



}

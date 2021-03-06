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
public class CreditCardPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CreditCardService creditCardService;


    @Test
    public void testPostCreditCard_Unauthorized() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(7220810501341782203L);
        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(creditCard);

        given(creditCardService.save(creditCard)).willReturn(creditCard);

        this.mockMvc.perform(post("/creditcards")).andExpect(status().isUnauthorized());
    }


    @WithMockUser(username="admin")
    @Test
    public void testPostCreditCard_BankNameValidationError() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(7220810501341782203L);
        creditCard.setCvv(123);
       // creditCard.setBankName("AXIS BANK");
        creditCard.setCardType("GOLD");
        creditCard.setCurrency("$");

        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(creditCard);

        given(creditCardService.save(creditCard)).willReturn(creditCard);

        this.mockMvc.perform(post("/creditcards").content(objectMapper.writeValueAsString(creditCard))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Validation Failed"));;
    }

    @WithMockUser(username="admin")
    @Test
    public void testPostCreditCard_LuhnCheckValidationError() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(7220810501341782202L);
        creditCard.setCvv(123);
        // creditCard.setBankName("AXIS BANK");
        creditCard.setCardType("GOLD");
        creditCard.setCurrency("$");

        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(creditCard);

        given(creditCardService.save(creditCard)).willReturn(creditCard);

        this.mockMvc.perform(post("/creditcards").content(objectMapper.writeValueAsString(creditCard))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Validation Failed"));;
    }

    @WithMockUser(username="admin")
    @Test
    public void testPostCreditCard_CvvValidationError() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(7220810501341782202L);
        //creditCard.setCvv(123);
         creditCard.setBankName("AXIS BANK");
        creditCard.setCardType("GOLD");
        creditCard.setCurrency("$");

        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(creditCard);

        given(creditCardService.save(creditCard)).willReturn(creditCard);

        this.mockMvc.perform(post("/creditcards").content(objectMapper.writeValueAsString(creditCard))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Validation Failed"));;
    }

    @WithMockUser(username="admin")
    @Test
    public void testPostCreditCard_CurrencyValidationError() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(7220810501341782202L);
        //creditCard.setCvv(123);
        creditCard.setBankName("AXIS BANK");
        creditCard.setCardType("GOLD");
        creditCard.setCurrency("$");

        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(creditCard);

        given(creditCardService.save(creditCard)).willReturn(creditCard);

        this.mockMvc.perform(post("/creditcards").content(objectMapper.writeValueAsString(creditCard))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Validation Failed"));;
    }

    @WithMockUser(username="admin")
    @Test
    public void testPostCreditCard() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(7220810501341782203L);
        creditCard.setCvv(123);
        creditCard.setBankName("AXIS BANK");
        creditCard.setCardType("GOLD");
        creditCard.setCurrency("$");

        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(creditCard);

        given(creditCardService.save(creditCard)).willReturn(creditCard);

        this.mockMvc.perform(post("/creditcards").content(objectMapper.writeValueAsString(creditCard))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }



}

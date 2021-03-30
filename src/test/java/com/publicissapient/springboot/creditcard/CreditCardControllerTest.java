package com.publicissapient.springboot.creditcard;

import java.util.ArrayList;
import java.util.List;

import com.publicissapient.springboot.creditcard.model.CreditCard;
import com.publicissapient.springboot.creditcard.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CreditCardService creditCardService;

    @Test
    public void testGetAllCreditCards() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(1234567891234567891L);
        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(creditCard);

        given(creditCardService.findAll()).willReturn(creditCardList);

        this.mockMvc.perform(get("/creditcards")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(12345678912345678L));
    }

}

package com.publicissapient.springboot.creditcard;

import com.publicissapient.springboot.creditcard.model.CreditCard;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@Slf4j
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CreditCardControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreditCard()
    {
        Assertions.assertTrue(
                this.restTemplate
                        .withBasicAuth("admin", "nimda")
                        .getForObject("http://localhost:" + port + "/creditcard/11111111111111111", CreditCard.class)
                        .getBankname().equals("UBS BANK"));

    }

    @Test
    public void testAllCreditCards() {

        CreditCard results = this.restTemplate.getForObject("http://localhost:" + port + "/creditcards", CreditCard.class);
        log.info(results.getBankname());
        assertTrue(
                this.restTemplate
                        .withBasicAuth("admin", "nimda")
                        .getForObject("http://localhost:" + port + "/creditcards", List.class).size() == 2);
    }
}


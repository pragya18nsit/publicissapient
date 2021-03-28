package com.publicissapient.springboot.creditcard;

import com.publicissapient.springboot.creditcard.model.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

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
                        .getForObject("http://localhost:" + port + "/creditcard/1", CreditCard.class)
                        .getId().equals(1));
    }

    @Test
    public void testAllCreditCards() {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/creditcards", List.class).size() == 2);
    }
}


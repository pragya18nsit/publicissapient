package com.publicissapient.springboot.creditcard.integration;

import com.publicissapient.springboot.creditcard.model.CreditCard;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@Slf4j
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CreditCardGetControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Value("${spring.security.user.name}")
    private String userName;
    @Value("${spring.security.user.password}")
    private String userPassword;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCreditCard()
    {
        Assertions.assertTrue(
                this.restTemplate
                        .withBasicAuth(userName, userPassword)
                        .getForObject("http://localhost:" + port + "/creditcards/7220810501341782203", CreditCard.class)
                        .getBankName().equals("UBS BANK"));

    }

    @Test
    public void testGetAllCreditCards() {

        CreditCard results = this.restTemplate.getForObject("http://localhost:" + port + "/creditcards", CreditCard.class);
        assertTrue(
                this.restTemplate
                        .withBasicAuth(userName, userPassword)
                        .getForObject("http://localhost:" + port + "/creditcards", List.class).size() == 2);
    }



}


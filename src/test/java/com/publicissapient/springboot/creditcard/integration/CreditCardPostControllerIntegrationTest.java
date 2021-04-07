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
import org.springframework.http.*;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CreditCardPostControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Value("${spring.security.user.name}")
    private String userName;
    @Value("${spring.security.user.password}")
    private String userPassword;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testPostCreditCard() throws URISyntaxException {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(8170573272878250872L);
        creditCard.setName("gupta");

        creditCard.setCvv(123);
        creditCard.setBankName("AXIS BANK");
        creditCard.setCardType("GOLD");
        creditCard.setCurrency("$");

        HttpHeaders headers = new HttpHeaders();

        headers.setBasicAuth("admin", "nimda");
        final String baseUrl = "http://localhost:"+ port +"/creditcards";
        URI uri = new URI(baseUrl);
        HttpEntity<CreditCard> request = new HttpEntity<>(creditCard, headers);
        ResponseEntity<String> result =  this.restTemplate
                .exchange(uri, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(200, result.getStatusCodeValue());

    }


    @Test
    public void testPostCreditCard_InvalidCVV() throws URISyntaxException {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(8170573272878250872L);
        creditCard.setName("gupta");

        //creditCard.setCvv(123);
        creditCard.setBankName("AXIS BANK");
        creditCard.setCardType("GOLD");
        creditCard.setCurrency("$");

        HttpHeaders headers = new HttpHeaders();

        headers.setBasicAuth("admin", "nimda");
        final String baseUrl = "http://localhost:"+ port +"/creditcards";
        URI uri = new URI(baseUrl);
        HttpEntity<CreditCard> request = new HttpEntity<>(creditCard, headers);
        ResponseEntity<String> result =  this.restTemplate
                .exchange(uri, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    public void testPostCreditCard_InvalidCurrency() throws URISyntaxException {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(8170573272878250872L);
        creditCard.setName("gupta");

        creditCard.setCvv(123);
        creditCard.setBankName("AXIS BANK");
        creditCard.setCardType("GOLD");
        //creditCard.setCurrency("$");

        HttpHeaders headers = new HttpHeaders();

        headers.setBasicAuth("admin", "nimda");
        final String baseUrl = "http://localhost:"+ port +"/creditcards";
        URI uri = new URI(baseUrl);
        HttpEntity<CreditCard> request = new HttpEntity<>(creditCard, headers);
        ResponseEntity<String> result =  this.restTemplate
                .exchange(uri, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    public void testPostCreditCard_LuhnCheck() throws URISyntaxException {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(8170573272878250871L);
        creditCard.setName("gupta");

        creditCard.setCvv(123);
        creditCard.setBankName("AXIS BANK");
        creditCard.setCardType("GOLD");
        creditCard.setCurrency("$");

        HttpHeaders headers = new HttpHeaders();

        headers.setBasicAuth("admin", "nimda");
        final String baseUrl = "http://localhost:"+ port +"/creditcards";
        URI uri = new URI(baseUrl);
        HttpEntity<CreditCard> request = new HttpEntity<>(creditCard, headers);
        ResponseEntity<String> result =  this.restTemplate
                .exchange(uri, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(422, result.getStatusCodeValue());

    }
}

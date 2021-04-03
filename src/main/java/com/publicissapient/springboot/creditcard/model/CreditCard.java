package com.publicissapient.springboot.creditcard.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Column(name = "id")
    @Id
    @NotNull
    Long id;
    @Min(1000000000000000000L)
    @Max(Long.MAX_VALUE)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "name")
    String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "cvv")
    @NotNull
    Integer cvv;


    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }



    @Column(name = "issue_date")
    Date issueDate;

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Column(name = "expiry_date")
    Date expiryDate;

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    @Column(name = "card_type")
    @NotNull
    String cardType;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String  cardType) {
        this.cardType = cardType;
    }


    @Column(name = "balance")
    Integer balance;


    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Column(name = "currency")
    @NotNull
    String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "bank_name")
    @NotNull
    String bankName;


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}

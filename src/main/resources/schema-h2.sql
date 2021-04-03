CREATE TABLE credit_card
        (
        id BIGINT(19) NOT NULL,
        name VARCHAR2(50) NOT NULL,
        cvv NUMBER(5) NOT NULL,
        issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        expiry_date DATE,
        bank_name VARCHAR(40)  NOT NULL,
        card_type VARCHAR(20),
        balance NUMBER(10) NOT NULL,
        currency VARCHAR(1) NOT NULL,
        PRIMARY KEY(id)
        );
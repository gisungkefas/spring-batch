package com.kefas.springbatch.dao;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private Long id;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "email_id")
    private String emailId;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "amount")
    private Double amount;


    @Column(name = "mail_triggered")
    private int mailTriggered = 0;


    public TransactionEntity(String transactionId, String mobileNumber, String emailId, Double amount) {
        super();
        this.transactionId = transactionId;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.amount = amount;
    }
}

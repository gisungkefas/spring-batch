package com.kefas.springbatch.service;

import com.kefas.springbatch.dao.TransactionEntity;
import com.kefas.springbatch.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskManager {

    @Autowired
    private TransactionRepository repository;

    public void sendMail(String email) throws InterruptedException {
        System.out.println(email + " email sending....");
        Thread.currentThread().sleep(1000);
        System.out.println(email + " email sent");
    }

    public TransactionEntity updateTransaction(Long id) {
        TransactionEntity entity = this.repository.findById(id).get();
        entity.setMailTriggered(1);
        return entity;

    }
}

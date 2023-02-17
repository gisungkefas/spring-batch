package com.kefas.springbatch;

import com.kefas.springbatch.dao.TransactionEntity;
import com.kefas.springbatch.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Pusher {

    @Autowired
    TransactionRepository repository;

    private Random rand = new Random();

    void init() {

        for (int i = 1; i < 2000; i++)
            repository.save(new TransactionEntity("TXN" + System.currentTimeMillis(), rand.nextInt(100000000) + "",
                    rand.nextInt(100000000) + "@gmail.com", Math.round(Math.random() * 1000) / 100.0));
    }
}

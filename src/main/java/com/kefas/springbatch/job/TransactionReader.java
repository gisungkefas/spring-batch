package com.kefas.springbatch.job;

import com.kefas.springbatch.dao.TransactionEntity;
import com.kefas.springbatch.repository.TransactionRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class TransactionReader implements ItemReader<TransactionEntity> {
    @Autowired
    private TransactionRepository repository;

    private List<TransactionEntity> transactions;

    private int index;

    @PostConstruct
    public void init() {
        transactions = repository.getAll();
        index = 0;
    }

    @Override
    public TransactionEntity read()
            throws Exception {
        TransactionEntity entity = null;
        if (transactions != null && transactions.size() > index) {
            entity = transactions.get(index);
            index++;
        }
        return entity;
    }
}

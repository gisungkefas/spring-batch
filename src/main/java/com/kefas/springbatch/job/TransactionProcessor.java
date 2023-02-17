package com.kefas.springbatch.job;

import com.kefas.springbatch.service.TaskManager;
import com.kefas.springbatch.dao.TransactionEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionProcessor implements ItemProcessor<TransactionEntity, TransactionEntity> {

    @Autowired
    private TaskManager taskManager;

    @Override
    public TransactionEntity process(TransactionEntity item) throws Exception {
        taskManager.sendMail(item.getEmailId());
        item.setMailTriggered(1);
        return taskManager.updateTransaction(item.getId());
    }
}

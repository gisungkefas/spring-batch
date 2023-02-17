package com.kefas.springbatch.config;

import com.kefas.springbatch.dao.TransactionEntity;
import com.kefas.springbatch.job.TransactionProcessor;
import com.kefas.springbatch.job.TransactionReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.annotation.Resource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private TransactionProcessor processor;
    @Autowired
    private TransactionReader reader;
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job informationUser(){
        return this.jobBuilderFactory.get("informationUser").start(fetchTransaction()).build();
    }

    @Bean
    public FlatFileItemWriter<TransactionEntity> writer() {
        FlatFileItemWriter<TransactionEntity> writer = new FlatFileItemWriter<>();
        writer.setResource((org.springframework.core.io.Resource) outputResource);
        writer.setAppendAllowed(true);
        writer.setLineAggregator(new DelimitedLineAggregator<TransactionEntity>() {
            {
                setDelimiter(" | ");
                setFieldExtractor(new BeanWrapperFieldExtractor<TransactionEntity>() {
                    {
                        setNames(new String[] { "id", "transactionId", "mobileNumber", "emailId", "amount",
                                "mailTriggered" });
                    }
                });
            }
        });
        return writer;
    }

    private Step fetchTransaction(){
        return stepBuilderFactory.get("fetchTransaction").<TransactionEntity, TransactionEntity>chunk(10).reader(reader)
                .processor(processor).writer(writer()).build();
    }

    private Resource outputResource = (Resource) new FileSystemResource("/Users/hem/app/transaction.csv");
}

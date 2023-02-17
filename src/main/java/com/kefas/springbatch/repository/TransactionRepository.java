package com.kefas.springbatch.repository;

import com.kefas.springbatch.dao.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository {

    @Query(nativeQuery = true, value = "select * from transaction limit 10")
    List<TransactionEntity> getAll();
}

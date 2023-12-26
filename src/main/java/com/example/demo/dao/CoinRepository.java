package com.example.demo.dao;

import com.example.demo.entity.vo.CoinEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CoinRepository extends CrudRepository<CoinEntity, String> {
    CoinEntity findByType(String type);
    @Modifying
    @Transactional
    @Query(value = "UPDATE t_coin tc SET tc.name = ?2 WHERE tc.type = ?1", nativeQuery = true)
    Integer updateCoinEntity(String type, String name);
}

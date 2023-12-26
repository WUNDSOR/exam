package com.example.demo.service;

import com.example.demo.entity.dto.CoinApiDto;
import com.example.demo.entity.vo.CoinApiVo;
import com.example.demo.entity.vo.CoinEntity;

import java.util.List;

public interface CoinService {
    CoinApiDto getBitcoinCurrentPrice();
    List<CoinApiVo> getBitcoinCurrentPriceDetail();
    CoinEntity addCoinEntity(CoinEntity coinEntity);
    void removeCoinEntity(String type);
    Integer updateCoinEntity(CoinEntity coinEntity);
    CoinEntity getCoinEntity(String type);
}

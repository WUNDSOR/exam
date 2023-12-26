package com.example.demo.service.impl;

import com.example.demo.dao.CoinRepository;
import com.example.demo.entity.dto.CoinApiDto;
import com.example.demo.entity.vo.CoinApiVo;
import com.example.demo.entity.vo.CoinEntity;
import com.example.demo.service.CoinService;
import com.example.demo.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CoinServiceImpl implements CoinService {
    private final String TARGET_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private final DateTimeFormatter voFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
    private final DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_DATE_TIME;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CoinRepository coinRepository;

    @Override
    public CoinApiDto getBitcoinCurrentPrice() {
        String response = HttpUtil.doGet(TARGET_URL);
        try {
            return objectMapper.readValue(response, CoinApiDto.class);
        } catch (Exception e) {
            log.error("Exception occur:", e);
        }
        return null;
    }

    @Override
    public List<CoinApiVo> getBitcoinCurrentPriceDetail() {
        List<CoinApiVo> list = new ArrayList<>();
        try {
            CoinApiDto dto = getBitcoinCurrentPrice();
            LocalDateTime updateTime = LocalDateTime.parse(dto.getTime().getUpdatedISO(), isoFormatter);
            String formatTime = voFormatter.format(updateTime);

            dto.getBpi().forEach((code, v) -> {
                CoinApiVo vo = new CoinApiVo();
                vo.setName(getCoinEntity(code).getName());
                vo.setType(code);
                vo.setExchangeRate(v.getRateFloat());
                vo.setUpdateTime(formatTime);
                list.add(vo);
            });
        } catch (Exception e) {
            log.error("Exception occur:", e);
        }
        return list;
    }

    @Override
    public CoinEntity addCoinEntity(CoinEntity entity) {
        return coinRepository.save(entity);
    }

    @Override
    public void removeCoinEntity(String type) {
        coinRepository.deleteById(type);
    }

    @Override
    public Integer updateCoinEntity(CoinEntity coinEntity) {
        return coinRepository.updateCoinEntity(coinEntity.getType(), coinEntity.getName());
    }

    @Override
    public CoinEntity getCoinEntity(String type) {
        return Optional.ofNullable(coinRepository.findByType(type)).orElse(new CoinEntity());
    }
}

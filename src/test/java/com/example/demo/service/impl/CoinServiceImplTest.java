package com.example.demo.service.impl;

import com.example.demo.dao.CoinRepository;
import com.example.demo.entity.dto.CoinApiDto;
import com.example.demo.entity.vo.CoinEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
class CoinServiceImplTest {
    @Mock
    private CoinRepository coinRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetBitcoinCurrentPrice() throws JsonProcessingException {
        String response = "{\"time\":{\"updated\":\"Dec 26, 2023 12:45:00 UTC\",\"updatedISO\":\"2023-12-26T12:45:00+00:00\",\"updateduk\":\"Dec 26, 2023 at 12:45 GMT\"},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"code\":\"USD\",\"symbol\":\"&#36;\",\"rate\":\"42,706.5074\",\"description\":\"United States Dollar\",\"rate_float\":42706.5074},\"GBP\":{\"code\":\"GBP\",\"symbol\":\"&pound;\",\"rate\":\"35,685.2159\",\"description\":\"British Pound Sterling\",\"rate_float\":35685.2159},\"EUR\":{\"code\":\"EUR\",\"symbol\":\"&euro;\",\"rate\":\"41,602.3734\",\"description\":\"Euro\",\"rate_float\":41602.3734}}}";
        Assertions.assertDoesNotThrow(() -> objectMapper.readValue(response, CoinApiDto.class));
    }

    @Test
    void testGetBitcoinCurrentPriceDetail() {
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_DATE_TIME;
        CoinApiDto dto = new CoinApiDto();
        dto.setTime(new CoinApiDto.Time());
        dto.getTime().setUpdatedISO("2023-12-26T12:45:00+00:00");
        dto.setBpi(new HashMap<>());

        LocalDateTime updateTime = Assertions.assertDoesNotThrow(() -> LocalDateTime.parse(dto.getTime().getUpdatedISO(), isoFormatter));

        DateTimeFormatter voFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        String formatTime = voFormatter.format(updateTime);
        Assert.isTrue(formatTime.equals("2023/12/26 12:45:00"), "格式轉換錯誤");
    }

    @Test
    void testAddCoinEntity() {
        CoinEntity entity = new CoinEntity();
        entity.setName("USD");
        entity.setType("美元");
        Mockito.when(coinRepository.save(entity)).thenReturn(entity);
        Assert.isTrue(coinRepository.save(entity).equals(entity), "保存失敗");
    }

    @Test
    void testRemoveCoinEntity() {
        String type = "USD";
        Assertions.assertDoesNotThrow(() -> coinRepository.deleteById(type));
    }

    @Test
    void testUpdateCoinEntity() {
        CoinEntity entity = new CoinEntity();
        entity.setName("USD");
        entity.setType("美元");
        Assertions.assertDoesNotThrow(() -> coinRepository.updateCoinEntity(entity.getType(), entity.getName()));
    }

    @Test
    void testGetCoinEntity() {
        String type = "USD";
        Mockito.when(coinRepository.findByType(type)).thenReturn(CoinEntity.builder().type("USD").name("美元").build());
        Assert.isTrue(Objects.nonNull(coinRepository.findByType(type)), "保存失敗");
    }

}
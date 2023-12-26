package com.example.demo.entity.vo;

import lombok.Data;

@Data
public class CoinApiVo {
    private String updateTime;
    private String type;
    private String name;
    private Float exchangeRate;
}

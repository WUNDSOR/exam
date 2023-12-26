package com.example.demo.controller;

import com.example.demo.entity.vo.CoinEntity;
import com.example.demo.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coin")
public class CoinController {
    @Autowired
    private CoinService coinService;

    @GetMapping("/bpi")
    public Object getBitcoinCurrentPrice() {
        return coinService.getBitcoinCurrentPrice();
    }

    @GetMapping("/bpi/detail")
    public Object getBitcoinCurrentPriceDetail() {
        return coinService.getBitcoinCurrentPriceDetail();
    }

    @RequestMapping("/addCoinEntity")
    public Object addCoinEntity(CoinEntity entity) {
        return coinService.addCoinEntity(entity);
    }

    @RequestMapping("/removeCoinEntity")
    public Object removeCoinEntity(String type) {
        coinService.removeCoinEntity(type);
        return "OK";
    }

    @RequestMapping("/updateCoinEntity")
    public Object updateCoinEntity(CoinEntity entity) {
        coinService.updateCoinEntity(entity);
        return "OK";
    }

    @RequestMapping("/getCoinEntity")
    public Object getCoinEntity(String type) {
        return coinService.getCoinEntity(type);
    }
}

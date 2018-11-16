package com.java.controller;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@Slf4j
public class StockController {

	@GetMapping("/stocks/{stockName}")
	public ResponseEntity getStockPrices(@PathVariable String stockName) throws IOException {
		log.info("Request made for stock "+ stockName);
		Stock stock=YahooFinance.get(stockName);
		BigDecimal price=stock.getQuote().getPrice();
		log.info("Response fetched for stock "+ stockName);
		return ResponseEntity.ok(new com.java.dto.Stock(stockName, price));
	}
}

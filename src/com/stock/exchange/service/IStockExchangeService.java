package com.stock.exchange.service;

import com.stock.exchange.dto.OrderBookResponse;
import com.stock.exchange.entity.OrderBook;

import java.util.List;

public interface IStockExchangeService {

    public void placeOrder(OrderBook orderBook);

    public List<OrderBookResponse> generateOrderReport();

}

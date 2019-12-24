package com.stock.exchange.facade;

import com.stock.exchange.dto.OrderBookResponse;

import java.util.List;

public interface IStockExchangeFacade {

    public void placeOrder(String ID, String time, String stockStr, String typeStr, String unit, String amount);

    public List<OrderBookResponse> generateOrderReport();
}

package com.stock.exchange.facade;

import com.stock.exchange.dto.OrderBookResponse;
import com.stock.exchange.factory.StockExchangeFactory;
import com.stock.exchange.service.IStockExchangeService;
import com.stock.exchange.entity.OrderBook;

import java.util.List;

public class StockExchangeFacade implements IStockExchangeFacade {

    IStockExchangeService stockExchangeService = StockExchangeFactory.getStockExchangeService();

    public void placeOrder(String ID, String time, String stockStr, String typeStr, String unit, String amount) {

        OrderBook orderBook = StockExchangeFactory.getOrderBookMapper().buildOrderBook(ID, time, stockStr, typeStr, unit, amount);

        stockExchangeService.placeOrder(orderBook);
    }

    public List<OrderBookResponse> generateOrderReport(){
        return stockExchangeService.generateOrderReport();
    }


}

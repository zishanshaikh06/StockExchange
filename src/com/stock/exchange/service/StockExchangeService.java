package com.stock.exchange.service;

import com.stock.exchange.aggregate.MinPriceQueue;
import com.stock.exchange.aggregate.OrderBookResponseList;
import com.stock.exchange.dto.OrderBookResponse;
import com.stock.exchange.factory.StockExchangeFactory;
import com.stock.exchange.entity.OrderBook;

import java.util.List;

public class StockExchangeService implements IStockExchangeService {

    private static final MinPriceQueue priceQueue = MinPriceQueue.getInstance();

    public void placeOrder(OrderBook orderBook) {

        StockExchangeFactory.getMatchingEngine().match(orderBook);

    }

    public List<OrderBookResponse> generateOrderReport() {
        return OrderBookResponseList.getInstance().getBookResponseList();

    }
}

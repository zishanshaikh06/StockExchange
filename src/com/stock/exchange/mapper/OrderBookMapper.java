package com.stock.exchange.mapper;

import com.stock.exchange.entity.OrderBook;
import com.stock.exchange.value.object.*;

public class OrderBookMapper {

    public OrderBook buildOrderBook(String ID, String time, String stockStr, String typeStr, String unit, String amount) {

        if(ID.startsWith("#")){
            ID = ID.substring(1, ID.length());
        }

        OrderID orderID = new OrderID(Integer.parseInt(ID));

        OrderTime orderTime = new OrderTime(time);

        Stock stock = new Stock(stockStr);

        TransactionType type = TransactionType.valueOf(typeStr);

        Quantity quantity = new Quantity(Integer.parseInt(unit));

        Price price = new Price(Double.valueOf(amount));

        OrderBook orderBook = new OrderBook(orderID, orderTime, stock, type, quantity, price);

        return orderBook;
    }
}

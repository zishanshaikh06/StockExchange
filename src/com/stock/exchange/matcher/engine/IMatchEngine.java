package com.stock.exchange.matcher.engine;

import com.stock.exchange.entity.OrderBook;

public interface IMatchEngine {
    public void match(OrderBook orderBook);
}

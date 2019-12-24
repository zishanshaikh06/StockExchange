package com.stock.exchange.aggregate;

import com.stock.exchange.entity.OrderBook;
import com.stock.exchange.value.object.Stock;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinPriceQueue {

    private static final MinPriceQueue MIN_PRICE_QUEUE = new MinPriceQueue();

    private static final PriorityQueue<OrderBook> minSellPrice = new PriorityQueue<>(new MinPriceQueueComp());

    private static final Map<Stock, PriorityQueue<OrderBook>> STOCK_PRIORITY_QUEUE_MAP = new LinkedHashMap<>();

    private MinPriceQueue() {
    }

    public static MinPriceQueue getInstance() {
        return MIN_PRICE_QUEUE;
    }

    public void addOrder(OrderBook orderBook) {

        STOCK_PRIORITY_QUEUE_MAP.computeIfAbsent(orderBook.getStock(), k -> minSellPrice);

        STOCK_PRIORITY_QUEUE_MAP.computeIfPresent(orderBook.getStock(), (k, v) -> minSellPrice);

        minSellPrice.add(orderBook);
    }

    public OrderBook getMinOrder(Stock stock) {
        if (STOCK_PRIORITY_QUEUE_MAP.size() > 0 && STOCK_PRIORITY_QUEUE_MAP.containsKey(stock)) {
            return STOCK_PRIORITY_QUEUE_MAP.get(stock).peek();
        }

        return null;
    }

    public OrderBook getMinOrder() {
        return minSellPrice.peek();
    }

    public OrderBook removeMinOrder() {
        return minSellPrice.poll();
    }


}

class MinPriceQueueComp implements Comparator<OrderBook> {

    public int compare(OrderBook s1, OrderBook s2) {
        if (s1.getPrice().getPrice() > s2.getPrice().getPrice())
            return 1;
        else if (s1.getPrice().getPrice() < s2.getPrice().getPrice())
            return -1;
        return 0;
    }
}



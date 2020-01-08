package com.stock.exchange.matcher.engine;

import com.stock.exchange.aggregate.MinPriceQueue;
import com.stock.exchange.aggregate.OrderBookResponseList;
import com.stock.exchange.dto.OrderBookResponse;
import com.stock.exchange.entity.OrderBook;
import com.stock.exchange.value.object.Quantity;
import com.stock.exchange.value.object.TransactionType;

import java.util.*;

public class MatchEngine implements IMatchEngine {

    private static final MinPriceQueue priceQueue = MinPriceQueue.getInstance();

    private static final OrderBookResponseList orderBookResponseList = OrderBookResponseList.getInstance();

    public void match(OrderBook orderBook) {

        if (orderBook.getTransactionType().equals(TransactionType.buy)) {

            double sum = 0.0;

            List<OrderBook> cacheOrder = new ArrayList<>();

            Map<Double, List<OrderBook>> priceMap = new HashMap<>();

            while ((orderBook.getQuantity().getQuantity() > 0)) {

                OrderBook order = priceQueue.getMinOrder(orderBook.getStock());

                if ((order != null) && (order.getPrice().getPrice() <= orderBook.getPrice().getPrice())) {

                    while (priceQueue.hasMoreOrder(orderBook.getStock()) &&
                            (order != null) && (order.getPrice().getPrice() < orderBook.getPrice().getPrice()) &&
                            (order.getQuantity().getQuantity() >= orderBook.getQuantity().getQuantity())) {

                        order = priceQueue.removeMinOrder(orderBook.getStock());

                        if (priceMap.containsKey(order.getPrice().getPrice())) {

                            priceMap.get(order.getPrice().getPrice()).add(order);

                        } else {

                            List<OrderBook> books = new ArrayList<>();
                            books.add(order);

                            priceMap.put(order.getPrice().getPrice(), books);

                        }
                    }

                    List<OrderBook> orderBooks = priceMap.get(order.getPrice().getPrice());

                    for (OrderBook book : orderBooks) {

                        sum += book.getQuantity().getQuantity();

                    }

                    double ratio = orderBook.getQuantity().getQuantity() / (sum);

                    System.out.println(ratio);

                    orderBooks.forEach(e -> {

                        Quantity quantity = null;

                        if (ratio > 0.0) {
                            quantity = new Quantity(e.getQuantity().getQuantity() * ratio);
                        } else {
                            quantity = new Quantity(e.getQuantity().getQuantity());
                        }

                        Quantity orderBookQ = orderBook.getQuantity();

                        orderBook.setQuantity(new Quantity(orderBookQ.getQuantity() - quantity.getQuantity()));

                        e.setQuantity(quantity);

                        //TODO: Logic to update the final report is yet to be implemented
                        //TODO: Corner case needs to be handled, currently just implemented the basic logic to handle duplicate prices,
                        //old code is present in MatchEngine_Old file

                        Double fQuant = 0.0;
                        Double soldQ = 0.0;

                        if (e.getQuantity().getQuantity() < orderBook.getQuantity().getQuantity()) {
                            fQuant = orderBook.getQuantity().getQuantity() - e.getQuantity().getQuantity();
                            soldQ = orderBook.getQuantity().getQuantity() - fQuant;
                        } else {
                            fQuant = e.getQuantity().getQuantity() - orderBook.getQuantity().getQuantity();
                            soldQ = e.getQuantity().getQuantity() - fQuant;
                        }

                        OrderBookResponse orderBookResponse = new OrderBookResponse(e.getOrderID(), new Quantity(soldQ), e.getPrice(), orderBook.getOrderID());

                        orderBookResponseList.addOrderBookResponse(orderBookResponse);

                        priceQueue.addOrder(e);
                    });
                }
            }
        } else {
            priceQueue.addOrder(orderBook);
        }
    }
}

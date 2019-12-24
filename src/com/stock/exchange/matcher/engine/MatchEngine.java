package com.stock.exchange.matcher.engine;

import com.stock.exchange.aggregate.MinPriceQueue;
import com.stock.exchange.aggregate.OrderBookResponseList;
import com.stock.exchange.dto.OrderBookResponse;
import com.stock.exchange.entity.OrderBook;
import com.stock.exchange.value.object.Quantity;
import com.stock.exchange.value.object.TransactionType;

public class MatchEngine implements IMatchEngine {

    private static final MinPriceQueue priceQueue = MinPriceQueue.getInstance();

    private static final OrderBookResponseList orderBookResponseList = OrderBookResponseList.getInstance();

    public void match(OrderBook orderBook) {

        if (orderBook.getTransactionType().equals(TransactionType.buy)) {

            while ((orderBook.getQuantity().getQuantity() > 0)) {

                OrderBook order = priceQueue.getMinOrder(orderBook.getStock());

                if ((order != null) && (order.getPrice().getPrice() < orderBook.getPrice().getPrice())) {

                    if (order.getQuantity().getQuantity() >= orderBook.getQuantity().getQuantity()) {

                        Integer fQuant = order.getQuantity().getQuantity() - orderBook.getQuantity().getQuantity();

                        Integer remQuant = order.getQuantity().getQuantity() - fQuant - orderBook.getQuantity().getQuantity();

                        Integer soldQ = order.getQuantity().getQuantity() - fQuant;

                        OrderBookResponse orderBookResponse = new OrderBookResponse(order.getOrderID(), new Quantity(soldQ), order.getPrice(), orderBook.getOrderID());

                        orderBookResponseList.addOrderBookResponse(orderBookResponse);

                        if (fQuant <= 0) {
                            priceQueue.removeMinOrder();
                            orderBook.setQuantity(new Quantity(remQuant));
                        } else {
                            order.setQuantity(new Quantity(fQuant));
                            orderBook.setQuantity(new Quantity(remQuant));
                        }
                    } else {
                        Integer fQuant = orderBook.getQuantity().getQuantity() - order.getQuantity().getQuantity();

                        Integer remQuant = orderBook.getQuantity().getQuantity() - fQuant - order.getQuantity().getQuantity();

                        Integer soldQ = orderBook.getQuantity().getQuantity() - fQuant;

                        OrderBookResponse orderBookResponse = new OrderBookResponse(order.getOrderID(), new Quantity(soldQ), order.getPrice(), orderBook.getOrderID());

                        orderBookResponseList.addOrderBookResponse(orderBookResponse);

                        if (remQuant <= 0) {
                            priceQueue.removeMinOrder();
                            orderBook.setQuantity(new Quantity(fQuant));
                        } else {
                            order.setQuantity(new Quantity(remQuant));
                            orderBook.setQuantity(new Quantity(fQuant));
                        }
                    }
                } else {
                    break;
                }
            }
        } else {
            priceQueue.addOrder(orderBook);
        }
    }
}

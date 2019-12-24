package com.stock.exchange.aggregate;

import com.stock.exchange.dto.OrderBookResponse;

import java.util.ArrayList;
import java.util.List;

public class OrderBookResponseList {

    private static final OrderBookResponseList ORDER_BOOK_RESPONSE_LIST = new OrderBookResponseList();

    private List<OrderBookResponse> bookResponseList = new ArrayList<>();

    private OrderBookResponseList(){}

    public static OrderBookResponseList getInstance(){
        return ORDER_BOOK_RESPONSE_LIST;
    }

    public void addOrderBookResponse(OrderBookResponse orderBookResponse){
        bookResponseList.add(orderBookResponse);
    }

    public List<OrderBookResponse> getBookResponseList(){
        return bookResponseList;
    }

}

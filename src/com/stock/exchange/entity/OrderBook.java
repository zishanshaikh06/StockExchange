package com.stock.exchange.entity;

import com.stock.exchange.value.object.*;

public class OrderBook {

    private OrderID orderID;
    private OrderTime orderTime;
    private Stock stock;
    private TransactionType transactionType;
    private Quantity quantity;
    private Price price;

    public OrderBook(OrderID orderID, OrderTime orderTime, Stock stock, TransactionType transactionType, Quantity quantity, Price price) {
        this.orderID = orderID;
        this.orderTime = orderTime;
        this.stock = stock;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderID getOrderID() {
        return orderID;
    }

    public void setOrderID(OrderID orderID) {
        this.orderID = orderID;
    }

    public OrderTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(OrderTime orderTime) {
        this.orderTime = orderTime;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}

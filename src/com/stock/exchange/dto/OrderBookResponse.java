package com.stock.exchange.dto;

import com.stock.exchange.value.object.OrderID;
import com.stock.exchange.value.object.Price;
import com.stock.exchange.value.object.Quantity;

public class OrderBookResponse {

    private OrderID sellerID;
    private Quantity quantity;
    private Price sellPrice;
    private OrderID buyerID;

    public OrderBookResponse(OrderID sellerID, Quantity quantity, Price sellPrice, OrderID buyerID) {
        this.sellerID = sellerID;
        this.quantity = quantity;
        this.sellPrice = sellPrice;
        this.buyerID = buyerID;
    }

    public OrderID getSellerID() {
        return sellerID;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Price getSellPrice() {
        return sellPrice;
    }

    public OrderID getBuyerID() {
        return buyerID;
    }

    @Override
    public String toString() {
        return "#" + this.sellerID.getOderId() + " " + this.quantity.getQuantity() + " " + this.sellPrice.getPrice() + " " + "#" + this.buyerID.getOderId();
    }
}

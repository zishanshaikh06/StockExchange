package com.stock.exchange.value.object;

public enum TransactionType {

    buy("buy"),
    sell("sell");

    private String type;

    TransactionType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}

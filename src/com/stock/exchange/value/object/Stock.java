package com.stock.exchange.value.object;

import java.util.Objects;

public class Stock {

    private final String stock;

    public Stock(String stock) {
        this.stock = stock;
    }

    public String getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock1 = (Stock) o;
        return Objects.equals(stock, stock1.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock);
    }
}

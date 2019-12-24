package com.stock.exchange.factory;

import com.stock.exchange.mapper.OrderBookMapper;
import com.stock.exchange.matcher.engine.IMatchEngine;
import com.stock.exchange.matcher.engine.MatchEngine;
import com.stock.exchange.service.IStockExchangeService;
import com.stock.exchange.service.StockExchangeService;
import com.stock.exchange.facade.IStockExchangeFacade;
import com.stock.exchange.facade.StockExchangeFacade;

public class StockExchangeFactory {

    public static OrderBookMapper getOrderBookMapper(){
        return new OrderBookMapper();
    }

    public static IStockExchangeService getStockExchangeService(){
        return new StockExchangeService();
    }

    public static IStockExchangeFacade getStockExchangeFacade(){
        return new StockExchangeFacade();
    }

    public static IMatchEngine getMatchingEngine(){
        return new MatchEngine();
    }

}

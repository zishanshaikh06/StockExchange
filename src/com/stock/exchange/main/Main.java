package com.stock.exchange.main;

import com.stock.exchange.dto.OrderBookResponse;
import com.stock.exchange.factory.StockExchangeFactory;
import com.stock.exchange.facade.IStockExchangeFacade;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            IStockExchangeFacade exchangeFacade = StockExchangeFactory.getStockExchangeFacade();

            while (true) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("\n Select options \n 1. Enter 1 below and press enter to place order \n 2. Enter 2 below and press enter to generate final order report\n 3. Enter 2 below to exit \n");
                Integer input = scanner.nextInt();

                if (input == 1) {

                    System.out.println("Enter the order details below and press enter \n");

                    scanner = new Scanner(System.in);

                    String orderIn = scanner.nextLine();

                    System.out.println("Order input " + orderIn);

                    if (orderIn != null && !orderIn.equals("")) {

                        String[] orderArr = orderIn.split(" ");

                        if (orderArr != null && orderArr.length == 6) {
                            exchangeFacade.placeOrder(orderArr[0], orderArr[1], orderArr[2], orderArr[3], orderArr[4], orderArr[5]);
                        } else {
                            System.out.println("Cannot place order, invalid input parameters");
                        }
                    }
                } else if (input == 2) {

                    System.out.println("Order Book Report: \n");

                    List<OrderBookResponse> res = exchangeFacade.generateOrderReport();

                    if (res != null && res.size() > 0) {
                        res.forEach(e -> {
                            System.out.println(e.toString());
                        });
                    } else {
                        System.out.println("Nothing to display in the report, report is empty, please place new orders \n");
                    }
                } else if (input == 3) {
                    System.exit(0);
                } else {
                    System.out.println("Invalid Input, please enter valid number");
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to place order, please check the input parameters");
        }
    }
}

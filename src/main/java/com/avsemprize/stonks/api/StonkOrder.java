package com.avsemprize.stonks.api;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.common.enums.SortDirection;
import net.jacobpeterson.alpaca.model.endpoint.order.CancelledOrder;
import net.jacobpeterson.alpaca.model.endpoint.order.Order;
import net.jacobpeterson.alpaca.model.endpoint.order.enums.CurrentOrderStatus;
import net.jacobpeterson.alpaca.model.endpoint.order.enums.OrderSide;
import net.jacobpeterson.alpaca.model.endpoint.order.enums.OrderStatus;
import net.jacobpeterson.alpaca.model.endpoint.order.enums.OrderTimeInForce;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class StonkOrder {
    private AlpacaAPI alpacaAPI;

    public StonkOrder(AlpacaAPI alpacaAPI){
        this.alpacaAPI = alpacaAPI;
    }

    public List<Order> getOrders(List<String> symbols, int limit, ZonedDateTime start, ZonedDateTime end){
        List<Order> orders;
        try{
            orders = this.alpacaAPI.orders().get(
                    CurrentOrderStatus.ALL,
                    limit,
                    start,
                    end,
                    SortDirection.DESCENDING,
                    true,
                    symbols
            );
        }catch (AlpacaClientException e){
            e.printStackTrace();
            orders = new ArrayList<>();
        }
        return orders;
    }

    public Order placeLimitOrder(String symbol, int quantity, OrderSide side, OrderTimeInForce orderTimeInForce, double limitPrice, boolean extendedHours){
        Order order;
        try{
            order = this.alpacaAPI.orders().requestLimitOrder(symbol, quantity, side, orderTimeInForce, limitPrice, extendedHours);
        }catch(AlpacaClientException e){
            e.printStackTrace();
            order = new Order();
        }
        return order;
    }
    public Order placePartialOrder(String symbol, double quantity, OrderSide orderSide){
        Order order;
        try{
            order = this.alpacaAPI.orders().requestFractionalMarketOrder(symbol, quantity, orderSide);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            order = new Order();
        }
        return order;
    }

    public Order updateOrder(Order order, int quantity, OrderTimeInForce timeInForce, double limitPrice){
        try{
            order = this.alpacaAPI.orders().get(order.getId(), false);
            if(order.getFilledAt() == null && order.getStatus().equals(OrderStatus.NEW)){
                order = this.alpacaAPI.orders().replace(
                        order.getId(),
                        quantity,
                        timeInForce,
                        limitPrice,
                        null,
                        null,
                        null
                );
            }
        }catch(AlpacaClientException e){
            e.printStackTrace();
        }
        return order;
    }
    public List<CancelledOrder> cancelAllOrders(){
        List<CancelledOrder> cancelledOrders;
        try{
            cancelledOrders = this.alpacaAPI.orders().cancelAll();
        }catch(AlpacaClientException e){
            e.printStackTrace();
            cancelledOrders = new ArrayList<>();
        }
        return cancelledOrders;
    }

    public Order cancelOrderById(String orderId){
        Order cancelledOrder;
        try{
            this.alpacaAPI.orders().cancel(orderId);
            cancelledOrder = this.alpacaAPI.orders().get(orderId, false);
        }catch (AlpacaClientException e){
            cancelledOrder = new Order();
        }
        return cancelledOrder;

    }
}

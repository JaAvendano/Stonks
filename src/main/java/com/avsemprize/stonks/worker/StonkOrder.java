package com.avsemprize.stonks.worker;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.common.enums.SortDirection;
import net.jacobpeterson.alpaca.model.endpoint.orders.CancelledOrder;
import net.jacobpeterson.alpaca.model.endpoint.orders.Order;
import net.jacobpeterson.alpaca.model.endpoint.orders.enums.CurrentOrderStatus;
import net.jacobpeterson.alpaca.model.endpoint.orders.enums.OrderSide;
import net.jacobpeterson.alpaca.model.endpoint.orders.enums.OrderStatus;
import net.jacobpeterson.alpaca.model.endpoint.orders.enums.OrderTimeInForce;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;

import java.time.ZonedDateTime;
import java.util.List;

public class StonkOrder {
    private AlpacaAPI alpacaAPI;

    public StonkOrder(AlpacaAPI alpacaAPI){
        this.alpacaAPI = alpacaAPI;
    }

    public List<Order> getOrders(List<String> symbols, int limit, ZonedDateTime start, ZonedDateTime end){

        try{
            return this.alpacaAPI.orders().get(
                    CurrentOrderStatus.ALL,
                    limit,
                    start,
                    end,
                    SortDirection.DESCENDING,
                    true,
                    symbols
            );
        }catch (AlpacaClientException e){
            return null;
        }
    }

    public Order placeLimitOrder(String symbol, double quantity, OrderSide side, OrderTimeInForce orderTimeInForce, double limitPrice, boolean extendedHours){
        Order order;
        try{
            return this.alpacaAPI.orders().requestLimitOrder(symbol, quantity, side, orderTimeInForce, limitPrice, extendedHours);
        }catch(AlpacaClientException e){
            return null;
        }
    }
    public Order placePartialOrder(String symbol, double quantity, OrderSide orderSide){

        try{
            return this.alpacaAPI.orders().requestFractionalMarketOrder(symbol, quantity, orderSide);
        }catch (AlpacaClientException e){
            return null;
        }
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
        try{
            return this.alpacaAPI.orders().cancelAll();
        }catch(AlpacaClientException e){
            return null;
        }
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

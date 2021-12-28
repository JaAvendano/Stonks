package com.avsemprize.stonks.api;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.order.Order;
import net.jacobpeterson.alpaca.model.endpoint.position.ClosePositionOrder;
import net.jacobpeterson.alpaca.model.endpoint.position.Position;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;

import java.util.ArrayList;
import java.util.List;

public class StonkPosition {
    private AlpacaAPI alpacaAPI;

    public StonkPosition(AlpacaAPI alpacaAPI){
        this.alpacaAPI = alpacaAPI;
    }

    public List<Position> getPositions(){
        List<Position> positions;
        try{
            positions = this.alpacaAPI.positions().get();
        }catch (AlpacaClientException e){
            e.printStackTrace();
            positions = new ArrayList<>();
        }
        return positions;
    }

    public Position getPositionBySymbol(String symbol){
        Position position;
        try{
            position = this.alpacaAPI.positions().getBySymbol(symbol);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            position = new Position();
        }
        return position;
    }

    public Order closePosition(String symbol, int quantity, double percentage){
        Order order;
        try{

            order = this.alpacaAPI.positions().close(symbol, quantity, percentage);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            order = new Order();
        }
        return order;
    }

    public List<ClosePositionOrder> closeAllPositions(){
        List<ClosePositionOrder> closePositionOrders;
        try{
            closePositionOrders = this.alpacaAPI.positions().closeAll(true);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            closePositionOrders = new ArrayList<>();
        }
        return closePositionOrders;
    }
}

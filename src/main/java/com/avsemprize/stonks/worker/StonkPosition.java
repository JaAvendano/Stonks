package com.avsemprize.stonks.worker;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.orders.Order;
import net.jacobpeterson.alpaca.model.endpoint.positions.ClosePositionOrder;
import net.jacobpeterson.alpaca.model.endpoint.positions.Position;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;
import java.util.List;

public class StonkPosition {
    private AlpacaAPI alpacaAPI;

    public StonkPosition(AlpacaAPI alpacaAPI){
        this.alpacaAPI = alpacaAPI;
    }

    public List<Position> getPositions(){

        try{
            return this.alpacaAPI.positions().get();
        }catch (AlpacaClientException e){
            return null;
        }
    }

    public Position getPositionBySymbol(String symbol){

        try{
            return this.alpacaAPI.positions().getBySymbol(symbol);
        }catch (AlpacaClientException e){
            return null;
        }
    }

    public Order closePosition(String symbol, double quantity, double percentage){

        try{

            return this.alpacaAPI.positions().close(symbol, quantity, percentage);
        }catch (AlpacaClientException e){
            return null;
        }
    }

    public List<ClosePositionOrder> closeAllPositions(){

        try{
            return this.alpacaAPI.positions().closeAll(true);
        }catch (AlpacaClientException e){
            return null;
        }
    }
}

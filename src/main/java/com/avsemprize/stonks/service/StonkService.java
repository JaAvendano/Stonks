package com.avsemprize.stonks.service;

import com.avsemprize.stonks.api.*;
import net.jacobpeterson.alpaca.AlpacaAPI;

public class StonkService {


    private AlpacaAPI alpacaAPI;

    public StonkService(String key, String secret){
        this.alpacaAPI = new AlpacaAPI(key, secret);
    }

    public StonkData getStockData(){
        return new StonkData(this.alpacaAPI);
    }

    public StonkOrder getStockOrder(){
        return new StonkOrder(this.alpacaAPI);
    }

    public StonkPortfolioHistory getStockPortfolioHistory(){
        return new StonkPortfolioHistory(this.alpacaAPI);
    }

    public StonkPosition getStockPosition(){
        return new StonkPosition(this.alpacaAPI);
    }

    public StonkWatchList getStockWatchList(){
        return new StonkWatchList(this.alpacaAPI);
    }


}

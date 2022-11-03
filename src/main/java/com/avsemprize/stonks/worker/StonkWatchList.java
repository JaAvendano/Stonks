package com.avsemprize.stonks.worker;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.watchlist.Watchlist;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;

import java.util.List;

public class StonkWatchList {
    private AlpacaAPI alpacaAPI;

    public StonkWatchList(AlpacaAPI alpacaAPI){
        this.alpacaAPI = alpacaAPI;
    }

    public List<Watchlist> getAllWatchLists(){

        try{
            return this.alpacaAPI.watchlist().get();
        }catch (AlpacaClientException e){
            return null;
        }
    }

    public Watchlist getWatchListById(String watchListId){

        try{
            return this.alpacaAPI.watchlist().get(watchListId);
        }catch (AlpacaClientException e){
            return null;
        }
    }

    public Watchlist addSecurityToWatchList(String watchListId){

        try {
            return this.alpacaAPI.watchlist().get(watchListId);
        }catch (AlpacaClientException e){
            return null;
        }
    }

    public Watchlist removeSecuritiesFromWatchList(String watchListId, String symbol){
        try{
            return this.alpacaAPI.watchlist().removeSymbol(watchListId, symbol);
        }catch (AlpacaClientException e){
            return null;
        }
    }

    public void deleteWatchList(String watchListId){
        try{
            this.alpacaAPI.watchlist().delete(watchListId);
        }catch (AlpacaClientException e){
            e.printStackTrace();
        }
    }

    public Watchlist createWatchList(String watchListName, String[] symbols){
        try{
            return this.alpacaAPI.watchlist().create(watchListName, symbols);
        }catch (AlpacaClientException e){
            return null;
        }
    }

    public Watchlist updateWatchList(String watchListId, String watchListName, String[] symbols){
        Watchlist watchlist;
        try{
            return this.alpacaAPI.watchlist().update(watchListId, watchListName, symbols);
        }catch (AlpacaClientException e){
            return null;
        }
    }
}

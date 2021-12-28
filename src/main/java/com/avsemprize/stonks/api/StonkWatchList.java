package com.avsemprize.stonks.api;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.watchlist.Watchlist;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;

import java.util.ArrayList;
import java.util.List;

public class StonkWatchList {
    private AlpacaAPI alpacaAPI;

    public StonkWatchList(AlpacaAPI alpacaAPI){
        this.alpacaAPI = alpacaAPI;
    }

    public List<Watchlist> getAllWatchLists(){
        List<Watchlist> watchlist;
        try{
            watchlist = this.alpacaAPI.watchlist().get();
        }catch (AlpacaClientException e){
            e.printStackTrace();
            watchlist = new ArrayList<>();
        }
        return watchlist;
    }

    public Watchlist getWatchListById(String watchListId){
        Watchlist watchlist;
        try{
            watchlist = this.alpacaAPI.watchlist().get(watchListId);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            watchlist = new Watchlist();
        }
        return watchlist;
    }

    public Watchlist addSecurityToWatchList(String watchListId){
        Watchlist watchlist;
        try {
            watchlist = this.alpacaAPI.watchlist().get(watchListId);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            watchlist = new Watchlist();
        }
        return watchlist;
    }

    public Watchlist removeSecuritiesFromWatchList(String watchListId, String symbol){
        Watchlist watchlist;
        try{
            watchlist = this.alpacaAPI.watchlist().removeSymbol(watchListId, symbol);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            watchlist = new Watchlist();
        }
        return watchlist;
    }

    public void deleteWatchList(String watchListId){
        try{
            this.alpacaAPI.watchlist().delete(watchListId);
        }catch (AlpacaClientException e){
            e.printStackTrace();
        }
    }

    public Watchlist createWatchList(String watchListName, String[] symbols){
        Watchlist watchlist;
        try{
            watchlist = this.alpacaAPI.watchlist().create(watchListName, symbols);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            watchlist = new Watchlist();
        }
        return watchlist;
    }

    public Watchlist updateWatchList(String watchListId, String watchListName, String[] symbols){
        Watchlist watchlist;
        try{
            watchlist = this.alpacaAPI.watchlist().update(watchListId, watchListName, symbols);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            watchlist = new Watchlist();
        }
        return watchlist;
    }
}

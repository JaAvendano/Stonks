package com.avsemprize.stonks.worker;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.common.historical.bar.enums.BarTimePeriod;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.stock.historical.bar.StockBarsResponse;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.stock.historical.bar.enums.BarAdjustment;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.stock.historical.bar.enums.BarFeed;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.stock.historical.quote.LatestStockQuoteResponse;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.stock.historical.quote.StockQuotesResponse;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.stock.historical.snapshot.Snapshot;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.stock.historical.trade.LatestStockTradeResponse;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.stock.historical.trade.StockTradesResponse;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class StonkData {
    private AlpacaAPI alpacaAPI;

    public StonkData(AlpacaAPI alpacaAPI){
        this.alpacaAPI = alpacaAPI;
    }

    public StockQuotesResponse getQuotes(String symbol, ZonedDateTime start, ZonedDateTime end, int limit, String pageToken){

        try {
            return this.alpacaAPI.stockMarketData().getQuotes(symbol, start, end, limit, pageToken);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            return null;
        }
    }

    public LatestStockQuoteResponse getLatestQuote(String symbol){

        try{
            return this.alpacaAPI.stockMarketData().getLatestQuote(symbol);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            return null;
        }
    }

    public LatestStockTradeResponse getLatestTrade(String symbol){

        try{
            return this.alpacaAPI.stockMarketData().getLatestTrade(symbol);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            return null;
        }
    }

    public StockTradesResponse getTrades(String symbol, ZonedDateTime start, ZonedDateTime end, int limit, String pageToken){

        try{
            return this.alpacaAPI.stockMarketData().getTrades(
                    symbol,
                    start,
                    end,
                    limit,
                    pageToken);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            return null;
        }
    }


    public StockBarsResponse getBars(String symbol, ZonedDateTime start, ZonedDateTime end, int limit, int timeBarDurationPeriod , String pageToken){

        try{
            return this.alpacaAPI
                    .stockMarketData()
                    .getBars(
                            symbol,
                            start,
                            end,
                            limit,
                            pageToken,
                            timeBarDurationPeriod,
                            BarTimePeriod.HOUR,
                            BarAdjustment.SPLIT,
                            BarFeed.SIP);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            return null;
        }
    }

    public Snapshot getSnapShot(String symbol){
        try{
            return this.alpacaAPI.stockMarketData().getSnapshot(symbol);
        }catch (AlpacaClientException e){
            return null;
        }

    }
    public Map<String, Snapshot> getSnapshots(List<String> symbols){
        try{
            return this.alpacaAPI.stockMarketData().getSnapshots(symbols);
        }catch (AlpacaClientException e){
            return null;
        }
    }
}

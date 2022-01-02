package com.avsemprize.stonks.api;

import com.avsemprize.stonks.models.Bar;
import com.avsemprize.stonks.models.Quote;
import com.avsemprize.stonks.models.SnapShot;
import com.avsemprize.stonks.models.Trade;
import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.bar.BarsResponse;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.bar.enums.BarAdjustment;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.bar.enums.BarTimePeriod;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.quote.LatestQuoteResponse;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.quote.QuotesResponse;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.trade.LatestTradeResponse;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.trade.TradesResponse;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StonkData {
    private AlpacaAPI alpacaAPI;

    public StonkData(AlpacaAPI alpacaAPI){
        this.alpacaAPI = alpacaAPI;
    }

    public List<Quote> getQuotes(String symbol, ZonedDateTime start, ZonedDateTime end, int limit, String pageToken){
        QuotesResponse quote;
        try {
            quote = this.alpacaAPI.marketData().getQuotes(symbol, start, end, limit, pageToken);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            quote = new QuotesResponse();
        }
        return this.convertQuotesResponse(symbol, quote);
    }

    public Quote getLatestQuote(String symbol){
        LatestQuoteResponse latestQuoteResponse;
        try{
            latestQuoteResponse = this.alpacaAPI.marketData().getLatestQuote(symbol);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            latestQuoteResponse = new LatestQuoteResponse();
        }
        return new Quote(symbol, latestQuoteResponse.getQuote());
    }

    public Trade getLatestTrade(String symbol){
        LatestTradeResponse latestTradeResponse;
        try{
            latestTradeResponse = this.alpacaAPI.marketData().getLatestTrade(symbol);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            latestTradeResponse = new LatestTradeResponse();
        }
        return new Trade(symbol, latestTradeResponse.getTrade());
    }

    public List<Trade> getTrades(String symbol, ZonedDateTime start, ZonedDateTime end, int limit, String pageToken){
        TradesResponse tradesResponse;
        try{
            tradesResponse = this.alpacaAPI.marketData().getTrades(
                    symbol,
                    start,
                    end,
                    limit,
                    pageToken);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            tradesResponse = new TradesResponse();
        }
        return this.convertTradeResponse(symbol, tradesResponse);
    }


    public List<Bar> getBars(String symbol, ZonedDateTime start, ZonedDateTime end, int limit, int timeBarDurationPeriod , String pageToken){
        BarsResponse barsResponse;
        try{
            barsResponse = this.alpacaAPI
                    .marketData()
                    .getBars(
                            symbol,
                            start,
                            end,
                            limit,
                            pageToken,
                            timeBarDurationPeriod,
                            BarTimePeriod.HOUR,
                            BarAdjustment.SPLIT);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            barsResponse = new BarsResponse();
        }
        return this.convertBarsResponse(barsResponse);
    }

    public SnapShot getSnapShot(String symbol){
        net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.snapshot.Snapshot snapshot;
        try{
            snapshot = this.alpacaAPI.marketData().getSnapshot(symbol);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            snapshot = new net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.snapshot.Snapshot();
        }
        return new SnapShot(symbol, snapshot);

    }
    public Map<String, SnapShot> getSnapshots(List<String> symbols){
        Map<String, net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.snapshot.Snapshot> snaps = new HashMap<>();
        try{
            snaps = this.alpacaAPI.marketData().getSnapshots(symbols);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            snaps = new HashMap<>();
        }
        return this.convertSnapshots(snaps);
    }

    private Map<String, SnapShot> convertSnapshots(Map<String, net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.snapshot.Snapshot> snapshots){
        Map<String, SnapShot> snaps = new HashMap<>();
        snapshots.forEach((key, value) ->{
            snaps.put(key, new SnapShot(key, value));
        });
        return snaps;
    }
    private List<Trade> convertTradeResponse(String symbol, TradesResponse tradesResponse){
        List<Trade> trades = new ArrayList<>();
        tradesResponse.getTrades().forEach(item ->{
            Trade trade = new Trade(symbol, item);
            trades.add(trade);
        });
        return trades;
    }

    private List<Bar> convertBarsResponse(BarsResponse barsResponse){
        List<Bar> bars = new ArrayList<>();
        barsResponse.getBars().forEach(item ->{
            Bar bar = new Bar(item);
            bars.add(bar);
        });
        return bars;
    }

    private List<Quote> convertQuotesResponse(String symbol, QuotesResponse response){
        List<Quote> quotes = new ArrayList<>();
        response.getQuotes().forEach(item ->{
            Quote quote = new Quote(symbol, item);
            quotes.add(quote);
        });
        return quotes;
    }
}

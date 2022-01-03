package com.avsemprize.stonks.models;

import lombok.Data;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.bar.BarsResponse;

import java.util.ArrayList;
import java.util.List;

@Data
public class Bars {

    private List<Bar> bars;
    private String symbol;
    private String nextPageToken;

    public Bars(BarsResponse response){
        this.symbol = response.getSymbol();
        this.nextPageToken = response.getNextPageToken();
        this.bars = this.convertBarsResponse(response);
    }

    private List<Bar> convertBarsResponse(BarsResponse response){
        List<Bar> bars = new ArrayList<>();
        response.getBars().forEach(item ->{
            Bar bar = new Bar(response.getSymbol(), item);
            bars.add(bar);
        });
        return bars;
    }
}

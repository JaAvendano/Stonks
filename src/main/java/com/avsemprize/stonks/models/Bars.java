package com.avsemprize.stonks.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.stock.historical.bar.StockBarsResponse;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Bars {

    private List<Bar> bars;
    private String symbol;
    private String nextPageToken;


    public Bars(StockBarsResponse bars){
        this.symbol = bars.getSymbol();
        this.nextPageToken = bars.getNextPageToken();
        this.bars = this.convertBarsResponse(bars);
    }

    private List<Bar> convertBarsResponse(StockBarsResponse response){
        List<Bar> bars = new ArrayList<>();
        if(response.getBars() != null){
            response.getBars().forEach(item ->{
                Bar bar = new Bar(response.getSymbol(), item);
                bars.add(bar);
            });
        }

        return bars;
    }
}

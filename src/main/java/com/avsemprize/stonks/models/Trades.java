package com.avsemprize.stonks.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.trade.TradesResponse;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Trades {
    private List<Trade> trades;
    private String symbol;
    @SerializedName("next_page_token")
    private String nextPageToken;

    public Trades(TradesResponse response){
        this.symbol = response.getSymbol();
        this.nextPageToken = response.getNextPageToken();
        this.trades = this.convertTradeResponse(response);
    }

    private List<Trade> convertTradeResponse(TradesResponse tradesResponse){
        List<Trade> trades = new ArrayList<>();
        if(tradesResponse.getTrades() != null){
            tradesResponse.getTrades().forEach(item ->{
                Trade trade = new Trade(tradesResponse.getSymbol(), item);
                trades.add(trade);
            });
        }

        return trades;
    }
}

package com.avsemprize.stonks.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Quote {
    private String symbol;
    @SerializedName("time")
    private String time;
    @SerializedName("ask_exchange")
    private String askExchange;
    @SerializedName("ask_price")
    private Double askPrice;
    @SerializedName("ask_prize")
    private int askSize;
    @SerializedName("bid_exchange")
    private String bidExchange;
    @SerializedName("bid_prize")
    private Double bidPrice;
    @SerializedName("bid_size")
    private int bidSize;
    @SerializedName("quote_conditions")
    List<String> quoteConditions;

    public Quote(String symbol, net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.quote.Quote quote) {
        this.symbol = symbol;
        this.time = quote.getT().toString();
        this.askExchange = quote.getAx();
        this.askPrice = quote.getAp();
        this.askSize = quote.getAs();
        this.bidExchange = quote.getBx();
        this.bidPrice = quote.getBp();
        this.bidSize = quote.getBs();
        this.quoteConditions = quote.getC();
    }
}

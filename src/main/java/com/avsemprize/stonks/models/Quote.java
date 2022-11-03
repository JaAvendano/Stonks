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

    public Quote(String symbol, net.jacobpeterson.alpaca.model.endpoint.marketdata.common.historical.quote.Quote quote) {
        this.symbol = symbol;
        this.time = quote.getTimestamp().toString();
        this.askPrice = quote.getAskPrice();
        this.bidPrice = quote.getBidPrice();
    }
}

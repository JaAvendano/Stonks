package com.avsemprize.stonks.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Bar {

    private String symbol;
    private String time;
    @SerializedName("open_price")
    private double openPrice;
    @SerializedName("high_price")
    private double highPrice;
    @SerializedName("low_price")
    private double lowPrice;
    @SerializedName("close_price")
    private double closePrice;
    private long volume;

    public Bar(String symbol, net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.bar.Bar bar){
        this.symbol = symbol;
        this.time = bar.getT().toString();
        this.openPrice = bar.getO();
        this.highPrice = bar.getH();
        this.lowPrice = bar.getL();
        this.closePrice = bar.getC();
        this.volume = bar.getV();
    }
}

package com.avsemprize.stonks.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.stock.historical.bar.StockBar;

@Data
@NoArgsConstructor
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

    public Bar(String symbol, StockBar bar){
        this.symbol = symbol;
        this.time = bar.getTimestamp().toString();
        this.openPrice = bar.getOpen();
        this.highPrice = bar.getHigh();
        this.lowPrice = bar.getLow();
        this.closePrice = bar.getClose();
        this.volume = bar.getVolume();
    }
}

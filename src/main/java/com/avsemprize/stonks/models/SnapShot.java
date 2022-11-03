package com.avsemprize.stonks.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.stock.historical.snapshot.Snapshot;

@Data
@NoArgsConstructor
public class SnapShot {
    @SerializedName("latest_quote")
    private Quote latestQuote;
    @SerializedName("latest_trade")
    private Trade latestTrade;
    @SerializedName("minute_bar")
    private Bar minuteBar;
    @SerializedName("daily_bar")
    private Bar dailyBar;
    @SerializedName("prev_daily_bar")
    private Bar previousDailyBar;


    public SnapShot(String symbol, Snapshot snapshot){
        this.latestQuote = new Quote(symbol, snapshot.getLatestQuote());
        this.latestTrade = new Trade(symbol, snapshot.getLatestTrade());
        this.minuteBar = new Bar(symbol, snapshot.getMinuteBar());
        this.dailyBar = new Bar(symbol, snapshot.getDailyBar());
        this.previousDailyBar = new Bar(symbol, snapshot.getPrevDailyBar());
    }
}

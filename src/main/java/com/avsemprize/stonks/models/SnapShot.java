package com.avsemprize.stonks.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
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

    public SnapShot(String symbol, net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.snapshot.Snapshot snapshot){
        this.latestQuote = new Quote(symbol, snapshot.getLatestQuote());
        this.latestTrade = new Trade(symbol, snapshot.getLatestTrade());
        this.minuteBar = new Bar(snapshot.getMinuteBar());
        this.dailyBar = new Bar(snapshot.getDailyBar());
        this.previousDailyBar = new Bar(snapshot.getPrevDailyBar());
    }
}

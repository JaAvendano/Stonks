package com.avsemprize.stonks.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Trade {
    private String symbol;
    private String time;
    private String exchange;
    @SerializedName("trade_price")
    private Double tradePrice;
    @SerializedName("trade_size")
    private int tradeSize;
    @SerializedName("trade_conditions")
    private List<String> tradeConditions;
    @SerializedName("trade_id")
    private long tradeId;
    private String tape;

    public Trade(String symbol, net.jacobpeterson.alpaca.model.endpoint.marketdata.common.historical.trade.Trade trade){
        this.symbol = symbol;
        this.time = trade.getTimestamp().toString();
        this.tradePrice = trade.getPrice();
        this.tradeId = trade.getTradeID();
    }
}

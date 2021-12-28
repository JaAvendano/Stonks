package com.avsemprize.stonks.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Trade {
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

    public Trade(net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.trade.Trade trade){
        this.time = trade.getT().toString();
        this.exchange = trade.getX();
        this.tradePrice = trade.getP();
        this.tradeSize = trade.getS();
        this.tradeConditions = trade.getC();
        this.tradeId = trade.getI();
        this.tape = trade.getZ();
    }
}

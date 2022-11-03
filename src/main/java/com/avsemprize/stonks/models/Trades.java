package com.avsemprize.stonks.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Trades {
    private List<Trade> trades;
    private String symbol;
    @SerializedName("next_page_token")
    private String nextPageToken;

    public Trades(Trades response){
        this.symbol = response.getSymbol();
        this.nextPageToken = response.getNextPageToken();
    }
}

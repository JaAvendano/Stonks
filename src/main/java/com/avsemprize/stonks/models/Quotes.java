package com.avsemprize.stonks.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class Quotes {

    private List<Quote> quotes;
    private String symbol;
    @SerializedName("next_page_token")
    private String nextPageToken;


    public Quotes(Quotes response){
        this.symbol = response.getSymbol();
        this.nextPageToken = response.getNextPageToken();
        this.quotes = response.quotes;
    }
}

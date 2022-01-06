package com.avsemprize.stonks.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.quote.QuotesResponse;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Quotes {

    private List<Quote> quotes;
    private String symbol;
    @SerializedName("next_page_token")
    private String nextPageToken;


    public Quotes(QuotesResponse response){
        this.symbol = response.getSymbol();
        this.nextPageToken = response.getNextPageToken();
        this.quotes = this.convertQuotesResponse(response);
    }


    private List<Quote> convertQuotesResponse(QuotesResponse response){
        List<Quote> quotes = new ArrayList<>();

        if(response.getQuotes() != null){
            // making sure there is no null value being fed.
            response.getQuotes().forEach(item ->{
                Quote quote = new Quote(response.getSymbol(), item);
                quotes.add(quote);
            });
        }

        return quotes;
    }
}

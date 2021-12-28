package com.avsemprize.stonks.api;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.portfoliohistory.PortfolioHistory;
import net.jacobpeterson.alpaca.model.endpoint.portfoliohistory.enums.PortfolioPeriodUnit;
import net.jacobpeterson.alpaca.model.endpoint.portfoliohistory.enums.PortfolioTimeFrame;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;

import java.time.LocalDate;

public class StonkPortfolioHistory {
    private AlpacaAPI alpacaAPI;

    public StonkPortfolioHistory(AlpacaAPI alpacaAPI){
        this.alpacaAPI = alpacaAPI;
    }

    public PortfolioHistory getPortfolioHistory(int timeLength, PortfolioPeriodUnit timeUnit, PortfolioTimeFrame timeFrame, LocalDate startDate){
        PortfolioHistory portfolioHistory;
        try{
            portfolioHistory = this.alpacaAPI.portfolioHistory().get(
                    timeLength,
                    timeUnit,
                    timeFrame,
                    startDate,
                    false);
        }catch (AlpacaClientException e){
            e.printStackTrace();
            portfolioHistory = new PortfolioHistory();
        }
        return portfolioHistory;
    }
}

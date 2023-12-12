/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;
import java.util.ArrayList;
import java.util.List;

import model.MarketModel.Market;


/**
 *
 * @author kal bugrara
 */

public class MarketCatalog {
    
    private List<Market> markets;
    

    public MarketCatalog() {
       this.markets = new ArrayList<>();
    }

    
    public void addMarket(Market market){
        markets.add(market);
    }

    //return all markets' names 
    public List<String> getMarketNames() {
        List<String> names = new ArrayList<>();
        for (Market market: markets){
            names.add(market.getName());
        }
        return names;
    }


}


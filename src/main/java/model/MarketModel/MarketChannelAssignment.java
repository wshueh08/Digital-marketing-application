/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

/**
 *
 * @author kal bugrara
 */


 //market link to channel
public class MarketChannelAssignment {
    
    Market market;
    Channel channel;
    
    public MarketChannelAssignment(Market m, Channel c){
        market = m;
        channel = c;
    }

    public MarketChannelAssignment(Market m2, java.nio.channels.Channel c2) {
    }

    public Market getMarket() {
        return market;
    }

    public Channel getChannel() {
        return channel;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;
import java.util.ArrayList;
import java.util.List;

import model.MarketModel.Channel;

/**
 *
 * @author kal bugrara
 */

public class ChannelCatalog {
    
    private List<Channel> channels;

    public ChannelCatalog() {
       this.channels = new ArrayList<>();
    }

    public void addChannel(Channel channel){
        channels.add(channel);
    }

    public List<String> getChannelTypes() {
        List<String> types = new ArrayList<>();
        for (Channel channel: channels){
            types.add(channel.getType());
        }
        return types;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.List;

/**
 *
 * @author kal bugrara
 */



public class Channel {

    String type;

    public Channel(String type) {
        this.type = type;
    }

   
    //get inStore or online
    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

   
}

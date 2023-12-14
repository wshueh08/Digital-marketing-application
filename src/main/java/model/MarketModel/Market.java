/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import model.ProductManagement.SolutionOffer;

/**
 *
 * @author kal bugrara
 */


// electronics, clothes, and sephora
public class Market {

    String name;
    ArrayList<SolutionOffer> so;
    ArrayList<MarketChannelAssignment> channels;
    ArrayList<String> characteristics;
    ArrayList<Market> submarkets;
    int size;


    public Market(String name) {
       this.name = name;
       characteristics = new ArrayList<String>();
       this.so = new ArrayList<>(); //6 market bundles
    }

    public String getName(){
        return name;
    }


    public void addSolutionOffer(SolutionOffer offer) {
        this.so.add(offer);
    }

    public List<SolutionOffer> getSolutionOffer() {
        return this.so;
    }



    @Override
    public String toString() {
        return "Market: " + name;
    }



}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ProductManagement;

import java.util.ArrayList;
import java.util.List;

import model.CustomerManagement.ChannelCatalog;
import model.MarketModel.MarketChannelAssignment;


/**
 *
 * @author kal bugrara
 */

public class SolutionOffer{
    
    ArrayList<Product> products;
    int price;
    MarketChannelAssignment marketChannelComb;
    String category; 
    String ads;


    public SolutionOffer(MarketChannelAssignment m){
        marketChannelComb = m;
        products = new ArrayList<Product>();
    }

    public SolutionOffer(MarketChannelAssignment m, ArrayList<Product> products, String ads, String category){
        this.marketChannelComb = m;
        this.products = products; // to use the passed products list
        this.ads = ads;
        this.category = category;
    }

    //create method to get the category of the SolutionOffer
    public String getCategory(){
        return this.category;
    }

    public void addProduct(Product p){
        this.products.add(p);
    }

    public void setPrice(int p){
        this.price = p;
    }

    public int getPrice(){
        return this.price;
    }

    public String getAds(){
        return this.ads;
    }

    
    public List<String>getProductNames(){
        List<String> productNames = new ArrayList<String>();
        for(Product product: products){
            productNames.add(product.getName());
        }
        return productNames;
    }


    public List<Product> getProductList(){
        return new ArrayList<>(products);
    }



    public MarketChannelAssignment getMarketChannelCombination(){
        return this.marketChannelComb;
    }




    @Override
    public String toString() {
        return "SolutionOffer\n" +
                "products: " + getProductList() +
                ", price: " + price +
                ", marketChannelComb: " + marketChannelComb +
                ", ads: '" + ads + '\'' +
                '}';
    }


   


}

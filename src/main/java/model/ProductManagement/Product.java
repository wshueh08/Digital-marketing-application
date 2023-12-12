/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.MarketModel.MarketChannelAssignment;
import model.OrderManagement.OrderItem;

/**
 *
 * @author kal bugrara
 */

public class Product {

    private String name;
    private int floorPrice;
    private int ceilingPrice;
    private int targetPrice;
    ArrayList<OrderItem> orderItems;
    MarketChannelAssignment marketChannelComb;

  
    public Product(int fp, int cp, int tp) {
        floorPrice = fp;
        ceilingPrice = cp;
        targetPrice = tp;
        orderItems = new ArrayList<OrderItem>();
    }

    public Product(String n, int fp, int cp, int tp) {
        name = n;
        floorPrice = fp;
        ceilingPrice = cp;
        targetPrice = tp;
        orderItems = new ArrayList<OrderItem>();
    }

    public Product(int fp, int cp, int tp, MarketChannelAssignment marketChannelComb) {
        floorPrice = fp;
        ceilingPrice = cp;
        targetPrice = tp;
        this.marketChannelComb = marketChannelComb; // 设置关联的MarketChannelAssignment
        orderItems = new ArrayList<OrderItem>();
    }


    public Product updateProduct(int fp, int cp, int tp) {
        floorPrice = fp;
        ceilingPrice = cp;
        targetPrice = tp;
        return this; // returns itself
    }

    public int getTargetPrice(){
        return targetPrice;
    }

    public void addOrderItem(OrderItem oi){
        orderItems.add(oi);
    }

    // Number of item sales above target
    public int getNumberOfProductSalesAboveTarget(){
        int sum = 0;
        for (OrderItem oi : orderItems) {
            if (oi.isActualAboveTarget() == true)
                sum = sum + 1;
        }
        return sum;
    }

    public int getNumberOfProductSalesBelowTarget() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            if (oi.isActualBelowTarget() == true)
                sum = sum + 1;
        }
        return sum;
    }

    public boolean isProductAlwaysAboveTarget() {
        for (OrderItem oi : orderItems) {
            if (oi.isActualAboveTarget() == false)
                return false; 
        }
        return true;
    }


    public int getOrderPricePerformance() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.calculatePricePerformance(); // positive and negative values
        }
        return sum;
    }

    public int getSalesVolume() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.getOrderItemTotal(); // positive and negative values
        }
        return sum;
    }

    public void setName(String n) {
        name = n;
    }



    // 添加一个设置关联MarketChannelAssignment的方法
    public void setMarketChannelCombination(MarketChannelAssignment marketChannelComb) {
        this.marketChannelComb = marketChannelComb;
    }

    public MarketChannelAssignment getMarketChannelCombination() {
        return marketChannelComb;
    }
    
  



    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getFloorPrice() {
        return floorPrice;
    }

    public int getCeilingPrice() {
        return ceilingPrice;
    }



    

}

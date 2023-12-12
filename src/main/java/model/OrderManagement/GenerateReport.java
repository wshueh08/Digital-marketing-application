package model.OrderManagement;

import java.util.HashMap;
import java.util.Map;

import model.Business.Business;
import model.CustomerManagement.CustomerProfile;
import model.ProductManagement.Product;

public class GenerateReport {
    
    public static void printAllReport(Business b) {

        
        MasterOrderList mol = b.getMasterOrderList();
        int totalRevenue = 0;

        Map<String, Integer> revenueByChannel = new HashMap<>();
        Map<String, Integer> revenueByMarket = new HashMap<>();
        int revenueFromAds = 0;
        int revenueNotFromAds = 0;

        for (Order order : mol.getOrderList()) {
            int orderValue = order.getOrderTotal();
            totalRevenue += orderValue;

            // 假设有方法来确定订单的市场和渠道
            String market = order.getMarketName();
            String channel = order.getChannelType();
            boolean isFromAds = order.isFromAds(); // 假设有方法来确定订单是否通过广告购买

            revenueByChannel.put(channel, revenueByChannel.getOrDefault(channel, 0) + orderValue);
            revenueByMarket.put(market, revenueByMarket.getOrDefault(market, 0) + orderValue);

            if (isFromAds) {
                revenueFromAds += orderValue;
            } else {
                revenueNotFromAds += orderValue;
            }
        }
        System.out.println("Report");
        System.out.println("Total Revenue: $" + totalRevenue);
        System.out.println("Revenue by Channel");
        revenueByChannel.forEach((channel, value) -> System.out.println("- " + channel + ": $" + value));
        System.out.println("Revenue by Market");
        revenueByMarket.forEach((market, value) -> System.out.println("- " + market + ": $" + value));
        System.out.println("Revenue by Ads");
        System.out.println("- Buy from ads: $" + revenueFromAds);
        System.out.println("- Not buy from ads: $" + revenueNotFromAds);
    }


}

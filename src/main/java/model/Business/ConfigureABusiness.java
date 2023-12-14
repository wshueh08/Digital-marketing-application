/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.Map;

import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.MarketCatalog;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.Personnel.Person;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;

/**
 *
 * @author kal bugrara
 */

public class ConfigureABusiness {

  static int upperPriceLimit = 50;
  static int lowerPriceLimit = 10;
  static int range = 5;
  static int productMaxQuantity = 5;


  //add
  public static Business createABusinessAndLoadALotOfData(String name, int marketCount, int channelCount, 
  int solutionOfferCount, int supplierCount, int productCount, int customerCount, int orderCount, int itemCount) {

    Business business = new Business(name);

    loadSuppliers(business, supplierCount);

    // Add new one
    loadMarkets(business, marketCount, channelCount, solutionOfferCount);

    loadProducts(business, productCount);

    loadCustomers(business, customerCount);

    loadOrders(business, orderCount, itemCount);

    return business;
  }



  public static void loadSuppliers(Business b, int supplierCount){
    Faker faker = new Faker();

    SupplierDirectory supplierDirectory = b.getSupplierDirectory();
    for (int index = 1; index <= supplierCount; index++) {
      supplierDirectory.newSupplier(faker.company().name());
    }
  }


  // create a new one: loadMarketsAndChannels
  public static void loadMarkets(Business b, int marketCount, int channelCount, int solutionOfferCount){
    SupplierDirectory supplierDirectory = b.getSupplierDirectory();
    MarketCatalog marketCatalog = b.getMarketCatalog();
    ChannelCatalog channelCatalog = b.getChannelCatalog();
    SolutionOfferCatalog solutionOfferCatalog = b.getSolutionOfferCatalog();


    Supplier supplier = new Supplier("Supplier");
    supplierDirectory.newSupplier("Supplier");

    ProductCatalog products = supplier.getProductCatalog();
    
    Product macbookforInstore = products.newProduct("macbook", 1000, 1000,1000);
    Product keyboardforInstore = products.newProduct("keyboard", 800, 800, 800);
    Product mouseforInstore = products.newProduct("mouse", 600,600 ,600);
    Product pantforInstore = products.newProduct("pant", 500, 500, 500);
    Product shoesforInstore = products.newProduct("shoes", 300,300,300); 
    Product hatforInstore = products.newProduct("hat", 200,200,200);
    Product facemaskforInstore = products.newProduct("face mask", 100,100,100);
    Product lotionforInstore = products.newProduct("lotion", 60,60,60);
    Product lipstickforInstore = products.newProduct("lipstick", 30,30,30);

    Product macbookforOnline = products.newProduct("macbook", 900,900,900);
    Product keyboardforOnline = products.newProduct("keyboard", 700,700,700);
    Product mouseforOnline = products.newProduct("mouse", 500,500,500);
    Product pantforOnline = products.newProduct("pant", 400,400,400);
    Product shoesforOnline = products.newProduct("shoes", 200,200,200);
    Product hatforOnline = products.newProduct("hat", 100,100,100);
    Product facemaskforOnline = products.newProduct("face mask", 90,90,90);
    Product lotionforOnline = products.newProduct("lotion", 50,50,50);
    Product lipstickforOnline = products.newProduct("lipstick", 20,20,20);


    Market m1 = new Market("electronics");
    Market m2 = new Market("clothes");
    Market m3 = new Market("sephora");


    
    marketCatalog.addMarket(m1);
    marketCatalog.addMarket(m2);
    marketCatalog.addMarket(m3);

  
    Channel c1 = new Channel("inStore");
    Channel c2 = new Channel("online");

    
    channelCatalog.addChannel(c1);
    channelCatalog.addChannel(c2);


    //six bundles for customers to select
    MarketChannelAssignment electronicInstore = new MarketChannelAssignment(m1, c1);
    MarketChannelAssignment clothesInstore = new MarketChannelAssignment(m2, c1);
    MarketChannelAssignment sephoraInstore = new MarketChannelAssignment(m3, c1);
    MarketChannelAssignment electronicOnline = new MarketChannelAssignment(m1, c2);
    MarketChannelAssignment clothesOnline = new MarketChannelAssignment(m2, c2);
    MarketChannelAssignment sephoraOnline = new MarketChannelAssignment(m3, c2);

    

    //in-store: 3 bundles
    SolutionOffer so1 = new SolutionOffer(electronicInstore);
    so1.addProduct(macbookforInstore);
    so1.addProduct(keyboardforInstore);
    so1.addProduct(mouseforInstore);
    macbookforInstore.setMarketChannelCombination(electronicInstore);
    keyboardforInstore.setMarketChannelCombination(electronicInstore);
    mouseforInstore.setMarketChannelCombination(electronicInstore);

    SolutionOffer so2 = new SolutionOffer(clothesInstore);
    so2.addProduct(pantforInstore);
    so2.addProduct(shoesforInstore);
    so2.addProduct(hatforInstore);
    pantforInstore.setMarketChannelCombination(clothesInstore);
    shoesforInstore.setMarketChannelCombination(clothesInstore);
    hatforInstore.setMarketChannelCombination(clothesInstore);
  
    SolutionOffer so3 = new SolutionOffer(sephoraInstore);
    so3.addProduct(facemaskforInstore);
    so3.addProduct(lotionforInstore);
    so3.addProduct(lipstickforInstore);
    facemaskforInstore.setMarketChannelCombination(sephoraInstore);
    lotionforInstore.setMarketChannelCombination(sephoraInstore);
    lipstickforInstore.setMarketChannelCombination(sephoraInstore);

    //online: 3 bundles
    SolutionOffer so4 = new SolutionOffer(electronicOnline);
    so4.addProduct(macbookforOnline);
    so4.addProduct(keyboardforOnline);
    so4.addProduct(mouseforOnline);
    macbookforOnline.setMarketChannelCombination(electronicOnline);
    keyboardforOnline.setMarketChannelCombination(electronicOnline);
    mouseforOnline.setMarketChannelCombination(electronicOnline);

    SolutionOffer so5 = new SolutionOffer(clothesOnline);
    so5.addProduct(pantforOnline);
    so5.addProduct(shoesforOnline);
    so5.addProduct(hatforOnline);
    pantforOnline.setMarketChannelCombination(clothesOnline);
    shoesforOnline.setMarketChannelCombination(clothesOnline);
    hatforOnline.setMarketChannelCombination(clothesOnline);

    SolutionOffer so6 = new SolutionOffer(sephoraOnline);
    so6.addProduct(facemaskforOnline);
    so6.addProduct(lotionforOnline);
    so6.addProduct(lipstickforOnline);
    facemaskforOnline.setMarketChannelCombination(sephoraOnline);
    lotionforOnline.setMarketChannelCombination(sephoraOnline);
    lipstickforOnline.setMarketChannelCombination(sephoraOnline);


    // 添加解決方案到解決方案目錄
    solutionOfferCatalog.addSolutionOffer(so1);
    solutionOfferCatalog.addSolutionOffer(so2);
    solutionOfferCatalog.addSolutionOffer(so3);
    solutionOfferCatalog.addSolutionOffer(so4);
    solutionOfferCatalog.addSolutionOffer(so5);
    solutionOfferCatalog.addSolutionOffer(so6);


    // 将 SolutionOffer 对象添加到相应的 Market 对象
    m1.addSolutionOffer(so1); // electronics的解决方案
    m1.addSolutionOffer(so4);
    m2.addSolutionOffer(so2); // clothes的解决方案
    m2.addSolutionOffer(so5); 
    m3.addSolutionOffer(so3); // Sephora的解决方案
    m3.addSolutionOffer(so6);
  }

  static void loadProducts(Business b, int productCount){
    SupplierDirectory supplierDirectory = b.getSupplierDirectory();

    for (Supplier supplier : supplierDirectory.getSupplierList()) {

      int randomProductNumber = getRandom(1, productCount);
      ProductCatalog productCatalog = supplier.getProductCatalog();

      for (int index = 1; index <= randomProductNumber; index++) {

        String productName = "Product #" + index + " from " + supplier.getName();
        int randomFloor = getRandom(lowerPriceLimit, lowerPriceLimit + range);
        int randomCeiling = getRandom(upperPriceLimit - range, upperPriceLimit);
        int randomTarget = getRandom(randomFloor, randomCeiling);

        productCatalog.newProduct(productName, randomFloor, randomCeiling, randomTarget);
      }
    }
  }


  static int getRandom(int lower, int upper){
    Random r = new Random();
    // nextInt(n) will return a number from zero to 'n'. Therefore e.g. if I want
    // numbers from 10 to 15
    // I will have result = 10 + nextInt(5)
    int randomInt = lower + r.nextInt(upper - lower+1);
    return randomInt;
  }



  static void loadCustomers(Business b, int customerCount) {
    CustomerDirectory customerDirectory = b.getCustomerDirectory();
    PersonDirectory personDirectory = b.getPersonDirectory();

    Faker faker = new Faker();

    for (int index = 1; index <= customerCount; index++) {
      Person newPerson = personDirectory.newPerson(faker.name().fullName());
      customerDirectory.newCustomerProfile(newPerson);
    }
  }

  
  static void loadOrders(Business b, int orderCount, int itemCount) {

    // reach out to masterOrderList
    MasterOrderList mol = b.getMasterOrderList();

    // pick a random customer (reach to customer directory)
    CustomerDirectory cd = b.getCustomerDirectory();
    SupplierDirectory sd = b.getSupplierDirectory();

    for (int index = 0; index < orderCount; index++) {

      CustomerProfile randomCustomer = cd.pickRandomCustomer();
      if (randomCustomer == null) {
        System.out.println("Cannot generate orders. No customers in the customer directory.");
        return;
      }

      // create an order for that customer
      Order randomOrder = mol.newOrder(randomCustomer);

      // add order items
      // -- pick a supplier first (randomly)
      // -- pick a product (randomly)
      // -- actual price, quantity

      int randomItemCount = getRandom(1, itemCount);
      for (int itemIndex = 0; itemIndex < randomItemCount; itemIndex++) {

        Supplier randomSupplier = sd.pickRandomSupplier();
        if (randomSupplier == null) {
          System.out.println("Cannot generate orders. No supplier in the supplier directory.");
          return;
        }

        ProductCatalog pc = randomSupplier.getProductCatalog();
        Product randomProduct = pc.pickRandomProduct();
        if (randomProduct == null) {
          System.out.println("Cannot generate orders. No products in the product catalog.");
          return;
        }

        int randomPrice = getRandom(randomProduct.getFloorPrice(), randomProduct.getCeilingPrice());
        int randomQuantity = getRandom(1, productMaxQuantity);

        randomOrder.newOrderItem(randomProduct, randomPrice, randomQuantity);
      }
    }
    // Make sure order items are connected to the order

  }


  //write method for auto-generate sales orders
  public static void generateOrders(Business b, int customerCount, int adsFee) {
    Faker faker = new Faker();
    final int MAX_PRODUCTS_PER_BUNDLE = 3;

    MasterOrderList mol = b.getMasterOrderList();
    CustomerDirectory cd = b.getCustomerDirectory();
    SolutionOfferCatalog solutionOfferCatalog = b.getSolutionOfferCatalog();

    int totalOrderValue = 0;
    Map<String, Integer> revenueByChannel = new HashMap<>();
    Map<String, Integer> revenueByMarket = new HashMap<>();
    int totalSolutionOfferValue = 0; 
    int revenueFromAds = 0, revenueNotFromAds = 0;

    for (int i = 0; i < customerCount; i++) {
        // Create a random customer
        String randomName = faker.name().fullName();
        Person newPerson = new Person(randomName);

        // Use the method in CustomerDirectory to create and add a new customer
        CustomerProfile customer = cd.newCustomerProfile(newPerson);

        // Randomly decide between choosing a solution offer or individual products
        boolean chooseSolutionOffer = Math.random() < 0.5;
        int orderValue = 0;
        System.out.println("\nCustomer " + randomName + " is buying: ");

        if (chooseSolutionOffer) {
            // Randomly pick a solution offer
            SolutionOffer offer = pickRandomSolutionOffer(solutionOfferCatalog);
            if (offer.getMarketChannelCombination() != null) {
                Order order = mol.newOrder(customer);
                String marketName = offer.getMarketChannelCombination().getMarket().getName();
                String channelName = offer.getMarketChannelCombination().getChannel().getType();

                System.out.println("Bundle: " + marketName + " (" + channelName + ")");

                for (Product product : offer.getProductList()) {
                    int price = product.getTargetPrice();
                    orderValue += price;
                    order.newOrderItem(product, price, 1); // design quantity is 1
                    System.out.println("---Product: " + product.getName() + ", Price: " + price + ", Quantity: 1");
                }
                orderValue += adsFee; // Add advertising fee for solution offers
                System.out.println("Advertising Revenue: " + adsFee);

                // Update advertising revenue
                revenueFromAds += adsFee;
                totalSolutionOfferValue += orderValue;
              

                // Update channel and market revenue
                updateRevenue(revenueByChannel, channelName, orderValue);
                updateRevenue(revenueByMarket, marketName, orderValue);
            }
        } else {
            // Choose individual products
            Order order = mol.newOrder(customer);
            int productCount = getRandom(1, MAX_PRODUCTS_PER_BUNDLE);

            for (int j = 0; j < productCount; j++) {
                // Randomly select products and add to order
                Product randomProduct = pickRandomProduct(solutionOfferCatalog);
                if (randomProduct.getMarketChannelCombination() != null) {
                    int randomPrice = getRandom(randomProduct.getFloorPrice(), randomProduct.getCeilingPrice());
                    int randomQuantity = getRandom(1, 10); // Assuming max quantity is 10

                    orderValue += randomPrice * randomQuantity;
                    order.newOrderItem(randomProduct, randomPrice, randomQuantity);
                    System.out.println("Product: " + randomProduct.getName() + ", Price: " + randomPrice + ", Quantity: " + randomQuantity);

                    // Update channel and market revenue for individual products
                    String marketName = randomProduct.getMarketChannelCombination().getMarket().getName();
                    String channelName = randomProduct.getMarketChannelCombination().getChannel().getType();
                    updateRevenue(revenueByChannel, channelName, randomPrice * randomQuantity);
                    updateRevenue(revenueByMarket, marketName, randomPrice * randomQuantity);
                }
            }
        }
        System.out.println("Order Value for Customer " + randomName + ": " + orderValue);
        totalOrderValue += orderValue;
    }
    // Calculate revenueNotFromAds after the loop
    revenueNotFromAds = totalOrderValue - revenueFromAds;
    printSummary(totalOrderValue, revenueByChannel, revenueByMarket, totalSolutionOfferValue, revenueFromAds, revenueNotFromAds);
}


private static void updateRevenue(Map<String, Integer> revenueMap, String key, int value) {
    revenueMap.put(key, revenueMap.getOrDefault(key, 0) + value);
}


// Print report summary
public static void printSummary(int totalRevenue, Map<String, Integer> revenueByChannel, Map<String, Integer> revenueByMarket,
int totalSolutionOfferValue, int revenueFromAds, int revenueNotFromAds) {
    System.out.println("---------------------------------------------------");
    System.out.println("<Company Report>");
    System.out.println("\nTotal Revenue: $" + totalRevenue);
    System.out.println("\nRevenue by Channel:");
    revenueByChannel.forEach((channel, value) -> System.out.println("- " + channel + ": $" + value));
    System.out.println("\nRevenue by Market:");
    revenueByMarket.forEach((market, value) -> System.out.println("- " + market + ": $" + value));
    System.out.println("\nRevenue from Bundles: $" + totalSolutionOfferValue);
    System.out.println("\nRevenue from Ads: $" + revenueFromAds);
    System.out.println("Revenue not from Ads: $" + revenueNotFromAds);
    System.out.println("----------------------------------------------------");
}



  // Helper methods
  static SolutionOffer pickRandomSolutionOffer(SolutionOfferCatalog catalog) {
    List<SolutionOffer> offers = catalog.getSolutionOffers();
    return offers.get(getRandom(0, offers.size() - 1));
  }


  static Product pickRandomProduct(SolutionOfferCatalog catalog) {
    List<SolutionOffer> offers = catalog.getSolutionOffers();
    List<Product> allProducts = new ArrayList<>();
    for (SolutionOffer offer : offers) {
      allProducts.addAll(offer.getProductList());
    }
  return allProducts.get(getRandom(0, allProducts.size() - 1));
  }


  

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.github.javafaker.Faker;

import model.Business.Business;
import model.Business.ConfigureABusiness;
import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.OrderManagement.GenerateReport;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.MasterOrderReport;
import model.OrderManagement.Order;
import model.OrderManagement.OrderSummary;
import model.Personnel.Person;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductsReport;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;

/**
 *
 * @author kal bugrara
 */
public class DigitalMarketingApplication {

  /**
   * @param args the command line arguments
   */
  static Scanner sc = new Scanner(System.in);
  static Business business = ConfigureABusiness.createABusinessAndLoadALotOfData("NEU", 3, 2, 6,1, 18, 3, 2, 2); 
  public static void main(String[] args) {


    SupplierDirectory sd = business.getSupplierDirectory();
    MasterOrderList mol = business.getMasterOrderList();
    MasterOrderReport orderReport = mol.generateMasterOrderReport();

    //test all data
    //business.printShortInfo();


    boolean exitCode = false;

    while (!exitCode) {
      System.out.println("\n1. I'm a customer, please give me something cool");
      System.out.println("2. I'm a staff, I want to check sales orders and report");
      System.out.println("3. Exit");

      int choice = sc.nextInt();
      sc.nextLine();

      switch (choice) {
        case 1:
          //customer option
          System.out.println("\nPlease enter your name: ");
          String customerName = sc.nextLine();

          Person person = new Person(customerName);
          CustomerProfile customerProfile = new CustomerProfile(person);
          helper(customerProfile);
          break;

        case 2:
            // Staff option - auto-generate sales orders and print report
            int customerCount = 50; 
            int adsFee = 100; 
            ConfigureABusiness.generateOrders(business, customerCount, adsFee);
            break;

        case 3:
            // Exit option
            exitCode = true;
            System.out.println("Exiting...");
            break;

        default:
            System.out.println("Invalid option, please try again.");
            break;
      }
    }
    sc.close();
}

    //for part1: generate to a method~
    public static void helper(CustomerProfile customerProfile) {
      System.out.println("\nPlease choose a department you want to buy: ");
      System.out.println("1. Electronics ($500-$1000)");
      System.out.println("2. Clothes ($100-$500)");
      System.out.println("3. Sephora! Some make-up stuff please ($20-$100)");
      System.out.println("4. Exit! ");

      int marketInput = sc.nextInt();
      if(marketInput==4)
        return;
      
      System.out.println("\nPlease choose a shopping channel: 1. in store 2. online 3. Ooops! Return to the home page please");
      int channelInput = sc.nextInt();
      if(channelInput==3)
        return; 
      
      sc.nextLine();


      //depending on how customers select, then solutionOffer will be provived
      SolutionOfferCatalog solutionCatalog = business.getSolutionOfferCatalog();
      List<SolutionOffer> offers = filterOffers(solutionCatalog, marketInput, channelInput);
      
      List<Product> selectedProducts = new ArrayList<Product>();//To store selected products


      // After filter, show the products inside the bundle
      System.out.println("Bundle contains: ");
    
      int total = 0;
      for (int i = 0; i < offers.size(); i++) {
        SolutionOffer offer = offers.get(i);

        for (Product product : offer.getProductList()) {
          int price = product.getTargetPrice();
          if (channelInput == 2) {
            price *= 0.8; // Apply discount on each product price if channelInput is 2
          }
          System.out.println("-" + product.getName() + ", $" +price);
          total += price; 
        }
        selectedProducts.addAll(offer.getProductList());//Add products to the list
      }
      System.out.println("Total price: " + total);

      // Check the shopping channel and print the corresponding slogan
      if (channelInput == 1) {
        System.out.println("\nStep In, Explore, Delight! Where Every Aisle Leads to Happiness!");
      } else if (channelInput == 2) {
        System.out.println("\nClick, Ship, Smile! Bringing Joy to Your Doorstep!");
      }
      System.out.println("");


      System.out.println("\nDo you want to buy this bundle? (hell yeah/nah)");
      String confirmation = sc.nextLine();
      if (confirmation.equalsIgnoreCase("hell yeah")) {

        MasterOrderList masterOrderList = business.getMasterOrderList();
        Order newOrder = masterOrderList.newOrder(customerProfile);

        for (Product product : selectedProducts) {
          int quantity = 1; 
          int price = product.getTargetPrice();
          newOrder.newOrderItem(product, price, quantity);
        }

        System.out.println("\nOrder Details:");
          newOrder.getOrderItemList().forEach(orderItem -> {
            Product product = orderItem.getSelectedProduct();
            int salesPrice = orderItem.getActualPrice();
            System.out.println(" - " + product.getName() + ": " + salesPrice);
          });

          System.out.println("Total Order Price: " + newOrder.getOrderTotal());
          System.out.println("\n<Order has been placed successfully>\n");
      } else {
          System.out.println("\n<Order cancelled>\n");
      }

      System.out.println("\nContinue shopping? (absolutely/nope)");
      String continueShopping = sc.nextLine();
      if (continueShopping.equalsIgnoreCase("nope")) {
        return;
      }
    }


    //filter both market and channel, then return the right offer for customer
    private static List<SolutionOffer> filterOffers(SolutionOfferCatalog solutionCatalog, int marketInput, int channelInput) {
    List<SolutionOffer> filteredOffers = new ArrayList<>();
    for (SolutionOffer offer : solutionCatalog.getSolutionOffers()) {
      MarketChannelAssignment mca = offer.getMarketChannelCombination();
      if (mca != null) {
        Market market = mca.getMarket();
        Channel channel = mca.getChannel();

        boolean marketMatch = (marketInput == 1 && market.getName().equals("electronics")) ||
          (marketInput == 2 && market.getName().equals("clothes")) ||
          (marketInput == 3 && market.getName().equals("sephora"));
        boolean channelMatch = (channelInput == 1 && channel.getType().equals("inStore")) ||
          (channelInput == 2 && channel.getType().equals("online"));

          if (marketMatch && channelMatch) {
            filteredOffers.add(offer);
            System.out.println("\n<Bundle added> ");
          }
      }
    }
    return filteredOffers;
    }
}

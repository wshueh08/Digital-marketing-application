/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.github.javafaker.Faker;

import model.Business.Business;
import model.Business.ConfigureABusiness;
import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
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
      int marketInput = 0;
      while (marketInput < 1 || marketInput > 4) {
        System.out.println("\nPlease choose a department you want to buy: ");
        System.out.println("1. Electronics ($500-$1000)");
        System.out.println("2. Clothes ($100-$500)");
        System.out.println("3. Sephora! Some make-up stuff please ($20-$100)");
        System.out.println("4. Exit! ");

        marketInput = getIntInput();
        if (marketInput < 1 || marketInput > 4) {
            System.out.println("Please type a valid number.");
        }
      }
      if (marketInput == 4) return;
      
      int channelInput = 0;
      while (channelInput < 1 || channelInput > 3) {
        System.out.println("\nPlease choose a shopping channel: 1. in store 2. online 3. Ooops! Return to the home page please");
        channelInput = getIntInput();
        if (channelInput < 1 || channelInput > 3) {
            System.out.println("Please type a valid number.");
        }
      }
      if (channelInput == 3) return;
      
      sc.nextLine();

    

      //depending on how customers select, then solutionOffer will be provived
      SolutionOfferCatalog solutionCatalog = business.getSolutionOfferCatalog();
      List<SolutionOffer> offers = filterCustomerOffers(solutionCatalog, marketInput, channelInput);
      
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


      String confirmationOrder = "";
      while (!confirmationOrder.equalsIgnoreCase("hell yeah") && !confirmationOrder.equalsIgnoreCase("nah")) {
        System.out.println("\nDo you want to buy this bundle? (hell yeah/nah)");
        confirmationOrder = sc.nextLine();
        if (!confirmationOrder.equalsIgnoreCase("hell yeah") && !confirmationOrder.equalsIgnoreCase("nah")) {
          System.out.println("I don't know your meaning, please type again.");
        }
      }

      if (confirmationOrder.equalsIgnoreCase("hell yeah")) {
        MasterOrderList masterOrderList = business.getMasterOrderList();
        Order newOrder = masterOrderList.newOrder(customerProfile);

        for (Product product : selectedProducts) {
          int quantity = 1; 
          int price = product.getTargetPrice();
          newOrder.newOrderItem(product, price, quantity);
        }

      }else{
        System.out.println("\n<Order cancelled>\n");
      }

      
      // Continue shopping loop
      String continueShopping = "";
      while (!continueShopping.equalsIgnoreCase("sure") && !continueShopping.equalsIgnoreCase("nope")) {
        System.out.println("\nContinue shopping? (sure/nope)");
        continueShopping = sc.nextLine();
        if (!continueShopping.equalsIgnoreCase("sure") && !continueShopping.equalsIgnoreCase("nope")) {
            System.out.println("I don't know your meaning, please type again.");
        }
      }

      if (continueShopping.equalsIgnoreCase("nope")) {
        return;
      }
    }




    // Method to safely read an integer from the user
    private static int getIntInput() {
      while (true) {
        try {
          return sc.nextInt();
        }catch(InputMismatchException e) {
          sc.nextLine(); // clear the invalid input
          System.out.println("Invalid input, please enter a number.");
        }
      }
    }




    //filter both market and channel, then return the right offer for customer
    private static List<SolutionOffer> filterCustomerOffers(SolutionOfferCatalog solutionCatalog, int marketInput, int channelInput) {
      List<SolutionOffer> filteredOffers = new ArrayList<>();
  
      for (SolutionOffer offer : solutionCatalog.getSolutionOffers()) {
          if (isOfferMatching(offer, marketInput, channelInput)) { 
              filteredOffers.add(offer);
              System.out.println("\n<Bundle added> ");
          }
      }
      return filteredOffers;
    }

  
    private static boolean isOfferMatching(SolutionOffer offer, int marketInput, int channelInput) {
      MarketChannelAssignment mca = offer.getMarketChannelCombination();
      if (mca == null) return false;
  
      Market market = mca.getMarket();
      Channel channel = mca.getChannel();
  
      return isMarketMatch(market, marketInput) && isChannelMatch(channel, channelInput);
    }
  
    private static boolean isMarketMatch(Market market, int marketInput) {
      switch (marketInput) {
        case 1: return market.getName().equals("electronics");
        case 2: return market.getName().equals("clothes");
        case 3: return market.getName().equals("sephora");
        default: return false;
      }
    }
  
    private static boolean isChannelMatch(Channel channel, int channelInput) {
      switch (channelInput) {
        case 1: return channel.getType().equals("inStore");
        case 2: return channel.getType().equals("online");
        default: return false;
      }
    }



  
}

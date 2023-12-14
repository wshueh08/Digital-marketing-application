/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.ArrayList;
import java.util.List;

import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.MarketCatalog;
import model.MarketModel.MarketChannelAssignment;
import model.MarketingManagement.MarketingPersonDirectory;
import model.OrderManagement.MasterOrderList;
import model.Personnel.EmployeeDirectory;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductSummary;
import model.ProductManagement.ProductsReport;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.SalesManagement.SalesPersonDirectory;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */

public class Business {

    String name;
    PersonDirectory persondirectory;
    MasterOrderList masterorderlist;
    SupplierDirectory suppliers;
    MarketCatalog marketcatalog;
    ChannelCatalog channelcatalog;
    SolutionOfferCatalog solutionoffercatalog;
    CustomerDirectory customerdirectory;
    EmployeeDirectory employeedirectory;
    SalesPersonDirectory salespersondirectory;
    UserAccountDirectory useraccountdirectory;
    MarketingPersonDirectory marketingpersondirectory;      
    List<MarketCatalog> markets;
    List<ChannelCatalog> channels;
    List<SolutionOffer> solutionOffers;
   

    public Business(String n) {
        name = n;
        masterorderlist = new MasterOrderList();
        suppliers = new SupplierDirectory();
//      solutionoffercatalog = new SolutionOfferCatalog();
        persondirectory = new PersonDirectory();
        customerdirectory = new CustomerDirectory(this);
        salespersondirectory = new SalesPersonDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        marketingpersondirectory = new MarketingPersonDirectory(this);
        employeedirectory = new EmployeeDirectory(this);
        marketcatalog = new MarketCatalog();
        channelcatalog = new ChannelCatalog();
        channels = new ArrayList<>();
        solutionoffercatalog = new SolutionOfferCatalog();
    }

    public int getSalesVolume(){
        return masterorderlist.getSalesVolume();
    }

    public PersonDirectory getPersonDirectory(){
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory(){
        return useraccountdirectory;
    }

    public MarketingPersonDirectory getMarketingPersonDirectory(){
        return marketingpersondirectory;
    }

    public SupplierDirectory getSupplierDirectory(){
        return suppliers;
    }

    // create
    public MarketCatalog getMarketCatalog(){
        return marketcatalog;
    }

    // create
    public ChannelCatalog getChannelCatalog(){
        return channelcatalog;
    }

    // create
    public SolutionOfferCatalog getSolutionOfferCatalog(){
        return solutionoffercatalog;
    }


    public ProductsReport getSupplierPerformanceReport(String n){
        Supplier supplier = suppliers.findSupplier(n);
        if (supplier == null) {
            return null;
        }
        return supplier.prepareProductsReport();
    }

    public ArrayList<ProductSummary> getSupplierProductsAlwaysAboveTarget(String n){
        ProductsReport productsreport = getSupplierPerformanceReport(n);
        return productsreport.getProductsAlwaysAboveTarget();
    }

    public int getHowManySupplierProductsAlwaysAboveTarget(String n){
        ProductsReport productsreport = getSupplierPerformanceReport(n); // see above
        int i = productsreport.getProductsAlwaysAboveTarget().size(); //return size of the arraylist
        return i;
    }

    public CustomerDirectory getCustomerDirectory(){
        return customerdirectory;
    }

    public SalesPersonDirectory getSalesPersonDirectory(){
        return salespersondirectory;
    }

    public MasterOrderList getMasterOrderList(){
        return masterorderlist;
    }

    public EmployeeDirectory getEmployeeDirectory(){
        return employeedirectory;
    }

    public void printShortInfo(){
        System.out.println("\nChecking business info:\n");
        //suppliers.printShortInfo();
        //customerdirectory.printShortInfo();
        //masterorderlist.printShortInfo();
        
        System.out.println("\nMarket:");
        MarketCatalog marketCatalog = getMarketCatalog();
        if(marketCatalog != null && !marketCatalog.getMarketNames().isEmpty()){
            for(String marketName : marketCatalog.getMarketNames()){
                System.out.println("-" + marketName);
            }
        }else{
            System.out.println("No market information available");
        }
        
      

        System.out.println("\nChannel:");
        ChannelCatalog channelCatalog = getChannelCatalog();
        if(channelCatalog != null && !channelCatalog.getChannelTypes().isEmpty()) {
            for(String channelType : channelCatalog.getChannelTypes()) {
                System.out.println("- " + channelType);
            }
        }else{
            System.out.println("No channel information available.");
        }


        System.out.println("\n<Solution Offers> ");
        SolutionOfferCatalog solutionOfferCatalog = getSolutionOfferCatalog();
        if(solutionOfferCatalog != null && !solutionOfferCatalog.getSolutionOffers().isEmpty()) {
        for(SolutionOffer solutionOffer : solutionOfferCatalog.getSolutionOffers()) {
            MarketChannelAssignment mca = solutionOffer.getMarketChannelCombination();
            if(mca != null) {
                System.out.println("\nMarket: " + mca.getMarket().getName() + ", Channel: " + mca.getChannel().getType());
            }

            List<Product> products = solutionOffer.getProductList();
            if(products != null && !products.isEmpty()) {
                for(Product product : products) {
                    System.out.println("\n- Product Name: " + product.getName());
                }
            }else{
                System.out.println("- No Product in this offer.");
            }
        }
    }else{
        System.out.println("No solution offers information available.");
    }
        System.out.println("---------------------------------------");
    }
}

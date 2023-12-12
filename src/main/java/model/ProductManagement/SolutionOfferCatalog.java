/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */

public class SolutionOfferCatalog{
    
    ArrayList<SolutionOffer> solutionoffers;

    public SolutionOfferCatalog(){
        this.solutionoffers = new ArrayList<>();
    }

    public void addSolutionOffer(SolutionOffer solutionOffer){
        solutionoffers.add(solutionOffer);
    }

    public ArrayList<SolutionOffer>getSolutionOffers(){
        return new ArrayList<>(solutionoffers);
    }
}

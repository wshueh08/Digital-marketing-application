/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Personnel;

/**
 *
 * @author kal bugrara
 */



public class Person {

    String id;
    String name;


    public Person(String name) {

        //this.id = id;
        this.name = name;
    }

    public String getPersonId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isMatch(String id) {
        if (getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getPersonId();
    }



}

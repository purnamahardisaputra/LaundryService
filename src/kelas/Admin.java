/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;

import java.util.ArrayList;

/**
 *
 * @author Purnama Hardi Saputra
 */

// sebagai parent class yang mengimplementasikan interface
public class Admin implements Detail{
    private ArrayList<String> id;
    private ArrayList<String> customer;
    private ArrayList<Double> items;
    private ArrayList<Double> price;
    private ArrayList<String> typeService;
    private ArrayList<String> typeDay;
    private ArrayList<String> typeLaundry;
    private ArrayList<String> typePayment;
    
    public Admin(ArrayList<String> id, ArrayList<String> customer, ArrayList<Double> items, 
            ArrayList<Double> price, ArrayList<String> typeService, ArrayList<String> typeDay, ArrayList<String> typeLaundry,
            ArrayList<String> typePayment){
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.price = price;
        this.typeService = typeService;
        this.typeDay = typeDay;
        this.typeLaundry = typeLaundry;
        this.typePayment = typePayment;
    }
    
    public Admin(){
    }
    
    @Override
    // mensetting nilai data bertipe string yang dimasukkan ke dalam ArrayList
    public ArrayList<ArrayList<String>> showDataString() {
        ArrayList<ArrayList<String>> dataString = new ArrayList<>();
        dataString.add(this.id);
        dataString.add(this.customer);
        dataString.add(this.typeService);
        dataString.add(this.typeDay);
        dataString.add(this.typeLaundry);
        dataString.add(this.typePayment);
        return dataString;
    }
    
    @Override
    // mensetting nilai data bertipe double yang dimasukkan ke dalam ArrayList
    public ArrayList<ArrayList<Double>> showDataDouble() {
        ArrayList<ArrayList<Double>> dataDouble = new ArrayList<>();
        dataDouble.add(this.items);
        dataDouble.add(this.price);
        return dataDouble;
    }
}

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
// merupakan child class yang menurunkan sifat dari kelas Admin
public class Tracking extends Admin{
    private ArrayList<String> status;
    
    //polimorfisme yaitu penambahan pada variabel status
    public Tracking (ArrayList<String> id, ArrayList<String> customer, ArrayList<Double> items, 
            ArrayList<Double> price, ArrayList<String> typeService, ArrayList<String> typeDay, ArrayList<String> typeLaundry,
            ArrayList<String> typePayment, ArrayList<String> status){
        super(id, customer, items, price, typeService, typeDay, typeLaundry, typePayment);
        this.status = status;
    }
    
    @Override
    public ArrayList<ArrayList<String>> showDataString() {
        ArrayList<ArrayList<String>> dataString = super.showDataString();
        dataString.add(this.status);
        return dataString;
    }

    @Override
    public ArrayList<ArrayList<Double>> showDataDouble() {
        return super.showDataDouble();
    }
}

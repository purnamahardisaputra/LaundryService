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
public class Storage extends Admin{
    public Storage(ArrayList<String> id, ArrayList<String> customer, ArrayList<Double> items, 
            ArrayList<Double> price, ArrayList<String> typeService, ArrayList<String> typeDay, ArrayList<String> typeLaundry,
            ArrayList<String> typePayment){
        super(id, customer, items, price, typeService, typeDay, typeLaundry, typePayment);
    }
    
    @Override
    public ArrayList<ArrayList<String>> showDataString() {
        return super.showDataString();
    }
    
    @Override
    public ArrayList<ArrayList<Double>> showDataDouble() {
        return super.showDataDouble();
    }
}

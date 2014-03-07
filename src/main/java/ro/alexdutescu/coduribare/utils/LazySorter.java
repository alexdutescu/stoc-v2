/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.alexdutescu.coduribare.utils;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import ro.alexdutescu.coduribare.model.StocTotal;


/**
 *
 * @author Alex Dutescu
 */
public class LazySorter implements Comparator<StocTotal> {

    private String sortField;
   
    private SortOrder sortOrder;
   
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(StocTotal stoc1, StocTotal stoc2) {
        try {
            Object value1 = StocTotal.class.getField(this.sortField).get(stoc1);
            Object value2 = StocTotal.class.getField(this.sortField).get(stoc2);

            int value = ((Comparable)value1).compareTo(value2);
           
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}



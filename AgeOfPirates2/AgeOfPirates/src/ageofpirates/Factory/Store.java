/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofpirates.Factory;

import java.io.Serializable;

/**
 *
 * @author Jasson
 */
public class Store extends Factory implements Serializable  {
    public Store() {
        super("Market", 2000,"1x2",4);
    }  
}

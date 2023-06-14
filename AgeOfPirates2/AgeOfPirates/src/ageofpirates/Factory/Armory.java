/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofpirates.Factory;

import java.io.Serializable;


public class Armory extends Factory implements Serializable  {
    String type;
    Weapons weapon;
    public Armory(int id) {        
        super("Armory",1500,"1x2",id);
    } 
}

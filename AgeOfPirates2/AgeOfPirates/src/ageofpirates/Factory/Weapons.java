/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofpirates.Factory;

import java.io.Serializable;


public class Weapons implements Serializable {
    String name;
    int cost;
    int shoots;
    int type;

    public Weapons(String name, int cost, int shoots, int type) {
        this.name = name;
        this.cost = cost;
        this.shoots = shoots;
        this.type = type;        
    }   

    public int getType() {
        return type;
    }
    
}

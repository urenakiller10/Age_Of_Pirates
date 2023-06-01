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
public class Factory implements Serializable {
    private String name;
    private int cost;
    private String bounds;
    private int id;
    private int num;
    public Factory(String name, int cost,String bounds,int id) {
        this.name = name;
        this.cost = cost;
        this.bounds=bounds;
        this.id=id;
    }
    public String getName() {
        return name;
    } 

    public String getBounds() {
        return bounds;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
}


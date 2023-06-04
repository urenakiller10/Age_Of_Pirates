/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofpirates.Factory;
import ageofpirates.Player;
import java.io.Serializable;


public class Mine extends Factory implements Serializable {
    ThreadCrono crono;
    int quantity=10;
    int speed=1;
    Player player; 
    public Mine(Player player) {
        super("Mine",1000,"1x2",5);
        crono = new ThreadCrono(this);
        crono.start();
        this.player=player;
    }   

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
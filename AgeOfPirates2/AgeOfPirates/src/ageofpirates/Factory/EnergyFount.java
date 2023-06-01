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
public class EnergyFount extends Factory implements Serializable  {

    public EnergyFount() {
        super("EnergyFount", 12000,"2x2",2);
    }
    
}

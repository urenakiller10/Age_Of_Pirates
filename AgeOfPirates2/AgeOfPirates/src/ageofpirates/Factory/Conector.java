/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofpirates.Factory;

import ageofpirates.Vertice;
import java.io.Serializable;
import java.util.ArrayList;


public class Conector extends Factory implements Serializable  {
    ArrayList<Vertice> aristas;
    public Conector() {
    super("Conector", 100,"1x1",3);
    }
    public void agregarArista (Vertice arista)
    {
    // si no está la arista para no repetir
    if (buscarArista(arista) == -1)
    aristas.add(new Vertice(0,0));
    }
    public int buscarArista(Vertice v)
    {
    for (int i = 0; i < aristas.size(); i++) {
    if (v.dato == aristas.get(i).dato)
    return i;
    }
    return -1;
    }
}

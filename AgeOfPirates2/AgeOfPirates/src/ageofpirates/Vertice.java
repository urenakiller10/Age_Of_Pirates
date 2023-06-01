/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ageofpirates;

import ageofpirates.Factory.Factory;
import java.util.ArrayList;

/**
 *
 * @author dmora
 */
public class Vertice {
    public int dato;
    boolean visitado;
    ArrayList<Vertice> aristas;
    int peso;//peso
    protected Factory factory;

    // cosntructor
    public Vertice(Factory factory)
    {
        aristas = new ArrayList<Vertice>();
        this.factory= factory;
        this.visitado = false;
    }

    public Vertice(int dato, int peso)
    {
        aristas = new ArrayList<Vertice>();
        this.dato = dato;
        this.visitado = false;
        this.peso = peso;
    }

    public void agregarArista (Vertice arista)
    {
        // si no está la arista para no repetir
        if (buscarArista(arista) == -1)
            aristas.add(new Vertice(0,0));
    }
    
    public void agregarArista (Vertice arista, int peso)
    {
        // si no está la arista para no repetir
        if (buscarArista(arista) == -1)
            aristas.add(new Vertice(arista.dato, peso));
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

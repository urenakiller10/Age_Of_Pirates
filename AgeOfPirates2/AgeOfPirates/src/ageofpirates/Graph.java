
package ageofpirates;

import ageofpirates.Factory.Factory;
import ageofpirates.Factory.Mine;
import java.util.ArrayList;


public class Graph {
    ArrayList<Vertice> vertices;
    public Graph()
    {
    vertices = new ArrayList<Vertice>();
    }



    // agrega a la lista
    public void agregarVertice(Factory factory)
    {
    vertices.add(new Vertice(factory));
    }


    // agrega las aristas
    public void agregarArista(Vertice origen, Vertice destino)
    {
    if (origen != null && destino != null)
    origen.agregarArista(destino);
    }



    // agrega las aristas con peso
    public void agregarArista(Vertice origen, Vertice destino, int peso)
    {
    if (origen != null && destino != null)
    origen.agregarArista(destino, peso);
    }



    // busca un vertice en la lista
    public Vertice buscarVertice (int valor){
    for (int i = 0; i < vertices.size(); i++) {
    if (vertices.get(i).dato == valor)
    return vertices.get(i);
    }
    return null;
    }
    public Vertice buscarVerticeInt (int valor){
    for (int i = 0; i < vertices.size(); i++) {
    if (vertices.get(i).factory.getId()== valor)
    return vertices.get(i);
    }
    return null;
    }
    public Vertice buscarVerticeFac (Factory valor){
    for (int i = 0; i < vertices.size(); i++) {
    if (vertices.get(i).factory== valor)
    return vertices.get(i);
    }
    return null;
    }
    public void cambiarMinas (int speed, int qty){
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).factory.getName().equals( "Mine")){
                Mine mine= (Mine)vertices.get(i).factory;
                mine.setSpeed(speed);
                mine.setQuantity(qty);
            }
        }

    }
    public boolean buscarArmeria(int id){
       for (int i = 0; i < vertices.size(); i++) {
        if (vertices.get(i).factory.getId()==id)
            return true;
        } 
       return false;
    }
    public int cantidadVertice (String nombre){
    int contador=0;
    for (int i = 0; i < vertices.size(); i++) {
        if (vertices.get(i).factory.getName().equals(nombre))
            contador++;
        }
    return contador;
    }
    
    public String[] buscarConectores (){
    if(cantidadVertice("Conector")!=0){
        String[]conectores=new String[cantidadVertice("Conector")]; 
        int contador=0;
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println(vertices.get(i).factory.getName());
            if (vertices.get(i).factory.getName().equals("Conector")){
                conectores[contador]=vertices.get(i).factory.getName()+vertices.get(i).factory.getId();
                contador++;
            }
        }
        return conectores;
    }
    return null;
    }
    // imprime la lista con sus listas de adyacencia
    public void imprimir ()
    {
    for (int i = 0; i < vertices.size(); i++)
    {
    System.out.print("Vertice "+vertices.get(i).factory.getName()+": ");
    for (int j = 0; j < vertices.get(i).aristas.size(); j++) {
    System.out.print(vertices.get(i).aristas.get(j).dato +" ");
    }
    System.out.println("");
    }
    }



    // elimina un vertice, de la lista y de las listas de adyacencia
    // imprime la lista con sus listas de adyacencia
    public void eliminar (Vertice v)
    {
//    for (int i = 0; i < vertices.size(); i++)
//    {
//        for (int j = 0; j < vertices.get(i).aristas.size(); j++) {
//            if (vertices.get(i).aristas.get(j).factory.getName().equals(v.factory.getName()))
//                vertices.get(i).aristas.remove(j);
//        }
//    }
//    vertices.remove(v);
    for (int i = 0; i < v.aristas.size(); i++) {
        v.aristas.remove(i);
    }
    System.out.println("El tamano del array antes de eliminarse era de: "+vertices.size());
    System.out.println("El objeto a eliminar es: "+v.factory.getName());
    vertices.remove(v);
    System.out.println("El tamano del array despues de eliminarse es de: "+vertices.size());
    }




    // Recorrido PROFUNDIDAD
    public void profundidad()
    {
    // recorre todos los nodos
    for (int i = 0; i < vertices.size(); i++)
    {
    if(vertices.get(i).visitado == false)
    {
    visitarAdyacentes(vertices.get(i));
    }
    }



    limpiarVisitados();// quita todos los nodos visitados



    }



    // vissita los nodos en la lista de adyacencia
    public void visitarAdyacentes(Vertice nodo)
    {
    visitarVertice(nodo);
    System.out.print(nodo.dato+" ");



    // para cada arista
    for (int i = 0; i < nodo.aristas.size(); i++)
    {
    // marca cada uno de los adyacentes
    if (visitadoVertice(nodo.aristas.get(i)) == false)
    {
    visitarAdyacentes(buscarVertice(nodo.aristas.get(i).dato));
    }
    }
    }



    public void visitarVertice(Vertice nodo)
    {
    for (int i = 0; i < vertices.size(); i++) {
    if (nodo.dato == vertices.get(i).dato)
    vertices.get(i).visitado = true;
    }
    }



    public boolean visitadoVertice(Vertice nodo)
    {
    for (int i = 0; i < vertices.size(); i++) {
    if (nodo.dato == vertices.get(i).dato)
    return vertices.get(i).visitado;
    }
    return false;// si no está
    }



    public void limpiarVisitados()
    {
    for (int i = 0; i < vertices.size(); i++) {
    Vertice vertice = vertices.get(i);
    vertice.visitado = false;
    }
    }




    //----------------------------------------
    public void anchura(Vertice v)
    {
    System.out.print(v.dato+" ");
    visitarVertice(v);// marca el primer nodo
    ArrayList<Vertice> cola = new ArrayList<Vertice>();
    // mete a la cola los adyacentes del nodo inicial
    for (int i = 0; i < v.aristas.size(); i++) {
        cola.add(buscarVertice(v.aristas.get(i).dato));// es para buscar el nodo en vertices
        visitarVertice(v.aristas.get(i));
        //System.out.println("COLA "+v.aristas.get(i).dato);
    }
    // mientras no se vacíe la cola
    while(!cola.isEmpty())
    {
        // trabaja con el primero de la cola
        Vertice actual = cola.remove(0);
        visitarVertice(actual);
        System.out.print(actual.dato+" ");
        // cada arista del vertice en la cola
        for (int i = 0; i < actual.aristas.size(); i++) {
            // si no se ha visitado se mete en la cola el adyacente
            if(visitadoVertice(actual.aristas.get(i))==false)
            {
                // si no está ya en la cola, se mete
                visitarVertice(buscarVertice(actual.aristas.get(i).dato));
                // System.out.println("METE"+ actual.aristas.get(i).dato+ " "+actual.aristas.get(i).visitado);
                cola.add(actual.aristas.get(i));
            }
        }
    }
    limpiarVisitados();
    }
}

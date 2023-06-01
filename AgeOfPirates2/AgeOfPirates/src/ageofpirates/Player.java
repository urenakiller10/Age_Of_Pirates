/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ageofpirates;

import ageofpirates.Factory.Weapons;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

 /*
 *
 * @author Diego
 */
public class Player {
    public static String IP_SERVER = "localhost"; //IP del Servidor
   Sea ventanaCliente; // Ventana del cliente
   DataInputStream entrada = null;//leer comunicacion
   DataOutputStream salida = null;//escribir comunicacion
   ObjectInputStream entradaObjetos=null;
   ObjectOutputStream salidaObjetos=null;
   Socket cliente = null;//para la comunicacion
   private int iron=0;
   private int money=4000;
   ArrayList<Weapons> canyon=new ArrayList<>();
   ArrayList<Weapons> canyonMulti=new ArrayList<>();
   ArrayList<Weapons> canyonRedBeard=new ArrayList<>();
   ArrayList<Weapons> canyonBomb=new ArrayList<>();
   ArrayList<Weapons>[] group = new ArrayList[4];
   //ArrayList<Weapons> weapons=new ArrayList<>(); 
   String nomCliente;// nombre del user
   /* Creates a new instance of Player */
   public Player(Sea vent) throws IOException
   {      
      this.ventanaCliente=vent;
      group[0] = canyon;
      group[1] = canyonMulti;
      group[2] = canyonRedBeard;
      group[3] = canyonBomb;
      
   }
   
   public void conexion() throws IOException 
   {
      try {
          // se conecta con dos sockets al server, uno comunicacion otro msjes
         cliente = new Socket(Player.IP_SERVER, 8081);
         // inicializa las entradas-lectura y salidas-escritura
         entrada = new DataInputStream(cliente.getInputStream());
         salida = new DataOutputStream(cliente.getOutputStream());
         entradaObjetos= new ObjectInputStream(cliente.getInputStream());
         salidaObjetos= new ObjectOutputStream(cliente.getOutputStream());
         salida.writeUTF(nomCliente);
      } catch (IOException e) {
         System.out.println("\tEl servidor no esta levantado");
         System.out.println("\t=============================");
      }
      // solo se le pasa entrada pues es solo para leer mensajes
      // el hiloCliente lee lo que el servidor le envia, opciones y como tiene referencia
      // a la ventana gato puede colocar en la pantalla cualquier cosa, como las
      //imagenes de X o O, llamar a metodo marcar, colocar el nombre de enemigo
      // o el suyo propio
      new threadPlayer(entradaObjetos ,entrada, ventanaCliente).start();
   }
   
   //GETTET AND SETTER
   public String getNombre()
   {
      return nomCliente;
   }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Weapons>[] getGroup() {
        return group;
    }

    public void setGroup(ArrayList<Weapons>[] group) {
        this.group = group;
    }
    


    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }
   
}

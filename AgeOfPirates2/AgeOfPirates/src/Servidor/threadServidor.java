/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;
import ageofpirates.Factory.Factory;
import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
//import tableroprogramario.Cards;
//import tableroprogramario.SuperBrosMemory;
//import static tableroprogramario.Tablero.BOARD_SIZE;
/**
 *
 * 
 */
public class threadServidor extends Thread
{
     Socket cliente = null;//referencia a socket de comunicacion de cliente
     DataInputStream entrada=null;//Para leer comunicacion
     ObjectInputStream entradaObjetos=null;//Para leer comunicacion
     DataOutputStream salida=null;//Para enviar comunicacion  
     ObjectOutputStream salidaObjetos=null;
     String nameUser; //Para el nombre del usuario de esta conexion
     servidor servidor;// referencia al servidor
     int[][] pairs;
     public int enemigoMemory;
     public int enemigoGato;
     // para envio de mensajes al enemigo
     int totalJugadores;
     private Color colorArray[];
     ArrayList<threadServidor> hilosEnemigos=new ArrayList<threadServidor>();
     threadServidor enemigo = null;
     // identificar el numero de jugador
     int numeroDeJugador;
     int rand_int;
     int rand_int2;
     boolean isReady=false;
     ArrayList<int[][]> enemiesFactories= new ArrayList<int[][]>();
     int factory[][];
     ArrayList<Integer> idList= new ArrayList<>();
     ArrayList<String> NameList= new ArrayList<>();
     //ArrayList<Integer> idList= new ArrayList<>();

     public threadServidor(Socket cliente,servidor serv, int num)
     {
        this.cliente = cliente;
        this.servidor = serv;
        this.numeroDeJugador = num;
        //nameUser=name;// inicialmente se desconoce, hasta el primer read del hilo
     }
    public void setColorArray(Color[] colorArray) {
        this.colorArray = colorArray;
    }
     //Getter an Setter...
     public String getNameUser()
     {
       return nameUser;
     }
     public void setNameUser(String name)
     {
       nameUser=name;
     }
     
     public void run()
     {
      try
      {
            // inicializa para lectura y escritura con stream de cliente
          entrada=new DataInputStream(cliente.getInputStream());//comunic
          salida=new DataOutputStream(cliente.getOutputStream());//comunic
          salidaObjetos=new ObjectOutputStream(cliente.getOutputStream());
          entradaObjetos=new ObjectInputStream(cliente.getInputStream());
          this.setNameUser(entrada.readUTF());
          System.out.println("1. Leyo nombre: " + nameUser);
          // Es el primer read que hace, para el nombre del user
      }
      catch (IOException e) {}
      //VARIABLES
        int opcion;
        int opcion2=0;
      while(true)
      {
          try
          {
              //Siempre espera leer un int que será la instruccion por hacer
             opcion=entrada.readInt();   
              System.out.println("opcion es: "+opcion);
             switch(opcion)
             {
//                case 1:                  
//                   int dados = entrada.readInt();
//                   System.out.println(dados);
//                   boolean estrella=entrada.readBoolean();
//                   for (int i = 0; i < hilosEnemigos.size(); i++) {
//                      hilosEnemigos.get(i).salida.writeInt(1);
//                      hilosEnemigos.get(i).salida.writeInt(dados);  
//                      hilosEnemigos.get(i).salida.writeBoolean(estrella);
//                    }                  
//                   break;
//                case 2:// 
//                    salida.writeInt(2);
//                    salida.writeInt(totalJugadores);                   
//                   break;
                case 3: //le envia el status, que es el numero de jugador y el nombre enemigo
                    salida.writeInt(3);
                    salida.writeInt(numeroDeJugador);
                    salida.writeUTF(nameUser);
                      try {
                          factory = (int[][])entradaObjetos.readObject();
                      } catch (ClassNotFoundException ex) {
                      }
                      System.out.println(nameUser+"  f: "+factory);
                    if(numeroDeJugador==totalJugadores){
                        switch (hilosEnemigos.size()) {
                            case 1:
                                for (int i = 0; i < hilosEnemigos.size()+1; i++) {
                                    if(i==0){
                                        hilosEnemigos.get(i).enemiesFactories.add(this.factory);
                                        hilosEnemigos.get(i).idList.add(this.numeroDeJugador);                                       
                                        hilosEnemigos.get(i).NameList.add(this.nameUser);      
                                        hilosEnemigos.get(i).salida.writeInt(6);
                                        hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).enemiesFactories);
                                        hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).idList);
                                        hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).NameList);
                                    }else{
                                        this.idList.add(hilosEnemigos.get(0).numeroDeJugador);
                                        this.NameList.add(hilosEnemigos.get(0).nameUser);
                                        this.enemiesFactories.add(hilosEnemigos.get(0).factory);
                                    }
                                }
                                break;
                            case 2:
                                for (int i = 0; i < hilosEnemigos.size()+1; i++) {
                                    switch (i) {
                                        case 0:
                                            hilosEnemigos.get(i).enemiesFactories.add(hilosEnemigos.get(i+1).factory);
                                            hilosEnemigos.get(i).enemiesFactories.add(this.factory);
                                            hilosEnemigos.get(i).idList.add(hilosEnemigos.get(i+1).numeroDeJugador); 
                                            hilosEnemigos.get(i).idList.add(this.numeroDeJugador); 
                                            hilosEnemigos.get(i).NameList.add(hilosEnemigos.get(i+1).nameUser); 
                                            hilosEnemigos.get(i).NameList.add(this.nameUser);
                                            hilosEnemigos.get(i).salida.writeInt(6);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).enemiesFactories);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).idList);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).NameList);
                                            break;
                                        case 1:
                                            hilosEnemigos.get(i).enemiesFactories.add(hilosEnemigos.get(i-1).factory);
                                            hilosEnemigos.get(i).enemiesFactories.add(this.factory);
                                            hilosEnemigos.get(i).idList.add(hilosEnemigos.get(i-1).numeroDeJugador);
                                            hilosEnemigos.get(i).idList.add(this.numeroDeJugador);  
                                            hilosEnemigos.get(i).NameList.add(hilosEnemigos.get(i-1).nameUser); 
                                            hilosEnemigos.get(i).NameList.add(this.nameUser);
                                            hilosEnemigos.get(i).salida.writeInt(6);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).enemiesFactories);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).idList);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).NameList);
                                            break;
                                        default:
                                            this.enemiesFactories.add(hilosEnemigos.get(0).factory);
                                            this.enemiesFactories.add(hilosEnemigos.get(1).factory);
                                            this.idList.add(hilosEnemigos.get(0).numeroDeJugador);
                                            this.idList.add(hilosEnemigos.get(1).numeroDeJugador);
                                            this.NameList.add(hilosEnemigos.get(0).nameUser);
                                            this.NameList.add(hilosEnemigos.get(1).nameUser);
                                        break;
                                    }
                                }
                                break;
                            case 3:
                                for (int i = 0; i <  hilosEnemigos.size()+1; i++) {
                                    switch(i){
                                        case 0:
                                            hilosEnemigos.get(i).enemiesFactories.add(hilosEnemigos.get(i+1).factory);
                                            hilosEnemigos.get(i).enemiesFactories.add(hilosEnemigos.get(i+2).factory);
                                            hilosEnemigos.get(i).enemiesFactories.add(this.factory);
                                            hilosEnemigos.get(i).idList.add(hilosEnemigos.get(i+1).numeroDeJugador);
                                            hilosEnemigos.get(i).idList.add(hilosEnemigos.get(i+2).numeroDeJugador);
                                            hilosEnemigos.get(i).idList.add(this.numeroDeJugador);
                                            hilosEnemigos.get(i).NameList.add(hilosEnemigos.get(i+1).nameUser); 
                                            hilosEnemigos.get(i).NameList.add(hilosEnemigos.get(i+2).nameUser); 
                                            hilosEnemigos.get(i).NameList.add(this.nameUser);
                                            hilosEnemigos.get(i).salida.writeInt(6);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).enemiesFactories);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).idList);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).NameList);
                                            break;
                                        case 1:
                                            hilosEnemigos.get(i).enemiesFactories.add(hilosEnemigos.get(i-1).factory);
                                            hilosEnemigos.get(i).enemiesFactories.add(hilosEnemigos.get(i+1).factory);
                                            hilosEnemigos.get(i).enemiesFactories.add(this.factory);
                                            hilosEnemigos.get(i).idList.add(hilosEnemigos.get(i-1).numeroDeJugador);
                                            hilosEnemigos.get(i).idList.add(hilosEnemigos.get(i+1).numeroDeJugador);
                                            hilosEnemigos.get(i).idList.add(this.numeroDeJugador);
                                            hilosEnemigos.get(i).NameList.add(hilosEnemigos.get(i-1).nameUser); 
                                            hilosEnemigos.get(i).NameList.add(hilosEnemigos.get(i+1).nameUser); 
                                            hilosEnemigos.get(i).NameList.add(this.nameUser);
                                            hilosEnemigos.get(i).salida.writeInt(6);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).enemiesFactories);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).idList);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).NameList);
                                            break;
                                        case 2:
                                            hilosEnemigos.get(i).enemiesFactories.add(hilosEnemigos.get(i-1).factory);
                                            hilosEnemigos.get(i).enemiesFactories.add(hilosEnemigos.get(i-2).factory);
                                            hilosEnemigos.get(i).enemiesFactories.add(this.factory);
                                            hilosEnemigos.get(i).idList.add(hilosEnemigos.get(i-1).numeroDeJugador);
                                            hilosEnemigos.get(i).idList.add(hilosEnemigos.get(i-2).numeroDeJugador);
                                            hilosEnemigos.get(i).idList.add(this.numeroDeJugador);
                                            hilosEnemigos.get(i).NameList.add(hilosEnemigos.get(i-1).nameUser); 
                                            hilosEnemigos.get(i).NameList.add(hilosEnemigos.get(i-2).nameUser); 
                                            hilosEnemigos.get(i).NameList.add(this.nameUser);
                                            hilosEnemigos.get(i).salida.writeInt(6);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).enemiesFactories);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).idList);
                                            hilosEnemigos.get(i).salidaObjetos.writeObject(hilosEnemigos.get(i).NameList);
                                            break;
                                        default:
                                             this.enemiesFactories.add(hilosEnemigos.get(0).factory);
                                             this.enemiesFactories.add(hilosEnemigos.get(1).factory);
                                             this.enemiesFactories.add(hilosEnemigos.get(2).factory);
                                             this.idList.add(hilosEnemigos.get(0).numeroDeJugador);
                                             this.idList.add(hilosEnemigos.get(1).numeroDeJugador);
                                             this.idList.add(hilosEnemigos.get(2).numeroDeJugador);
                                             this.NameList.add(hilosEnemigos.get(0).nameUser);
                                             this.NameList.add(hilosEnemigos.get(1).nameUser);
                                             this.NameList.add(hilosEnemigos.get(2).nameUser);
                                            break;
                                    }
                                }
                                break;
                        }
                        salida.writeInt(6);
                        salidaObjetos.writeObject(this.enemiesFactories);
                        salidaObjetos.writeObject(idList);
                        salidaObjetos.writeObject(NameList);
                    }
                   break;
                 case 4:
// lee el mensaje enviado desde el jframe
                     String mensaje = entrada.readUTF();
                     // envia un 4 al thradCliente enemigo
                     for (int i = 0; i < hilosEnemigos.size(); i++) {
                      hilosEnemigos.get(i).salida.writeInt(4);
                      hilosEnemigos.get(i).salida.writeUTF(mensaje);
                    } 
                     // envia el emnsaje al thread cliente enemigo
                     System.out.println("Op4: envia 4 y mensaje: "+ mensaje);
                 break;
                 case 6:
                    salida.writeInt(6);
                    for (int i = 0; i < hilosEnemigos.size(); i++) {
                      hilosEnemigos.get(i).salida.writeInt(6);
                      
                    } 
                 break;
//                 case 7:
//                    salida.writeInt(7);
//                    boolean haGanado = entrada.readBoolean();
//                    salida.writeBoolean(haGanado);                  
//                 break;
                 case 9:
                    int fila = entrada.readInt();
                    int columna = entrada.readInt();
                    int tipo = entrada.readInt();
                    int id=entrada.readInt();
//                    salida.writeInt(9);
//                    salida.writeInt(fila);
//                    salida.writeInt(columna);
//                    salida.writeInt(tipo);
                    for (int i = 0; i < hilosEnemigos.size(); i++) {
                      hilosEnemigos.get(i).salida.writeInt(9);
                      hilosEnemigos.get(i).salida.writeInt(fila);
                      hilosEnemigos.get(i).salida.writeInt(columna);
                      hilosEnemigos.get(i).salida.writeInt(tipo);
                      hilosEnemigos.get(i).salida.writeInt(id);                        
                    }  
                 break;
                 case 10:
                    int idAtacado = entrada.readInt();
                    int columnaAtacada = entrada.readInt();
                    int filaAtacada = entrada.readInt();
                    for (int i = 0; i < hilosEnemigos.size(); i++) {
                        if(hilosEnemigos.get(i).numeroDeJugador==idAtacado){
                            hilosEnemigos.get(i).salida.writeInt(10);
                            hilosEnemigos.get(i).salida.writeInt(filaAtacada);
                            hilosEnemigos.get(i).salida.writeInt(columnaAtacada);
                        }                       
                    }  
                 break;
                 case 11:
// lee el mensaje enviado desde el jframe
                     String bitacora = entrada.readUTF();
                     // envia un 4 al thradCliente enemigo
                     int idAtacado2 = entrada.readInt();
                     for (int i = 0; i < hilosEnemigos.size(); i++) {
                      if(hilosEnemigos.get(i).numeroDeJugador==idAtacado2){
                            hilosEnemigos.get(i).salida.writeInt(11);
                            hilosEnemigos.get(i).salida.writeUTF(bitacora); 
                      }                     
                    } 
                     // envia el emnsaje al thread cliente enemigo
                     System.out.println("Op4: envia 4 y mensaje: "+ bitacora);
                 break;
                 case 12:
                    for (int i = 0; i < hilosEnemigos.size(); i++) {
                        hilosEnemigos.get(i).salida.writeInt(12);                    
                    } 
                 break;
                 default:
                     break;
             }
          }
          catch (IOException e) {
              System.out.println("El cliente termino la conexion");break;}
      }
      servidor.ventana.mostrar("Se removio un usuario");
      
      try
      {
          servidor.ventana.mostrar("Se desconecto un usuario: "+nameUser);
          cliente.close();
      }  
        catch(IOException et)
        {servidor.ventana.mostrar("no se puede cerrar el socket");}   
     }

     // Envia su nombre a todos los demas usuarios excepto él
     public void enviaUser()
     {
        if (enemigo != null)
        {
        try
        {
            enemigo.salida.writeInt(2);//escribe opcion de agregar 2
            enemigo.salida.writeUTF(this.getNameUser());//escribe nombre  
            System.out.println("2. Envia 2 y username "+ "2" +getNameUser());
        }
        catch (IOException e) {}
        }
     }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
//import static tableroprogramario.Tablero.BOARD_SIZE;

/**
 *
 * @author [Redacted]
 */
public class servidor {
    int[] playerNumbers;
    //String[] playerNames;
    String nomCliente;
    int number;  
    JFrameServidor ventana;
    public ArrayList<Socket> clientes;
    public ArrayList<threadServidor> hilosServer;
    int numeroClientes;
//    protected int []path = new int[BOARD_SIZE-2];
    
    public servidor(JFrameServidor padre)
    {
        // asigna la ventana
        this.ventana = padre;
        clientes=new ArrayList<>();
        hilosServer=new ArrayList<>();
    }
    
    public void runServer()
    {
        try {
            //crea el socket servidor para aceptar dos conexiones
            numeroClientes = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de clientes :"));
            while(numeroClientes>4||numeroClientes<2){
                JOptionPane.showMessageDialog(null, "Cantidad de jugadores invalida (2<=x<=4)", "Error", JOptionPane.ERROR_MESSAGE);
                numeroClientes = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de clientes :"));
            }
            playerNumbers= new int[numeroClientes];
//            playerNames= new String[numeroClientes];
//            for(int i=0;i<numeroClientes;i++){
//                nomCliente = JOptionPane.showInputDialog("Introducir Nick jugador "+(i+1)+" :");
//                playerNumbers[i]=number;
//                playerNames[i]=nomCliente;
//            }
            addClientes(numeroClientes);
            ServerSocket serv = new ServerSocket(8081);
            ventana.mostrar(".::Servidor Activo");
            ventana.mostrar(".::Esperando "+numeroClientes+" usuarios");
            addClientes(numeroClientes);
            aceptClientes(numeroClientes, serv);
            setEnemigos(numeroClientes);                   
            while (true)
            {          
            }
            
        } catch (IOException ex) {
            ventana.mostrar("ERROR ... en el servidor");
        }
    }

private void addClientes(int cantidadClientes){
        for (int i = 0; i < cantidadClientes; i++) {
            clientes.add(new Socket());          
        }
    }
private void aceptClientes(int cantidadClientes,ServerSocket serv){
    try {
        for (int i = 0; i < cantidadClientes; i++) {
            Socket cliente=clientes.get(i);
            cliente = serv.accept();
            ventana.mostrar(".:: Cliente "+i+" Aceptado");
            threadServidor user=new threadServidor(cliente, this,i+1);
            user.totalJugadores=numeroClientes;
//            user.path=path;
            hilosServer.add(user);
            user.start();
            }
        }catch (IOException ex) {}
    }

private void setEnemigos(int cantidadClientes){
    for (int i = 0; i < cantidadClientes; i++) {
            threadServidor user=hilosServer.get(i);
            for (int j = 0; j <cantidadClientes ; j++) {
                threadServidor enemigo=hilosServer.get(j);                 
                if(user!=enemigo){
                    user.hilosEnemigos.add(enemigo);
                }
            }
        }
    }
}



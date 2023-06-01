package ageofpirates;
import ageofpirates.Factory.Factory;
import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class threadPlayer extends Thread{
   //solo de lectura
   DataInputStream entrada;
   ObjectInputStream entradaObjetos;
   Sea vcli; //referencia acliente
   public threadPlayer (ObjectInputStream entradaObjetos,DataInputStream entrada,Sea vcli) throws IOException
   {
      this.entrada=entrada;
      this.entradaObjetos=entradaObjetos;
      this.vcli=vcli;
      
   }
   public void run()
   {
      int opcion;
      
      while(true)
      {         
         try{
             opcion=entrada.readInt();
            switch(opcion)
            {
//               case 1:
//                  int datos = entrada.readInt();
//                  boolean estrella = entrada.readBoolean();
//                  vcli.tirar(datos,estrella);
//                  break;
//               case 2:  
//                  vcli.totalDeJugadores = entrada.readInt();                 
//                  vcli.playerQty=vcli.totalDeJugadores;                  
//                  vcli.initPlayers();
//                  vcli.initBoard();
//                  vcli.setTablero();
//                  break;
                 case 3://lee el numero del jugador
                    vcli.numeroJugador = entrada.readInt();
                    String name=entrada.readUTF();
                    vcli.setTitle(name);
                break;
                case 4:
                 {
                    // lee el mensaje
                    vcli.txaMensajes.setText(vcli.txaMensajes.getText()+entrada.readUTF());
                 }
                break;                          
                 case 6:
                    System.out.println("enrte23\n");
                    vcli.enemiesFactories=(ArrayList<int[][]>) entradaObjetos.readObject();
                    vcli.idList=(ArrayList<Integer>) entradaObjetos.readObject();
                    vcli.nameList=(ArrayList<String>) entradaObjetos.readObject();
                    for (int i = 0; i < vcli.enemiesFactories.size(); i++) {
                        vcli.boardEnemy.add(new enemyBoard(vcli.enemiesFactories.get(i),vcli.idList.get(i),vcli,vcli.nameList.get(i)));
                        switch(i){
                            case 0:
                                vcli.btnEnemy1.setText(vcli.nameList.get(i));
                                vcli.btnEnemy1.setEnabled(true);
                                break;
                            case 1:
                                vcli.btnEnemy2.setText(vcli.nameList.get(i));
                                vcli.btnEnemy2.setEnabled(true);
                                break;
                            case 2:
                                vcli.btnEnemy3.setText(vcli.nameList.get(i));
                                vcli.btnEnemy3.setEnabled(true);
                                break;
                        }
                    }
                    vcli.playerQty=vcli.enemiesFactories.size()+1;
                    System.out.println(vcli.enemiesFactories.size());
                     for (int i = 0; i < vcli.enemiesFactories.size(); i++) {
                         System.out.println(vcli.enemiesFactories.get(i));
                     }
                 break;
//                 
//                 case 7:
//                     vcli.getBtnDados().setEnabled(true);
//                     boolean haGanado = entrada.readBoolean();
//                     vcli.haGanado(haGanado);
//                 break;
//                 
//                 case 8:
//                     vcli.getBtnDados().setEnabled(true);
//                 break;
                 case 9:
                     int fila = entrada.readInt();
                     int columna = entrada.readInt();
                     int tipo = entrada.readInt();
                     int id = entrada.readInt();
                     for (int i = 0; i < vcli.boardEnemy.size(); i++) {
                         if(vcli.boardEnemy.get(i).id==id){
                             vcli.boardEnemy.get(i).colocar(fila, columna, tipo);
                         }
                     }                   
                 break;
                 case 10:
                     int filaAtacada = entrada.readInt();
                     int columnaAtacada = entrada.readInt();
                     vcli.atacado(filaAtacada, columnaAtacada);
                 break;
                 case 11:
                 {
                    // lee el mensaje
                    vcli.txaBitacora.setText(vcli.txaBitacora.getText()+entrada.readUTF());
                 }
                 case 12:
                 {
                    vcli.updateTurno();
                 }
//                 case 10:
//                     int objetivo = entrada.readInt();
//                     vcli.setFlorHielo(objetivo);
//                 break;  
//                 case 11:
//                     int numAvanzar = entrada.readInt();
//                     vcli.tirarCola(numAvanzar);
//                 break;  
//                 case 12:                
//                     try {
//                         vcli.iniciarBrosMemory((int[][])entradaObjetos.readObject());
//                     } catch (ClassNotFoundException ex) {
//                     }
//                 break;
//
//                 case 13:
//                        int fila = entrada.readInt();
//                        int columna = entrada.readInt();
//                        int fila2 = entrada.readInt();
//                        int columna2 = entrada.readInt();                   
//                        vcli.bMemory.setEnemyMovement(fila, columna, fila2, columna2);
//                 break;
//                 case 14: 
//                        vcli.bMemory.turno();
//                 break;
//                 case 15:                  
//                     try {
//                         vcli.iniciarMarioCards((Cards[][])entradaObjetos.readObject());
//                     } catch (ClassNotFoundException ex) {
//                     }
//                 break;
//                 case 16:                  
//                     vcli.marioC.turno();
//                 break;
//                 case 17:                  
//                     try {
//                         vcli.marioC.setEnemyMovement((Cards)entradaObjetos.readObject());
//                     } catch (ClassNotFoundException ex) {
//                     }
//                 break;
//                 case 18:                  
//                     vcli.iniciarGato();
//                 break;
//                 case 19://mensaje enviado
//                    if(vcli.gatoJ!=null){
//                        int col = entrada.readInt();//lee columna
//                        int fil = entrada.readInt();//lee fila
//                        // llama a marcar, que es lo que hace el cliente cuando
//                        // el enemigo marco la sailla
//                        vcli.gatoJ.marcar(col,fil);  
//                    }
//                 break;
                 case 20://mensaje enviado
                     vcli.player.salida.writeInt(20);
                 break;
//                 case 21://mensaje enviado
//                    int _ganador = entrada.readInt();//lee columna
//                    System.out.println(_ganador);  
//                    // llama a marcar, que es lo que hace el cliente cuando
//                    // el enemigo marco la sailla
//                    vcli.turnoGanador(_ganador);
//                 break;
            }
         }
         catch (IOException e){
            System.out.println(e.getCause()+" \nError en la comunicacion "+"Informacion para el usuario");
            break;
         } catch (ClassNotFoundException ex) {
              Logger.getLogger(threadPlayer.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      System.out.println("se desconecto el servidor");
   }

   
}
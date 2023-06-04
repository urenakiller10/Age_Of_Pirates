package ageofpirates.Factory;

import ageofpirates.Sea;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class WitchTemple extends Factory implements Serializable  {
    ThreadCrono crono;
    Sea sea;
    int type;
    public WitchTemple(Sea sea) {
        super("WitchTemple",2500,"1x2",6);
        crono = new ThreadCrono(this);
        crono.start();
        this.sea=sea;
        
    }
    
    public void kraken(){
        String enemyName=JOptionPane.showInputDialog("Introducir el nombre del enemigo a enviar el kraken:");
            for (int i = 0; i < sea.enemiesFactories.size() ; i++) {
                if(enemyName.equals(sea.nameList.get(i))){
                    //encuentro al enemigo, luego escojo una estuctura random y sus casillas adyacentes las destruyo
                    int fila=0;
                    int columna=0;
                    int [][] logic= sea.enemiesFactories.get(i);
                    boolean stop=false;
                    for (int j = 0; j < sea.enemiesFactories.get(i).length; j++) {
                        if(stop==true){
                            break;
                        }else{
                            for (int k = 0; k < sea.enemiesFactories.get(i).length; k++) {
                               if(logic[j][k]==type){
                                   System.out.println("  as "+j+" "+k);
                                   fila=j;
                                   columna=k;
                                   stop=true;
                                   break;
                               } 
                            }
                        }
                    }
                    switch(type){
                        case 2:
                            //logic[fila][columna];
                            sea.boardEnemy.get(i).buttonArray[fila][columna].setIcon(new ImageIcon(getClass().getResource("whirlpool.jpg")));
                            sea.boardEnemy.get(i).buttonArray[fila+1][columna].setIcon(new ImageIcon(getClass().getResource("whirlpool.jpg")));
                            sea.boardEnemy.get(i).buttonArray[fila+1][columna+1].setIcon(new ImageIcon(getClass().getResource("whirlpool.jpg")));
                            sea.boardEnemy.get(i).buttonArray[fila][columna+1].setIcon(new ImageIcon(getClass().getResource("whirlpool.jpg")));
                            sea.boardEnemy.get(i).atackBoard[fila][columna]=1;
                            sea.boardEnemy.get(i).atackBoard[fila+1][columna]=1;
                            sea.boardEnemy.get(i).atackBoard[fila+1][columna+1]=1;
                            sea.boardEnemy.get(i).atackBoard[fila][columna+1]=1;
                            break;
                            
                        case 3:
                            sea.boardEnemy.get(i).buttonArray[fila][columna].setIcon(new ImageIcon(getClass().getResource("whirlpool.jpg")));
                            sea.boardEnemy.get(i).atackBoard[fila][columna]=1;
                            break;
                        default:
                            sea.boardEnemy.get(i).buttonArray[fila][columna].setIcon(new ImageIcon(getClass().getResource("whirlpool.jpg")));
                            sea.boardEnemy.get(i).buttonArray[fila+1][columna].setIcon(new ImageIcon(getClass().getResource("whirlpool.jpg")));
                            sea.boardEnemy.get(i).atackBoard[fila][columna]=1;
                            sea.boardEnemy.get(i).atackBoard[fila+1][columna]=1;
                            break;     
                    }
                    sea.grafoMatriz.eliminar(sea.grafoMatriz.buscarFactoryFac(sea.factoryBoard[columna][fila].getNum()));
                    break;
                }
            }
    }
    public void shield(){
        int qtyShields= ThreadLocalRandom.current().nextInt(2, 5);
        sea.qtyShields+=qtyShields;
    }
    
}

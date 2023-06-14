package ageofpirates.Factory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GhostShip extends Factory{
    int fila, columna;
    private JButton[][] buttonArray;
    private int[][] logicBoard; 
    private int[][] ghostBoard; 
    public GhostShip(int fila,int columna,JButton[][] buttonArray,int[][] logicBoard,int[][] ghostBoard) {
        super("GhostShip", 2500, "0x0", 11);
        this.fila=fila;
        this.columna=columna;
        this. buttonArray= buttonArray;
        this.logicBoard=logicBoard;
        (new actuar()).start();
        this.ghostBoard=ghostBoard;
    }
    public class actuar extends Thread{
        private boolean isRunnig=true, isPaused=false;
        protected  int segundos;
        protected int minutos;
        @Override
        public void run() {
            while(isRunnig){
                try {
                    sleep(1000);
                    while(isPaused){
                        sleep(100);
                    }
                    segundos++;
                    System.out.println(segundos);
                    if(segundos==90){
                        System.out.println("enrte");
                        isRunnig=false;
                        int qtyButtons= ThreadLocalRandom.current().nextInt(4, 8);
                        for (int i = 0; i < qtyButtons; i++) {
                            for (int j = 0; j < qtyButtons; j++) {
                                if((fila+i)<=19&&(columna+j)<=19){
                                    if(logicBoard[fila+i][columna+j]!=0){
                                        ghostBoard[fila+i][columna+j]=1;
                                        switch(logicBoard[fila+i][columna+j]){
                                            case 1:
                                                buttonArray[fila+i][columna+j].setIcon(new ImageIcon(getClass().getResource("whirlpool.jpg")));
                                                break;
                                            case 2:
                                                buttonArray[fila+i][columna+j].setIcon(new ImageIcon(getClass().getResource("energyB.png")));
                                                break;
                                            case 3:
                                                buttonArray[fila+i][columna+j].setIcon(new ImageIcon(getClass().getResource("conectorB.png")));
                                                break;
                                            case 4:
                                                buttonArray[fila+i][columna+j].setIcon(new ImageIcon(getClass().getResource("merchantB.png")));
                                                break;
                                            case 5:
                                                buttonArray[fila+i][columna+j].setIcon(new ImageIcon(getClass().getResource("IronMineB.png")));
                                                break;
                                            case 6:
                                                buttonArray[fila+i][columna+j].setIcon(new ImageIcon(getClass().getResource("WitchTempleB.png")));
                                                break;
                                            case 7:
                                                buttonArray[fila+i][columna+j].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                                                break;
                                            case 8:
                                                buttonArray[fila+i][columna+j].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                                                break;
                                            case 9:
                                                buttonArray[fila+i][columna+j].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                                                break;
                                            case 10:
                                                buttonArray[fila+i][columna+j].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(GhostShip.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
    }
    
}
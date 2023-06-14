package ageofpirates.Factory;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JButton;
import javax.swing.JLabel;



public class ThreadCrono extends Thread implements Serializable{

    public boolean isRunning = true, isPaused=false;
    protected  int segundos=30;
    protected int minutos=4;
    Mine mine;
    WitchTemple witchTemple;
    ThreadCrono(Mine mine){
        this.mine=mine;
    }
    
    ThreadCrono(WitchTemple witchTemple){
        this.witchTemple=witchTemple;
    }


    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    

    public void run(){ 
        while(isRunning){
            try {
                sleep(1000);
                segundos++;
                if (segundos > 59){
                    segundos = 0;
                    minutos++;
                    if(minutos > 59){
                        minutos = 0;
                    }
                }
                if(witchTemple!=null){
                    if(minutos==5){
                        minutos=0;
//                        if(new Random().nextInt(2)==0){
                           witchTemple.type= ThreadLocalRandom.current().nextInt(2, 10);
                           while(witchTemple.sea.grafoMatriz.buscarFactory(witchTemple.type)){
                                witchTemple.type= ThreadLocalRandom.current().nextInt(2, 10);
                           }
                           witchTemple.kraken();
                        //}
//                        else{
//                           witchTemple.shield(); 
//                        }                   
                    }  
                }
                else{
                    if(segundos%mine.speed==0){
                        mine.player.setIron(mine.player.getIron()+mine.quantity);
                    }
                }
                while(isPaused){
                    sleep(10);
                }
            } catch (InterruptedException ex) {
                
            } 
        }
    }
    
    protected boolean isStop(){
        return isRunning==false;
    }
    
    public void Stop(){
        this.isRunning=false;
    }
    public void Pause(){
        this.isPaused=true;
    }
     public void NotPause(){
        this.isPaused=false;
    }
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    
}
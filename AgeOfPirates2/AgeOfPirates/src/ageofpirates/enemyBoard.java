/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofpirates;

import ageofpirates.Factory.Factory;
import ageofpirates.Factory.GhostShip;
import ageofpirates.Factory.Mine;
import ageofpirates.Factory.Weapons;
import static ageofpirates.Sea.BOARD_SIZE;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class enemyBoard extends javax.swing.JFrame {


    public static final int BUTTON_SIZE = 60;
    public static final int BOARD_SIZE = 20;
    public static final int PLAYER_HEIGH = 10;
    public static final int PLAYER_WIDTH = 20;
    protected int playerQty;
    protected Color colorArray[];
    public JButton[][] buttonArray= new JButton[BOARD_SIZE][BOARD_SIZE];
    private int[][] logicBoard=  new int[BOARD_SIZE][BOARD_SIZE];
    public int[][] atackBoard=  new int[BOARD_SIZE][BOARD_SIZE];
    private int[][] ghostBoard=  new int[BOARD_SIZE][BOARD_SIZE];
    private Factory[][] factoryBoard=  new Factory[BOARD_SIZE][BOARD_SIZE];
    public JLabel fondo;
    protected final  ImageIcon beta= new ImageIcon(getClass().getResource("energy.png"));
    protected final  ImageIcon alpha= new ImageIcon(getClass().getResource("conector.png"));
    protected boolean atack=false;
    protected Weapons objective; 
    protected boolean isAvailable=true;
    JComboBox eleccion;
    protected Graph graph= new Graph();
    public int contador=0;
    String nomCliente;
    int numeroJugador;
    int id;
    Sea sea;
    String rival;
    int disparos=0;
    boolean ghost=false;

    /**
     * Creates new form enemyBoard
     */
    public enemyBoard(int[][] logicBoard, int id,Sea sea,String nombre) {
        initComponents();    
        this.fondo=new JLabel(new ImageIcon(getClass().getResource("ocean.png")));
        initBoard();
        this.logicBoard=logicBoard;
        this.id=id;
        this.sea=sea;
        this.rival=nombre;
        //setImages();
    }
    public void colocar(int fila,int columna,int tipo){
        switch(tipo){
            case 1:
                logicBoard[columna][fila]=tipo;
                break;
            case 2:
                logicBoard[columna][fila]=tipo;
                logicBoard[columna+1][fila]=tipo;
                logicBoard[columna+1][fila+1]=tipo;
                logicBoard[columna][fila+1]=tipo;
                //setImages();
                break;
            case 3:
                logicBoard[columna][fila]=tipo;
                //setImages();
                break;
            case 4:
                logicBoard[columna][fila]=tipo;
                logicBoard[columna+1][fila]=tipo;
                //setImages();
                break;    
            case 5:
                logicBoard[columna][fila]=tipo;
                logicBoard[columna+1][fila]=tipo;
                //setImages();
                break; 
            case 6:
                logicBoard[columna][fila]=tipo;
                logicBoard[columna+1][fila]=tipo;
                //setImages();
                break; 
            case 7:
                logicBoard[columna][fila]=tipo;
                logicBoard[columna+1][fila]=tipo;
                //setImages();
                break; 
            case 8:
                logicBoard[columna][fila]=tipo;
                logicBoard[columna+1][fila]=tipo;
                //setImages();
                break; 
            case 9:
                logicBoard[columna][fila]=tipo;
                logicBoard[columna+1][fila]=tipo;
                //setImages();
                break; 
            case 10:
                logicBoard[columna][fila]=tipo;
                logicBoard[columna+1][fila]=tipo;
                //setImages();
                break;            
        }
    }
    private void setImages(){
        for(int i=0;i<BOARD_SIZE;i++)
        {
            for(int j=0;j<BOARD_SIZE;j++)
            {
               switch(logicBoard[i][j]){
                    case 1:
                        buttonArray[i][j].setIcon(new ImageIcon(getClass().getResource("whirlpool.jpg")));
                        break;
                    case 2:
                        buttonArray[i][j].setIcon(new ImageIcon(getClass().getResource("energyB.png")));
                        break;
                    case 3:
                        buttonArray[i][j].setIcon(new ImageIcon(getClass().getResource("conectorB.png")));
                        break;
                    case 4:
                        buttonArray[i][j].setIcon(new ImageIcon(getClass().getResource("merchantB.png")));
                        break;
                    case 5:
                        buttonArray[i][j].setIcon(new ImageIcon(getClass().getResource("IronMineB.png")));
                        break;
                    case 6:
                        buttonArray[i][j].setIcon(new ImageIcon(getClass().getResource("WitchTempleB.png")));
                        break;
                    case 7:
                        buttonArray[i][j].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                        break;
                    case 8:
                        buttonArray[i][j].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                        break;
                    case 9:
                        buttonArray[i][j].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                        break;
                    case 10:
                        buttonArray[i][j].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                        break;
                }
            }
        }
    }
protected void initBoard(){
        for(int i=0;i<BOARD_SIZE;i++)
        {
            for(int j=0;j<BOARD_SIZE;j++)
            {
                // coloca imagen a todos vacio
                buttonArray[i][j] = new JButton();
                //añade al panel el boton;
                SeaPanel.add(buttonArray[i][j]);
                // coloca dimensiones y localidad
                buttonArray[i][j].setBounds(18*i+10, 18*j+10, 20, 20);
                // coloca el comand como i , j 
                buttonArray[i][j].setActionCommand(i+","+j);//i+","+j
                
//                aclickSobreTablero(evt);ñade el listener al boton
                buttonArray[i][j].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                        
                    try {
                        clickSobreTablero(evt);
                    } catch (IOException ex) {
                        Logger.getLogger(enemyBoard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }     
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                        
                    MouseOntheButton(evt);

                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    MouseOuttheButton(evt);
                }
                });
 
                //en logico indica estado en disponible
                logicBoard[i][j]=0;
            }
        }
        this.SeaPanel.add(fondo);
        fondo.setBounds(0,0,871,607);
    }
public boolean isDestroyed(int columna,int fila,int tipo){
    switch(tipo){
            case 1:
                logicBoard[columna][fila]=tipo;
                break;
            case 2:
                if(!(columna+1>BOARD_SIZE||fila+1>BOARD_SIZE)&&atackBoard[columna+1][fila]==1&&atackBoard[columna+1][fila+1]==1&&atackBoard[columna][fila+1]==1){
                    return true;
                }
                else if(!(columna-1<0||fila+1>BOARD_SIZE)&&atackBoard[columna-1][fila]==1&&atackBoard[columna][fila+1]==1&&atackBoard[columna-1][fila+1]==1){
                    return true;
                }
                else if(!(columna+1>BOARD_SIZE||fila-1<0)&&atackBoard[columna+1][fila]==1&&atackBoard[columna][fila-1]==1&&atackBoard[columna+1][fila-1]==1){
                    return true;
                }
                else if(!(columna-1<0||fila-1<0)&&atackBoard[columna-1][fila]==1&&atackBoard[columna][fila-1]==1&&atackBoard[columna-1][fila-1]==1){
                    return true;
                }
                return false;   
            case 3:
                return true;
            case 4,5,6,7,8,9,10:
                if(!(columna+1>BOARD_SIZE)&&atackBoard[columna+1][fila]==1){
                    return true;
                }
                else if(!(columna-1<0)&&atackBoard[columna-1][fila]==1){
                    return true;
                }
                return false;      
        }
        return false;
}
public void atacarAlAzar() throws IOException{
    Random random=new Random(); 
    String mensaje;
    String bitacoraEnemiga;
    int fila = random.nextInt(BOARD_SIZE);
    int columna = random.nextInt(BOARD_SIZE);
    while(atackBoard[columna][fila]!=0){
        fila = random.nextInt(BOARD_SIZE);
        columna = random.nextInt(BOARD_SIZE);
    }
    if(logicBoard[columna][fila]!=0){
        buttonArray[columna][fila].setIcon(alpha);
        mensaje= "ATAQUE: Cañon a "+rival+" ha acertado en ("+fila+","+columna+")"+"\n";
        bitacoraEnemiga="RECIBIDO: ataque recibido en un edificio en las coordenadas ("+fila+","+columna+") por parte de "+sea.nomCliente+"\n";
    }   
    else{
       mensaje= "ATAQUE: Cañon a "+rival+" fallido en ("+fila+","+columna+")"+"\n";
       bitacoraEnemiga="RECIBIDO: el ataque enemigo golpeo en el oceano en las coordenadas ("+fila+","+columna+") por parte de "+sea.nomCliente+"\n";
       buttonArray[columna][fila].setIcon(beta); 
    }
    atackBoard[columna][fila]=1;
    //isDestroyed(columna, fila,logicBoard[columna][fila]);
    sea.player.salida.writeInt(10);
    sea.player.salida.writeInt(id);
    sea.player.salida.writeInt(columna);
    sea.player.salida.writeInt(fila);
    sea.txaBitacora.append(mensaje);
    sea.player.salida.writeInt(11);
    // le envia el mensaje
    sea.player.salida.writeUTF(bitacoraEnemiga);  
    sea.player.salida.writeInt(id);
    
    
}
public void clickSobreTablero(java.awt.event.MouseEvent evt) throws IOException
    {
        if(ghost){
            JButton bottonPress=(JButton)evt.getComponent();
            String boton = bottonPress.getActionCommand();
            int columna, fila,posicion;
            posicion=boton.indexOf(",");
            columna=Integer.parseInt(boton.substring(0,posicion));
            fila=Integer.parseInt(boton.substring(1+posicion));
            new GhostShip(columna,fila,buttonArray, logicBoard,ghostBoard);
            this.dispose();
            ghost=false;
        }
        else if(atack!=true){
            return;
        }
        else{
            JButton bottonPress=(JButton)evt.getComponent();
            String boton = bottonPress.getActionCommand();
            int columna, fila,posicion;
            posicion=boton.indexOf(",");
            columna=Integer.parseInt(boton.substring(0,posicion));
            fila=Integer.parseInt(boton.substring(1+posicion));
            if(atackBoard[columna][fila]==1){
                JOptionPane.showMessageDialog(null, "ya has atacado este posicion");
                return;
            }
            String mensaje;
            String bitacoraEnemiga;
            switch(objective.getType()){
                case 0:
                    if(isAvailable==true){
                        //objective.setNum(contador);
                        //graph.agregarVertice(objective);
                        if(logicBoard[columna][fila]!=0){
                            buttonArray[columna][fila].setIcon(alpha);
                            mensaje= "ATAQUE: Cañon a "+rival+" ha acertado en ("+fila+","+columna+")"+"\n";
                            bitacoraEnemiga="RECIBIDO: ataque recibido en un edificio en las coordenadas ("+fila+","+columna+") por parte de "+sea.nomCliente+"\n";
                        }   
                        else{
                           mensaje= "ATAQUE: Cañon a "+rival+" fallido en ("+fila+","+columna+")"+"\n";
                           bitacoraEnemiga="RECIBIDO: el ataque enemigo golpeo en el oceano en las coordenadas ("+fila+","+columna+") por parte de "+sea.nomCliente+"\n";
                           buttonArray[columna][fila].setIcon(beta); 
                        }
                        atackBoard[columna][fila]=1;
                        //isDestroyed(columna, fila,logicBoard[columna][fila]);
                        sea.player.salida.writeInt(10);
                        sea.player.salida.writeInt(id);
                        sea.player.salida.writeInt(columna);
                        sea.player.salida.writeInt(fila);
                        sea.txaBitacora.append(mensaje);
                        sea.player.salida.writeInt(11);
                        // le envia el mensaje
                        sea.player.salida.writeUTF(bitacoraEnemiga);  
                        sea.player.salida.writeInt(id);
                        atack=false;
                        sea.updateTurno();
                    }
                    break;
                case 1:
                   if(isAvailable==true){
                        //objective.setNum(contador);
                        //graph.agregarVertice(objective);
                        if(logicBoard[columna][fila]!=0){
                            buttonArray[columna][fila].setIcon(alpha);
                            mensaje= "ATAQUE: Cañon multiple a "+rival+" ha acertado en ("+fila+","+columna+")"+"\n";
                            bitacoraEnemiga="RECIBIDO: ataque recibido en un edificio en las coordenadas ("+fila+","+columna+") por parte de "+sea.nomCliente+"\n";
                            sea.player.salida.writeInt(10);
                            sea.player.salida.writeInt(id);
                            sea.player.salida.writeInt(columna);
                            sea.player.salida.writeInt(fila);
                            sea.txaBitacora.append(mensaje);
                            sea.player.salida.writeInt(11);
                            // le envia el mensaje
                            sea.player.salida.writeUTF(bitacoraEnemiga);  
                            sea.player.salida.writeInt(id);
                            for (int i = 0; i < 4; i++) {
                                atacarAlAzar();
                            }
                        }   
                        else{
                           mensaje= "ATAQUE: Cañon multiple a "+rival+" fallido en ("+fila+","+columna+")"+"\n";
                           bitacoraEnemiga="RECIBIDO: el ataque enemigo golpeo en el oceano en las coordenadas ("+fila+","+columna+") por parte de "+sea.nomCliente+"\n";
                           sea.player.salida.writeInt(10);
                           sea.player.salida.writeInt(id);
                           sea.player.salida.writeInt(columna);
                           sea.player.salida.writeInt(fila);
                           sea.txaBitacora.append(mensaje);
                           sea.player.salida.writeInt(11);
                           // le envia el mensaje
                           sea.player.salida.writeUTF(bitacoraEnemiga);  
                           sea.player.salida.writeInt(id);
                           buttonArray[columna][fila].setIcon(beta); 
                        }
                        atackBoard[columna][fila]=1;
                        //isDestroyed(columna, fila,logicBoard[columna][fila]);                       
                        atack=false;
                        sea.updateTurno();
                    }
                    break;
                case 2:
                    if(isAvailable==true){
                        //objective.setNum(contador);
                        //graph.agregarVertice(objective);
                        if(logicBoard[columna][fila]!=0){
                            buttonArray[columna][fila].setIcon(alpha);
                            mensaje= "ATAQUE: Cañon a "+rival+" ha acertado en ("+fila+","+columna+")"+"\n";
                            bitacoraEnemiga="RECIBIDO: ataque recibido en un edificio en las coordenadas ("+fila+","+columna+") por parte de "+sea.nomCliente+"\n";
                        }   
                        else{
                           mensaje= "ATAQUE: Cañon a "+rival+" fallido en ("+fila+","+columna+")"+"\n";
                           bitacoraEnemiga="RECIBIDO: el ataque enemigo golpeo en el oceano en las coordenadas ("+fila+","+columna+") por parte de "+sea.nomCliente+"\n";
                           buttonArray[columna][fila].setIcon(beta); 
                        }
                        atackBoard[columna][fila]=1;
                        //isDestroyed(columna, fila,logicBoard[columna][fila]);
                        sea.player.salida.writeInt(10);
                        sea.player.salida.writeInt(id);
                        sea.player.salida.writeInt(columna);
                        sea.player.salida.writeInt(fila);
                        sea.txaBitacora.append(mensaje);
                        sea.player.salida.writeInt(11);
                        // le envia el mensaje
                        sea.player.salida.writeUTF(bitacoraEnemiga);  
                        sea.player.salida.writeInt(id);
                    }
                    disparos--;
                    if(disparos==0){
                        atack=false;                       
                        sea.updateTurno();   
                    }   
                break;
                case 3:
                   if(isAvailable==true){
                        //objective.setNum(contador);
                        //graph.agregarVertice(objective);
                        if(new Random().nextInt(2)==0){
                            ///si la primera disparos acierta
                            if(logicBoard[columna][fila]!=0){
                                buttonArray[columna][fila].setIcon(alpha);
                                ///si ambas bombas aciertan
                                if(columna+1<BOARD_SIZE&&atackBoard[columna+1][fila]==0&&logicBoard[columna+1][fila]!=0){
                                    buttonArray[columna+1][fila].setIcon(alpha);
                                    mensaje= "ATAQUE: Bomba a "+rival+" ha acertado en ("+fila+","+columna+"), "+"("+fila+","+(columna+1)+")"+"\n";
                                    bitacoraEnemiga="RECIBIDO: ataque recibido en un edificio en las coordenadas ("+fila+","+columna+"), "+"("+fila+","+(columna+1)+") por parte de "+sea.nomCliente+"\n";
                                    atackBoard[columna][fila]=1;
                                    atackBoard[columna+1][fila]=1;
                                    sea.player.salida.writeInt(10);
                                    sea.player.salida.writeInt(id);
                                    sea.player.salida.writeInt(columna);
                                    sea.player.salida.writeInt(fila);
                                    sea.player.salida.writeInt(10);
                                    sea.player.salida.writeInt(id);
                                    sea.player.salida.writeInt(columna+1);
                                    sea.player.salida.writeInt(fila);
                                    // le envia el mensaje
                                    sea.txaBitacora.append(mensaje);
                                    sea.player.salida.writeInt(11);
                                    sea.player.salida.writeUTF(bitacoraEnemiga);  
                                    sea.player.salida.writeInt(id);
                                }
                                ///si la primera disparos acierta pero la segunda falla
                                else{            
                                    mensaje= "ATAQUE: Bomba a "+rival+" ha acertado en ("+fila+","+columna+"), pero FALLO en: "+"("+fila+","+(columna+1)+")"+"\n";
                                    bitacoraEnemiga="RECIBIDO: ataque recibido en un edificio en las coordenadas ("+fila+","+columna+"), pero golpeo el oceano en las coordenadas: "+"("+fila+","+(columna+1)+") por parte de "+sea.nomCliente+"\n";
                                    ///si la primera disparos acierta y la segunda falla pero no se sale de los limites
                                    if(columna+1<BOARD_SIZE&&atackBoard[columna+1][fila]==0){
                                        buttonArray[columna+1][fila].setIcon(beta);
                                        atackBoard[columna][fila]=1;
                                        atackBoard[columna+1][fila]=1;
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna);
                                        sea.player.salida.writeInt(fila);
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna+1);
                                        sea.player.salida.writeInt(fila);
                                        // le envia el mensaje
                                        sea.txaBitacora.append(mensaje);
                                        sea.player.salida.writeInt(11);
                                        sea.player.salida.writeUTF(bitacoraEnemiga);  
                                        sea.player.salida.writeInt(id);
                                    }
                                    ///si la primera disparos acierta y la segunda falla pero si se sale de los limites
                                    else{
                                        atackBoard[columna][fila]=1;
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna);
                                        sea.player.salida.writeInt(fila);
                                        // le envia el mensaje
                                        sea.txaBitacora.append(mensaje);
                                        sea.player.salida.writeInt(11);
                                        sea.player.salida.writeUTF(bitacoraEnemiga);  
                                        sea.player.salida.writeInt(id); 
                                    }                                    
                                }                               
                            }   
                            ///si la primera disparos falla
                            else{
                               buttonArray[columna][fila].setIcon(beta); 
                                ///si la primera disparos falla pero la segunda acierta
                                if(columna+1<BOARD_SIZE&&atackBoard[columna+1][fila]==0&&logicBoard[columna+1][fila]!=0){
                                    buttonArray[columna+1][fila].setIcon(alpha); 
                                    mensaje= "ATAQUE: Bomba a "+rival+" fallido en ("+fila+","+columna+"), pero ACERTO en: "+"("+fila+","+(columna+1)+")"+"\n";
                                    bitacoraEnemiga="RECIBIDO: el ataque enemigo golpeo en el oceano en las coordenadas ("+fila+","+columna+"), pero acerto en las coordenadas: "+"("+fila+","+(columna+1)+") por parte de "+sea.nomCliente+"\n";
                                    atackBoard[columna][fila]=1;
                                    atackBoard[columna+1][fila]=1;
                                    sea.player.salida.writeInt(10);
                                    sea.player.salida.writeInt(id);
                                    sea.player.salida.writeInt(columna);
                                    sea.player.salida.writeInt(fila);
                                    sea.player.salida.writeInt(10);
                                    sea.player.salida.writeInt(id);
                                    sea.player.salida.writeInt(columna+1);
                                    sea.player.salida.writeInt(fila);
                                    // le envia el mensaje
                                    sea.txaBitacora.append(mensaje);
                                    sea.player.salida.writeInt(11);
                                    sea.player.salida.writeUTF(bitacoraEnemiga);  
                                    sea.player.salida.writeInt(id);
                                }
                                ///si ambas bombas fallan
                                else{            
                                    mensaje= "ATAQUE: Bomba a "+rival+" fallido en ("+fila+","+columna+"), "+"("+fila+","+(columna+1)+")"+"\n";
                                    bitacoraEnemiga="RECIBIDO: el ataque enemigo golpeo en el oceano en las coordenadas ("+fila+","+columna+"), "+"("+fila+","+(columna+1)+") por parte de "+sea.nomCliente+"\n";
                                    ///si ambas bombas fallan pero la segunda esta dentro del tablero
                                    if(columna+1<BOARD_SIZE&&atackBoard[columna+1][fila]==0){
                                        buttonArray[columna+1][fila].setIcon(beta); 
                                        atackBoard[columna][fila]=1;
                                        atackBoard[columna+1][fila]=1;
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna);
                                        sea.player.salida.writeInt(fila);
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna+1);
                                        sea.player.salida.writeInt(fila);
                                        // le envia el mensaje
                                        sea.txaBitacora.append(mensaje);
                                        sea.player.salida.writeInt(11);
                                        sea.player.salida.writeUTF(bitacoraEnemiga);  
                                        sea.player.salida.writeInt(id);
                                    }
                                    ///si ambas bombas fallan pero la segunda esta fuera del tablero
                                    else{
                                        atackBoard[columna][fila]=1; 
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna);
                                        sea.player.salida.writeInt(fila);
                                        // le envia el mensaje
                                        sea.txaBitacora.append(mensaje);
                                        sea.player.salida.writeInt(11);
                                        sea.player.salida.writeUTF(bitacoraEnemiga);  
                                        sea.player.salida.writeInt(id); 
                                    }
                                }                          
                            }                                                       
                        }
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        else{
                            if(logicBoard[columna][fila]!=0){
                                buttonArray[columna][fila].setIcon(alpha);
                                if(fila+1<BOARD_SIZE&&atackBoard[columna][fila+1]==0&&logicBoard[columna][fila+1]!=0){
                                    buttonArray[columna][fila+1].setIcon(alpha);
                                    mensaje= "ATAQUE: Bomba a "+rival+" ha acertado en ("+fila+","+columna+"), "+"("+(fila+1)+","+columna+")"+"\n";
                                    bitacoraEnemiga="RECIBIDO: ataque recibido en un edificio en las coordenadas ("+fila+","+columna+"), "+"("+(fila+1)+","+columna+") por parte de "+sea.nomCliente+"\n";
                                    atackBoard[columna][fila]=1;
                                    atackBoard[columna][fila+1]=1;
                                    sea.player.salida.writeInt(10);
                                    sea.player.salida.writeInt(id);
                                    sea.player.salida.writeInt(columna);
                                    sea.player.salida.writeInt(fila);
                                    sea.player.salida.writeInt(10);
                                    sea.player.salida.writeInt(id);
                                    sea.player.salida.writeInt(columna);
                                    sea.player.salida.writeInt(fila+1);
                                    // le envia el mensaje
                                    sea.txaBitacora.append(mensaje);
                                    sea.player.salida.writeInt(11);
                                    sea.player.salida.writeUTF(bitacoraEnemiga);  
                                    sea.player.salida.writeInt(id);
                                }
                                else{            
                                    mensaje= "ATAQUE: Bomba a "+rival+" ha acertado en ("+fila+","+columna+"), pero FALLO en: "+"("+(fila+1)+","+columna+")"+"\n";
                                    bitacoraEnemiga="RECIBIDO: ataque recibido en un edificio en las coordenadas ("+fila+","+columna+"), pero golpeo el oceano en las coordenadas: "+"("+(fila+1)+","+columna+") por parte de "+sea.nomCliente+"\n";
                                    if(fila+1<BOARD_SIZE&&atackBoard[columna][fila+1]==0){
                                        buttonArray[columna][fila+1].setIcon(beta);
                                        atackBoard[columna][fila]=1;
                                        atackBoard[columna][fila+1]=1;
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna);
                                        sea.player.salida.writeInt(fila);
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna);
                                        sea.player.salida.writeInt(fila+1);
                                        // le envia el mensaje
                                        sea.txaBitacora.append(mensaje);
                                        sea.player.salida.writeInt(11);
                                        sea.player.salida.writeUTF(bitacoraEnemiga);  
                                        sea.player.salida.writeInt(id);
                                    }
                                    else{
                                        atackBoard[columna][fila]=1;
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna);
                                        sea.player.salida.writeInt(fila);
                                        // le envia el mensaje
                                        sea.txaBitacora.append(mensaje);
                                        sea.player.salida.writeInt(11);
                                        sea.player.salida.writeUTF(bitacoraEnemiga);  
                                        sea.player.salida.writeInt(id); 
                                    }                                    
                                }                               
                            }   
                            else{
                               buttonArray[columna][fila].setIcon(beta); 
                                if(fila+1<BOARD_SIZE&&atackBoard[columna][fila+1]==0&&logicBoard[columna][fila+1]!=0){
                                    buttonArray[columna][fila+1].setIcon(alpha); 
                                    mensaje= "ATAQUE: Bomba a "+rival+" fallido en ("+fila+","+columna+"), pero ACERTO en: "+"("+(fila+1)+","+columna+")"+"\n";
                                    bitacoraEnemiga="RECIBIDO: el ataque enemigo golpeo en el oceano en las coordenadas ("+fila+","+columna+"), pero acerto en las coordenadas: "+"("+(fila+1)+","+columna+") por parte de "+sea.nomCliente+"\n";
                                    atackBoard[columna][fila]=1;
                                    atackBoard[columna][fila+1]=1;
                                    sea.player.salida.writeInt(10);
                                    sea.player.salida.writeInt(id);
                                    sea.player.salida.writeInt(columna);
                                    sea.player.salida.writeInt(fila);
                                    sea.player.salida.writeInt(10);
                                    sea.player.salida.writeInt(id);
                                    sea.player.salida.writeInt(columna);
                                    sea.player.salida.writeInt(fila+1);
                                    // le envia el mensaje
                                    sea.txaBitacora.append(mensaje);
                                    sea.player.salida.writeInt(11);
                                    sea.player.salida.writeUTF(bitacoraEnemiga);  
                                    sea.player.salida.writeInt(id);
                                }
                                else{            
                                    mensaje= "ATAQUE: Bomba a "+rival+" fallido en ("+fila+","+columna+"), "+"("+(fila+1)+","+columna+")"+"\n";
                                    bitacoraEnemiga="RECIBIDO: el ataque enemigo golpeo en el oceano en las coordenadas ("+fila+","+columna+"), "+"("+(fila+1)+","+columna+") por parte de "+sea.nomCliente+"\n";
                                    if(fila+1<BOARD_SIZE&&atackBoard[columna][fila+1]==0){
                                        buttonArray[columna][fila+1].setIcon(beta); 
                                        atackBoard[columna][fila]=1;
                                        atackBoard[columna][fila+1]=1;
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna);
                                        sea.player.salida.writeInt(fila);
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna);
                                        sea.player.salida.writeInt(fila+1);
                                        // le envia el mensaje
                                        sea.txaBitacora.append(mensaje);
                                        sea.player.salida.writeInt(11);
                                        sea.player.salida.writeUTF(bitacoraEnemiga);  
                                        sea.player.salida.writeInt(id);
                                    }
                                    else{
                                        atackBoard[columna][fila]=1;
                                        sea.player.salida.writeInt(10);
                                        sea.player.salida.writeInt(id);
                                        sea.player.salida.writeInt(columna);
                                        sea.player.salida.writeInt(fila);
                                        // le envia el mensaje
                                        sea.txaBitacora.append(mensaje);
                                        sea.player.salida.writeInt(11);
                                        sea.player.salida.writeUTF(bitacoraEnemiga);  
                                        sea.player.salida.writeInt(id); 
                                    }
                                }                          
                            } 
                        }    
                        disparos--;
                        if(disparos==0){
                            atack=false;                       
                            sea.updateTurno();   
                        }                       
                    }
                    break;         
            }
        }
        
//        if(graph.buscarConectores()!=null){
//            System.out.println("holaaa");
//            eleccion=new JComboBox();
//            eleccion.addItem("hola");
//            eleccion.addItem("holaa");
//            eleccion.addItem("holaaa");
//            eleccion.setBounds(50, 50,90,20);   
//            SeaPanel.add(eleccion);
//            eleccion.setVisible(true);
//        }        
    }
    
    public void MouseOuttheButton(java.awt.event.MouseEvent evt)
    {
        if(atack==false){
            return;
        }
        else{
            JButton bottonPress=(JButton)evt.getComponent();
            String boton = bottonPress.getActionCommand();
            int columna, fila,posicion;
            posicion=boton.indexOf(",");
            columna=Integer.parseInt(boton.substring(0,posicion));
            fila=Integer.parseInt(boton.substring(1+posicion));
            if(ghostBoard[columna][fila]!=0&&atackBoard[columna][fila]==0){
                switch(logicBoard[columna][fila]){
                    case 1:
                        buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("whirlpool.png")));
                        break;
                    case 2:
                        buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("energyB.png")));
                        break;
                    case 3:
                        buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("conectorB.png")));
                        break;
                    case 4:
                        buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("merchantB.png")));
                        break;
                    case 5:
                        buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("IronMineB.png")));
                        break;
                    case 6:
                        buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("WitchTempleB.png")));
                        break;
                    case 7:
                        buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                        break;
                    case 8:
                        buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                        break;
                    case 9:
                        buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                        break;
                    case 10:
                        buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("merchanB.jpg")));
                        break;
                }
            }
            else{
                erasePlace(objective.getType(), columna, fila);
            }         
        }            
    }
    public void erasePlace(int bounds,int columna,int fila){
        switch(bounds){
            case 0:
                erase1x1(columna, fila);
                break;
            case 1:
                erase1x1(columna, fila);
                break;
            case 2:
                erase1x1(columna, fila);
                break;
            case 3:
                erase1x1(columna, fila);
                break;
        }
    }
    public void erase1x1(int columna,int fila){
        //buttonArray[columna][fila].setIcon(null);
        if(atackBoard[columna][fila]==0)
            buttonArray[columna][fila].setIcon(null);
        else
            chooseImage(columna, fila);
    }
    public void erase1x2(int columna,int fila){
        if(columna+1>=BOARD_SIZE&&fila+1>=BOARD_SIZE){
            buttonArray[columna][fila].setIcon(null);
        }
        else if(columna+1>=BOARD_SIZE){
            buttonArray[columna][fila].setIcon(null);
            //buttonArray[columna][fila+1].setIcon(null);
        }
        else{
            if( logicBoard[columna][fila]==0)
                buttonArray[columna][fila].setIcon(null);
            else
                chooseImage(columna, fila);
            if( logicBoard[columna+1][fila]==0)
                buttonArray[columna+1][fila].setIcon(null);
            else
                chooseImage(columna+1, fila);
        }
    }
    public void erase2x2(int columna,int fila){
        if(columna+1>=BOARD_SIZE&&fila+1>=BOARD_SIZE){
            buttonArray[columna][fila].setIcon(null);
        }
        else if(columna+1>=BOARD_SIZE){
            buttonArray[columna][fila].setIcon(null);
            buttonArray[columna][fila+1].setIcon(null);
        }
        else if(fila+1>=BOARD_SIZE){
            buttonArray[columna][fila].setIcon(null);
            buttonArray[columna+1][fila].setIcon(null);
        }
        else{
            if( logicBoard[columna][fila]==0)
                buttonArray[columna][fila].setIcon(null);
            else
                chooseImage(columna, fila);
            if( logicBoard[columna+1][fila+1]==0)
                buttonArray[columna+1][fila+1].setIcon(null);
            else
                chooseImage(columna+1, fila+1);

            if( logicBoard[columna][fila+1]==0)
                buttonArray[columna][fila+1].setIcon(null);
            else
                chooseImage(columna, fila+1);

            if( logicBoard[columna+1][fila]==0)
                buttonArray[columna+1][fila].setIcon(null);
            else
                chooseImage(columna+1, fila);
        } 
    }
    public void MouseOntheButton(java.awt.event.MouseEvent evt)            
    {      
        if(atack==false){
            return;
        }
        else{
            isAvailable=true;
            JButton bottonPress=(JButton)evt.getComponent();
            String boton = bottonPress.getActionCommand();
            int columna, fila,posicion;
            posicion=boton.indexOf(",");
            columna=Integer.parseInt(boton.substring(0,posicion));
            fila=Integer.parseInt(boton.substring(1+posicion));
            choosePlace(objective.getType(), columna, fila);
        }             
    }
    ////////////////////////////////////////////////////////////////////////
    public void choosePlace(int bounds,int columna,int fila){
        switch(bounds){
            case 0:
                set1x1(columna, fila);
                break;
            case 1:
                set1x1(columna, fila);
                break;
            case 2:
                set1x1(columna, fila);
                break;
            case 3:
                set1x1(columna, fila);
                break;
        }
    }
    public void set1x1(int columna,int fila){
        buttonArray[columna][fila].setIcon(beta);
//        if( logicBoard[columna][fila]==0)
//            buttonArray[columna][fila].setIcon(beta);
//        else{
//            buttonArray[columna][fila].setIcon(alpha);
//            isAvailable=false;
//        }
    }
    public void set1x2(int columna,int fila){
        if(columna+1>=BOARD_SIZE){
            buttonArray[columna][fila].setIcon(alpha);
            //buttonArray[columna][fila+1].setIcon(alpha);
            isAvailable=false;
        }
        else{
            if( logicBoard[columna][fila]==0)
                buttonArray[columna][fila].setIcon(beta);
            else{
                buttonArray[columna][fila].setIcon(alpha);
                isAvailable=false;
            }
            if( logicBoard[columna+1][fila]==0)
                buttonArray[columna+1][fila].setIcon(beta);
            else{
                buttonArray[columna+1][fila].setIcon(alpha);
                isAvailable=false;
            }
        }
    }
    public void set2x2(int columna,int fila){
        if(columna+1>=BOARD_SIZE&&fila+1>=BOARD_SIZE){
            buttonArray[columna][fila].setIcon(alpha);
            isAvailable=false;
        }
        else if(columna+1>=BOARD_SIZE){
            buttonArray[columna][fila].setIcon(alpha);
            buttonArray[columna][fila+1].setIcon(alpha);
            isAvailable=false;
        }
        else if(fila+1>=BOARD_SIZE){
            buttonArray[columna][fila].setIcon(alpha);
            buttonArray[columna+1][fila].setIcon(alpha);
            isAvailable=false;
        }
        else{
            if( logicBoard[columna][fila]==0)
                buttonArray[columna][fila].setIcon(beta);
            else{
                buttonArray[columna][fila].setIcon(alpha);
                isAvailable=false;
            }
            if( logicBoard[columna+1][fila+1]==0)
                buttonArray[columna+1][fila+1].setIcon(beta);
            else{
                buttonArray[columna+1][fila+1].setIcon(alpha);
                isAvailable=false;
            }
            if( logicBoard[columna][fila+1]==0)
                buttonArray[columna][fila+1].setIcon(beta);
            else{
                buttonArray[columna][fila+1].setIcon(alpha);
                isAvailable=false;
            }
            if( logicBoard[columna+1][fila]==0)
                buttonArray[columna+1][fila].setIcon(beta);
            else{
                buttonArray[columna+1][fila].setIcon(alpha);
                isAvailable=false;
            }
        } 
    }
    public void chooseImage(int columna,int fila){
        if(logicBoard[columna][fila]!=0){
            buttonArray[columna][fila].setIcon(alpha);
        }   
        else{
           buttonArray[columna][fila].setIcon(beta); 
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SeaPanel = new javax.swing.JPanel();
        btnAtackCanyon = new javax.swing.JButton();
        btnAtackMultipleCanyon = new javax.swing.JButton();
        btnAtackBombCanyon = new javax.swing.JButton();
        btnAtackRedBeardCanyon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnAtackCanyon.setText("Atacar canon");
        btnAtackCanyon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtackCanyonActionPerformed(evt);
            }
        });

        btnAtackMultipleCanyon.setText("Atacar canon Multiple");
        btnAtackMultipleCanyon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtackMultipleCanyonActionPerformed(evt);
            }
        });

        btnAtackBombCanyon.setText("Atacar canon Bomba");
        btnAtackBombCanyon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtackBombCanyonActionPerformed(evt);
            }
        });

        btnAtackRedBeardCanyon.setText("Atacar canon Barba roja");
        btnAtackRedBeardCanyon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtackRedBeardCanyonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SeaPanelLayout = new javax.swing.GroupLayout(SeaPanel);
        SeaPanel.setLayout(SeaPanelLayout);
        SeaPanelLayout.setHorizontalGroup(
            SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeaPanelLayout.createSequentialGroup()
                .addContainerGap(518, Short.MAX_VALUE)
                .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAtackMultipleCanyon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAtackCanyon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAtackBombCanyon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAtackRedBeardCanyon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(101, 101, 101))
        );
        SeaPanelLayout.setVerticalGroup(
            SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeaPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnAtackCanyon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAtackMultipleCanyon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAtackBombCanyon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAtackRedBeardCanyon)
                .addContainerGap(365, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SeaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SeaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtackCanyonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtackCanyonActionPerformed
        // TODO add your handling code here:
        if(id!=sea.turno){
            JOptionPane.showMessageDialog(null, "No estas en tu turno para atacar");
            return;
        }
        else if(sea.player.getGroup()[0].size()<=0){
            JOptionPane.showMessageDialog(null, "No cuentas con suficiente municion de cañon");
            return;
        }
        else{
           objective= sea.player.getGroup()[0].get(0);
            sea.player.getGroup()[0].remove(0);
            JOptionPane.showMessageDialog(null, "Dispara el cañon donde lo desees");
            atack=true; 
        }        
    }//GEN-LAST:event_btnAtackCanyonActionPerformed

    private void btnAtackMultipleCanyonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtackMultipleCanyonActionPerformed
        // TODO add your handling code here:
        if(id!=sea.turno){
            JOptionPane.showMessageDialog(null, "No estas en tu turno para atacar");
            return;
        }
        else if(sea.player.getGroup()[1].size()<=0){
            JOptionPane.showMessageDialog(null, "No cuentas con suficiente municion de cañon multiple");
            return;
        }
        else{
            objective= sea.player.getGroup()[1].get(0);
            sea.player.getGroup()[1].remove(0);
            JOptionPane.showMessageDialog(null, "Dispara el cañon multiple donde lo desees");
            atack=true; 
        }    
    }//GEN-LAST:event_btnAtackMultipleCanyonActionPerformed

    private void btnAtackBombCanyonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtackBombCanyonActionPerformed
        // TODO add your handling code here:
        if(id!=sea.turno){
            JOptionPane.showMessageDialog(null, "No estas en tu turno para atacar");
            return;
        }
        else if(sea.player.getGroup()[3].size()<=0){
            JOptionPane.showMessageDialog(null, "No cuentas con suficiente municion de cañon bomba");
            return;
        }
        else{
            objective= sea.player.getGroup()[3].get(0);
            sea.player.getGroup()[3].remove(0);
            JOptionPane.showMessageDialog(null, "Dispara el cañon bomba donde lo desees");
            disparos=3;
            atack=true; 
        }    
    }//GEN-LAST:event_btnAtackBombCanyonActionPerformed

    private void btnAtackRedBeardCanyonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtackRedBeardCanyonActionPerformed
        // TODO add your handling code here:
        if(id!=sea.turno){
            JOptionPane.showMessageDialog(null, "No estas en tu turno para atacar");
            return;
        }
        else if(sea.player.getGroup()[2].size()<=0){
            JOptionPane.showMessageDialog(null, "No cuentas con suficiente municion de cañon barba roja");
            return;
        }
        else{
            objective= sea.player.getGroup()[2].get(0);
            sea.player.getGroup()[2].remove(0);
            JOptionPane.showMessageDialog(null, "Dispara el cañon barba roja donde lo desees");
            disparos=10;
            atack=true; 
        }
    }//GEN-LAST:event_btnAtackRedBeardCanyonActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SeaPanel;
    private javax.swing.JButton btnAtackBombCanyon;
    private javax.swing.JButton btnAtackCanyon;
    private javax.swing.JButton btnAtackMultipleCanyon;
    private javax.swing.JButton btnAtackRedBeardCanyon;
    // End of variables declaration//GEN-END:variables
}

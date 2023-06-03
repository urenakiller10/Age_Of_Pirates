/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofpirates;

import ageofpirates.Factory.Conector;
import ageofpirates.Factory.EnergyFount;
import ageofpirates.Factory.Factory;
import ageofpirates.Factory.Store;
import ageofpirates.Factory.Weapons;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Sea extends javax.swing.JFrame {
    public static final int BUTTON_SIZE = 60;
    public static final int BOARD_SIZE = 20;
    public static final int PLAYER_HEIGH = 10;
    public static final int PLAYER_WIDTH = 20;
    protected int playerQty;
    protected Player player;
    protected Color colorArray[];
    public JButton[][] buttonArray= new JButton[BOARD_SIZE][BOARD_SIZE];
    private int[][] logicBoard=  new int[BOARD_SIZE][BOARD_SIZE];
    public Factory[][] factoryBoard=  new Factory[BOARD_SIZE][BOARD_SIZE];
    public JLabel fondo;
    protected final  ImageIcon beta= new ImageIcon(getClass().getResource("oci.jpg"));
    protected final  ImageIcon alpha= new ImageIcon(getClass().getResource("conector.png"));
    protected boolean colocacion=false;
    protected boolean connect=false;
    protected Factory objective; 
    protected boolean isAvailable=true;
    protected JComboBox eleccion;
    public int contador=0;
    protected Tutorial tutorial=new Tutorial();
    protected threadLabel labelThread=new threadLabel();
    protected String nomCliente;
    int numeroJugador;
    public ArrayList<int[][]> enemiesFactories;
    public ArrayList<Integer> idList;
    public ArrayList<String> nameList;
    public ArrayList<enemyBoard> boardEnemy=new ArrayList<enemyBoard>();
    private boolean isConected=false;
    private int[][] atackBoard=  new int[BOARD_SIZE][BOARD_SIZE];
    int turno=1;
    public int qtyShields=0;
    public GrafoMatriz grafoMatriz=new GrafoMatriz(BOARD_SIZE);

    public Sea() {
        nomCliente = JOptionPane.showInputDialog("Introducir su nombre:");
        initComponents();
        try {
            player= new Player(this);
            player.setNomCliente(nomCliente);
        } catch (IOException ex) {
            Logger.getLogger(Sea.class.getName()).log(Level.SEVERE, null, ex);
        }       
        this.fondo=new JLabel(new ImageIcon(getClass().getResource("oci.jpg")));
        

        
        
        
        
        initBoard();  
        setRemolinos();       
        tutorial.start();
        btnSend.setEnabled(false);
        labelThread.start();
        this.enemiesFactories=new ArrayList<>();
        //System.out.println(this.getBounds());
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
                
                //aclickSobreTablero(evt);ñade el listener al boton
                buttonArray[i][j].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                        
                    try {
                        clickSobreTablero(evt);
                    } catch (IOException ex) {
                        Logger.getLogger(Sea.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }     
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                        
                    MouseOntheButton(evt);

                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    MouseOuttheButton(evt);
                }
                });
 
                // en logico indica estado en disponible
                logicBoard[i][j]=0;
            }
        }
        this.SeaPanel.add(fondo);
        fondo.setBounds(0,0,871,607);
    }



public class Tutorial extends Thread{
    public boolean isRunning = true, isPaused=false;
    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }
    public void run(){ 
        while(isRunning){
            try {
                JOptionPane.showMessageDialog(null, "Primero se construirá una Fuente de Energía");
                objective= new  EnergyFount();
                JOptionPane.showMessageDialog(null, "Coloca la Fuente donde desee");
                colocacion=true;
                while(colocacion==true){
                    sleep(10);
                }
                JOptionPane.showMessageDialog(null, "Ahora se construirá un conector");
                objective= new Conector();
                JOptionPane.showMessageDialog(null, "Coloca el conector donde desee");
                colocacion=true;
                while(colocacion==true){
                    sleep(10);
                }
                grafoMatriz.agregarArista(grafoMatriz.vertices[0], grafoMatriz.vertices[1]);
                JOptionPane.showMessageDialog(null, "Por último se coloca el mercado");
                objective= new Store();
                JOptionPane.showMessageDialog(null, "Coloca el mercado donde desee");
                colocacion=true;
                while(colocacion==true){
                    sleep(10);
                }
                grafoMatriz.agregarArista(grafoMatriz.vertices[1], grafoMatriz.vertices[2]);
                //graph.imprimir();
                isRunning=false;
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
public class threadLabel extends Thread{
    public boolean isRunning = true, isPaused=false;
    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }
    public void run(){ 
        while(isRunning){
            try {
                lblQuantityCanyon.setText("Total de cañones: "+player.getGroup()[0].size());
                lblQuantityMultipleCanyon.setText("Total de cañones multiples: "+player.getGroup()[1].size());
                lblQuantityRedBeardCanyon.setText("Total de cañones barba roja: "+player.getGroup()[2].size());
                lblQuantityBombCanyon.setText("Total de cañones bomba: "+player.getGroup()[3].size());
                lblMoney.setText("Total de Dinero: "+player.getMoney());
                lbliron.setText("Total de Hierro: "+player.getIron());
                while(isPaused){
                    sleep(10);
                }
            }catch (InterruptedException ex) {   
            } 
        }
    }
}
public void atacado(int fila, int columna){
    atackBoard[columna][fila]=1;
    if(isDestroyed(columna, fila, logicBoard[columna][fila])==true){
        System.out.println("Me atacaron una fabrica con el numero: "+factoryBoard[columna][fila].getNum());
        System.out.println("Me atacaron una: "+factoryBoard[columna][fila].getName());
        if(logicBoard[columna][fila]==3){
            Vertice vertice=grafoMatriz.buscarFactoryFac(factoryBoard[columna][fila].getNum());
        }
        grafoMatriz.eliminar(grafoMatriz.buscarFactoryFac(factoryBoard[columna][fila].getNum()));       
    }
}
protected void setRemolinos(){
    Random random=new Random();    
    for(int i=0;i<2;i++)
        {
            int fila = random.nextInt(BOARD_SIZE);
            int columna = random.nextInt(BOARD_SIZE);
            while(logicBoard[fila][columna]!=0){
                fila = random.nextInt(BOARD_SIZE);
                columna = random.nextInt(BOARD_SIZE);
            }
            logicBoard[columna][fila]=1;
            buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("whirlpool.jpg")));
        }    
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SeaPanel = new javax.swing.JPanel();
        btnTienda = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        txfSend = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaMensajes = new javax.swing.JTextArea();
        lblMoney = new javax.swing.JLabel();
        lbliron = new javax.swing.JLabel();
        btnCreateCanyon = new javax.swing.JButton();
        lblQuantityCanyon = new javax.swing.JLabel();
        btnCreateMultipleCanyon = new javax.swing.JButton();
        lblQuantityMultipleCanyon = new javax.swing.JLabel();
        btnCreateRedBeardCanyon = new javax.swing.JButton();
        lblQuantityRedBeardCanyon = new javax.swing.JLabel();
        btnCreateBombCanyon = new javax.swing.JButton();
        lblQuantityBombCanyon = new javax.swing.JLabel();
        btnEnemy1 = new javax.swing.JButton();
        btnEnemy2 = new javax.swing.JButton();
        btnEnemy3 = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaBitacora = new javax.swing.JTextArea();
        lblTurno = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnTienda.setBackground(new java.awt.Color(255, 255, 51));
        btnTienda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTienda.setText("Tienda");
        btnTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiendaActionPerformed(evt);
            }
        });

        btnIniciar.setBackground(new java.awt.Color(153, 255, 153));
        btnIniciar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnSend.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSend.setText("Enviar");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txaMensajes.setColumns(20);
        txaMensajes.setRows(5);
        jScrollPane1.setViewportView(txaMensajes);

        lblMoney.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMoney.setForeground(new java.awt.Color(51, 255, 0));
        lblMoney.setText("jLabel1");

        lbliron.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbliron.setForeground(new java.awt.Color(51, 255, 0));
        lbliron.setText("jLabel2");

        btnCreateCanyon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateCanyon.setText("Crear cañón(500 kg)");
        btnCreateCanyon.setActionCommand("Crear cañón");
        btnCreateCanyon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateCanyonActionPerformed(evt);
            }
        });

        lblQuantityCanyon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuantityCanyon.setForeground(new java.awt.Color(242, 242, 242));
        lblQuantityCanyon.setText("jLabel1");

        btnCreateMultipleCanyon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateMultipleCanyon.setText("Crear cañón múltiple (1000 kg)");
        btnCreateMultipleCanyon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateMultipleCanyonActionPerformed(evt);
            }
        });

        lblQuantityMultipleCanyon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuantityMultipleCanyon.setForeground(new java.awt.Color(242, 242, 242));
        lblQuantityMultipleCanyon.setText("jLabel1");

        btnCreateRedBeardCanyon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateRedBeardCanyon.setText("Crear cañón \nBarba Roja (5000kg)");
        btnCreateRedBeardCanyon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateRedBeardCanyonActionPerformed(evt);
            }
        });

        lblQuantityRedBeardCanyon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuantityRedBeardCanyon.setForeground(new java.awt.Color(242, 242, 242));
        lblQuantityRedBeardCanyon.setText("jLabel1");

        btnCreateBombCanyon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateBombCanyon.setText("Crear cañón bomba (2000kg)");
        btnCreateBombCanyon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateBombCanyonActionPerformed(evt);
            }
        });

        lblQuantityBombCanyon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuantityBombCanyon.setForeground(new java.awt.Color(242, 242, 242));
        lblQuantityBombCanyon.setText("jLabel1");

        btnEnemy1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEnemy1.setText("player1");
        btnEnemy1.setEnabled(false);
        btnEnemy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnemy1ActionPerformed(evt);
            }
        });

        btnEnemy2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEnemy2.setText("player2");
        btnEnemy2.setEnabled(false);
        btnEnemy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnemy2ActionPerformed(evt);
            }
        });

        btnEnemy3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEnemy3.setText("player2");
        btnEnemy3.setActionCommand("player3");
        btnEnemy3.setEnabled(false);
        btnEnemy3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnemy3ActionPerformed(evt);
            }
        });

        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ageofpirates/Configuracion.png"))); // NOI18N
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });

        txaBitacora.setColumns(20);
        txaBitacora.setRows(5);
        jScrollPane2.setViewportView(txaBitacora);

        lblTurno.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTurno.setForeground(new java.awt.Color(242, 242, 242));
        lblTurno.setText("Turno: Jugador 1");

        javax.swing.GroupLayout SeaPanelLayout = new javax.swing.GroupLayout(SeaPanel);
        SeaPanel.setLayout(SeaPanelLayout);
        SeaPanelLayout.setHorizontalGroup(
            SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeaPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTienda, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(659, 659, 659))
            .addGroup(SeaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeaPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeaPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(200, 200, 200))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeaPanelLayout.createSequentialGroup()
                                .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(SeaPanelLayout.createSequentialGroup()
                                        .addComponent(lblTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(SeaPanelLayout.createSequentialGroup()
                                        .addComponent(lblMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbliron, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(SeaPanelLayout.createSequentialGroup()
                                        .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnEnemy2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnEnemy3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(23, 23, 23)
                                        .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnCreateCanyon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeaPanelLayout.createSequentialGroup()
                                                .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(btnCreateRedBeardCanyon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblQuantityMultipleCanyon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblQuantityRedBeardCanyon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnCreateBombCanyon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnCreateMultipleCanyon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(4, 4, 4))
                                            .addComponent(lblQuantityBombCanyon, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(SeaPanelLayout.createSequentialGroup()
                                        .addComponent(btnEnemy1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(lblQuantityCanyon, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(654, 654, 654))))
                    .addGroup(SeaPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SeaPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(487, 487, 487))
                            .addGroup(SeaPanelLayout.createSequentialGroup()
                                .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(SeaPanelLayout.createSequentialGroup()
                                        .addGap(79, 79, 79)
                                        .addComponent(txfSend, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(SeaPanelLayout.createSequentialGroup()
                                        .addGap(225, 225, 225)
                                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        SeaPanelLayout.setVerticalGroup(
            SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeaPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTurno))
                .addGap(18, 18, 18)
                .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoney)
                    .addComponent(lbliron))
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreateCanyon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantityCanyon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnemy1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreateMultipleCanyon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantityMultipleCanyon)
                    .addComponent(btnEnemy2))
                .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SeaPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCreateRedBeardCanyon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblQuantityRedBeardCanyon))
                    .addGroup(SeaPanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnEnemy3)))
                .addGap(6, 6, 6)
                .addComponent(btnCreateBombCanyon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblQuantityBombCanyon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SeaPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txfSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSend)
                        .addGap(9, 9, 9)
                        .addGroup(SeaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTienda)
                            .addComponent(btnIniciar))))
                .addGap(111, 111, 111))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SeaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(579, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SeaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiendaActionPerformed
        Market market=new Market(player,this);    
        market.setVisible(true);         
    }//GEN-LAST:event_btnTiendaActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        try {
            player.conexion();
            player.salida.writeInt(3);
            player.salidaObjetos.writeObject(logicBoard);
            btnSend.setEnabled(true);
            btnIniciar.setEnabled(false);
            isConected=true;
        } catch (IOException ex) {
            Logger.getLogger(Sea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        try {
            // se toma lo escrito
            String mensaje = txfSend.getText();
            // se muestra en el text area
            txaMensajes.append(player.nomCliente+"> "+ mensaje + "\n");
            // se limpia el textfield
            txfSend.setText("");

            // envia al server la opcion 4 para que le pase al enemigo
            // lo escrito
            player.salida.writeInt(4);
            // le envia el mensaje
            player.salida.writeUTF(player.nomCliente+"> "+mensaje+"\n");           
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnCreateCanyonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateCanyonActionPerformed
        if(grafoMatriz.buscarFactory(7)){
            System.out.println(player.getIron());
            if(player.getIron()>=500){
                //graph.agregarVertice(new Armory(7));
                Weapons weapons= new Weapons("canyon",500,1,0);
                player.getGroup()[0].add(weapons);
                player.setIron(player.getIron()-500);
                System.out.println("Me cree");
            }
            else{
                JOptionPane.showMessageDialog(null, "Insufiente material");
            }    
        }
        else{
            JOptionPane.showMessageDialog(null, "No existe la armería de cañones");
        }
    }//GEN-LAST:event_btnCreateCanyonActionPerformed

    private void btnCreateMultipleCanyonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateMultipleCanyonActionPerformed
        if(grafoMatriz.buscarFactory(8)){
            System.out.println(player.getIron());
            if(player.getIron()>=1000){
                //graph.agregarVertice(new Armory(7));
                Weapons weapons= new Weapons("MultipleCanyon",1000,1,1);
                player.getGroup()[1].add(weapons);
                player.setIron(player.getIron()-1000);
                System.out.println("Me cree");
            }
            else{
                JOptionPane.showMessageDialog(null, "Insufiente material");
            }    
        }
        else{
            JOptionPane.showMessageDialog(null, "No existe la armería de cañones");
        }
    }//GEN-LAST:event_btnCreateMultipleCanyonActionPerformed

    private void btnCreateRedBeardCanyonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateRedBeardCanyonActionPerformed
        if(grafoMatriz.buscarFactory(9)){
            System.out.println(player.getIron());
            if(player.getIron()>=5000){
                //graph.agregarVertice(new Armory(7));
                Weapons weapons= new Weapons("RedBeardCanyon",5000,10,2);
                player.getGroup()[2].add(weapons);
                player.setIron(player.getIron()-5000);
                System.out.println("Me cree");
            }
            else{
                JOptionPane.showMessageDialog(null, "Insufiente material");
            }    
        }
        else{
            JOptionPane.showMessageDialog(null, "No existe la armería de cañones");
        }
    }//GEN-LAST:event_btnCreateRedBeardCanyonActionPerformed

    private void btnCreateBombCanyonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateBombCanyonActionPerformed
        if(grafoMatriz.buscarFactory(10)){
            System.out.println(player.getIron());
            if(player.getIron()>=2000){
                //graph.agregarVertice(new Armory(7));
                Weapons weapons= new Weapons("BombCanyon",2000,3,3);
                player.getGroup()[3].add(weapons);
                player.setIron(player.getIron()-2000);
                System.out.println("Me cree");
            }
            else{
                JOptionPane.showMessageDialog(null, "Insufiente material");
            }    
        }
        else{
            JOptionPane.showMessageDialog(null, "No existe la armería de cañones");
        }
    }//GEN-LAST:event_btnCreateBombCanyonActionPerformed

    private void btnEnemy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnemy1ActionPerformed
        boardEnemy.get(0).setVisible(true);
    }//GEN-LAST:event_btnEnemy1ActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
       int newVelocidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva velocidad de producción de las minas:"));
       int newCantidad=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad de hierro que produce la mina:"));
       grafoMatriz.cambiarMinas(newVelocidad, newCantidad);
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnEnemy2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnemy2ActionPerformed
        // TODO add your handling code here:
        if(enemiesFactories.size()>1){
            boardEnemy.get(1).setVisible(true);
        }
    }//GEN-LAST:event_btnEnemy2ActionPerformed

    private void btnEnemy3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnemy3ActionPerformed
        // TODO add your handling code here:
        if(enemiesFactories.size()>2){
            boardEnemy.get(2).setVisible(true);
        }
    }//GEN-LAST:event_btnEnemy3ActionPerformed
    public void clickSobreTablero(java.awt.event.MouseEvent evt) throws IOException
    {
        if(colocacion!=true&&connect!=true){
            return;
        }
        else if(connect==true){
            if(isAvailable==true){
                JButton bottonPress=(JButton)evt.getComponent();
                String boton = bottonPress.getActionCommand();
                int columna, fila,posicion;
                posicion=boton.indexOf(",");
                columna=Integer.parseInt(boton.substring(0,posicion));
                fila=Integer.parseInt(boton.substring(1+posicion));       
                grafoMatriz.agregarArista(grafoMatriz.vertices[factoryBoard[columna][fila].getNum()], grafoMatriz.vertices[contador-1]);
                connect=false;
                //JOptionPane.showMessageDialog(null, "se ha conectado tu "+objective.getName()+" a un conector");
                chooseImage(columna, fila, logicBoard[columna][fila]);
            }
        }
        else{
            JButton bottonPress=(JButton)evt.getComponent();
            String boton = bottonPress.getActionCommand();
            int columna, fila,posicion;
            posicion=boton.indexOf(",");
            columna=Integer.parseInt(boton.substring(0,posicion));
            fila=Integer.parseInt(boton.substring(1+posicion)); 
            switch(objective.getBounds()){
                case "2x2":
                    if(isAvailable==true){
                        objective.setNum(contador);
                        grafoMatriz.agregarVertice(objective);
                        //graph.agregarVertice(objective);
                        factoryBoard[columna][fila]=objective;
                        factoryBoard[columna+1][fila+1]=objective;
                        factoryBoard[columna][fila+1]=objective;
                        factoryBoard[columna+1][fila]=objective;                        
                        logicBoard[columna][fila]=objective.getId();
                        logicBoard[columna+1][fila+1]=objective.getId();
                        logicBoard[columna][fila+1]=objective.getId();
                        logicBoard[columna+1][fila]=objective.getId(); 
                        chooseImage(columna, fila, logicBoard[columna][fila]);
                        chooseImage(columna+1, fila+1, logicBoard[columna+1][fila]); 
                        chooseImage(columna, fila+1, logicBoard[columna][fila]);
                        chooseImage(columna+1, fila, logicBoard[columna+1][fila]);                  
                        colocacion=false;
                        if(objective.getName().equals("Conector")){
                            grafoMatriz.agregarArista(grafoMatriz.vertices[0], grafoMatriz.vertices[contador]);
                        }
                        else if((!tutorial.isRunning)){
                            JOptionPane.showMessageDialog(null, "Ahora conecta tu "+objective.getName()+" a un conector");
                            connect=true;
                            if(isConected){
                                player.salida.writeInt(9);
                                player.salida.writeInt(fila);
                                player.salida.writeInt(columna);
                                player.salida.writeInt(objective.getId());
                                player.salida.writeInt(this.numeroJugador);
                                
                            }
                        }                          
                        contador++;
                        System.out.println(contador);
                    }
                    break;
                case "1x1":
                    if(isAvailable==true){
                        grafoMatriz.agregarVertice(objective);
                        objective.setNum(contador);
                        //graph.agregarVertice(objective);
                        factoryBoard[columna][fila]=objective;
                        logicBoard[columna][fila]=objective.getId();
                        chooseImage(columna, fila, logicBoard[columna][fila]);                   
                        colocacion=false;
                        if(objective.getName().equals("Conector")){
                            grafoMatriz.agregarArista(grafoMatriz.vertices[0], grafoMatriz.vertices[contador]);
                        }
                        else if((!tutorial.isRunning)){
                            JOptionPane.showMessageDialog(null, "Ahora conecta tu "+objective.getName()+" a un conector");
                            connect=true;
                            if(isConected){
                                player.salida.writeInt(9);
                                player.salida.writeInt(fila);
                                player.salida.writeInt(columna);
                                player.salida.writeInt(objective.getId());
                                player.salida.writeInt(this.numeroJugador);
                            }
                        }                        
                        contador++;
                        System.out.println(contador);
                    }
                    break;
                case "1x2":
                    if(isAvailable==true){
                        grafoMatriz.agregarVertice(objective);
                        objective.setNum(contador);
                        //graph.agregarVertice(objective);
                        factoryBoard[columna][fila]=objective;
                        factoryBoard[columna+1][fila]=objective;
                        logicBoard[columna][fila]=objective.getId();
                        logicBoard[columna+1][fila]=objective.getId(); 
                        chooseImage(columna, fila, logicBoard[columna][fila]);
                        chooseImage(columna+1, fila, logicBoard[columna+1][fila]);                  
                        colocacion=false; 
                        if(objective.getName().equals("Conector")){
                            grafoMatriz.agregarArista(grafoMatriz.vertices[0], grafoMatriz.vertices[contador]);
                        }
                        else if((!tutorial.isRunning)){
                            JOptionPane.showMessageDialog(null, "Ahora conecta tu "+objective.getName()+" a un conector");
                            connect=true;
                            if(isConected){
                                player.salida.writeInt(9);
                                player.salida.writeInt(fila);
                                player.salida.writeInt(columna);
                                player.salida.writeInt(objective.getId());
                                player.salida.writeInt(this.numeroJugador);
                            }
                        }                                
                        contador++;
                        System.out.println(contador);
                    }
                    break;           
            }
        }
        
     
    }
    public void MouseOntheButton(java.awt.event.MouseEvent evt)            
    {      
        if(colocacion==false&&connect==false){
            return;
        }
        else if(connect==true){
            isAvailable=true;
            JButton bottonPress=(JButton)evt.getComponent();
            String boton = bottonPress.getActionCommand();
            int columna, fila,posicion;
            posicion=boton.indexOf(",");
            columna=Integer.parseInt(boton.substring(0,posicion));
            fila=Integer.parseInt(boton.substring(1+posicion));
            if( logicBoard[columna][fila]==3)
                buttonArray[columna][fila].setIcon(beta);
            else{
                buttonArray[columna][fila].setIcon(alpha);
                isAvailable=false;
            }
        }
        else{
            isAvailable=true;
            JButton bottonPress=(JButton)evt.getComponent();
            String boton = bottonPress.getActionCommand();
            int columna, fila,posicion;
            posicion=boton.indexOf(",");
            columna=Integer.parseInt(boton.substring(0,posicion));
            fila=Integer.parseInt(boton.substring(1+posicion));
            choosePlace(objective.getBounds(), columna, fila);
        }             
    }
    public void MouseOuttheButton(java.awt.event.MouseEvent evt)
    {
        if(colocacion==false&&connect==false){
            return;
        }
        else{
            JButton bottonPress=(JButton)evt.getComponent();
            String boton = bottonPress.getActionCommand();
            int columna, fila,posicion;
            posicion=boton.indexOf(",");
            columna=Integer.parseInt(boton.substring(0,posicion));
            fila=Integer.parseInt(boton.substring(1+posicion));
            erasePlace(objective.getBounds(), columna, fila);
        }            
    }
    public void erasePlace(String bounds,int columna,int fila){
        switch(bounds){
            case "1x1":
                erase1x1(columna, fila);
                break;
            case "1x2":
                erase1x2(columna, fila);
                break;
            case "2x2":
                erase2x2(columna, fila);
                break;
        }
    }
    public void erase1x1(int columna,int fila){
        if( logicBoard[columna][fila]==0)
            buttonArray[columna][fila].setIcon(null);
        else
            chooseImage(columna, fila, logicBoard[columna][fila]);
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
                chooseImage(columna, fila, logicBoard[columna][fila]);
            if( logicBoard[columna+1][fila]==0)
                buttonArray[columna+1][fila].setIcon(null);
            else
                chooseImage(columna+1, fila, logicBoard[columna+1][fila]);
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
                chooseImage(columna, fila, logicBoard[columna][fila]);
            if( logicBoard[columna+1][fila+1]==0)
                buttonArray[columna+1][fila+1].setIcon(null);
            else
                chooseImage(columna+1, fila+1, logicBoard[columna+1][fila+1]);

            if( logicBoard[columna][fila+1]==0)
                buttonArray[columna][fila+1].setIcon(null);
            else
                chooseImage(columna, fila+1, logicBoard[columna][fila+1]);

            if( logicBoard[columna+1][fila]==0)
                buttonArray[columna+1][fila].setIcon(null);
            else
                chooseImage(columna+1, fila, logicBoard[columna+1][fila]);
        } 
    }
    ////////////////////////////////////////////////////////////////////////
    public void choosePlace(String bounds,int columna,int fila){
        switch(bounds){
            case "1x1":
                set1x1(columna, fila);
                break;
            case "1x2":
                set1x2(columna, fila);
                break;
            case "2x2":
                set2x2(columna, fila);
                break;
        }
    }
    public void set1x1(int columna,int fila){
        if( logicBoard[columna][fila]==0)
            buttonArray[columna][fila].setIcon(beta);
        else{
            buttonArray[columna][fila].setIcon(alpha);
            isAvailable=false;
        }
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
    public void chooseImage(int columna,int fila,int i){
        switch(i){
            case 1:
                buttonArray[columna][fila].setIcon(new ImageIcon(getClass().getResource("whirlpool.jpg")));
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
    public boolean isDestroyed(int columna,int fila,int tipo){
    switch(tipo){
//            case 1:
//                logicBoard[fila][columna]=tipo;
//                break;
            case 2:
                if(!(columna+1>=BOARD_SIZE||fila+1>=BOARD_SIZE)&&atackBoard[columna+1][fila]==1&&logicBoard[columna+1][fila]==2&&atackBoard[columna+1][fila+1]==1&&logicBoard[columna+1][fila+1]==2&&atackBoard[columna][fila+1]==1&&logicBoard[columna][fila+1]==2){
                    return true;
                }
                else if(!(columna-1<0||fila+1>=BOARD_SIZE)&&atackBoard[columna-1][fila]==1&&logicBoard[columna-1][fila]==2&&atackBoard[columna][fila+1]==1&&logicBoard[columna][fila+1]==2&&atackBoard[columna-1][fila+1]==1&&logicBoard[columna-1][fila+1]==2){
                    return true;
                }
                else if(!(columna+1>=BOARD_SIZE||fila-1<0)&&atackBoard[columna+1][fila]==1&&logicBoard[columna+1][fila]==2&&atackBoard[columna][fila-1]==1&&logicBoard[columna][fila-1]==2&&atackBoard[columna+1][fila-1]==1&&logicBoard[columna+1][fila-1]==2){
                    return true;
                }
                else if(!(columna-1<0||fila-1<0)&&atackBoard[columna-1][fila]==1&&logicBoard[columna-1][fila]==2&&atackBoard[columna][fila-1]==1&&logicBoard[columna][fila-1]==2&&atackBoard[columna-1][fila-1]==1&&logicBoard[columna-1][fila-1]==2){
                    return true;
                }
                return false;   
            case 3:
                return true;
            case 4:
                if(!(columna+1>=BOARD_SIZE)&&atackBoard[columna+1][fila]==1&&logicBoard[columna+1][fila]==4){
                    return true;
                }
                else if(!(columna-1<0)&&atackBoard[columna-1][fila]==1&&logicBoard[columna-1][fila]==4){
                    return true;
                }
                return false;
            case 5:
                if(!(columna+1>=BOARD_SIZE)&&atackBoard[columna+1][fila]==1&&logicBoard[columna+1][fila]==5){
                    return true;
                }
                else if(!(columna-1<0)&&atackBoard[columna-1][fila]==1&&logicBoard[columna-1][fila]==5){
                    return true;
                }
                return false; 
            case 6:
                if(!(columna+1>=BOARD_SIZE)&&atackBoard[columna+1][fila]==1&&logicBoard[columna+1][fila]==6){
                    return true;
                }
                else if(!(columna-1<0)&&atackBoard[columna-1][fila]==1&&logicBoard[columna-1][fila]==6){
                    return true;
                }
                return false; 
            case 7:
                if(!(columna+1>=BOARD_SIZE)&&atackBoard[columna+1][fila]==1&&logicBoard[columna+1][fila]==7){
                    return true;
                }
                else if(!(columna-1<0)&&atackBoard[columna-1][fila]==1&&logicBoard[columna-1][fila]==7){
                    return true;
                }
                return false; 
            case 8:
                if(!(columna+1>=BOARD_SIZE)&&atackBoard[columna+1][fila]==1&&logicBoard[columna+1][fila]==8){
                    return true;
                }
                else if(!(columna-1<0)&&atackBoard[columna-1][fila]==1&&logicBoard[columna-1][fila]==8){
                    return true;
                }
                return false; 
            case 9:
                if(!(columna+1>=BOARD_SIZE)&&atackBoard[columna+1][fila]==1&&logicBoard[columna+1][fila]==9){
                    return true;
                }
                else if(!(columna-1<0)&&atackBoard[columna-1][fila]==1&&logicBoard[columna-1][fila]==9){
                    return true;
                }
                return false; 
            case 10:
                if(!(columna+1>=BOARD_SIZE)&&atackBoard[columna+1][fila]==1&&logicBoard[columna+1][fila]==10){
                    return true;
                }
                else if(!(columna-1<0)&&atackBoard[columna-1][fila]==1&&logicBoard[columna-1][fila]==10){
                    return true;
                }
                return false; 

            default:
                return false;
        }
    }
    private void nextPlayer(){
        turno++;
        if(turno >playerQty)
            turno = 1;        
    }
    
    protected void updateTurno(){
        nextPlayer();
        lblTurno.setText("Turno: jugador " + turno);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sea().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SeaPanel;
    private javax.swing.JButton btnCreateBombCanyon;
    private javax.swing.JButton btnCreateCanyon;
    private javax.swing.JButton btnCreateMultipleCanyon;
    private javax.swing.JButton btnCreateRedBeardCanyon;
    protected javax.swing.JButton btnEnemy1;
    protected javax.swing.JButton btnEnemy2;
    protected javax.swing.JButton btnEnemy3;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnSettings;
    private javax.swing.JButton btnTienda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMoney;
    private javax.swing.JLabel lblQuantityBombCanyon;
    private javax.swing.JLabel lblQuantityCanyon;
    private javax.swing.JLabel lblQuantityMultipleCanyon;
    private javax.swing.JLabel lblQuantityRedBeardCanyon;
    protected javax.swing.JLabel lblTurno;
    private javax.swing.JLabel lbliron;
    protected javax.swing.JTextArea txaBitacora;
    protected javax.swing.JTextArea txaMensajes;
    private javax.swing.JTextField txfSend;
    // End of variables declaration//GEN-END:variables
}

/*
 * Banquero2View.java
 */

package banquero2;

import java.awt.GridLayout;
import java.awt.Label;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Label;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import javax.swing.event.*;



/**
 * The application's main frame.
 */
public class Banquero2View extends FrameView implements ActionListener {

    public Banquero2View(SingleFrameApplication app) {
        super(app);

        initComponents();
        initComponents2();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Banquero2App.getApplication().getMainFrame();
            aboutBox = new Banquero2AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Banquero2App.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(banquero2.Banquero2App.class).getContext().getResourceMap(Banquero2View.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(banquero2.Banquero2App.class).getContext().getActionMap(Banquero2View.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 509, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    
    
    /* MATIAS CODE */
    
    private JTextField[][] matrizTiene;
    private JTextField[][] matrizNecesidad;
    private JTextField[] vectorDisponible;
    private JTextField[] vectorPedido;
    private JTextField procPedido;
    private Button cargar, guardar, simular;
    
    private void initComponents2() {
        JPanel matTiene = new JPanel();
        crearMatrizTiene(matTiene);

        JPanel matNecesidad = new JPanel();
        crearMatrizNecesida(matNecesidad);

        JPanel vectDisponibles = new JPanel();
        crearVectDisponibles(vectDisponibles);

        JPanel vectPedido = new JPanel();
        crearVectPedido(vectPedido);
        
        //javax.swing.GroupLayout layout = new javax.swing.GroupLayout(mainPanel);
        //mainPanel.setLayout(layout);
        JPanel panelLlenado = new JPanel();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelLlenado);
        panelLlenado.setLayout(layout);       
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup() //.addGap(160,260,36)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(matTiene)
                    .addComponent(vectDisponibles)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(matNecesidad) //.addGap(180, Short.MAX_VALUE))
                    .addComponent(vectPedido)
                    )
                );
        layout.setVerticalGroup(
                layout.createSequentialGroup() //.addGap(160,260,36)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(matTiene)
                    .addComponent(matNecesidad) //.addGap(180, Short.MAX_VALUE))

                 )
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(vectDisponibles)
                    .addComponent(vectPedido)
                    )
                );
        
        JPanel botonera = new JPanel();
        crearBotonera(botonera);
        
        javax.swing.GroupLayout layoutGral = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(layoutGral);
        layoutGral.setAutoCreateGaps(true);
        layoutGral.setAutoCreateContainerGaps(true);
        layoutGral.setHorizontalGroup(
                layoutGral.createSequentialGroup() //.addGap(160,260,36)
                    .addComponent(panelLlenado)
                    .addComponent(botonera)
                );
        layoutGral.setVerticalGroup(
                layoutGral.createSequentialGroup() //.addGap(160,260,36)
                .addGroup(layoutGral.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panelLlenado)
                    .addComponent(botonera) //.addGap(180, Short.MAX_VALUE))
                 )
                );
        
        //lalalala.pack();

    }// </editor-fold>
    
    private void crearMatrizTiene(JPanel matTiene){
        matTiene.setBorder(new TitledBorder("Matriz de tiene"));
        //componentes.setToolTipText("Acá se ve lo que tiene cada proceso");

        GridLayout layout1 = new GridLayout(9, 9);
        layout1.setHgap(2);
        layout1.setVgap(2);

        this.matrizTiene = new JTextField[9][9];

        matTiene.setLayout(layout1);

        matTiene.add(new Label(" "));
        for (Integer i = 1; i <= 8; i++) {
            matTiene.add(new Label("  R" + i));
        }
        for (Integer i = 1; i <= 8; i++) {
            matTiene.add(new Label("P" + i));
            for (Integer j = 1; j <= 8; j++) {
                this.matrizTiene[i][j] = new JTextField(2);
                this.matrizTiene[i][j].setText(i + ", " + j);
                this.matrizTiene[i][j].setHorizontalAlignment(JTextField.CENTER);
                //this.matrizTiene[i][j].setEditable(false);
                this.matrizTiene[i][j].setToolTipText("Cantidad del recurso " + j + " que tiene el proceso " + i + "." );
                matTiene.add(this.matrizTiene[i][j]);
            }
        }
    }
 
    private void crearMatrizNecesida(JPanel matNecesidad){
        matNecesidad.setBorder(new TitledBorder("Matriz de máximos"));
        //componentes.setToolTipText("Acá se ve lo que tiene cada proceso");

        GridLayout layout2 = new GridLayout(9, 9);
        layout2.setHgap(2);
        layout2.setVgap(2);

        this.matrizNecesidad = new JTextField[9][9];

        matNecesidad.setLayout(layout2);

        matNecesidad.add(new Label(" "));
        for (Integer i = 1; i <= 8; i++) {
            matNecesidad.add(new Label("  R" + i));
        }
        for (Integer i = 1; i <= 8; i++) {
            matNecesidad.add(new Label("P" + i));
            for (Integer j = 1; j <= 8; j++) {
                this.matrizNecesidad[i][j] = new JTextField(2);
                this.matrizNecesidad[i][j].setText(i + ", " + j);
                this.matrizNecesidad[i][j].setHorizontalAlignment(JTextField.CENTER);
                //this.matrizNecesidad[i][j].setEditable(false);
                this.matrizNecesidad[i][j].setToolTipText("Cantidad del recurso " + j + " que tiene el proceso " + i + "." );
                matNecesidad.add(this.matrizNecesidad[i][j]);
            }
        }

    }
    
    private void crearVectDisponibles(JPanel vectDisponibles){
        vectDisponibles.setBorder(new TitledBorder("Recursos Disponibles"));
        //componentes.setToolTipText("Acá se ve lo que tiene cada proceso");

        GridLayout layout3 = new GridLayout(2, 9);
        layout3.setHgap(2);
        layout3.setVgap(2);

        this.vectorDisponible = new JTextField[9];

        vectDisponibles.setLayout(layout3);

        for (Integer i = 1; i <= 8; i++) {
            vectDisponibles.add(new Label("  R" + i));
        }
        vectDisponibles.add(new Label(" "));
        
        for (Integer i = 1; i <= 8; i++) {
            this.vectorDisponible[i] = new JTextField(2);
            this.vectorDisponible[i].setText(i.toString());
            this.vectorDisponible[i].setHorizontalAlignment(JTextField.CENTER);
            //this.vectorDisponible[i].setEditable(false);
            this.vectorDisponible[i].setToolTipText("Cantidad del recurso " + i + " disponible." );
            vectDisponibles.add(this.vectorDisponible[i]);
        }
        
        vectDisponibles.add(new Label(" "));

    }
    
    private void crearVectPedido (JPanel vectPedido){
        vectPedido.setBorder(new TitledBorder("Nuevo pedido"));
        //componentes.setToolTipText("Acá se ve lo que tiene cada proceso");

        GridLayout layout3 = new GridLayout(2, 9);
        layout3.setHgap(2);
        layout3.setVgap(2);

        this.vectorPedido = new JTextField[9];

        vectPedido.setLayout(layout3);

        vectPedido.add(new Label("  P"));
        for (Integer i = 1; i <= 8; i++) {
            vectPedido.add(new Label("  R" + i));
        }
        
        this.procPedido = new JTextField(2);
        this.procPedido.setText("3");
        this.procPedido.setHorizontalAlignment(JTextField.CENTER);
        this.procPedido.setToolTipText("Proceso que realiza el pedido." );
        vectPedido.add(this.procPedido);
        
        for (Integer i = 1; i <= 8; i++) {
            this.vectorPedido[i] = new JTextField(2);
            this.vectorPedido[i].setText(i.toString());
            this.vectorPedido[i].setHorizontalAlignment(JTextField.CENTER);
            //this.vectorPedido[i].setEditable(false);
            this.vectorPedido[i].setToolTipText("Cantidad del recurso " + i + " disponible." );
            vectPedido.add(this.vectorPedido[i]);
        }
    }
    
    private void crearBotonera (JPanel botonera) {
        GridLayout layout = new GridLayout(3, 1);
        layout.setHgap(2);
        layout.setVgap(2);

        botonera.setLayout(layout);
        
        this.cargar = new Button("Cargar");
        this.guardar = new Button("Guardar");
        this.simular = new Button("Simular");
        
        botonera.add(this.cargar);
        botonera.add(this.guardar);
        botonera.add(this.simular);
        /*ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("I was selected.");
            
            }
        };*/
        
        this.cargar.addActionListener(this);
        this.guardar.addActionListener(this);
        this.simular.addActionListener(this);
    }

//     private void actionPerformed( Event evt ) {
     public void actionPerformed(ActionEvent evt) {

         if( evt.getSource().equals( this.cargar ) ){
             System.out.println( "Se ha pulsado el boton de cargar" );
             this.desserializar();
         }
         if( evt.getSource().equals( this.guardar ) ){
             System.out.println( "Se ha pulsado el boton de guardar" );
             this.serializar();
         }
         if( evt.getSource().equals( this.simular ) ){
             System.out.println( "Se ha pulsado el boton de simular" );
         }         
     }
     
     private void serializar() { 
        try {
        FileOutputStream f = new FileOutputStream("tmp.txt"); 
        ObjectOutputStream s = new ObjectOutputStream(f); 
        //String company = "My Good Company"; 
        //s.writeObject(company); 
        //s.writeObject(this.text_tiene11.getText());
        //s.writeObject(this.);
        for (Integer i = 1; i <= 8; i++) {
            for (Integer j = 1; j <= 8; j++) {
                s.writeObject(this.matrizTiene[i][j].getText());                
            }
        }
        
        for (Integer i = 1; i <= 8; i++) {
            for (Integer j = 1; j <= 8; j++) {
                s.writeObject(this.matrizNecesidad[i][j].getText());                
            }
        }
        for (Integer j = 1; j <= 8; j++) {
            s.writeObject(this.vectorDisponible[j].getText());                
        }
        
        s.writeObject(this.procPedido.getText());
        for (Integer j = 1; j <= 8; j++) {
            s.writeObject(this.vectorPedido[j].getText());                
        }
        
        System.out.println("Grabado");
        
        s.flush();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
    
    private void desserializar() { 
        try {
        FileInputStream f = new FileInputStream("tmp.txt"); 
        ObjectInputStream s = new ObjectInputStream(f); 
        //String company = ""; 

        //company = (String) s.readObject();
        //this.text_tiene11.setText((String) s.readObject());
        //this = s.readObject();
        //System.out.println(company + " "); 
        for (Integer i = 1; i <= 8; i++) {
            for (Integer j = 1; j <= 8; j++) {
                this.matrizTiene[i][j].setText((String) s.readObject());                
            }
        }
        for (Integer i = 1; i <= 8; i++) {
            for (Integer j = 1; j <= 8; j++) {
                this.matrizNecesidad[i][j].setText((String) s.readObject());                
            }
        }
        
        for (Integer j = 1; j <= 8; j++) {
            this.vectorDisponible[j].setText((String) s.readObject());                
        }
        
        this.procPedido.setText((String) s.readObject());
        for (Integer j = 1; j <= 8; j++) {
            this.vectorPedido[j].setText((String) s.readObject());                
        }
        
        System.out.println("\tCargado");
        
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (ClassNotFoundException cl) {
            System.out.println(cl.getMessage());
        }
    }


}



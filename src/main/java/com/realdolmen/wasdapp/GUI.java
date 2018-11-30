/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.wasdapp;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.realdolmen.wasdapp.domain.Informatie;
import com.realdolmen.wasdapp.exceptions.NoQueryPossibleException;
import com.realdolmen.wasdapp.repositories.InformatieRepository;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
import static com.itextpdf.text.pdf.PdfFileSpecification.url;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import static com.realdolmen.wasdapp.repositories.AbstractRepository.LOGIN;
import static com.realdolmen.wasdapp.repositories.AbstractRepository.PASSWORD;
import com.realdolmen.wasdapp.services.InformatieService;
import java.awt.Desktop;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author KDLBL62
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form test
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        fileChooser = new javax.swing.JFileChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CSV ->  PDF generater");

        jButton1.setText("Selecteer csv");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Stuur data door");
        jButton2.setActionCommand("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Genereer PDF");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(164, 164, 164)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static final String LOGIN = "root";
    public static final String PASSWORD = "root";
    private String bestand;
    private String url;
    public Connection createConnection() throws SQLException {
          System.out.println("trying connection");
        return DriverManager.getConnection(url, LOGIN, PASSWORD);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        try {
          // What to do with the file, e.g. display it in a TextArea
         jTextArea1.read( new FileReader( file.getAbsolutePath() ), null );
         bestand = file.getAbsolutePath();
         jLabel1.setText("Path: " + file.getAbsolutePath());
        } catch (IOException ex) {
          System.out.println("problem accessing file"+file.getAbsolutePath());
        }
    } else {
        System.out.println("File access cancelled by user.");
    }    
    }//GEN-LAST:event_jButton1ActionPerformed
double ParseDouble(String strNumber) {
   if (strNumber != null && strNumber.length() > 0) {
       try {
          return Double.parseDouble(strNumber);
       } catch(Exception e) {
          return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
       }
   }
   else return 0;
}

    InformatieRepository informatieRepository = new InformatieRepository();
    InformatieService informatieService = new InformatieService(informatieRepository);
    ArrayList<Informatie> info = new ArrayList<Informatie>();
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Reader reader = Files.newBufferedReader(Paths.get(bestand));
            CsvToBean<Informatie> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Informatie.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<Informatie> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) 
            {    Informatie infor = csvUserIterator.next();
                info.add(new Informatie(infor.getTitel(),infor.getLocatie(),infor.getStraat(),infor.getNummer(),infor.getPostcode(),infor.getGemeente(),infor.getLand(),infor.getOmschrijving(),infor.getWiki_link(),infor.getWebsite(),infor.getTelefoon(),infor.getEmail(),infor.getPrijs(),infor.getPersoon())) ;
            }
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] line = jTextArea1.getText().split("\n");
        for (int i=1;i<line.length;i++){
            System.out.println(line[i]);
//           String[] data = line[i].split(",", -1);
//           for (int x=0; x < data.length; x++){
//               info.add(new Informatie(data[0],data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11], ParseDouble(data[12]), data[13])) ;         
//           }          
        }     
        try {
            informatieRepository.insertItems(info);
        } catch (NoQueryPossibleException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String bestand_naam = "./Informatie.pdf";
        Document pdf = new Document();
        
        try {
            PdfWriter.getInstance(pdf, new FileOutputStream(bestand_naam));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }       
            pdf.open();        
            List<Informatie> info = new ArrayList<>();
            List<Informatie> informatie;
        try {
            
            informatie = informatieRepository.findAll();
         for(Informatie i:informatie){
             BarcodeQRCode code = new BarcodeQRCode(i.getTitel(), 200, 200, null);
             Image codeIMG = code.getImage();
             com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 25, BaseColor.BLACK);
             Paragraph lijn = new Paragraph(i.getTitel(),font);
             pdf.add( Chunk.NEWLINE );
             pdf.add(lijn);
             pdf.add( Chunk.NEWLINE );
             pdf.add(codeIMG);
             pdf.newPage();
    }
        } catch (NoQueryPossibleException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
            pdf.close();
            File mijnestand = new File(bestand_naam);
        try {
            Desktop.getDesktop().open(mijnestand);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jButton3ActionPerformed
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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}

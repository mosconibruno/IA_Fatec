/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuronioAdeline;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author lab7
 */
public class teste extends javax.swing.JFrame {
    
    static double [] pesos = new double[600];
    static double [] backup = new double[600];
    
    public static void main(String args[]){
        grafico g = new grafico();
        grafico gf;
        
        combinacao_linear af = new combinacao_linear();
        
        double [][] x5 = {{3.09}, {0.6}, {2.233}, {0.604}};
        double [][] x6 = {{3.1246}, {0.5527}, {2.6568}, {0.6692}};
        af.iteracao_II();
        // pesos = af.testa_af(x5);
        pesos = af.testa_af(af.w);
        // set_pesos(pesos);
        
        JFrame aplicacao = new JFrame();
        aplicacao.getContentPane().setBackground(new Color (255,255,255));
        aplicacao.setTitle("Redes Neurais Artificiais");
        aplicacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aplicacao.add(g);
        aplicacao.setSize(500, 480);
        aplicacao.setVisible(true);
           
    }
    
    public double [] get_pesos(){
    return pesos;
    }
    
}

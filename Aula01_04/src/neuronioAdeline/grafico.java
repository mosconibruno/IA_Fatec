/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuronioAdeline;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author lab7
 */
public class grafico extends JPanel {
    
    static String mx, mn;
    static double maximo, minimo, passo, a, b, c, d, e, f, g, h, i, j, k;
    
    double evidencia;
    
    double []t = new double [300];
    double []w = new double [300];
    private double []ff = new double [600];
    private double []fg = new double [600];
    
    teste aa = new teste();
    
    grafico(double [] shapes){
        ff = shapes;
    }

    grafico() {
    }
    
    public void paintComponent(Graphics l){
    
        super.paintComponent(l);
        minimo = 0;
        maximo = 1;
        passo = (maximo - minimo)/10;
        
        a = minimo;
        b = minimo + passo;
        c = minimo + 2*passo;
        d = minimo + 3*passo;
        e = minimo + 4*passo;
        f = minimo + 5*passo;
        g = minimo + 6*passo;
        h = minimo + 7*passo;
        i = minimo + 8*passo;
        j = minimo + 9*passo;
        k = minimo + 10*passo;
        
        l.setColor(Color.lightGray);
        
        // linhas horizontais
        int p = 70; //p = 80;
        do{
            for(int i = 0; i < 320; i = i + 20)
                l.drawLine(0, p, i, p);
            p = p + 22; //20;
        }while(p <= 340);
        
        // linhas verticais
        
        int q = 0;
        do{
            for(int i = 0; i < 200; i = i + 20)
                l.drawLine(q, 70, q, 332);
            q = q + 20;
        }while(q < 320);
        
        l.setColor(Color.red);
        l.drawString("'sinal 1 ", 60, 50);
        
        l.setColor(Color.blue);
        l.drawString("'sinal 2 ", 130, 50);
        
        l.setColor(Color.BLACK);
        int m = 0, escala_horizontal = 0;
        
        // escala_horizontal
        
        for(int i = 0; i <= 300; i = i + 20){
            l.drawString("" + m, escala_horizontal, 350);
            escala_horizontal = escala_horizontal + 20;
            m = m + 2;
        }
        l.drawString("(*10)", 340, 350);
        
        //escala vertical
        l.drawString("-1.0  ", 320, 330); // +a
        l.drawString("-0.8  ", 320, 305); // +b
        l.drawString("-0.6  ", 320, 280); // +c
        l.drawString("-0.4  ", 320, 255); // +d
        l.drawString("-0.2  ", 320, 230); // +e
        l.drawString(" 0.0  ", 320, 205); // +f
        l.drawString(" 0.2  ", 320, 180); // +g
        l.drawString(" 0.4  ", 320, 155); // +h
        l.drawString(" 0.6  ", 320, 130); // +i
        l.drawString(" 0.9  ", 320, 105); // +j
        l.drawString(" 1.0  ", 320, 80); // +k
            
        ff = aa.get_pesos();
        
        System.arraycopy(ff, 0, fg, 0, 600);
        
        for(int nn = 0; nn < 300; nn++){
            t[nn] = ff[nn];
        }
        
        System.arraycopy(ff, 300, w, 0, 300);
        
        int xx = 0;
        double y = 0, n = 0;
        
        for(int z = 0; z <= 299; z++){
            y = +203 +(-250*t[z])*0.1;
            n = +203 +(-250*w[z])*0.1;
            l.setColor(Color.red);
            l.drawLine(xx-1, (int) y-1, xx, (int)y);
            l.setColor(Color.blue);
            l.drawLine(xx-1, (int) n-1, xx, (int)n);
            xx++;
        
        }
        
    }
}

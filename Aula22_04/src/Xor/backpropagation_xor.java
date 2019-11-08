/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xor;

/**
 *
 * @author lab7
 */
public class backpropagation_xor {
    // entradas
    double [][] x1 = {{0.0}, {0.0}, {1.0}, {1.0}};
    double [][] x2 = {{0.0}, {1.0}, {0.0}, {1.0}};
    
    //sinapses vw
    double [][] v1 = {{0.9}, {0.9}};
    double [][] v2 = {{0.9}, {0.9}};
    double [][] v0 = {{-1.45}, {-0.45}};
    // sinapses wy
    double [][] w1 = {{-1.99}};
    double [][] w2 = {{0.9}};
    
    // saida e suas funções de ativação
    double [] y1 = new double[4];
    double [][] y0 = {{-0.45}};
    double [] y1f = new double [4];
    double [] y1ff = new double [4];
    // saída desejada
    double [] y1d = {0.0, 1.0, 1.0, 0.0};
    
    
    double [] erro1 = new double [4];
    
    //limiar de ativação e taxa de aprendizagem
    double alpha = 0.1;
    double limiar = 0.0;
    
    
    double [][] zv = new double [2][1];
    double [][] zvf = new double [2][1];
    double [][] zvff = new double [2][1];
    
    // ajuste das sinapses w
    double [][] deltaw1 = new double [1][1];
    double [][] deltaw2 = new double [1][1];
    
    double [][] deltay0 = {{-0.45}};
    
    //ajuste das sinapses v
    double [][] deltav1 = new double [2][1];
    double [][] deltav2 = new double [2][1];
    double [][] deltav0 = new double [2][1];
    
    public void iteracao_IX(){
        int n = 0;
        
        while (n < 10) {  //nº de treinamentos
            for(int x = 0; x < x1.length; x++){
                zv[0][0] = v0[0][0] + x1[x][0]*v1[0][0] + x2[x][0]*v2[0][0];
                zv[1][0] = v0[1][0] + x1[x][0]*v1[1][0] + x2[x][0]*v2[1][0];
                // System.out.println("zv[0][0]= " + zv[0][0]);
                // System.out.println("zv[1][0]= " + zv[1][0]);
                
                if (zv[0][0] > limiar)
                {zvf[0][0] = 1.0; zvff[0][0] = 1.0;}
                else {zvf[0][0] = 0.0; zvff[0][0] = 0.0;}
                
                if (zv[1][0] > limiar)
                {zvf[1][0] = 1.0; zvff[1][0] = 1.0;}
                else {zvf[1][0] = 0.0; zvff[1][0] = 0.0;}
                
                y1[x] = y0[0][0] + zvf[0][0]*w1[0][0] + zvf[1][0]*w2[0][0];
                if(y1[x] > limiar)
                {y1f[x] = 1.0; y1ff[x] = 1.0;}
                else {y1f[x] = 0.0; y1ff[x] = 0.0;}
                
                erro1[x] = (y1d[x] - y1f[x]);
                
                deltay0[0][0] = alpha*erro1[x];
                
                deltaw1[0][0] = alpha*erro1[x]*y1ff[x]*zvf[0][0];
                deltaw2[0][0] = alpha*erro1[x]*y1ff[x]*zvf[1][0];
                
                deltav1[0][0] = alpha*zvff[0][0]*(erro1[x]*w1[0][0])*x1[x][0];
                deltav1[1][0] = alpha*zvff[1][0]*(erro1[x]*w2[0][0])*x1[x][0];
                
                deltav2[0][0] = alpha*zvff[0][0]*(erro1[x]*w1[0][0])*x1[x][0];
                deltav2[1][0] = alpha*zvff[0][0]*(erro1[x]*w2[0][0])*x1[x][0];
                
                deltav0[0][0] = alpha*erro1[x]*zvf[0][0];
                deltav0[1][0] = alpha*erro1[x]*zvf[1][0];
                
                System.out.println("y1f[" + x + "]= " + y1f[x]);
                
            }
        
            y0[0][0] = y0[0][0] + deltay0[0][0];
            
            w1[0][0] = w1[0][0] + deltaw1[0][0];
            
            w2[0][0] = w2[0][0] + deltaw2[0][0];
            
            v1[0][0] = v1[0][0] + deltav1[0][0];
            v1[1][0] = v1[1][0] + deltav1[1][0];
            
            v2[0][0] = v2[0][0] + deltav2[0][0];
            v2[1][0] = v2[1][0] + deltav2[1][0];
            
            v0[0][0] = v0[0][0] + deltav0[0][0];
            v0[1][0] = v0[1][0] + deltav0[1][0];
            
            n = n + 1;
            
            System.out.println("número de treinametos " + n + "\n");
            for(int k = 0; k < erro1.length; k++)
                System.out.println("erro1 [" + k + "]= " + erro1[k] + "\n");
        }
       
    }
    
    public void testa_2neuronios_discreta(double [][] x3, double [][] x4, int z,
                                        double [][] vv0, double [][] vv1,
                                        double [][] vv2, double [][] ww1,
                                        double [][] ww2, double [][] yy0){
    
                    this.x1 = x3;
                    this.x2 = x4;
                    this.v1 = vv1;
                    this.v2 = vv2;
                    this.v0 = vv0;
                    this.w1 = ww1;
                    this.w2 = ww2;
                    this.y0 = yy0;
                    
                    //System.out.println("x1 = " + x1);
                    //System.out.println("x2 = " + x2);
                    
                    System.out.println("pesos ");
                    
                    for(int j = 0; j <= 1; j++){
                    
                    System.out.println("peso v0[" + j + "]" + v0[j][0]);
                    System.out.println("peso v1[" + j + "]" + v1[j][0]);
                    System.out.println("peso v2[" + j + "]" + v2[j][0]);
                    
                    System.out.println("peso w1[" + 0 + "]" + w1[0][0]);
                    System.out.println("peso w2[" + 0 + "]" + w2[0][0]);
                    
                    System.out.println("peso y0[" + 0 + "]" + y0[0][0]);
                    }
                    
                    zv[0][0] = v0[0][0] + x1[z][0]*v1[0][0] + x2[z][0]*v2[0][0];
                    zv[1][0] = v0[1][0] + x1[z][0]*v1[1][0] + x2[z][0]*v2[1][0];
                    
                    if(zv[0][0] > limiar)
                        zvf[0][0] = 1.0;
                    else zvf[0][0] = 0.0;
                    
                    if(zv[1][0] > limiar)
                        zvf[1][0] = 1.0;
                    else zvf[1][0] = 0.0;
                    
                    y1[z] = y0[0][0] + zvf[0][0]*w1[0][0] + zvf[1][0]*w2[0][0];
                    if(y1[z] > limiar)
                        y1f[z] = 1.0;
                    else y1f[z] = 0.0;                   
    }
       
}

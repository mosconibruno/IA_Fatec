/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuronioAdeline;

/**
 *
 * @author lab7
 */
public class combinacao_linear {
    double [] f1 = new double[300];
    double [] f2 = new double[300];
    double [] f3 = new double[300];
    double [] f4 = new double[300];
    double [] f5 = new double[300];
    double [] f6 = new double[300];
    double [] f8 = new double[300];
    double [] f10 = new double[300];
    double [] f12 = new double[300];
    double [] f = new double[300];
    double [] t = new double[300];
    
    double w [][] = {{-2.4013}, {0.393}, {1.902}, {0.429}};
    
    double taxa_aprendizado = 0.000027;
    
    public void iteracao_II(){
        for(int x = 0; x < 300; x++){
            f1[x] = Math.sin(x*Math.PI/180);
            f2[x] = Math.cos(x*Math.PI/180);
            f3[x] = x*Math.PI/180;
            f[x] = -Math.PI + 0.565*f1[x] + 2.657*f2[x] + 0.674*f3[x];
           // B
       //     f4[x]= (Math.sin(x*Math.PI/180));
       //     f5[x]= (Math.cos(x*Math.PI/180));
       //    f6[x]= (x*Math.PI/180)*Math.random();
            f8[x] = w[0][0] + w[1][0]*f4[x] + w[2][0]*f5[x] + w[3][0]*f6[x];
          // D
      //      f4[x]= (Math.sin(x*Math.PI/180));
      //      f5[x]= (Math.cos(x*Math.PI/180))*Math.random()*2.4;
       //     f6[x]= (x*Math.PI/180);
            f10[x] = w[0][0] + w[1][0]*f4[x] + w[2][0]*f5[x] + w[3][0]*f6[x];
          // F
            f4[x]= (Math.sin(x*Math.PI/180))*5.9;
            f5[x]= (Math.cos(x*Math.PI/180))*1.2;
            f6[x]= (x*Math.PI/180)*0.4;
            f12[x] = w[0][0] + w[1][0]*f4[x] + w[2][0]*f5[x] + w[3][0]*f6[x];
            
            
        }
        
        int n = 0;
        while (n < 32){  // nº de treinamentos
        
            w = entradaf1(w);
            System.out.println("pesos w = \t" + w[0][0]);
            System.out.println("pesos w = \t" + w[1][0]);
            System.out.println("pesos w = \t" + w[2][0]);
            System.out.println("pesos w = \t" + w[3][0]);
            n = n + 1;
            System.out.println("número de treinamentos " + n + "\n");
        } 
    }
    
    public double [][] entradaf1(double [][]w) {
        System.out.println("entrada f1 ");
        
        for(int i = 0; i < 300; i++){
            t[i] = w[0][0] + w[1][0]*f1[i] + w[2][0]*f2[i] + w[3][0]*f3[i];
            w[0][0] = w[0][0] + taxa_aprendizado*Math.pow((f[i] - t[i]), 2.0)*0.5;
            w[1][0] = w[1][0] + taxa_aprendizado*Math.pow((f[i] - t[i]), 2.0)*0.5*f1[i];
            w[2][0] = w[2][0] + taxa_aprendizado*Math.pow((f[i] - t[i]), 2.0)*0.5*f2[i];
            w[3][0] = w[3][0] + taxa_aprendizado*Math.pow((f[i] - t[i]), 2.0)*0.5*f3[i];
        }
        return w;
    }
    
    public double [] testa_af(double [][] ww){
        System.out.println("TESTE DA REDE NEURAL \n");
        double []ff = new double[300];
        double []gg = new double[600];
        
        System.out.println("pesos resultantes do treinamento ");
        System.out.println("pesos w = \t" + ww[0][0]);
        System.out.println("pesos w = \t" + ww[1][0]);
        System.out.println("pesos w = \t" + ww[2][0]);
        System.out.println("pesos w = \t" + ww[3][0]);
        
        for(int i = 0; i < 300; i++)
            ff[i] = ww[0][0] + ww[1][0]*f4[i] + ww[2][0]*f5[i] + ww[3][0]*f6[i];
            int k = 0;
            for(int a = 0; a < 300; a++)
                gg[a] = f12[a];
            for(int j = 300; j < 600; j++){
                gg[j] = ff[k];
                k++;
            }
 
         return gg;
    }
    
}

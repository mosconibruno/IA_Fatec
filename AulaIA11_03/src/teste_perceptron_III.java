/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lab7
 */
public class teste_perceptron_III {
    
    public static void main(String args[]){
    
        perceptron_III d = new perceptron_III();
        
        double x3[][] = {
            {-1, -1},
            {0.0, 0.0},
            {0.15, 0.2},
            {0.33, 0.1}}; // entradas x1
        double x4[][] = {
            {-1, -1},
            {0.77, 0.55},
            {0.8, 0.1},
            {0.95, 0.75}};
        
        d.iteracao_perceptron();
        d.verifica_perceptron(d.w, x3, x4);
    }
}

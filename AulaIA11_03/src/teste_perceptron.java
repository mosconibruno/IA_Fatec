/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lab7
 */
public class teste_perceptron {
 
    public static void main(String args[]){
    
        perceptron p = new perceptron();
        p.iteracao();
        p.testa_rede(p.w, p.x0, p.x1);
        
    
    }
    
}

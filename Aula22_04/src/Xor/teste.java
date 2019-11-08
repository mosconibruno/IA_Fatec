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
public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        backpropagation_xor b4 = new backpropagation_xor();
        b4.iteracao_IX();
        
        for(int x = 0; x <= 3; x++)
            b4.testa_2neuronios_discreta(b4.x1, b4.x2, x, b4.v0, b4.v1, b4.v2, b4.w1, b4.w2, b4.y0);
    
    }
    
}

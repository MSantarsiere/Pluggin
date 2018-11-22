/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.plug.dado;

/**
 *
 * @author Rembor
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rembor
 */
public class WriteCvs {

   

    // stampa il csv nella base directory del progetto
    
    static private File file = new File("matrice.csv");

    
    public static void writeDataAtOnce(ArrayList<Integer> linea) {

        
        
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            for (int i = 0; i < linea.size(); i++) {
                System.out.print(linea.get(i) + "  ");
                int indice = linea.get(i);

                out.print(indice + " ");
            }
            out.println();
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(WriteCvs.class.getName()).log(Level.SEVERE, null, ex);
        }
		
    }

}

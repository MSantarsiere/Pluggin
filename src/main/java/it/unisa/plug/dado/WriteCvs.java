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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Rembor
 */
public class WriteCvs {

    // stampa il csv nella base directory del progetto
    static private File file = new File("C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto\\matrice.csv");
    static private File file1 = new File("C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto\\matricecosti.csv");
 
   
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

    static void createCostMatrix() {
        
        
BufferedReader br = null;
		FileReader fr = null;

		try {

            FileWriter fw = new FileWriter(file1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            
            BufferedReader r=new BufferedReader(new FileReader(file));
Scanner scanner = new Scanner(file);  
while (scanner.hasNextLine()) {  
   String line = scanner.nextLine();
   int count = StringUtils.countMatches(line, "1");
               out.println(count);
            out.flush();		
              
}
	

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
    }

	
    

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        File permutazioni=new File("C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto\\Var.txt");
               
              WriteCvs.createNewXMl(permutazioni);
    }

    static void createNewXMl(File permutazioni) throws ParserConfigurationException, SAXException, IOException {
 

            String[] array = null;
            BufferedReader r=new BufferedReader(new FileReader(permutazioni));
Scanner scanner = new Scanner(permutazioni);  
while (scanner.hasNextLine()) {  
   String line = scanner.nextLine();
   array = line.split(" ", -1);
               	
              
}

for (int i=0;i<array.length;i++){
 
    
    
}
	

                 File fXmlFile = new File("C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto\\testSuite.xml");           
         
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);

            dbf.setNamespaceAware(true);
        
            dbf.setFeature("http://xml.org/sax/features/namespaces", false);
        
            dbf.setFeature("http://xml.org/sax/features/validation", false);
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(fXmlFile);
 NodeList list3 = doc.getElementsByTagName("TestCase");

                  //  System.out.println(list3.getLength());

            for (int count = 0; count < list3.getLength(); count++) {
                // System.out.println(list3.item(count).getAttributes().getNamedItem("ci").getNodeValue());

                Node tempNode = list3.item(count);

                // make sure it's element node.
                if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                    if (tempNode.hasAttributes()) {

                        // get attributes names and values
                        NamedNodeMap nodeMap4 = tempNode.getAttributes();

                        for (int i = 0; i < nodeMap4.getLength(); i++) {
                            Node node = nodeMap4.item(i);
                            String tes = "id";

                            if (tes.equals(node.getNodeName())) {
                                Node node1 = node;

                                if (node1.getNodeValue().equals(array[i])) {
                               
                                    
                                } 

                            }

                        }

                    }

                }

            }

                
                
                
                
		                    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.plug.dado;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Rembor
 */
@Mojo(name = "hello")
public class MyMojo extends AbstractMojo {

    @Parameter(property = "msg")

    /**
     * My File.
     *
     * @parameter msg tiene la path del pom del progetto che si prende
     */
    private String msg;

    public void execute()
            throws MojoExecutionException {

        try {
            getLog().info("Hello " + msg);
            openFile(msg);
        } catch (IOException ex) {
            Logger.getLogger(MyMojo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private void openFile(String msg) throws IOException {

        File file = new File("C:\\Users\\Rembor\\Documents\\NetBeansProjects\\dado\\testSuite.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            String usr = document.getElementsByTagName("Class").item(0).getTextContent();
            String pwd = document.getElementsByTagName("method").item(0).getTextContent();
            getLog().info("Hello " + usr);
            getLog().info("Hello " + pwd);

            /*
             Serve per evitare il bug dovuto al fatto che il plugin non riconosce se sia mvn.bat o 
              mvn.cmd funziona solo per windows
             */
            String mvn = getMvnCommand();
            Process process = Runtime.getRuntime().exec(mvn + " clean verify -f C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto\\pom.xml -Dtest=" + usr + "#" + pwd);
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(MyMojo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String getMvnCommand() {
        String mvnCommand = "mvn";
        if (File.separatorChar == '\\') {
            mvnCommand = findExecutableOnPath("mvn.cmd");
            if (mvnCommand == null) {
                mvnCommand = findExecutableOnPath("mvn.bat");
            }
        }
        return mvnCommand;
    }

    public static String findExecutableOnPath(String name) {
        for (String dirname : System.getenv("PATH").split(File.pathSeparator)) {
            File file = new File(dirname, name);
            if (file.isFile() && file.canExecute()) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }

}

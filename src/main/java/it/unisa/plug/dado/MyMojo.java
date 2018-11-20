/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.plug.dado;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

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
     * @parameter
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

    }

}

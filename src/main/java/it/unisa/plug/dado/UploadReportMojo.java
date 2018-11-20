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
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

@Mojo(name = "upload", defaultPhase = LifecyclePhase.TEST, threadSafe = true, requiresDependencyResolution = ResolutionScope.TEST)
public class UploadReportMojo
        extends AbstractMojo {


    @Parameter(property = "titti", defaultValue="")
    private String endPoint;


    public void execute()
            throws MojoExecutionException {
         getLog().info("Hejcccpvyipio " + endPoint);
        File file = new File("target/perf4junit/report.xml");
        FileInputStream fis;
        BufferedInputStream bis;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost http = new HttpPost(endPoint);
            http.setEntity(new InputStreamEntity(bis, file.length()));
            HttpResponse response = httpclient.execute(http);
            getLog().info(endPoint);
            bis.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
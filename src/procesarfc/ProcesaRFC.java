/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesarfc;

import java.net.URL;
import java.io.*;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author lelguea
 */
public class ProcesaRFC {
    
    public static void main(String[] args) throws Exception {
        
        System.setProperty("https.proxyHost", "proxyti");
        System.setProperty("https.proxyPort", "8080");
        ArrayList<Integer>  lista = new ArrayList<>();
        
        lista.add(1883);
        lista.add(1887);
        lista.add(1924);
        lista.add(2080);
        lista.add(4271);
        lista.add(4273);
        lista.add(4274);
        lista.add(4275);
        lista.add(4276);
        lista.add(4277);
    
        System.out.println("\\begin{table}[htbp]\n" +
            "\\centering\n" +
            "\\caption{Estándares con base en RFC}\n" +
            "\\label{RFC}\n" +
            "\\begin{tabular}{lllr}\n" +
            "\\cline{1-4}\n" +
            "\\\\\n" +
            "Título & Texto & PDF & Información \\\\ \\\\ \\cline{1-4} \n" +
            "\\\\");
        
        for (int i=0;i<lista.size();i++) {
            int id=lista.get(i);
            String httpsURL = "https://www.rfc-editor.org/info/rfc"+id;
            URL myurl = new URL(httpsURL);
            HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
            InputStream ins = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(isr);

            String inputLine;
            String Salida="";

            while ((inputLine = in.readLine()) != null) {
              Salida=Salida+"\n"+inputLine;
            }
            //System.out.println(Salida);
            int inicio=Salida.indexOf("citation_title");
            int fin=Salida.indexOf("\">", inicio+2);
            //System.out.println(inicio+"-"+fin);

            String Titulo=Salida.substring(inicio+25, fin);
            //System.out.println(Titulo);
            System.out.println(Titulo.trim()+" & \\urllink[border=1,color=0 0 1]{https://www.rfc-editor.org/rfc/rfc"+id+".txt}{RFC"+id+".txt} & \\urllink[border=1,color=1 0 0]{https://www.rfc-editor.org/rfc/pdfrfc/rfc"+id+".txt.pdf}{RFC"+id+".pdf} & \\urllink[border=1,color=0 1 0]{https://www.rfc-editor.org/info/rfc"+id+"}{RFC"+id+"} \\\\ \\\\ \n");
            in.close();
        }
        System.out.println("\\cline{1-4} \n" +
            "\\end{tabular}\n" +
            "\\end{table}");
    }
}
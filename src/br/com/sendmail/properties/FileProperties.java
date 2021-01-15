package br.com.sendmail.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileProperties {

   
    public static Properties getProp() throws IOException
    {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./properties/dados.properties");
        props.load(file);
        return props;
       
    }
   
    public static void main(String args[]) throws IOException
    {
        Properties prop = getProp();
        System.out.println("Nome = "+prop.getProperty("prop.nome"));
        System.out.println("Diretorio = "+prop.getProperty("prop.dir"));
    }
}
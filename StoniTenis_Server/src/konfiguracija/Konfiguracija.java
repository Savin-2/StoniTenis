/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Savin
 */
public class Konfiguracija {

    private static Konfiguracija instance;
    private Properties konfiguracija;

    private Konfiguracija() {
        try {
            konfiguracija = new Properties();
            konfiguracija.load(new FileInputStream("C:\\Users\\Savin\\Documents\\NetBeansProjects\\StoniTenis_Server\\config\\config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.getLogger(Konfiguracija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static Konfiguracija getInstance() {

        if (instance == null) {

            instance = new Konfiguracija();
        }
        return instance;
    }

    public String getProperty(String key){
    return konfiguracija.getProperty(key, "n/a");
    }
    
    public void setProperty(String key, String value){
    
    konfiguracija.setProperty(key, value);
    }

    public void sacuvajIzmene(){
    
        try {
            konfiguracija.store(new FileOutputStream("C:\\Users\\Savin\\Documents\\NetBeansProjects\\StoniTenis_Server\\config\\config.properties"), null);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.getLogger(Konfiguracija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    
    }
    
}

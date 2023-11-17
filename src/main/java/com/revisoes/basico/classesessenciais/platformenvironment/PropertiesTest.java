package com.revisoes.basico.classesessenciais.platformenvironment;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args)
            throws Exception {

        // set up new properties object from file "myProperties.txt"
        FileInputStream propFile = new FileInputStream("myProperties.txt");
        
        Properties myProperty = new Properties(System.getProperties());
        myProperty.load(propFile);

        // set the system properties
        System.setProperties(myProperty);
        // display new properties
        System.getProperties().list(System.out);
    }
}
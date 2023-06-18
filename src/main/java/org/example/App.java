package org.example;

import org.example.Servicios.GestionAdministrator;


public class App 
{
    public static void main( String[] args )
    {
        GestionAdministrator admin=new GestionAdministrator();
       // admin.addAdministrator();
        admin.listAdministrator();
        admin.deleteAdministrator();


    }
}

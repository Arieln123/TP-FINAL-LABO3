package org.example;


import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {

        Scanner scanner = new Scanner(System.in);

        Menu menu=new Menu();

        menu.startMenu(scanner);
        scanner.close();


    }
}

package org.example.Servicios;


import org.example.Modelos.Recepcionist;
import org.example.Modelos.Status;
import org.example.Repositorios.IRepository;
import org.example.Repositorios.PassengerRepo;
import org.example.Repositorios.RecepcionRepo;
import org.example.Modelos.Passenger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import  java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;







        Date fecha2 = formato.parse(fechaF2);

        if(fecha1 >= fecha2){
        System.out.println( "Fecha 1 es mayor o igual a fecha2" );
        }else{
        System.out.println( "Fecha2 es menor que fecha1");
        }


public class GestionRecepcionist{
    IRepository<Recepcionist>  recepcionistRepo= new RecepcionRepo();
    IRepository<Passenger> passRepo=new PassengerRepo();

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";


    public GestionRecepcionist() {
    }

    public void listRecepcionist() {

       RecepcionRepo admin=new RecepcionRepo();
        List<Recepcionist> lista =admin.listar();

        for (int i=0;i<lista.size();i++){
            if (lista.get(i).getStatus()== Status.ACTIVE) {
                System.out.println(lista.get(i));
            }
        }
    }

    public void addRecepcionist(ArrayList<Recepcionist> lista){
        Recepcionist recep=new Recepcionist();
        Scanner scanner=new Scanner();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Ingrese nombre del recepcionista:");
            recep.setName(scanner.nextLine());
            System.out.println("Ingrese apellido del recepcionista:");
            recep.setLastName(scanner.nextLine());
            System.out.println("Ingrese la direccion del recepcionista: ");
            recep.setAddress(scanner.nextLine());
            System.out.println("Ingrese  DNI del recepcionista: ");
            recep.setDni(scanner.nextLine());
            System.out.println("Ingrese el pais de ");
            recep.setCountry(scanner.nextLine());
            recep.setStatus(Status.ACTIVE);
            recep.setId(recepcionistRepo.listar().size()+1);

            try{
                if (passRepo.existe(recep)==false){
                    passRepo.agregar(recep);
                    System.out.println("El nuevo recepcionista se ha agregado correctamente");
                }
                else {
                    throw new IOException("Este es un error personalizado");
                }
            }
            catch (IOException e){
                System.out.println("“El recepcionista ya existe”");

            }

            System.out.println("¿Desea agregar otro recepcionista? s/n");
            seguir = scanner.next();
        }
        scanner.close();
        lista.add(recep);
    }
    public void addPassenger(ArrayList<Passenger> lista){
        Passenger pass=new Passenger();
        Recepcionist recep=new Recepcionist();
        Scanner scanner=new Scanner();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {
            recep.makeReservation(pass);
            pass.setId(passRepo.listar().size()+1);

            try{
                if (passRepo.existe(pass)==false){
                    passRepo.agregar(pass);
                    System.out.println("El nuevo pasajero se ha agregado correctamente");
                }
                else {
                    throw new IOException("Este es un error personalizado");
                }
            }
            catch (IOException e){
                System.out.println("“El pasajero ya existe”");

            }

            System.out.println("¿Desea agregar otro pasajero? s/n");
            seguir = scanner.next();
        }
        scanner.close();
        lista.add(pass);
    }

    public void addRecepcionist() {
        Scanner sc=new Scanner(System.in);
        Recepcionist recep = new Recepcionist();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nombre");
            recep.setName(sc.next());
            System.out.println("Ingrese el Apellido");
            recep.setLastName(sc.next());
            System.out.println("Ingrese  Direccion");
            recep.setAddress(sc.next());
            System.out.println("Ingrese  Dni");
            recep.setDni(sc.next());
            recep.setId(recepcionistRepo.listar().size()+1);
            recep.setStatus(Status.ACTIVE);
            try{
                if (recepcionistRepo.existe(recep)==false){
                    recepcionistRepo.agregar(recep);
                    System.out.println("El nuevo Recepcionista se ha agregado"+GREEN+" correctamente"+RESET);
                }
                else {
                    throw new IOException("Este es un error personalizado");
                }
            }
            catch (IOException e){
                System.out.println(GREEN+"El Recepcionista ya existe"+RESET);
            }
            System.out.println("¿Desea agregar otro Recepcionista? s/n");
            seguir = sc.next();
        }
        sc.close();
    }



}

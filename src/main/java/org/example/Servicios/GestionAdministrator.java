package org.example.Servicios;

import org.example.Modelos.Administrator;
import org.example.Modelos.Passenger;
import org.example.Modelos.Recepcionist;
import org.example.Modelos.Status;
import org.example.Repositorios.AdministratorRepo;
import org.example.Repositorios.IRepository;
import org.example.Repositorios.PassengerRepo;
import org.example.Repositorios.RecepcionRepo;

import java.io.IOException;
import java.util.*;

public class GestionAdministrator  {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    IRepository<Recepcionist> recepcionistRepo = new RecepcionRepo();

    IRepository<Administrator> adminRepo = new AdministratorRepo();
    IRepository<Passenger> passengerRepo = new PassengerRepo();

    public GestionAdministrator() {
    }

    public void listAdministrator() {

        AdministratorRepo admin=new AdministratorRepo();
        List<Administrator> lista =admin.listar();

        for (int i=0;i<lista.size();i++){
            if (lista.get(i).getStatus()== Status.ACTIVE) {
                System.out.println(lista.get(i));
            }
        }
    }

    public void addAdministrator(Scanner scanner) {
        Scanner sc=new Scanner(System.in);
        Administrator admin = new Administrator();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nombre");
            admin.setName(sc.next());


            System.out.println("Ingrese el Apellido");
            admin.setLastName(sc.next());

            System.out.println("Ingrese  Direccion");
            admin.setAddress(sc.next());
            System.out.println("Ingrese  Dni");
            admin.setDni(sc.next());


            System.out.println("Ingrese la Pais");
            admin.setCountry(sc.next());

            admin.setId(adminRepo.listar().size()+1);
            admin.setStatus(Status.ACTIVE);
            try{
                if (adminRepo.existe(admin)==false){
                    adminRepo.agregar(admin);
                    System.out.println("El nuevo Admin se ha agregado"+GREEN+" correctamente"+RESET);
                }
                else {
                    throw new IOException("Este es un error personalizado");
                }
            }
            catch (IOException e){
                System.out.println(GREEN+"El Admin ya existe"+RESET);

            }

            System.out.println("¿Desea agregar otro Admin? s/n");
            seguir = sc.next();

        }

    }

    public void addPassengert(Scanner scanner){

        Scanner sc=new Scanner(System.in);
        Passenger passenger = new Passenger();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nombre");
            passenger.setName(sc.next());


            System.out.println("Ingrese el Apellido");
            passenger.setLastName(sc.next());

            System.out.println("Ingrese  Direccion");
            passenger.setAddress(sc.next());
            System.out.println("Ingrese  Dni");
            passenger.setDni(sc.next());



            System.out.println("Ingrese la Pais");
            passenger.setCountry(sc.next());

            passenger.setId(passengerRepo.listar().size()+1);
            passenger.setStatus(Status.ACTIVE);
            try{
                if (passengerRepo.existe(passenger)==false){
                    passengerRepo.agregar(passenger);
                    System.out.println("El nuevo Pasajero se ha agregado"+GREEN+" correctamente"+RESET);
                }
                else {
                    throw new IOException("Este es un error personalizado");
                }
            }
            catch (IOException e){
                System.out.println(GREEN+"El Pasajero ya existe"+RESET);

            }


            System.out.println("¿Desea agregar otro Admin? s/n");
            seguir = sc.next();
        }







    }

    public void addRecepcionist(Scanner scanner) {
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

    }


    public void deleteAdministrator(Scanner scanner) {
        Scanner sc=new Scanner(System.in);
        Administrator admin = new Administrator();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Ingrese el numero de DNI del administrador a  "+RED+"eliminar"+RESET);
            admin.setDni(sc.next());
            if (!adminRepo.existe(admin)) {
                System.out.println(RED+"Error"+RESET+" al eliminar, el numero de DNI no pertenece a ningun cliente");
            } else {

                adminRepo.eliminar(admin);
                System.out.println("El Administrador se ha eliminado"+GREEN+" correctamente"+RESET);
            }
            System.out.println("¿Desea eliminar otro Administrador? s/n");
            seguir = sc.next();
        }

    }



}

package org.example;

import org.example.Modelos.Administrator;
import org.example.Modelos.Passenger;
import org.example.Modelos.Room;
import org.example.Repositorios.AdministratorRepo;
import org.example.Repositorios.IRepository;
import org.example.Repositorios.PassengerRepo;
import org.example.Repositorios.RoomRepo;
import org.example.Servicios.GestionAdministrator;
import  org.example.Modelos.Recepcionist;
import org.example.Servicios.GestionRecepcionist;
import org.example.Servicios.GestionRoom;


import java.util.Scanner;

public class Menu {

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String RESET = "\u001B[0m";

    public Menu() {
    }

    public void startMenu() {

        System.out.println("Bienvenido al sistema de reservas de HOTEL TRESVAGOS");
        System.out.println("Que clase de usuario eres?:");
        System.out.println(BLUE+"    1- Administrador"+RESET);
        System.out.println(GREEN+"    2- Recepcionista"+RESET);
        System.out.println(YELLOW+"    3- Pasajero"+RESET);
        switch (answerInt(2)) {
            case 1:
                administratorMenu();
                break;
            case 2:
                recepcionistMenu();
                break;
            default:
                System.out.println("\n\n\n\n\n");
                startMenu();
                break;
        }

    }

    public void administratorMenu() {
        System.out.println("Bienvenido" + BLUE + " ADMINISTRADOR"+RESET+", que desea hacer?");
        System.out.println("    1- Listar Administradores");
        System.out.println("    2- Crear usuarios");
        System.out.println("    3- Eliminar usuarios");
        System.out.println("    4-Volver al menu anterior");
        GestionAdministrator admin = new GestionAdministrator();

        switch (answerInt(4)) {
            case 1:
                System.out.println(BLUE + "La lista de administradores activos:" + RESET);
                admin.listAdministrator();
                administratorMenu();
                break;
            case 2:
                System.out.println("que usuario desea" + GREEN + " crear" + RESET);
                System.out.println("1- Administrador");
                System.out.println("2- Recepcionista");
                System.out.println("3- Pasajero");
                switch (answerInt(3)) {
                    case 1:
                        admin.addAdministrator();
                        administratorMenu();
                        return;
                    case 2:
                        admin.addRecepcionist();
                        System.out.println("asd");
                        administratorMenu();
                        return;
                    case 3:
                        System.out.println("asd");
                        admin.addPassengert();
                        break;
                }
                break;
            case 3:
                System.out.println("que usuario desea" + RED + "eliminar" + RESET);
                System.out.println("1- Administrador");
                System.out.println("2- Recepcionista");
                System.out.println("3- Pasajero");
                switch (answerInt(3)) {
                    case 1:
                        admin.deleteAdministrator();
                        administratorMenu();

                        break;
                    case 2:

                        break;
                    case 3:
                        administratorMenu();
                        break;
                    case 5:
                        startMenu();
                        break;
                    default:
                        System.out.println("\n\n\n");
                        administratorMenu();
                        break;
                }
                break;
            case 4:
                System.out.println("\n\n\n");
                administratorMenu();
                break;
        }
    }
               public int answerInt(int lenght) {
                Scanner sc = new Scanner(System.in);
                int rta = 0;
                do {
                    System.out.print("Respuesta: ");
                    rta = sc.nextInt();
                } while (rta < 0 || rta > lenght);
                int res=rta;
                sc.close();
                return res;
            }
           public int answerInt() {
                Scanner sc = new Scanner(System.in);
                System.out.print("Respuesta: ");
                return sc.nextInt();
            }


    public void recepcionistMenu() {

        Recepcionist recepcionist = new Recepcionist();
        GestionRecepcionist gest=new GestionRecepcionist();
        IRepository<Room> roomRepo=new RoomRepo();
        IRepository<Passenger> passRepo=new PassengerRepo();
        Scanner sc=new Scanner(System.in);
        System.out.println("Bienvenido RECEPCIONISTA, ingrese su DNI");
        recepcionist.setDni(sc.nextLine());

        if (recepcionist == null) {
            System.out.println("ERROR");
            recepcionistMenu();
        } else {
            System.out.println("Hola " + recepcionist.getName() + "!");
            System.out.println("Que deseas hacer?");
            System.out.println("    1- Hacer reservas");
            System.out.println("    2- Ver estado de habitaciones");
            System.out.println("    3- Ver pasajeros");
            switch (answerInt(3)) {
                case 1:
                     gest.makeReservation();
                    break;
                case 2:
                    System.out.println(roomRepo.listar());
                    break;
                case 3:
                    System.out.println(passRepo.listar());
                    break;
                default:
                    System.out.println("\n\n\n");
                    recepcionistMenu();
                    break;
            }
        }
    }

/*
    public void addUser(Data data) {
        int typeUser = 0;
        String name = null;
        String lastName = null;
        Integer id = null;
        String address = null;
        String country = null;
        System.out.println("Ingrese tipo de Usuario: 1:recepcionista / 2:pasajero");
        typeUser = answerInt();
        System.out.println("Ingrese su DNI");
        id = answerInt();
        System.out.println("Apellido: ");
        lastName = DataManager.answerString();
        System.out.println("Dirección: ");
        address = DataManager.answerString();
        System.out.println("Nacionalidad: ");
        country = DataManager.answerString();
        if (typeUser == 1) {
            Recepcionist recepcionist = new Recepcionist(id, name, lastName, address, country);
            data.addRecepcionist(recepcionist);
        } else {
            Passenger passenger = new Passenger(id, name, lastName, address, country);
            data.addPassenger(passenger);
        }
    }*/

}
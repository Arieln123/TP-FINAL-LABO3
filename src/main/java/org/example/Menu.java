package org.example;

import org.example.Modelos.Passenger;
import org.example.Modelos.Room;
import org.example.Repositorios.IRepository;
import org.example.Repositorios.PassengerRepo;
import org.example.Repositorios.RecepcionRepo;
import org.example.Repositorios.RoomRepo;
import org.example.Servicios.GestionAdministrator;
import  org.example.Modelos.Recepcionist;
import org.example.Servicios.GestionPassenger;
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

    public void startMenu(Scanner scanner) {
        Scanner sc=new Scanner(System.in);
        String seguir = "s";
        int opcion = 0;

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Bienvenido al sistema de reservas de HOTEL TRESVAGOS");
            System.out.println("Que clase de usuario eres?:");
            System.out.println(BLUE+"    1- Administrador"+RESET);
            System.out.println(GREEN+"    2- Recepcionista"+RESET);

            try {
                opcion = scanner.nextInt();
            } catch (RuntimeException e) {
                System.out.println("Caracter invalido, debe ingresar un numero");
                scanner.nextLine();
            }

            switch (opcion) {
                case 1:
                    administratorMenu(scanner);
                    break;
                case 2:
                    recepcionistMenu(scanner);
                    break;
                case 3:
                    seguir = "n";
                    break;
                default:
                    System.out.println("el numero no es valido");
                    break;
            }

            if (!seguir.equalsIgnoreCase("n")) {
                System.out.println("¿Desea volver al menu? s/n");
                seguir = scanner.next();
            }
        }
    }

    public void administratorMenu(Scanner scanner) {
        String seguir = "s";
        int opcion = 0;


        while (seguir.equalsIgnoreCase("s")) {
            System.out.println("Bienvenido" + BLUE + " ADMINISTRADOR" + RESET + ", que desea hacer?");
            System.out.println(YELLOW+"    1- Listar Administradores"+RESET);
            System.out.println("    2- Crear usuarios o Habitacion");
            System.out.println("    3- Eliminar usuarios");
            System.out.println("    4-Volver al menu anterior");
            Scanner scanner1 = scanner;
            GestionAdministrator admin = new GestionAdministrator();

            try {
                opcion = scanner1.nextInt();
            } catch (RuntimeException e) {
                System.out.println("Caracter invalido, debe ingresar un numero");
                scanner1.nextLine();
            }
            switch (opcion) {
                case 1:
                    ListaMenuAdministrador(scanner);

                    break;
                case 2:
                        altaMenuAdministrador(scanner);
                        break;
                case 3:
                    bajaMenuAdministrador(scanner);
                       break;

            }
        }
    }

    public void recepcionistMenu(Scanner scanner) {

        Recepcionist recepcionist = new Recepcionist();
        GestionRecepcionist gest=new GestionRecepcionist();
        IRepository<Room> roomRepo=new RoomRepo();
        IRepository<Passenger> passRepo=new PassengerRepo();
        Scanner sc=new Scanner(System.in);
        System.out.println("Bienvenido"+GREEN+"RECEPCIONISTA"+GREEN+", ingrese su DNI");
        recepcionist.setDni(sc.nextLine());

        if (recepcionist == null) {
            System.out.println("ERROR");
            recepcionistMenu(scanner);
        } else {
            System.out.println("Hola " + recepcionist.getName() + "!");
            System.out.println("Que deseas hacer?");
            System.out.println("    1- Hacer reservas");
            System.out.println("    2- Ver estado de habitaciones");
            System.out.println("    3- Ver pasajeros");

            System.out.print("Respuesta: ");
            String rta = sc.next();
            switch (rta) {
                case "1":
                     gest.makeReservation(scanner);
                    break;
                case "2":
                    System.out.println(roomRepo.listar());
                    break;
                case "3":
                    System.out.println(passRepo.listar());
                    break;
                default:
                    System.out.println("\n\n\n");
                    recepcionistMenu(scanner);
                    break;
            }
        }
        sc.close();
    }

    public  void altaMenuAdministrador(Scanner scanner){

        Scanner sc=new Scanner(System.in);
        String seguir = "s";
        int opcion = 0;
        GestionAdministrator admin = new GestionAdministrator();

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("que usuario desea" + GREEN + " crear" + RESET);
            System.out.println("1- Administrador");
            System.out.println("2- Recepcionista");
            System.out.println("3- Pasajero");
            System.out.println("4- Habitacion");
            System.out.println("Cualquier otra tecla- Volver al menu anterior");

            try {
                opcion = scanner.nextInt();
            } catch (RuntimeException e) {
                System.out.println("Caracter invalido, debe ingresar un numero");
                scanner.nextLine();
            }

            switch (opcion) {
                case 1:
                    admin.addAdministrator(scanner);
                    break;
                case 2:
                    admin.addRecepcionist(scanner);
                    break;
                case 3:
                    admin.addPassengert(scanner);
                    break;
                case 4:
                    GestionRoom room = new GestionRoom();
                    room.addRoom(scanner);
                    break;
                case  5:
                    seguir="n";
                    break;

                default:

                    System.out.println("el numero no es valido");
                    break;
            }

            if (!seguir.equalsIgnoreCase("n")) {
                System.out.println("¿Desea volver al menu? s/n");
                seguir = scanner.next();
            }
        }
    }


    public  void bajaMenuAdministrador(Scanner scanner){

        Scanner sc=new Scanner(System.in);
        String seguir = "s";
        int opcion = 0;
        GestionAdministrator admin = new GestionAdministrator();

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("que usuario desea" + RED + " eliminar" + RESET);
            System.out.println("1- Administrador");
            System.out.println("2- Recepcionista");
            System.out.println("3- Pasajero");
            System.out.println("4- Habitacion");
            System.out.println("5-Volver");

            try {
                opcion = scanner.nextInt();
            } catch (RuntimeException e) {
                System.out.println("Caracter invalido, debe ingresar un numero");
                scanner.nextLine();
            }

            switch (opcion) {
                case 1:
                        admin.deleteAdministrator(scanner);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case  5:
                    seguir="n";
                    break;
                default:
                    System.out.println("el numero no es valido");
                    break;
            }

            if (!seguir.equalsIgnoreCase("n")) {
                System.out.println("¿Desea volver al menu? s/n");
                seguir = scanner.next();
            }
        }
    }




    public  void ListaMenuAdministrador(Scanner scanner){

        Scanner sc=new Scanner(System.in);
        String seguir = "s";
        int opcion = 0;


        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("que usuario desea" + YELLOW + " Listar" + RESET);
            System.out.println("1- Administrador");
            System.out.println("2- Recepcionista");
            System.out.println("3- Pasajero");
            System.out.println("4- Habitacion");
            System.out.println("5-Volver");

            try {
                opcion = scanner.nextInt();
            } catch (RuntimeException e) {
                System.out.println("Caracter invalido, debe ingresar un numero");
                scanner.nextLine();
            }

            switch (opcion) {
                case 1:
                    GestionAdministrator admin = new GestionAdministrator();
                    admin.listAdministrator();

                    break;
                case 2:
                    GestionRecepcionist recep=new GestionRecepcionist();
                        recep.listRecepcionist();
                    break;
                case 3:
                    GestionPassenger pass =new GestionPassenger();
                    pass.listPassenger();
                    break;
                case 4:

                    break;
                case  5:
                    seguir="n";
                    break;
                default:
                    System.out.println("el numero no es valido");
                    break;
            }

            if (!seguir.equalsIgnoreCase("n")) {
                System.out.println("¿Desea volver al menu? s/n");
                seguir = scanner.next();
            }
        }
    }
}

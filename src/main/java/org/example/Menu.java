package org.example;

import org.example.Modelos.Administrator;
import org.example.Modelos.Passenger;
import org.example.Modelos.Room;
import org.example.Repositorios.*;
import org.example.Servicios.*;
import  org.example.Modelos.Recepcionist;


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

            System.out.println("Bienvenido al sistema de reservas de HOTEL "+BLUE+"TRES"+CYAN+"VA"+RED+"GOS"+RESET);
            System.out.println("Que clase de usuario eres?:");
            System.out.println(BLUE+"    1- Administrador"+RESET);
            System.out.println(GREEN+"    2- Recepcionista"+RESET);
            System.out.printf("    3- TERMINAR\n");

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
        Administrator admin=new Administrator();
        AdministratorRepo repo=new AdministratorRepo();
        Scanner sc=new Scanner(System.in);

        while (seguir.equalsIgnoreCase("s")) {
            System.out.println("Bienvenido" + BLUE + " ADMINISTRADOR" + RESET + ", ingrese su DNI");
            admin.setDni(sc.nextLine());


            boolean verdad = repo.existe(admin);
            if (verdad == false) {
                System.out.println(RED+"ERROR"+RESET);
                administratorMenu(scanner);
            } else {
                System.out.print(BLUE+"Bienvenido, ");
                System.out.print(repo.info(admin).getName());
                System.out.print(" ");
                System.out.println(repo.info(admin).getLastName());
                System.out.println("¿Que deseas hacer?");
                System.out.println(YELLOW + "    1- Listar" + RESET);
                System.out.println(GREEN + "    2- Crear" + RESET);
                System.out.println(RED + "    3- Eliminar usuarios" + RESET);
                System.out.println("    4-Volver al menu anterior");

                try {
                    opcion = sc.nextInt();
                } catch (RuntimeException e) {
                    System.out.println("Caracter invalido, debe ingresar un numero");
                    sc.nextLine();
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
                    case 4:
                        startMenu(scanner);
                        break;
                }
            }
        }
    }

    public void recepcionistMenu(Scanner scanner) {
        String seguir = "s";
        Recepcionist recepcionist = new Recepcionist();
        GestionRecepcionist gest=new GestionRecepcionist();
        RecepcionRepo repo=new RecepcionRepo();
        GestionRoom gestr = new GestionRoom();
        IRepository<Room> roomRepo=new RoomRepo();
        IRepository<Passenger> passRepo=new PassengerRepo();
        Scanner sc=new Scanner(System.in);
        System.out.println("Bienvenido"+GREEN+" RECEPCIONISTA"+RESET+", ingrese su DNI");
        recepcionist.setDni(sc.nextLine());
        while (seguir.equalsIgnoreCase("s")) {
        boolean  verdad=repo.existe(recepcionist);
        if (verdad == false ) {
            System.out.println(RED+"ERROR"+RESET);
            recepcionistMenu(scanner);
        } else {
            System.out.print(GREEN+"Bienvenido, ");
            System.out.print(repo.info(recepcionist).getName());
            System.out.print(" ");
            System.out.println(repo.info(recepcionist).getLastName());
            System.out.println("Que deseas hacer?");
            System.out.println(RESET);
            System.out.println("    1- Hacer reservas");
            System.out.println("    2- Ver estado de habitaciones");
            System.out.println("    3- Ver pasajeros");
            System.out.println("    4- Listado de reservas y tarifas.");

            System.out.print("Respuesta: ");
            int rta = sc.nextInt();
            switch (rta) {
                case 1 :
                     gest.makeReservation(scanner);
                    break;
                case 2:
                    System.out.println(YELLOW+"LISTA DE HABITACIONES"+RESET);
                    gestr.listRoom();
                    break;
                case 3:
                    System.out.println(YELLOW+"LISTA DE PASAJEROS"+RESET);
                    GestionPassenger pass =new GestionPassenger();
                    pass.listPassenger();
                    break;
                case 4:
                    System.out.println(YELLOW+"LISTA DE TARIFAS"+RESET);
                    GestionBooking gestBook=new GestionBooking();
                    gestBook.listBooking();
                    break;
                case  5:
                    seguir="n";
                    break;
                default:
                    System.out.println("el numero no es valido");
                    break;
            }
        }
            if (!seguir.equalsIgnoreCase("n")) {
                System.out.println("¿Desea volver al menu? s/n");
                seguir = scanner.next();
            }
    }
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
            System.out.println("5- Volver al menu anterior");

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
            System.out.println("3- Habitacion");
            System.out.println("4-Volver");

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
                     admin.deleteRecepcionist(scanner);

                    break;
                case 3:
                        GestionRoom room=new GestionRoom();
                        room.modificar(scanner);
                    break;

                case  4:
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

            System.out.println("Que  desea" + YELLOW + " Listar" + RESET);
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
                    System.out.println(YELLOW+"LISTA DE ADMINISTRADORES"+RESET);
                    GestionAdministrator admin = new GestionAdministrator();
                    admin.listAdministrator();
                    break;
                case 2:
                    System.out.println(YELLOW+"LISTA DE RECEPCIONISTAS"+RESET);

                    GestionRecepcionist recep=new GestionRecepcionist();
                        recep.listRecepcionist();
                    break;
                case 3:
                    System.out.println(YELLOW+"LISTA DE PASAJEROS"+RESET);

                    GestionPassenger pass =new GestionPassenger();
                    pass.listPassenger();
                    break;
                case 4:
                    System.out.println(YELLOW+"LISTA DE HABITACIONES"+RESET);

                    GestionRoom Room=new GestionRoom();
                        Room.listRoom();
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

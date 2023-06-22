package org.example.Servicios;


import org.example.Modelos.*;
import org.example.Repositorios.IRepository;
import org.example.Repositorios.RoomRepo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import  java.util.Scanner;

public class GestionRoom {
    IRepository<Room> roomRepo=new RoomRepo();

    public void addRoom() {
        Scanner sc=new Scanner(System.in);
        Room room = new Room();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Ingrese el Id");
            room.setId(sc.nextInt());
            System.out.println("Ingrese el tipo de habitación" + " 1-  SIMPLE, 2-DOBLE, 3-SUITE");
            int input=sc.nextInt();
            if (input==1)
                room.setType(Type.SIMPLE);
            if (input==2)
                room.setType(Type.DOBLE);
            if(input==3)
                room.setType(Type.SUITE);

            room.setId(roomRepo.listar().size()+1);
            room.setStatus(RoomStatus.DISPONIBLE);
            try{
                if (!roomRepo.existe(room)){
                    roomRepo.agregar(room);
                    System.out.println("La nueva habitacion se ha agregado  correctamente");
                }
                else {
                    throw new IOException("Este es un error personalizado");
                }
            }
            catch (IOException e){
                System.out.println("La habitacion ya existe");

            }


            System.out.println("¿Desea agregar otra habitacion? s/n");
            seguir = sc.next();
        }
        sc.close();
    }

    public void modificar(Scanner scanner) {
        Room room = new Room();
        String seguir = "s";
        while (seguir.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el numero de ID de la habitacion a modificar ");
            room.setId(scanner.nextInt());
            if (!roomRepo.existe(room)) {
                System.out.println("Error el ID no pertenece a una habitacion.");
            } else {
                scanner.nextLine();
                System.out.println("ingrese estatus \n 1-  DISPONIBLE, 2-NO_DISPONIBLE, 3-OCUPADO");
                int categoria = scanner.nextInt();
                if (categoria == 1)
                    room.setStatus(RoomStatus.DISPONIBLE);
                if (categoria == 2)
                    room.setStatus(RoomStatus.NO_DISPONIBLE);//reparacion, o eliminacion
                if (categoria == 3)
                    room.setStatus(RoomStatus.OCUPADO);
                System.out.println("La habitacion se modifico correctamente");
            }
            System.out.println("¿Desea modificar otra Habitacion? s/n");
            seguir = scanner.next();
        }
    }

}

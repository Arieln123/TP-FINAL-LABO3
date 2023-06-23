package org.example.Servicios;


import org.example.Modelos.*;
import org.example.Repositorios.AdministratorRepo;
import org.example.Repositorios.IRepository;
import org.example.Repositorios.RecepcionRepo;
import org.example.Repositorios.RoomRepo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import  java.util.Scanner;

public class GestionRoom {
    IRepository<Room> roomRepo=new RoomRepo();
    ArrayList<Room> roomList = new ArrayList<>();

    public void addRoom(Scanner scanner)  {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Scanner sc=new Scanner(System.in);
        Room room = new Room();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {
            roomList = roomRepo.listar();
            room.setId(roomRepo.listar().size()+1);
            Date fechaInic= null;
            try {
                fechaInic = formato.parse("2023-06-22");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            room.setDiaInicial(fechaInic);
            room.setDiaFinal(fechaInic);

            System.out.println("Ingrese el tipo de habitación" + " 1-  SIMPLE, 2-DOBLE, 3-SUITE");
            int input=sc.nextInt();
            switch (input) {
                case 1:
                    room.setType(Type.SIMPLE);
                    break;
                case 2:
                    room.setType(Type.DOBLE);
                    break;
                case  3:
                    room.setType(Type.SUITE);
                    break;
            }
            room.setStatus(RoomStatus.DISPONIBLE);
            if (!roomRepo.existe(room)){
                roomRepo.agregar(room);
                System.out.println("La nueva habitacion se ha agregado  correctamente");
            }
            else {
                System.out.println("La habitacion ya existe");
            }
            System.out.println("¿Desea agregar otra habitacion? s/n");
            seguir = sc.next();
        }

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
                roomRepo.modificar(room);
            }
            System.out.println("¿Desea modificar otra Habitacion? s/n");
            seguir = scanner.next();
        }
    }

    public void listRoom() {

        RoomRepo room= new RoomRepo();
        List<Room> lista =room.listar();

        for (int i=0;i<lista.size();i++){
            System.out.println(lista.get(i));
        }
    }

}

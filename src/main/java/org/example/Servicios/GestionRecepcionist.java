package org.example.Servicios;


import org.example.Modelos.*;
import org.example.Repositorios.IRepository;
import org.example.Repositorios.PassengerRepo;
import org.example.Repositorios.RecepcionRepo;

import java.util.ArrayList;
import java.util.List;
import  java.util.Scanner;

import org.example.Repositorios.RoomRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class GestionRecepcionist implements Reservation{
    IRepository<Recepcionist>  recepcionistRepo= new RecepcionRepo();
    IRepository<Passenger> passRepo=new PassengerRepo();
    IRepository<Room> roomRepo=new RoomRepo();

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

    public void addPassenger(Scanner scanner){
        GestionAdministrator admin=new GestionAdministrator();
        admin.addPassengert(scanner);
    }

    @Override
    public void makeReservation(Scanner scanner) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Room> rooms=new ArrayList<>();
        Room room1=new Room();

        scanner = new Scanner(System.in);
        addPassenger(scanner);
        System.out.println("ingrese tipo de habitacion deseada\n "+" 1-  SIMPLE, 2-DOBLE, 3-SUITE");
        int input=scanner.nextInt();
        if (input==1)
            room1.setType(Type.SIMPLE);
        if (input==2)
            room1.setType(Type.DOBLE);
        if(input==3)
            room1.setType(Type.SUITE);
        System.out.println("ingrese fecha de ingreso de pasajero:");
        try {
            room1.setDiaInicial(formato.parse(scanner.nextLine()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("ingrese fecha de egreso de pasajero:");
        try {
            room1.setDiaFinal(formato.parse(scanner.nextLine()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for(int i=0;i<rooms.size();i++){
            //aca tengo que comparar status tipo y fechas
            if(rooms.get(i).getStatus().equals(RoomStatus.DISPONIBLE) && (rooms.get(i).getType().equals(room1.getType()) && rooms.get(i).getDiaFinal().after(room1.getDiaInicial()))) {
                System.out.println("Se asigno la habitacion" + i);
            }
        }
    }



}

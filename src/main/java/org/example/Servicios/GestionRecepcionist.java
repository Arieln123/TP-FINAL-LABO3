package org.example.Servicios;


import org.example.Modelos.*;
import org.example.Repositorios.IRepository;
import org.example.Repositorios.PassengerRepo;
import org.example.Repositorios.RecepcionRepo;

import java.util.Date;
import java.time.Period;
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

    public Recepcionist infoRecep(String dni){
        RecepcionRepo admin=new RecepcionRepo();
        List<Recepcionist> lista =admin.listar();
        Recepcionist rece = new Recepcionist();
        for (int i=0;i<lista.size();i++){
            if (lista.get(i).getDni()== dni) {
                rece = lista.get(i);
                return rece;
            }
        }
        return null;
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

    public void  addPassenger(Scanner scanner){
        GestionAdministrator admin=new GestionAdministrator();
        admin.addPassengert(scanner);
    }

    @Override
    public void makeReservation(Scanner scanner) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Room> rooms= new ArrayList<>();
        rooms = roomRepo.listar();
        Room room1=new Room();
        scanner = new Scanner(System.in);
        GestionBooking gestbook = new GestionBooking();
        addPassenger(scanner);
        Date fechaInicial;
        Date fechaFinal;

        System.out.println("ingrese tipo de habitacion deseada\n "+" 1-SIMPLE $50 x noche , 2-DOBLE $100 x noche , 3-SUITE $200 x noche ");
        int input=scanner.nextInt();
        switch (input){
            case 1:
                room1.setType(Type.SIMPLE);
                break;
            case 2:
                room1.setType(Type.DOBLE);
                break;
            case 3:
                room1.setType(Type.SUITE);
                break;
        }

        System.out.println("ingrese fecha de ingreso de pasajero:(YYYY-MM-DD)");
        try {
            String fecha=scanner.next();
            fechaInicial=formato.parse(fecha);
            room1.setDiaInicial(fechaInicial);

        }catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ingrese anio,mes y dia de egreso de pasajero:(YYYY-MM-DD)");
        try {
            String fecha=scanner.next();
            fechaFinal=formato.parse(fecha);
            room1.setDiaFinal(fechaFinal);

        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long dias = Math.abs(room1.getDiaFinal().getTime() - room1.getDiaInicial().getTime());

        double fare =0.0;
        switch (room1.getType()){
            case SIMPLE:
                fare = (double)(dias * 50);
                break;

            case DOBLE:
                fare = (double)(dias * 100);
                break;

            case SUITE:
                fare = (double)(dias * 200);
                break;
        }

        for(int i=0;i<rooms.size();i++) {
            if (rooms.get(i).getStatus().equals(RoomStatus.DISPONIBLE) && (rooms.get(i).getType().equals(room1.getType()) && (rooms.get(i).getDiaFinal().before(room1.getDiaInicial())))) {
                gestbook.addBooking(1, room1.getId(), fechaInicial,fechaFinal, fare);
                rooms.get(i).setDiaInicial(fechaInicial);
                rooms.get(i).setDiaFinal(fechaFinal);
                rooms.get(i).setStatus(RoomStatus.OCUPADO);
                roomRepo.modificar(rooms.get(i));
                System.out.println("Se asigno la habitacion" + i);
            }
        }
    }

}

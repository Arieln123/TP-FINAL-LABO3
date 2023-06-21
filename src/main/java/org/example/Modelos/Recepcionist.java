package org.example.Modelos;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Recepcionist extends User implements Reservation {

    @Override
    public void makeReservation(Passenger passenger, ArrayList<Room> rooms) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scanner=new Scanner();
        Room room1=new Room();
        System.out.println("--------------------------------------------");
        System.out.println("-----------Datos de reservación-------------");
        System.out.println("Ingrese nombre del pasajero:");
        passenger.setName(scanner.nextLine());
        System.out.println("Ingrese apellido del pasajero:");
        passenger.setLastName(scanner.nextLine());
        System.out.println("Ingrese la direccion: ");
        passenger.setAddress(scanner.nextLine());
        System.out.println("Ingrese  DNI del pasajero: ");
        passenger.setDni(scanner.nextLine());
        System.out.println("Ingrese el pais");
        passenger.setCountry(scanner.nextLine());
        passenger.setStatus(Status.ACTIVE);
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
            if(rooms.get(i).getStatus().equals(RoomStatus.DISPONIBLE) && (rooms.get(i).getType().equals(room1.type && (rooms.get(i).getDiaInicial()>room1.getDiaInicial()||rooms.get(i).getDiaFinal()>room1.getDiaFinal())))){
                System.out.println("Se asigno la habitacion"+ i);
            }
        }
    }

/*
    public void checkIn(){
        Scanner scanner=new Scanner();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha1 = new Date();
        System.out.println("Ingrese la feha de ingreso a la habitacion");
        fecha1.setTime(Date.parse(scanner.nextLine()));
    }
    public void checkOut(){
        Scanner scanner=new Scanner();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha1 = new Date();
        System.out.println("Ingrese la feha de egreso a la habitacion");
        fecha1.setTime(Date.parse(scanner.nextLine()));
    }

*/

}

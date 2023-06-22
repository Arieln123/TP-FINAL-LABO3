package org.example.Servicios;

import org.example.Modelos.Booking;
import org.example.Repositorios.BookingRepo;
import org.example.Repositorios.IRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionBooking {

    IRepository<Booking> bookRepo = new BookingRepo();
    ArrayList<Booking> listabook = bookRepo.listar();


    public GestionBooking() {
    }

    public void listBooking() {

        for (int i=0;i<listabook.size();i++){
                System.out.println(listabook.get(i));
        }
    }

    public void addBooking(Integer idPass, Integer idRoom, Date strDate, Date endDate, double fares) {
        Booking book = new Booking();
            book.setIdBooking(bookRepo.listar().size()+1);
            book.setIdPassenger(idPass);
            book.setIdRoom(idRoom);
            book.setStartDate(strDate);
            book.setEndDate(endDate);
            book.setFares(fares);
    }
    public void deleteAdministrator() {
        Scanner sc=new Scanner(System.in);
        Booking book = new Booking();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Ingrese el numero de ID del Booking a  eliminar");
            book.setIdBooking(sc.nextInt());

            if (!bookRepo.existe(book)) {
                System.out.println("Error al eliminar, el numero de ID no pertenece a ningun Booking");
            } else {
                bookRepo.eliminar(book);
                System.out.println("El Booking se ha eliminado correctamente");
            }
            System.out.println("¿Desea eliminar otro Booking? s/n");
            seguir = sc.next();
        }
    }

}

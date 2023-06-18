package org.example.Servicios;

import org.example.Modelos.Administrator;
import org.example.Modelos.Passenger;
import org.example.Modelos.Status;
import org.example.Repositorios.AdministratorRepo;
import org.example.Repositorios.IRepository;
import org.example.Repositorios.PassengerRepo;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GestionPassenger {
    IRepository<Passenger> passRepo = new PassengerRepo();

    public GestionPassenger() {
    }

    public void listPassenger() {

        PassengerRepo pass=new PassengerRepo();
        List<Passenger> lista =pass.listar();

        for (int i=0;i<lista.size();i++){
            if (lista.get(i).getStatus()== Status.ACTIVE) {
                System.out.println(lista.get(i));
            }
        }
    }

    public void addAdministrator() {
        Scanner sc=new Scanner(System.in);
        Passenger pass = new Passenger();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nombre");
            pass.setName(sc.next());


            System.out.println("Ingrese el Apellido");
            pass.setLastName(sc.next());

            System.out.println("Ingrese  Direccion");
            pass.setAddress(sc.next());



            System.out.println("Ingrese la Pais");
            pass.setCountry(sc.next());

            pass.setId(passRepo.listar().size()+1);
            pass.setStatus(Status.ACTIVE);
            try{
                if (passRepo.existe(pass)==false){
                    passRepo.agregar(pass);
                    System.out.println("El nuevo pasajero se ha agregado correctamente");
                }
                else {
                    throw new IOException("Este es un error personalizado");
                }
            }
            catch (IOException e){
                System.out.println("“El pasajero ya existe”");

            }


            System.out.println("¿Desea agregar otro pasajero? s/n");
            seguir = sc.next();
        }
        sc.close();
    }

    public void deletePassenger() {
        Scanner sc=new Scanner(System.in);
        Passenger pass = new Passenger();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Ingrese el numero de ID del pasajero a  eliminar");
            pass.setId(sc.nextInt());

            if (!passRepo.existe(pass)) {
                System.out.println("Error al eliminar, el numero de ID no pertenece a ningun pasajero");
            } else {
                passRepo.eliminar(pass);
                System.out.println("El pasajero se ha eliminado correctamente");
            }
            System.out.println("¿Desea eliminar otro pasajero? s/n");
            seguir = sc.next();
        }
    }

}

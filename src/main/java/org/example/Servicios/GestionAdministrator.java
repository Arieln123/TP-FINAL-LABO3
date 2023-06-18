package org.example.Servicios;

import org.example.Modelos.Administrator;
import org.example.Modelos.Status;
import org.example.Repositorios.AdministratorRepo;
import org.example.Repositorios.IRepository;

import java.io.IOException;
import java.util.*;

public class GestionAdministrator  {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    IRepository<Administrator> adminRepo = new AdministratorRepo();

    public GestionAdministrator() {
    }

    public void listAdministrator() {

        AdministratorRepo admin=new AdministratorRepo();
        List<Administrator> lista =admin.listar();

        for (int i=0;i<lista.size();i++){
            if (lista.get(i).getStatus()== Status.ACTIVE) {
                System.out.println(lista.get(i));
            }
        }
    }

    public void addAdministrator() {
        Scanner sc=new Scanner(System.in);
        Administrator admin = new Administrator();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nombre");
            admin.setName(sc.next());


            System.out.println("Ingrese el Apellido");
            admin.setLastName(sc.next());

            System.out.println("Ingrese  Direccion");
            admin.setAddress(sc.next());



            System.out.println("Ingrese la Pais");
            admin.setCountry(sc.next());

            admin.setId(adminRepo.listar().size()+1);
            admin.setStatus(Status.ACTIVE);
            try{
                if (adminRepo.existe(admin)==false){
                    adminRepo.agregar(admin);
                    System.out.println("El nuevo Admin se ha agregado"+GREEN+" correctamente"+RESET);
                }
                else {
                    throw new IOException("Este es un error personalizado");
                }
            }
            catch (IOException e){
                System.out.println(GREEN+"El Admin ya existe"+RESET);

            }


            System.out.println("¿Desea agregar otro Admin? s/n");
            seguir = sc.next();
        }
        sc.close();
    }
    public void deleteAdministrator() {
        Scanner sc=new Scanner(System.in);
        Administrator admin = new Administrator();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Ingrese el numero de ID del administrador a  "+RED+"eliminar"+RESET);
            admin.setId(sc.nextInt());

            if (!adminRepo.existe(admin)) {
                System.out.println(RED+"Error"+RESET+" al eliminar, el numero de ID no pertenece a ningun cliente");
            } else {
                adminRepo.eliminar(admin);
                System.out.println("El Administrador se ha eliminado"+GREEN+" correctamente"+RESET);
            }
            System.out.println("¿Desea eliminar otro Administrador? s/n");
            seguir = sc.next();
        }
    }



}

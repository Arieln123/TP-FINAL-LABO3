package org.example.Servicios;

import org.example.Modelos.Administrator;
import org.example.Modelos.Status;
import org.example.Repositorios.AdministratorRepo;
import org.example.Repositorios.IRepository;

import java.io.IOException;
import java.util.*;

public class GestionAdministrator  {

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
                    System.out.println("El nuevo Admin se ha agregado correctamente");
                }
                else {
                    throw new IOException("Este es un error personalizado");
                }
            }
            catch (IOException e){
                System.out.println("“El Admin ya existe”");

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

            System.out.println("Ingrese el numero de ID del administrador a  eliminar");
            admin.setId(sc.nextInt());

            if (!adminRepo.existe(admin)) {
                System.out.println("Error al eliminar, el numero de ID no pertenece a ningun cliente");
            } else {
                adminRepo.eliminar(admin);
                System.out.println("El Administrador se ha eliminado correctamente");
            }
            System.out.println("¿Desea eliminar otro Administrador? s/n");
            seguir = sc.next();
        }
    }



}

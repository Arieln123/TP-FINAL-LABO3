package org.example.Servicios;

import org.example.Models.Room;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Models.RoomStatus;
import org.example.Repository.IRepository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import  java.util.Scanner;

public class RoomRepo implements IRepository<Room> {

    private final File pathJson = new File("src/main/java/org/example/Archivos/Administrators.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Room> rooms;

    public RecepcionistRepo() {

    }

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Room.class);
            this.rooms = mapper.readValue(pathJson, collectionType);
        } catch (IOException e) {
            this.rooms = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(pathJson, this.rooms);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Room> listar() {
        cargar();
        return this.rooms;
    }

    @Override
    public void agregar(Room... room) {
        cargar();
        this.rooms.addAll(Arrays.asList(room));
        guardar();
    }

    //Elimina cliente y room
    @Override
    public void eliminar(Room room) {
        cargar();
        this.rooms.get(room.getId() - 1).setStatus(RoomStatus.NO_DISPONIBLE);
        guardar();
    }

    @Override

    public void modificar(Scanner scanner) {
        Room room = new Room();
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Ingrese el numero de ID del producto a modificar ");
            room.setId(scanner.nextInt());
            if (!RoomRepo.existe(room)) {
                System.out.println("Error el ID no pertenece a ningun producto");
            } else {
                scanner.nextLine();
                System.out.println("ingrese estatus \n 1-  DISPONIBLE, 2-NO_DISPONIBLE, 3-OCUPAD");
                int categoria = scanner.nextInt();
                if (categoria == 1)
                    room.setStatus(RoomStatus.DISPONIBLE);
                if (categoria == 2)
                   room.setStatus(RoomStatus.NO_DISPONIBLE);
                if (categoria == 3)
                    room.setStatus(RoomStatus.OCUPADO);

                productoRepo.modificar(producto);
                System.out.println("El Producto se modifico correctamente");
            }
            System.out.println("¿Desea modificar otro producto? s/n");
            seguir = scanner.next();
        }


    }

    @Override
    public boolean existe(Administrator objeto) {
        cargar();
        if (this.administrators.contains(objeto)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Administrator info(Administrator objeto) {
        cargar();

        for (Administrator c : this.administrators) {
            if (c.equals(objeto)) {
                return c;
            }
        }
        return null;
    }
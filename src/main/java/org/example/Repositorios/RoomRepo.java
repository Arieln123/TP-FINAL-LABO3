package org.example.Repositorios;

import org.example.Models.Room;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class RoomRepo implements IRepository<Room> {
    private final File pathJson = new File("C:\\Users\\Usuario\\Desktop\\SegundoParcialJimenez\\src\\main\\java\\org\\example\\Archivos\\productos.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Room> rooms;

    public RoomRepo() {
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

    @Override
    public void eliminar(Room room) {
        cargar();
        this.rooms.remove(room);
        guardar();
    }

    @Override
    public void modificar(Room room) {
        cargar();
        for (Room p : this.rooms) {
            if (p.equals(room)) {
                int index = rooms.indexOf(p);
                rooms.set(index, room);
                break;
            }
        }
        guardar();
    }

    @Override
    public boolean existe(Producto producto) {
        cargar();
        if (this.productos.contains(producto)) {
            return true;
        } else {
            return false;
        }
    }
}
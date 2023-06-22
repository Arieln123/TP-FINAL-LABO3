package org.example.Repositorios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Modelos.Room;
import org.example.Modelos.RoomStatus;
import org.example.Modelos.Status;
import org.example.Modelos.Recepcionist;
import org.example.Modelos.Passenger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class RecepcionRepo implements IRepository<Recepcionist> {

    private final File pathJson = new File("src/main/java/org/example/Archivos/Recepcionists.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private  ArrayList<Recepcionist> recepcionists;

    public RecepcionRepo() {

    }

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Recepcionist.class);
            this.recepcionists= mapper.readValue(pathJson, collectionType);
        } catch (IOException e) {
            this.recepcionists = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(pathJson, this.recepcionists);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Recepcionist> listar() {
        cargar();
        return this.recepcionists;
    }

    //agrega cliente
    @Override
    public void agregar(Recepcionist... recepcionists) {

        cargar();
        this.recepcionists.addAll(Arrays.asList(recepcionists));
        guardar();

    }


    @Override
    public void eliminar(Recepcionist recepcionist) {
        cargar();
        this.recepcionists.get(recepcionist.getId() - 1).setStatus(Status.INACTIVE);
        guardar();
    }


    @Override
    public void modificar(Recepcionist recepcionist) {
        cargar();
        for (Recepcionist c : this.recepcionists) {
            if (c.equals(recepcionist)) {
                int index = recepcionists.indexOf(c);
                recepcionists.set(index, recepcionist);
                break;
            }
        }
        guardar();
    }

    @Override
    public boolean existe(Recepcionist objeto) {
        cargar();
        if (this.recepcionists.contains(objeto)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Recepcionist info(Recepcionist objeto) {
        return null;
    }

}
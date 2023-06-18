package org.example.Repositorios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Modelos.Passenger;
import org.example.Modelos.Status;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PassengerRepo implements IRepository<Passenger>{
    private final File pathJson = new File("src/main/java/org/example/Archivos/Passengers.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Passenger> passengers;

    public PassengerRepo() {

    }

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class,Passenger.class);
            this.passengers = mapper.readValue(pathJson,collectionType);
        }catch (IOException e){
            this.passengers = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(pathJson,this.passengers);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Passenger> listar() {
        cargar();
        return this.passengers;
    }

    @Override
    public void agregar(Passenger... objeto) {

        cargar();
        this.passengers.addAll(Arrays.asList(objeto));
        guardar();

    }

    @Override
    public void eliminar(Passenger objeto) {
        cargar();
        this.passengers.get(objeto.getId()-1).setStatus(Status.INACTIVE);
        guardar();
    }

    @Override
    public void modificar(Passenger objeto) {
        cargar();
        for (Passenger c : this.passengers){
            if(c.equals(objeto)){
                int index = passengers.indexOf(c);
                passengers.set(index,objeto);
                break;
            }
        }
        guardar();
    }

    @Override
    public boolean existe(Passenger objeto) {
        cargar();
        if(this.passengers.contains(objeto)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Passenger info(Passenger objeto) {
        cargar();

        for (Passenger c : this.passengers){
            if(c.equals(objeto)){
                return c;
            }
        }
        return null;
    }
}

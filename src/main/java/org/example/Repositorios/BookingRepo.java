package org.example.Repositorios;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Modelos.Booking;
import org.example.Modelos.Status;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class BookingRepo implements IRepository<Booking>{
    private final File pathJson = new File("src/main/java/org/example/Archivos/Bookings.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Booking> bookings;

    public BookingRepo() {

    }

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class,Booking.class);
            this.bookings = mapper.readValue(pathJson,collectionType);
        }catch (IOException e){
            this.bookings = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(pathJson,this.bookings);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Booking> listar() {
        cargar();
        return this.bookings;
    }

    @Override
    public void agregar(Booking... objeto) {

        cargar();
        this.bookings.addAll(Arrays.asList(objeto));
        guardar();

    }

    @Override
    public void eliminar(Booking objeto) {
        cargar();
        this.bookings.get(objeto.getIdBooking()-1);
        guardar();
    }

    @Override
    public void modificar(Booking objeto) {
        cargar();
        for (Booking c : this.bookings){
            if(c.equals(objeto)){
                int index = bookings.indexOf(c);
                bookings.set(index,objeto);
                break;
            }
        }
        guardar();
    }

    @Override
    public boolean existe(Booking objeto) {
        cargar();
        if(this.bookings.contains(objeto)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Booking info(Booking objeto) {
        return null;
    }

}

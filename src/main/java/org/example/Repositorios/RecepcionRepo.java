package org.example.Repositorios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Modelos.*;

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


    @Override
    public void agregar(Recepcionist... recepcionists) {

        cargar();
        this.recepcionists.addAll(Arrays.asList(recepcionists));
        guardar();

    }


    @Override
    public void eliminar(Recepcionist objeto) {
        cargar();
        System.out.println(objeto.getDni());
        for (Recepcionist c : this.recepcionists){
            if(c.equals(objeto)){
                int index = recepcionists.indexOf(c);
                System.out.println(c);
                c.setStatus(Status.INACTIVE);
                recepcionists.set(index,c);
                break;
            }
        }
        guardar();
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
    public Recepcionist info(Recepcionist objeto){
        cargar();

        for (Recepcionist c : this.recepcionists){

            if(c.equals(objeto)){

                return c;
            }
        }
        return null;
    }
}
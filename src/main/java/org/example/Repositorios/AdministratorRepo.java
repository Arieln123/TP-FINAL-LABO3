package org.example.Repositorios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Modelos.Administrator;
import org.example.Modelos.Status;
import org.example.Servicios.GestionAdministrator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AdministratorRepo implements IRepository<Administrator> {

    private final File pathJson = new File("src/main/java/org/example/Archivos/Administrators.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Administrator> administrators;

    public AdministratorRepo() {

    }

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Administrator.class);
            this.administrators = mapper.readValue(pathJson, collectionType);
        } catch (IOException e) {
            this.administrators = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(pathJson, this.administrators);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Administrator> listar() {
        cargar();
        return this.administrators;
    }

    @Override
    public void agregar(Administrator... objeto) {

        cargar();
        this.administrators.addAll(Arrays.asList(objeto));
        guardar();

    }

    @Override
    public void eliminar(Administrator objeto) {
        cargar();
        System.out.println(objeto.getDni());
        for (Administrator c : this.administrators){
            if(c.equals(objeto)){
                int index = administrators.indexOf(c);
                System.out.println(c);
                c.setStatus(Status.INACTIVE);
                administrators.set(index,c);
                break;
            }
        }
        guardar();

    }

    @Override
    public void modificar(Administrator objeto) {
        cargar();
        IRepository admin=new AdministratorRepo();

        admin.info(objeto);
        System.out.println(admin);
        for (Administrator c : this.administrators) {
            if (c.equals(objeto)) {
                int index = administrators.indexOf(c);
                administrators.set(index, objeto);
                break;
            }
        }
        guardar();
    }

    @Override
    public boolean existe(Administrator objeto) {
        cargar();

        if (this.administrators.contains(objeto))
            return true;
        else
            return false;


    }
    @Override
    public Administrator info(Administrator objeto){
        cargar();

        for (Administrator c : this.administrators){
            if(c.equals(objeto)){
                return c;
            }
        }
        return null;
    }
}
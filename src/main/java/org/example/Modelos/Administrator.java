package org.example.Modelos;

import java.io.Serializable;

public class Administrator extends User implements Serializable {

    public Administrator(Integer id, String name, String lastName, String address, String country,Status status,String dni) {
        super(id,name, lastName, address, country,status,dni);
    }

    public Administrator() {

    }




}

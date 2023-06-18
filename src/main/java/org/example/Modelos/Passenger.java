package org.example.Modelos;

public class Passenger extends User {
    public Passenger(Integer id, String name, String lastName, String address, String country, Status status,String dni) {
        super(id, name, lastName, address, country, status,dni);
    }

    public Passenger() {
    }


}

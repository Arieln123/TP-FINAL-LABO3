package org.example.Modelos;

import java.util.Objects;

public abstract class User {

    private String name;
    private String lastName;
    private Integer id;
    private String address;
    private String country;

    private Status status;
    public User() {
    }

    public User(Integer id, String name, String lastName, String address, String country,Status status) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.address = address;
        this.country = country;
        this.status=status;
    }

    //GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() + ", Nombre: " + this.getName() + " " + this.getLastName() + ", Dirección: " +
                this.getAddress() + ", País: " + this.getCountry();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

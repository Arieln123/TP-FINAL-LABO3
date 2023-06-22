package org.example.Modelos;


import java.util.Objects;

public class Recepcionist extends User {
    Integer id;
    String name;
    String lastName;
    String address;
    String country;
    Status status;
    String dni;


    //Constructores
    public Recepcionist(Integer id, String name, String lastName, String address, String country, Status status, String dni) {
        super(id, name, lastName, address, country, status, dni);
    }

    public Recepcionist() {

    }
    //endregion

    //Getters y setters

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String getDni() {
        return dni;
    }

    @Override
    public void setDni(String dni) {
        this.dni = dni;
    }
    //endregion

    //Equals y hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recepcionist that)) return false;
        if (!super.equals(o)) return false;
        return getDni().equals(that.getDni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDni());
    }


    //endregion

    //toString

    @Override
    public String toString() {
        return "Recepcionist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", status=" + status +
                ", dni='" + dni + '\'' +
                '}';
    }

    //endregion


}

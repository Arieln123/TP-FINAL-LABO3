package org.example.Modelos;

import java.util.Objects;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Room {
    int id;
    Type type;
    RoomStatus status;
    Date diaInicial;
    Date diaFinal;

    // Constructores

    public Room() {
    }

    public Room(int id, Type type, RoomStatus status, Date diaInicial, Date diaFinal) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.diaInicial = diaInicial;
        this.diaFinal = diaFinal;
    }

    //Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Date getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(Date diaInicial) {
        this.diaInicial = diaInicial;
    }

    public Date getDiaFinal() {
        return diaFinal;
    }

    public void setDiaFinal(Date diaFinal) {
        this.diaFinal = diaFinal;
    }

    //Equals y hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return getId() == room.getId() && getType() == room.getType() && getStatus() == room.getStatus() && getDiaInicial().equals(room.getDiaInicial()) && getDiaFinal().equals(room.getDiaFinal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    //toSting

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", type=" + type +
                ", status=" + status +
                ", diaInicial=" + diaInicial +
                ", diaFinal=" + diaFinal +
                '}';
    }
}

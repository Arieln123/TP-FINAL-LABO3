package org.example.Modelos;

import java.util.Objects;

public class Room {
    int id;
    Type type;
    RoomStatus status;
    // Constructores

    public Room() {
    }

    public Room(int id, Type type, RoomStatus status) {
        this.id = id;
        this.type = type;
        this.status = status;
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
    //Equals y hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return getId() == room.getId() && getType() == room.getType() && getStatus() == room.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

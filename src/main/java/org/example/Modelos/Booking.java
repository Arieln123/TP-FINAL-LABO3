package org.example.Modelos;
import java.sql.Date;
import java.util.List;

public class Booking {

    private Integer idBooking;
    private Integer idPassenger;
    private Integer idRoom;
    private Date startDate;
    private Date endDate;
    private double fares;

    public Booking() {
    }

    public Booking(Integer idBooking,Integer idPassenger, Integer idRoom, Date startDate, Date endDate, double fares) {
        this.idBooking = idBooking;
        this.idPassenger = idPassenger;
        this.idRoom = idRoom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fares = fares;
    }

    public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public Integer getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(Integer idPassenger) {
        this.idPassenger = idPassenger;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getFares() {
        return fares;
    }

    public void setFares(double fares) {
        this.fares = fares;
    }
}
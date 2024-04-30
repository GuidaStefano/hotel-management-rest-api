package it.itsvil.hotelmanagement.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Guest guest;

    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutDate;

    public Booking(Room room, Guest guest, Date checkInDate, Date checkOutDate) {
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Booking() {

    }

    public Long getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public Guest getUser() {
        return guest;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

}

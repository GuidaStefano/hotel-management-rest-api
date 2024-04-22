package it.itsvil.hotelmanagement.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Room room;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutDate;

    public Booking(Room room, User user, Date checkInDate, Date checkOutDate) {
        this.room = room;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

}

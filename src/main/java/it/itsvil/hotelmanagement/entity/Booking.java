package it.itsvil.hotelmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static it.itsvil.hotelmanagement.util.DateUtil.DATE_PATTERN;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Room room;

    @ManyToOne
    @JsonIgnore
    private User user;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private Date checkInDate;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private Date checkOutDate;

    @Column(nullable = false)
    private double price;

    public Booking(Room room, User user, Date checkInDate, Date checkOutDate) {
        this.room = room;
        this.user = user;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        price = calculatePrice();
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

    public double getPrice() {
        return price;
    }

    private double calculatePrice() {
        long differenceInMillis = checkOutDate.getTime() - checkInDate.getTime();
        long differenceInDays = TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);
        return differenceInDays * room.getPricePerNight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}

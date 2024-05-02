package it.itsvil.hotelmanagement.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private double pricePerNight;

    @Column(nullable = false)
    private int maxCapacity;

    @OneToMany(mappedBy = "room")
    private Set<Booking> bookings;

    public Room(Type type, double pricePerNight, int maxCapacity) {
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
        bookings = new HashSet<>();
    }

    public Room() {

    }

    public Type getType() {
        return type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public enum Type {
        BASE,
        DELUXE,
        SUITE
    }

}

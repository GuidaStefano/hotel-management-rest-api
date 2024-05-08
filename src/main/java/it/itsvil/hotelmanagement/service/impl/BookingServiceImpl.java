package it.itsvil.hotelmanagement.service.impl;

import it.itsvil.hotelmanagement.entity.Booking;
import it.itsvil.hotelmanagement.entity.Room;
import it.itsvil.hotelmanagement.entity.User;
import it.itsvil.hotelmanagement.repository.BookingRepository;
import it.itsvil.hotelmanagement.repository.RoomRepository;
import it.itsvil.hotelmanagement.service.BookingService;
import it.itsvil.hotelmanagement.wrapper.BookingRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(User user, BookingRequest bookingRequest, boolean simulate) {
        if (!simulate)
            Objects.requireNonNull(user, "user cannot be null");

        Objects.requireNonNull(bookingRequest, "payload cannot be null");

        Date checkInDate = bookingRequest.checkInDate();
        Date checkOutDate = bookingRequest.checkOutDate();

        Objects.requireNonNull(checkInDate, "check in date cannot be null");
        Objects.requireNonNull(checkOutDate, "check out date cannot be null");

        if (checkInDate.after(checkOutDate))
            throw new IllegalArgumentException("check in date cannot be after check out date");

        if (checkInDate.before(new Date()))
            throw new IllegalArgumentException("check in date cannot be before current date");

        long roomId = bookingRequest.roomId();

        Optional<Room> roomOpt = roomRepository.findById(roomId);
        if (roomOpt.isEmpty())
            throw new IllegalArgumentException("room not found");

        Room room = roomOpt.get();
        if (!roomRepository.findAvailableRoomsBetweenDates(checkInDate, checkOutDate).contains(room))
            throw new IllegalArgumentException("room is not available for the selected dates");

        Booking booking = new Booking(room, simulate ? null : user, checkInDate, checkOutDate);
        return simulate ? booking : bookingRepository.save(booking);
    }

    @Override
    public Booking createBooking(User user, BookingRequest bookingRequest) {
        return createBooking(user, bookingRequest, false);
    }

    @Override
    public Booking getBooking(User user, long id) {
        Objects.requireNonNull(user, "user cannot be null");

        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isEmpty())
            throw new IllegalArgumentException("booking not found");

        Booking booking = bookingOpt.get();
        if (!booking.getUser().equals(user))
            throw new IllegalArgumentException("booking not found 1");

        return booking;
    }

    @Override
    public Set<Booking> getBookings(User user) {
        Objects.requireNonNull(user, "user cannot be null");

        return bookingRepository.findByUser(user);
    }

    @Override
    public void deleteBooking(User user, long id) {
        Booking booking = getBooking(user, id); // tutti i controlli vengono già effettuati!
        bookingRepository.delete(booking);
    }

}

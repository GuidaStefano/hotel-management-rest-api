package it.itsvil.hotelmanagement.config.data;

import it.itsvil.hotelmanagement.entity.Room;
import it.itsvil.hotelmanagement.repository.RoomRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoomRepository roomRepository;

    public DataLoader(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (roomRepository.count() == 0) {
            roomRepository.save(new Room(Room.Type.BASE, 50f, 2));
            roomRepository.save(new Room(Room.Type.BASE, 65f, 3));
            roomRepository.save(new Room(Room.Type.BASE, 80f, 4));
            roomRepository.save(new Room(Room.Type.DELUXE, 100f, 2));
            roomRepository.save(new Room(Room.Type.DELUXE, 130f, 4));
            roomRepository.save(new Room(Room.Type.DELUXE, 180f, 6));
            roomRepository.save(new Room(Room.Type.SUITE, 300f, 2));
            roomRepository.save(new Room(Room.Type.SUITE, 450f, 4));
        }
    }

}

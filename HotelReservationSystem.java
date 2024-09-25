
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private String type;
    private double price;
    private boolean available;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
        this.available = true;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void reserve() {
        available = false;
    }

    @Override
    public String toString() {
        return type + " - $" + price + (available ? " (Available)" : " (Not Available)");
    }
}

class Reservation {
    private Room room;
    private String guestName;

    public Reservation(Room room, String guestName) {
        this.room = room;
        this.guestName = guestName;
    }

    @Override
    public String toString() {
        return "Reservation for " + guestName + ": " + room.getType() + " at $" + room.getPrice();
    }
}

public class HotelReservationSystem {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public HotelReservationSystem() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room("Single", 100));
        rooms.add(new Room("Double", 150));
        rooms.add(new Room("Suite", 250));
    }

    public void searchAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public void makeReservation(String guestName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter room type (Single/Double/Suite): ");
        String roomType = scanner.nextLine();
        Room selectedRoom = null;

        for (Room room : rooms) {
            if (room.getType().equalsIgnoreCase(roomType) && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            selectedRoom.reserve();
            Reservation reservation = new Reservation(selectedRoom, guestName);
            reservations.add(reservation);
            System.out.println("Reservation successful! " + reservation);
            // Mock payment processing
            System.out.println("Processing payment of $" + selectedRoom.getPrice() + "...");
            System.out.println("Payment successful!");
        } else {
            System.out.println("Room not available or invalid room type.");
        }
    }

    public void viewBookings() {
        System.out.println("\n--- Your Reservations ---");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public static void main(String[] args) {
        HotelReservationSystem hotel = new HotelReservationSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View My Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    hotel.searchAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String guestName = scanner.nextLine();
                    hotel.makeReservation(guestName);
                    break;
                case 3:
                    hotel.viewBookings();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you for using the hotel reservation system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

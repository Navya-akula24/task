package SGT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private String type;
    private int roomNumber;
    private boolean isAvailable;
    private double price;

    public Room(String type, int roomNumber, double price) {
        this.type = type;
        this.roomNumber = roomNumber;
        this.price = price;
        this.isAvailable = true;
    }

    public String getType() {
        return type;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + ") - Rs." + price + " per night" + (isAvailable ? " - Available" : " - Booked");
    }
}

class Booking {
    private String customerName;
    private Room room;
    private int nights;
    private double totalCost;

    public Booking(String customerName, Room room, int nights) {
        this.customerName = customerName;
        this.room = room;
        this.nights = nights;
        this.totalCost = room.getPrice() * nights;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Room getRoom() {
        return room;
    }

    public int getNights() {
        return nights;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Booking for " + customerName + ": " + room + ", Nights: " + nights + ", Total Cost: Rs." + totalCost;
    }
}

public class hotel {
    private List<Room> rooms;
    private List<Booking> bookings;

    public hotel() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room("Standard", 101, 100));
        rooms.add(new Room("Standard", 102, 100));
        rooms.add(new Room("Deluxe", 201, 150));
        rooms.add(new Room("Deluxe", 202, 150));
        rooms.add(new Room("Suite", 301, 200));
        rooms.add(new Room("Suite", 302, 200));
    }

    public void showAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public void makeReservation(String customerName, int roomNumber, int nights) {
        Room room = findRoom(roomNumber);
        if (room != null && room.isAvailable()) {
            room.setAvailable(false);
            Booking booking = new Booking(customerName, room, nights);
            bookings.add(booking);
            System.out.println("Reservation successful: " + booking);
        } else {
            System.out.println("Room not available or doesn't exist.");
        }
    }

    public void showBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            System.out.println("Bookings:");
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    public void processPayment(double amount) {
        System.out.println("Processing payment of Rs." + amount + "...");
        System.out.println("Payment successful.");
    }

    public static void main(String[] args) {
        hotel system = new hotel();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. Show All Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.showAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter customer name: ");
                    scanner.nextLine(); // Consume newline
                    String customerName = scanner.nextLine();
                    System.out.print("Enter room number to book: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter number of nights: ");
                    int nights = scanner.nextInt();

                    Room room = system.findRoom(roomNumber);
                    if (room != null && room.isAvailable()) {
                        system.makeReservation(customerName, roomNumber, nights);
                        system.processPayment(room.getPrice() * nights);
                    } else {
                        System.out.println("Room is not available or doesn't exist.");
                    }
                    break;
                case 3:
                    system.showBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

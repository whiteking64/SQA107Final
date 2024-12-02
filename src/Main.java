import java.util.Scanner;

public class Main {
    private static final CourierManager manager = new CourierManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===============================");
        System.out.println(" Welcome to Delivery Dilemmas!");
        System.out.println("===============================");

        while (true) {
            displayMenu();
            int choice = getValidChoice();

            try {
                switch (choice) {
                    case 1:
                        addNewPackage();
                        break;
                    case 2:
                        manager.displayPackages();
                        break;
                    case 3:
                        sortPackages();
                        break;
                    case 4:
                        searchPackage();
                        break;
                    case 5:
                        System.out.println("Thank you for using Delivery Dilemmas!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println(); // Empty line for readability
        }
    }

    private static void displayMenu() {
        System.out.println("Please select an option:");
        System.out.println("1. Add a new package");
        System.out.println("2. Display all packages and shipping costs");
        System.out.println("3. Sort packages by weight");
        System.out.println("4. Search for a package by Tracking ID");
        System.out.println("5. Exit");
    }

    private static int getValidChoice() {
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void addNewPackage() {
        try {
            System.out.print("Enter package type (Standard/Express): ");
            String type = scanner.nextLine().trim().toLowerCase();

            System.out.print("Enter tracking ID: ");
            String trackingID = scanner.nextLine().trim();

            System.out.print("Enter destination: ");
            String destination = scanner.nextLine().trim();

            System.out.print("Enter weight: ");
            double weight = Double.parseDouble(scanner.nextLine().trim());

            Package newPackage;
            if (type.equals("standard")) {
                newPackage = new StandardPackage(trackingID, destination, weight);
            } else if (type.equals("express")) {
                newPackage = new ExpressPackage(trackingID, destination, weight);
            } else {
                throw new IllegalArgumentException("Invalid package type. Must be Standard or Express.");
            }

            manager.addPackage(newPackage);
            System.out.println("Package added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number for weight.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void sortPackages() {
        System.out.println("Original Package List:");
        manager.displayPackages();

        manager.sortByWeight();

        System.out.println("\nPackages sorted by weight!");
        System.out.println("Sorted Package List:");
        manager.displayPackages();
    }

    private static void searchPackage() {
        System.out.print("Enter Tracking ID: ");
        String trackingID = scanner.nextLine().trim();

        Package found = manager.searchByTrackingID(trackingID);
        if (found != null) {
            System.out.println("Package Found:");
            System.out.println(found);
        } else {
            System.out.println("Package not found with tracking ID: " + trackingID);
        }
    }
}
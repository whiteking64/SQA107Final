import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CourierManager {
    private final ArrayList<Package> packages;

    public CourierManager() {
        packages = new ArrayList<>();
    }

    // Add a package to the collection
    public void addPackage(Package pkg) {
        packages.add(pkg);
    }

    // Display all packages
    public void displayPackages() {
        if (packages.isEmpty()) {
            System.out.println("No packages in the system.");
            return;
        }

        System.out.println("Package List:");
        for (int i = 0; i < packages.size(); i++) {
            System.out.println((i + 1) + ". " + packages.get(i));
        }
    }

    // TODO: Replace with bubble sort implementation in `feature1` branch
    // Currently using Java's built-in sort as temporary solution
    public void sortByWeight() {
        packages.sort(Comparator.comparing(Package::getWeight));
    }

    // Binary Search implementation
    public Package searchByTrackingID(String trackingID) {
        // First sort by tracking ID for binary search
        packages.sort(Comparator.comparing(Package::getTrackingID));

        int left = 0;
        int right = packages.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Package midPackage = packages.get(mid);
            int comparison = midPackage.getTrackingID().compareTo(trackingID);

            if (comparison == 0) {
                return midPackage;
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }
}

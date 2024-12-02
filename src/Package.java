import java.util.regex.Pattern;
import java.util.regex.Matcher;

public abstract class Package {
    protected String trackingID;
    protected String destination;
    protected double weight;

    // Regular expression patterns
    private static final Pattern TRACKING_PATTERN = Pattern.compile("PKG\\d{5}");
    private static final Pattern DESTINATION_PATTERN = Pattern.compile("\\d+\\s+[A-Za-z\\s]+(?:Street|Avenue|Road|Lane|Drive)");

    public Package(String trackingID, String destination, double weight) throws IllegalArgumentException {
        if (!validateTrackingID(trackingID)) {
            throw new IllegalArgumentException("Invalid tracking ID format. Must be 'PKG' followed by 5 digits.");
        }
        if (!validateDestination(destination)) {
            throw new IllegalArgumentException("Invalid destination format. Must include street number and name.");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be a positive number.");
        }

        this.trackingID = trackingID;
        this.destination = destination;
        this.weight = weight;
    }

    // Abstract method to calculate shipping cost
    public abstract double calculateShippingCost();

    // Validate tracking ID format
    protected boolean validateTrackingID(String trackingID) {
        Matcher matcher = TRACKING_PATTERN.matcher(trackingID);
        return matcher.matches();
    }

    // Validate destination format
    protected boolean validateDestination(String destination) {
        Matcher matcher = DESTINATION_PATTERN.matcher(destination);
        return matcher.matches();
    }

    // Getters
    public String getTrackingID() {
        return trackingID;
    }

    public String getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("Tracking ID: %s | Destination: %s | Weight: %.1f | Cost: $%.2f",
                trackingID, destination, weight, calculateShippingCost());
    }
}

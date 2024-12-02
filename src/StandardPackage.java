public class StandardPackage extends Package {
    private static final double RATE = 2.5;

    public StandardPackage(String trackingID, String destination, double weight) {
        super(trackingID, destination, weight);
    }

    @Override
    public double calculateShippingCost() {
        return weight * RATE;
    }
}

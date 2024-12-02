public class ExpressPackage extends Package {
    private static final double RATE = 4.0;

    public ExpressPackage(String trackingID, String destination, double weight) {
        super(trackingID, destination, weight);
    }

    @Override
    public double calculateShippingCost() {
        return weight * RATE;
    }
}

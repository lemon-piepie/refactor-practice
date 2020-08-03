package com.twu.refactoring;

public class Receipt {
    private static final int fixedCharges = 50;
    private static final double peakTimeMultiplier = 1.2;
    private static final double offPeakMultiplier = 1.0;
    private static final int rateChangeDistance = 10;
    private static final int rateForFirst10km_NonAC = 15;
    private static final int rateFromThereOn_NonAC = 12;
    private static final int rateForFirst10km_AC = 20;
    private static final int rateFromThereOn_AC = 17;
    private static final double taxRate = 0.1;

    private final Taxi taxi;

    public Receipt(Taxi taxi) {
        this.taxi = taxi;
    }

    public double getTotalCost() {
        double totalCost = 0;

        // fixed charges
        totalCost += fixedCharges;

        // taxi charges
        int totalKms = taxi.getTotalKms();
        double peakTimeMultiple = taxi.isPeakTime() ? peakTimeMultiplier : offPeakMultiplier;
        double costForFirst10km_AC = rateForFirst10km_AC * peakTimeMultiple;
        double costFromThereOn_AC = rateFromThereOn_AC * peakTimeMultiple;
        double costForFirst10km_NonAC = rateForFirst10km_NonAC * peakTimeMultiple;
        double costFromThereOn_NonAC = rateFromThereOn_NonAC * peakTimeMultiple;
        double DistanceMin = Math.min(rateChangeDistance, totalKms);
        double DistanceMax = Math.max(0, totalKms - rateChangeDistance);

        if(taxi.isAirConditioned()) {
            totalCost += DistanceMin * costForFirst10km_AC;
            totalCost += DistanceMax * costFromThereOn_AC;
        } else {
            totalCost += DistanceMin * costForFirst10km_NonAC;
            totalCost += DistanceMax * costFromThereOn_NonAC;
        }

        return totalCost * (1 + taxRate);
    }
}

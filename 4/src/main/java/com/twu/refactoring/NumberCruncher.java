package com.twu.refactoring;

public class NumberCruncher {
    private final int[] numbers;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    int evenNumber = 0;
    int oddNumber = 0;
    int positiveNumber = 0;
    int negativeNumber = 0;

    void countEachType(){
        for (int number : this.numbers) {
            if (number % 2 == 0) this.evenNumber++;
            if (number % 2 == 1) this.oddNumber++;
            if (number >= 0) this.positiveNumber++;
            if (number >= 0) this.negativeNumber++;
        }
    }

    public int countEven() {
        this.countEachType();
        return this.evenNumber;
    }

    public int countOdd() {
        this.countEachType();
        return this.oddNumber;
    }

    public int countPositive() {
        this.countEachType();
        return this.positiveNumber;
    }

    public int countNegative() {
        this.countEachType();
        return this.negativeNumber;
    }
}

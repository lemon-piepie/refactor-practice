package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	private int amountFor2DaysRegular = 2;
	private int basicDayForRegular = 2;
	private double rateForRegular = 1.5;
	private int rateForNewRelease = 3;
	private double amountFor3DaysChildren = 1.5;
	private int basicDayForChildren = 3;
	private double rateForChildren = 1.5;

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = rentalList.iterator();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental each = rentals.next();

			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += amountFor2DaysRegular;
				if (each.getDaysRented() > basicDayForRegular)
					thisAmount += (each.getDaysRented() - basicDayForRegular) * rateForRegular;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += each.getDaysRented() * rateForNewRelease;
				break;
			case Movie.CHILDRENS:
				thisAmount += amountFor3DaysChildren;
				if (each.getDaysRented() > basicDayForChildren)
					thisAmount += (each.getDaysRented() - basicDayForChildren) * rateForChildren;
				break;
			}

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
					&& each.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;

		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

}

package de.bcxp.challenge;

import java.util.Arrays;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        data_processing temperature = new data_processing("programming-challenge/src/main/resources/de/bcxp/challenge/weather.csv", ',');
        data_processing population = new data_processing("programming-challenge/src/main/resources/de/bcxp/challenge/countries.csv", ';');

        String dayWithSmallestTempSpread = temperature.getNameOfMaxDifference("MxT", "MnT", "Day");
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

        //TODO: Better special character handling
        String countryWithHighestPopulationDensity = population.getNameOfMaxDivision("Population", "Area (kmÂ²)", "Name");
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }
}

package de.bcxp.challenge;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.*;

public class temperature_difference {
    
    static void load_data(String filename) throws IOException {
        FileReader filereader = new FileReader(filename);
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                                  .withSkipLines(1)
                                  .build();
        List<String[]> allData = csvReader.readAll();
        System.out.print(allData);
    }
}

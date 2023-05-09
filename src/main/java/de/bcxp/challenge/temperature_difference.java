package de.bcxp.challenge;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.opencsv.*;

public class temperature_difference {

    String[][] dataTable;
    String[] columnNames;

    public temperature_difference(String data_filename){
        try {
            load_data(data_filename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void load_data(String filename) throws IOException {
        FileReader filereader = new FileReader(filename);
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                                  .build();
        this.columnNames = csvReader.readNext();
        List<String[]> data_list = csvReader.readAll();                        
        this.dataTable = data_list.toArray(new String[data_list.size()][]);
    }

    /**
     * @param column_name_a
     * @param column_name_b
     * @return 
     */
    public Float[] calculate_difference(String column_name_a, String column_name_b){
        //Get column from column name
        int column_a_index = Arrays.asList(this.columnNames).indexOf(column_name_a);
        int column_b_index = Arrays.asList(this.columnNames).indexOf(column_name_b);

        int column_length = this.dataTable.length;
        System.out.println("column_a_index"+column_a_index);
        System.out.println("column length" + column_length);
        System.out.println(this.dataTable[0][column_a_index]);
        Float[] differences = new Float[column_length];
        for(int i=0; i<column_length; i++){
            //Get the values in row i
            Float a_value = Float.valueOf(this.dataTable[i][column_a_index]);
            Float b_value = Float.valueOf(this.dataTable[i][column_b_index]);
            differences[i]= a_value - b_value;
        }
        return differences;
    }
}

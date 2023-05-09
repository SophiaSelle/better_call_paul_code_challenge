package de.bcxp.challenge;

import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.opencsv.*;

public class data_processing {

    String[][] dataTable;
    String[] columnNames;

    public data_processing(String data_filename, char separator){
        try {
            load_data(data_filename, separator);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void load_data(String filename, char separator) throws IOException {
        FileReader filereader = new FileReader(filename);
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(separator)
                .build();
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();
        this.columnNames = csvReader.readNext();
        List<String[]> data_list = csvReader.readAll();                        
        this.dataTable = data_list.toArray(new String[data_list.size()][]);
    }

    public Float[] calculate_difference(String column_name_a, String column_name_b){
        //Get column from column name
        int column_a_index = Arrays.asList(this.columnNames).indexOf(column_name_a);
        int column_b_index = Arrays.asList(this.columnNames).indexOf(column_name_b);

        int column_length = this.dataTable.length;
        Float[] differences = new Float[column_length];
        for(int i=0; i<column_length; i++){
            //Get the values in row i
            Float a_value = Float.valueOf(this.dataTable[i][column_a_index]);
            Float b_value = Float.valueOf(this.dataTable[i][column_b_index]);
            differences[i]= a_value - b_value;
        }
        return differences;
    }

    public Float[] calculate_division(String column_name_a, String column_name_b){
        int column_a_index = Arrays.asList(this.columnNames).indexOf(column_name_a);
        int column_b_index = Arrays.asList(this.columnNames).indexOf(column_name_b);
        
        int column_length = this.dataTable.length;
        Float[] results = new Float[column_length];

        for(int i=0; i<column_length; i++){
            //Get the values in row i
            NumberFormat format = NumberFormat.getInstance();
            Float a_value = (float) 0;
            Float b_value = (float) 0;
            try {
                a_value = format.parse(this.dataTable[i][column_a_index]).floatValue();
                b_value = format.parse(this.dataTable[i][column_b_index]).floatValue();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            results[i]= a_value/b_value;
        }
        return results;
    }

    public String getNameOfMax(int name_column_index, Float[] value_column){
        List <Float> numberList = Arrays.asList(value_column);
        int indexOfMax = numberList.indexOf(Collections.max(numberList));
        return this.dataTable[indexOfMax][name_column_index];
    }

    public String getNameOfMaxDifference(String column_a, String column_b, String column_names){
        Float[] difference_column = this.calculate_difference(column_a, column_b);
        int column_names_index = Arrays.asList(this.columnNames).indexOf(column_names);
        return getNameOfMax(column_names_index, difference_column);
    }

    public String getNameOfMaxDivision(String column_a, String column_b, String column_names){
        Float[] difference_column = this.calculate_division(column_a, column_b);
        int column_names_index = Arrays.asList(this.columnNames).indexOf(column_names);
        return getNameOfMax(column_names_index, difference_column);
    }

}

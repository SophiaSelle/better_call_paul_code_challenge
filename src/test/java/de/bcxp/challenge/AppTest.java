package de.bcxp.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Example JUnit 5 test case.
 */
class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    void setUp() {
        successLabel = "successful";
    }

    @Test
    void aPointlessTest() {
        assertEquals("successful", successLabel, "My expectations were not met");
    }

    @Test
    void testConstructor() {
        data_processing dummy = new data_processing("src/test/resources/test_data.csv", ',');
        assertNotNull(dummy.dataTable);
        assertNotNull(dummy.columnNames);
        assertEquals(dummy.columnNames[0], "column1");
        assertEquals(dummy.dataTable[0][1], "5");
    }

    @Test
    void testCalculateDifference() {
        data_processing dummy = new data_processing("src/test/resources/test_data.csv", ',');
        Float[] difference = dummy.calculate_difference("column1", "column2");
        assertNotNull(difference);
        assertEquals(difference.length, 3);
        assertEquals(difference[0], -1);
        assertEquals(difference[1], 1);
        assertEquals(difference[2], 6);
    }

    @Test
    void testCalculateDivision() {
        data_processing dummy = new data_processing("src/test/resources/test_data.csv", ',');
        Float[] quotient = dummy.calculate_division("column1", "column2");
        assertNotNull(quotient);
        assertNotNull(dummy.dataTable);
        float q0 = (float) 4/5;
        float q1 = (float) 7/6;
        float q2 = 4;
        assertEquals(quotient.length, 3);
        assertEquals(quotient[0], q0);
        assertEquals(quotient[1], q1);
        assertEquals(quotient[2], q2);
    }

    @Test
    void testGetNameOfMax(){
        data_processing dummy = new data_processing("src/test/resources/test_data.csv", ',');
        Float[] values =  {(float) 4, (float) 7, (float) 8};
        String name = dummy.getNameOfMax(2, values);
        assertEquals(name, "c");
    }

    @Test
    void testGetNameOfMaxDifference(){
        data_processing dummy = new data_processing("src/test/resources/test_data.csv", ',');
        String name = dummy.getNameOfMaxDifference("column1", "column2", "column3");
        assertEquals(name, "c");
    }

    @Test
    void testGetNameOfMaxDivision(){
        data_processing dummy = new data_processing("src/test/resources/test_data.csv", ',');
        String name = dummy.getNameOfMaxDivision("column1", "column2", "column3");
        assertEquals(name, "c");
    }
}
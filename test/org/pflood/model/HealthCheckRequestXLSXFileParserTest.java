package org.pflood.model;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import java.io.FileInputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HealthCheckRequestXLSXFileParserTest {

    ArrayList<ArrayList<String>> testCaseData = null;
    ArrayList<String> row1 = null;
    ArrayList<String> row2 = null;
    ArrayList<String> row3 = null;

    FileInputStream inputFile = null;
    MockMultipartFile mockFile = null;
    HealthCheckRequestXLSXFileParser requestParser = null;
    ArrayList<ArrayList<String>> mockFileData = null;

    @Test
    public void testGetSheetData() throws Exception {

        row1 = new ArrayList<String>();
        row1.add("ID");
        row1.add("first_name");
        row1.add("last_name");
        row1.add("dob");

        row2 = new ArrayList<String>();
        row2.add("1");
        row2.add("Patrick");
        row2.add("Flood");
        row2.add("01/01/1970");

        row3 = new ArrayList<String>();
        row3.add("2");
        row3.add("Patrick2");
        row3.add("Flood2");
        row3.add("02/02/1970");

        testCaseData = new ArrayList<ArrayList<String>>();
        testCaseData.add(row1);
        testCaseData.add(row2);
        testCaseData.add(row3);

        inputFile = new FileInputStream("testdata/people_mock.xlsx");
        mockFile = new MockMultipartFile("file", "people_mock.xlsx", "multipart/form-data", inputFile);
        requestParser = new HealthCheckRequestXLSXFileParser(mockFile);
        mockFileData = requestParser.getSheetData();

        assertEquals(testCaseData, mockFileData);
    }
}
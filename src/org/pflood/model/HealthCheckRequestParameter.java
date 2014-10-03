package org.pflood.model;

import java.util.ArrayList;

/**
 * Holds results parameters from Health Check Request
 *  1) Client / Program
 *  2) XLSX / CSV file which has been converted to ArrayList [][]
 *  3) File Format
 */
public class HealthCheckRequestParameter {

    private String program;
    private ArrayList<ArrayList<String>> sheetData;
    private String fileFormat;

    public HealthCheckRequestParameter(String program, ArrayList<ArrayList<String>> sheetData, String fileFormat) {
        this.program = program;
        this.sheetData = sheetData;
        this.fileFormat = fileFormat;
    }

    public String getProgram() {
        return program;
    }

    public ArrayList<ArrayList<String>> getSheetData() {
        return sheetData;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setSheetData(ArrayList<ArrayList<String>> sheetData) {
        this.sheetData = sheetData;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }
}

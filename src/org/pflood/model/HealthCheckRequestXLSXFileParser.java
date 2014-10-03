package org.pflood.model;

import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Takes uploaded MultipartFile and converts to ArrayList<ArrayList<String>> for writing to DB
 */

public class HealthCheckRequestXLSXFileParser {

    private MultipartFile uploadedFile;

    private ArrayList<ArrayList<String>> sheetData;
    ArrayList<String> rowData = null;

    public HealthCheckRequestXLSXFileParser(MultipartFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public ArrayList<ArrayList<String>> getSheetData() {

        try{
            InputStream stream = uploadedFile.getInputStream();

            // Use POI to read excel file from input stream
            Workbook wb = WorkbookFactory.create(stream);
            Sheet sheet = wb.getSheetAt(0);
            Header header = sheet.getHeader();

            int rowsCount = sheet.getLastRowNum();

            // Copy file to sheetData 2-D array list
            sheetData = new ArrayList<ArrayList<String>>();
            for (int h = 0; h <= rowsCount; h++) {
                Row row = sheet.getRow(h);
                rowData = new ArrayList<String>();
                int colCounts = row.getLastCellNum();
                for (int k = 0; k < colCounts; k++) {
                    Cell cell = row.getCell(k);

                    if (cell == null)
                        rowData.add("");
                    else
                    {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        rowData.add(cell.getStringCellValue());
                    }
                }
                sheetData.add(rowData);
            }
            return sheetData;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }

        return sheetData;
    }
}

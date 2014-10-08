package org.pflood.data;

import org.junit.Test;
import org.pflood.model.HealthCheckResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SQLServerDBAOTest {

    SQLServerDBAO testDBAO = null;
    Statement stmt = null;

            // testSaveTest
    ArrayList<ArrayList<String>> testSaveData = null;
    ArrayList<String> testSaveRow = null;
    ArrayList<String> testSelect = null;
    String ID;
    String first_name;
    String last_name;
    String dob;

    // testGetTestResults
    HealthCheckResult expectedResult = null;
    ArrayList<HealthCheckResult> expectedResultArray = null;
    ArrayList<HealthCheckResult> resultArray = null;

    @Test
    public void testInitialise() throws SQLException {
        try {
            testDBAO = new SQLServerDBAO();

            assertTrue(testDBAO.con.isValid(1));
        }
        catch (Exception ex) {
            ex.getMessage();
        }
        finally{
            testDBAO.remove();
        }
    }

    @Test
    public void testSaveTest() throws SQLException {
        try {
            testSaveData = new ArrayList<ArrayList<String>>();

            testSaveRow = new ArrayList<String>();
            testSaveRow.add("-1");
            testSaveRow.add("Patrick");
            testSaveRow.add("Flood");
            testSaveRow.add("03/03/1970");

            testSaveData.add(null);
            testSaveData.add(testSaveRow);

            testDBAO = new SQLServerDBAO();
            testDBAO.con.setAutoCommit(false);

            testDBAO.saveTest(testSaveData);

            // Retrieve saved data

            stmt = testDBAO.con.createStatement();
            String sql = "select ID, first_name, last_name, dob " +
                    "from [pf_data].[dbo].[filechecker_test] where [ID] = -1 ";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                ID = rs.getString("ID");
                first_name = rs.getString("first_name");
                last_name = rs.getString("last_name");
                dob = rs.getString("dob");
            }

            testSelect = new ArrayList<String>();
            testSelect.add(ID);
            testSelect.add(first_name);
            testSelect.add(last_name);
            testSelect.add(dob);

            assertEquals(testSaveRow, testSelect);
        }
        catch (Exception ex) {
            ex.getMessage();
        }
        finally{
            testDBAO.con.rollback();
            testDBAO.remove();
        }
    }

    @Test
    public void testGetTestResults() throws SQLException {
        try{
            expectedResult = new HealthCheckResult("TEST", "-1", "99/99/1970",
                    "[DOB] must be in dd/mm/yyyy format", "4");
            expectedResultArray = new ArrayList<HealthCheckResult>();
            expectedResultArray.add(expectedResult);

            testDBAO = new SQLServerDBAO();
            testDBAO.con.setAutoCommit(false);

            stmt = testDBAO.con.createStatement();
            String sql1 = "delete * from [pf_data].[dbo].[filechecker_test] ";
            stmt.executeUpdate(sql1);

            // Insert error row into [filechecker_test]
            String sql2 = "insert into [pf_data].[dbo].[filechecker_test] " +
                    "values ('1', 'Patrick', 'Flood', '01/01/1970')";
            stmt.executeUpdate(sql2);

            resultArray = testDBAO.getTestResults();

            System.out.println("aaa " + resultArray.get(0));

            assertEquals(resultArray, expectedResultArray);
        }
        catch (Exception ex) {
            ex.getMessage();
        }
        finally{
            testDBAO.con.rollback();
            testDBAO.remove();
        }

    }
}
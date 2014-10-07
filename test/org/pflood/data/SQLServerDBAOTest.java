package org.pflood.data;

import org.junit.Test;
import org.pflood.data.SQLServerDBAO;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class SQLServerDBAOTest {

    SQLServerDBAO testDBAO = null;

    @Test
    public void testInitialise() throws SQLException {
        try {
            testDBAO = new SQLServerDBAO();
        }
        catch (Exception ex) {
            ex.getMessage();
        }
        assertTrue(testDBAO.con.isValid(1));
    }
}
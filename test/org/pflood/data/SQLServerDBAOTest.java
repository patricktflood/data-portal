package org.pflood.data;

import org.junit.Test;
import org.pflood.data.SQLServerDBAO;

import static org.junit.Assert.*;

public class SQLServerDBAOTest {

    SQLServerDBAO testDBAO = null;

    @Test
    public void testInitialise() {
        try {
            testDBAO = new SQLServerDBAO();
        }
        catch (Exception ex) {
            ex.getMessage();
        }
        assertEquals("created", testDBAO.getStatus());
    }
}
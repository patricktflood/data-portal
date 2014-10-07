package org.pflood.data;

import org.pflood.model.HealthCheckResult;

import java.sql.*;
import java.util.ArrayList;

public class SQLServerDBAO {

    Connection con;

    public static String url = "jdbc:sqlserver://paddydb.cnsq3xhchaax.eu-west-1.rds.amazonaws.com:1433";
    public static String dbdriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String username = "paddydb";
    public static String password = "paddydb01";

    public SQLServerDBAO() throws Exception{

        try {
            Class.forName(dbdriver);
            con = DriverManager.getConnection(url, username, password);
        }
        catch (Exception ex) {
            throw new Exception("Couldn't open connection to database: "
                    + ex.getMessage());
        }
    }

    public void remove() {
        try {
            con.close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void saveTest(ArrayList<ArrayList<String>> sheetData) throws Exception {

        try
        {
            sheetData.remove(0);

            String sql1 = "truncate table [pf_data].[dbo].[filechecker_test_temp] ";

            String sql2 = "insert into [pf_data].[dbo].[filechecker_test_temp] " +
                    "([ID] " +
                    ",[first_name] " +
                    ",[last_name] " +
                    ",[dob]) " +
                    "values (?, ?, ?, ?)";

            PreparedStatement ps1 = con.prepareStatement(sql1);
            PreparedStatement ps2 = con.prepareStatement(sql2);
            CallableStatement cs1 = con.prepareCall("{call pf_data.dbo.usp_filechecker_test_migrate}");

            for (ArrayList<String> row : sheetData )
            {
                ps2.setString(1, row.get(0));
                ps2.setString(2, row.get(1));
                ps2.setString(3, row.get(2));
                ps2.setString(4, row.get(3));
                ps2.addBatch();
            }

            ps1.execute();
            ps2.executeBatch();
            cs1.execute();

            ps1.close();
            ps2.close();
            cs1.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public ArrayList<HealthCheckResult> getTestResults() throws Exception {

        ArrayList<HealthCheckResult> results = new ArrayList<HealthCheckResult>();

        try {
            CallableStatement cs = con.prepareCall("{call pf_data.dbo.usp_filechecker_test_verify_data}");

            ResultSet rs;
            rs = cs.executeQuery();

            while (rs.next())
            {
                HealthCheckResult hcr =
                        new HealthCheckResult(rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5));

                results.add(hcr);
            }

            cs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return results;
    }
}

package com.spiralSpotManagement.ReportModule;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ReportOperation {
    public static void viewAllSpots() throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select  * from Spot_table");

        String[][] table = new String[][] { { "Spot Id", "User Id", "Category Id", "Location Id", "Spot Name", "Spot Description", "Views", "Viewers", "Rates", "Spot Status","Registration Date", "Updated Date"} };
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));


        final StringBuilder formatString = new StringBuilder("");
        boolean leftJustifiedRows= false;
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + e.getValue() + "s "));
        formatString.append("|\n");

        Stream.iterate(0, (i -> i < table.length), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(), table[a]));
        while (rs.next()) {
            String spot_id = rs.getString("spot_id");
            String user_id = rs.getString("user_id");
            String category_id = rs.getString("category_id");
            String location_id = rs.getString("location_id");
            String spot_name = rs.getString("spot_name");
            String spot_description = rs.getString("spot_description");
            int views = rs.getInt("views");
            int viewers = rs.getInt("viewers");
            int rates=rs.getInt("rates");
            String status = rs.getString("status");
            Date registration_date = rs.getDate("registration_date");
            Date update_date = rs.getDate("update_date");


            String[][] table1 = new String[][] { { spot_id, user_id, category_id, location_id, spot_name, spot_description, String.valueOf(views), String.valueOf(viewers), String.valueOf(rates),status,
                    String.valueOf(registration_date), String.valueOf(update_date)} };

            Map<Integer, Integer> columnLengths1 = new HashMap<>();
            Arrays.stream(table1).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
                if (columnLengths1.get(i) == null) {
                    columnLengths1.put(i, 0);
                }
                if (columnLengths1.get(i) < a[i].length()) {
                    columnLengths1.put(i, a[i].length());
                }
            }));


            final StringBuilder formatString1 = new StringBuilder("");
            columnLengths.entrySet().stream().forEach(e -> formatString1.append("| %" +  e.getValue() + "s "));
            formatString1.append("|\n");

            Stream.iterate(0, (i -> i < table1.length), (i -> ++i))
                    .forEach(a -> System.out.printf(formatString1.toString(), table1[a]));
        }
        connection.close();
    }
}

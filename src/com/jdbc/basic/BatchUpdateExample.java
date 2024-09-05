package com.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdateExample {
	
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "root";

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            // Step 1: Create a Statement object
            statement = connection.createStatement();

            // Step 2: Add multiple SQL statements to the batch
            statement.addBatch("INSERT INTO students (id, name, age) VALUES (3, 'Bob', 25)");
            statement.addBatch("INSERT INTO students (id, name, age) VALUES (4, 'Carol', 27)");
            statement.addBatch("UPDATE students SET age = 24 WHERE id = 2");

            // Step 3: Execute the batch
            int[] affectedRecords = statement.executeBatch();

            // Step 4: Process the result
            System.out.println("Batch executed successfully! Rows affected: " + affectedRecords.length);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

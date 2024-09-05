package com.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementExample {
	
	/*
	 * execute(): Executes any SQL (returns true if ResultSet is returned, false for update count).
	 * executeQuery(): Executes a SELECT query and returns a ResultSet.
	 * executeUpdate(): Executes INSERT, UPDATE, or DELETE (returns the number of affected rows).
	 * executeBatch(): Executes a batch of SQL statements in one go.
	 */
	
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

            // Step 2: Execute a simple SQL INSERT query
            String sql = "INSERT INTO students (id, name, age) VALUES (1, 'John', 22)";
            int rowsAffected = statement.executeUpdate(sql); // Executes DML statements
            
            // Step 3: Process the result
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully!");
            }

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

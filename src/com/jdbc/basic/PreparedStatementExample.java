package com.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementExample {
	
	/*
	 * setXXX(): Sets values for place-holders (e.g., setInt(), setString()).
	 * execute(): Same as in Statement, but with parameterized queries.
	 * executeQuery(): Executes a SELECT query and returns a ResultSet (for parameterized queries).
	 * executeUpdate(): Executes INSERT, UPDATE, or DELETE with parameters.
	 */
    
	public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "root";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            // Step 1: Create a PreparedStatement object with a parameterized SQL query
            String sql = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // Step 2: Set the values for the placeholders (?)
            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, "Alice");
            preparedStatement.setInt(3, 23);

            // Step 3: Execute the query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully using PreparedStatement!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

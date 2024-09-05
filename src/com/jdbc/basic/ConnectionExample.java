package com.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionExample {
    public static void main(String[] args) {
        
    	
    	/*
    	 * Database connection URL
    	 * We can provide it hard-coded here or create an database.properties file and load it 
    	 * using the java.util.Properties;
    	 */
        String url = "jdbc:mysql://localhost:3306/mydb"; // Replace 'mydb' with your database name
        String user = "root";                            // Database username
        String password = "root";                        // Database password

        Connection connection = null;

        try {
        	/*  
        	 * Step 1: Register JDBC driver
             * We can use any of these 2 methods
             *  i. Class.forNam e("com.mysql.cj.jdbc.Driver");
             *  ii. DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
             * Or if we are using JDBC 4.0 (Java 6) or more. we don't even need to register the driver
             * This concept is called "Auto-loading".
             * 
        	 */
        	Class.forName("com.mysql.cj.jdbc.Driver");
    		
            
            
            // Step 2: Open a connection
        	// If we are using Properties, we can directly pass it instead of user and password
            connection = DriverManager.getConnection(url, user, password);

            // If connection is successful
            if (connection != null) {
                System.out.println("Connected to the database successfully!");
            }

        } catch (ClassNotFoundException e) {
            // Handle error if the driver class is not found
            System.out.println("JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle SQL connection errors
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        } finally {
            try {
                // Step 3: Close the connection when finished
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

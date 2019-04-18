package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://35.199.153.134:3306/OCAdopt", "eshields", "big0notAtion");

            System.out.println("Connection to MYSQL database successful");
            if (con.isClosed()) {
                con = DriverManager.getConnection(
                        "jdbc:mysql://35.199.153.134:3306/OCAdopt", "eshields", "big0notAtion");

            }
            Shelters shelters = new Shelters();
            shelters.addShelters(con);
            Animals animals = new Animals();
            animals.addAnimals(con);
        }
        catch (Exception e)
        {
            System.out.print("Error message: ");
            System.out.print(e.getMessage());
        }
    }
}
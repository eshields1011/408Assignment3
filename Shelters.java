package com.company;

import java.sql.*;
import java.io.*;
import org.apache.commons.csv.*;

public class Shelters {
    public void addShelters(Connection con){
        try {
            Reader in = new FileReader("shelters.csv");
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            for (CSVRecord record : records) {
                String sheltername = record.get(0);
                String city = record.get(1);
                String street = record.get(2);
                String phonenumber = record.get(3);

                PreparedStatement addShelter = con.prepareStatement(
                        "INSERT INTO Shelters(ShelterName, City, Street, PhoneNumber) VALUES (?,?,?,?)");
                addShelter.setString(1, sheltername);
                addShelter.setString(2, city);
                addShelter.setString(3, street);
                addShelter.setString(4, phonenumber);
                addShelter.executeUpdate();
            }
        }
        catch (Exception e)
        {
            System.out.print("Error message: ");
            System.out.print(e.getMessage());
        }

    }
}


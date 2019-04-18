package com.company;

import java.sql.*;
import java.io.*;
import org.apache.commons.csv.*;
import java.util.Scanner;

public class Animals {
    public void addAnimals (Connection con)
    {
        try {
            Scanner keyboard = new Scanner (System.in);
            System.out.println("Enter the name of the csv file with animal data: ");
            String filename = keyboard.nextLine();
            System.out.println("Database will take a few minutes to fully update...");
            Reader animalsin = new FileReader(filename);
            Iterable<CSVRecord> logs = CSVFormat.EXCEL.parse(animalsin);

            for (CSVRecord animal : logs) {
                int typeid = Integer.parseInt(animal.get(0));
                String name = animal.get(1);
                String color = animal.get(2);
                int age = Integer.parseInt(animal.get(3));
                int gender = Integer.parseInt(animal.get(4));
                int weight = Integer.parseInt(animal.get(5));
                int price = Integer.parseInt(animal.get(6));
                int breed = Integer.parseInt(animal.get(7));
                int shelterid = Integer.parseInt(animal.get(8));

                PreparedStatement animals = con.prepareStatement(
                        "INSERT INTO Animals(AnimalName, ShelterID) VALUES (?,?)");
                animals.setString(1, name);
                animals.setInt(2, shelterid);
                animals.executeUpdate();

                PreparedStatement animaltypes = con.prepareStatement(
                        "INSERT INTO AnimalTypes(TypeID, AnimalName) VALUES (?,?)");
                animaltypes.setInt(1, typeid);
                animaltypes.setString(2, name);
                animaltypes.executeUpdate();

                PreparedStatement addbreed = con.prepareStatement(
                        "INSERT INTO Breed(BreedID, AnimalName) VALUES (?,?)");
                addbreed.setInt(1, breed);
                addbreed.setString(2, name);
                addbreed.executeUpdate();

                PreparedStatement animalinfo = con.prepareStatement(
                        "INSERT INTO AnimalInfo(Color, Age, Gender, Weight, Price, AnimalInfoID) VALUES (?,?,?,?,?,?)");
                PreparedStatement st = con.prepareStatement("SELECT MAX(AnimalID) FROM Animals");
                ResultSet rs = st.executeQuery();
                rs.next();
                int infoid = rs.getInt(1);

                animalinfo.setString(1, color);
                animalinfo.setInt(2, age);
                animalinfo.setInt(3, gender);
                animalinfo.setInt(4, weight);
                animalinfo.setInt(5, price);
                animalinfo.setInt(6, infoid);
                animalinfo.executeUpdate();
            }
        }
        catch (Exception e)
        {
            System.out.print("Error message: ");
            System.out.print(e.getMessage());
        }
    }
}


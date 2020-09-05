package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Files {
    private String encFile;
    private String decFile;

    public Files(String encFile, String decFile) {
        this.encFile = encFile;
        this.decFile = decFile;
    }

    public String readFromFile() {
        String data = "";
        try {
            File myObj = new File(encFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    public void writeToFile(String data) {

        try {
            FileWriter myWriter = new FileWriter(decFile);
            myWriter.write(data);
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
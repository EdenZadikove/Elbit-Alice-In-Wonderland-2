package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Files r = new Files("cypher.txt", "decrypted_cypher.txt");
        String data = r.readFromFile();

        //call Playfair algo
        PlayfairCipherDecryption x = new PlayfairCipherDecryption();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a keyword:");
        String keyword = sc.next();
        x.initMat(keyword);
        System.out.println("The matrix is: ");
        x.printMatrix();
        r.writeToFile(x.decryptMessage(data));
        System.out.println("The decrypted text is in the following path:\n" + System.getProperty("user.dir") + "\n" +"file name: decrypted_cypher.txt");
    }
}

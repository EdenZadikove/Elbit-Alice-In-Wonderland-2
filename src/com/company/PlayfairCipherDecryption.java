package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayfairCipherDecryption
{
    private String KeyWord = new String();
    private String Key = new String();
    private char   matrix_arr[][] = new char[5][5];


    public void initMat(String key) {
        int index = 0;
        String newKey = "";
        this.KeyWord = key;
        while(index < key.length()) {
            char current = key.charAt(index++);
            current = Character.toLowerCase(current);
            if(!isCharExists(newKey, current)) //char does not exists in newKey
                newKey += current; //concat not existing char  to newKey
        }

        for(int i = 0; i < 26; i++ ) {
            char current = (char)(i+97); //ascii
            if(!isCharExists(newKey,current) && current!='j')
                newKey += current;
        }

        //fill the matrix with the key
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                matrix_arr[i][j] = newKey.charAt(i*5 + j);
            }
        }
    }

    private boolean isCharExists(String key, char letter) {
        return (key.indexOf(letter) != -1);
    }


    //divided a given string into array of pairs
    private String[] Divid2Pairs(String new_string)
    {
        String original = new_string.replace(" ", ""); //replace spaces with 'x' char
        int size = original.length();

        if (size % 2 != 0)
        {
            size++;
            original = original + 'x';
        }

        String x[] = new String[size / 2];
        int counter = 0;
        for (int i = 0; i < size / 2; i++)
        {
            x[i] = original.substring(counter, counter + 2);
            counter = counter + 2;
        }
        return x;
    }

    public int[] GetDiminsions(char letter)
    {
        int[] key = new int[2];
        if (letter == 'j')
            letter = 'i';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (matrix_arr[i][j] == Character.toLowerCase(letter))
                {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }

    public String decryptMessage(String Code)
    {
        String data = Code;
        String Original = new String();
        String src_arr[] = Divid2Pairs(Code);
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++)
        {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            if (part1[0] == part2[0])
            {
                if (part1[1] > 0)
                    part1[1]--;
                else
                    part1[1] = 4;
                if (part2[1] > 0)
                    part2[1]--;
                else
                    part2[1] = 4;
            }
            else if (part1[1] == part2[1])
            {
                if (part1[0] > 0)
                    part1[0]--;
                else
                    part1[0] = 4;
                if (part2[0] > 0)
                    part2[0]--;
                else
                    part2[0] = 4;
            }
            else
            {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Original = Original + matrix_arr[part1[0]][part1[1]]
                    + matrix_arr[part2[0]][part2[1]];
        }
        return setDecryptkey(Original, data);
    }

    private String setDecryptkey(String decryptkey, String data) {
        StringBuilder newString = new StringBuilder(data);
        int j = 0;

        for(int i = 0; i < decryptkey.length(); i++) {
            char letter = decryptkey.charAt(i);
            while((newString.charAt(j) < 97 || newString.charAt(j) > 122 ) && (newString.charAt(j) < 65 || newString.charAt(j) > 90))
                j++;
            newString.setCharAt(j++, letter);
        }
        return newString.toString();
    }

    public void printMatrix() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++)
                System.out.print(matrix_arr[i][j] + " ");
            System.out.println();
        }
    }
}

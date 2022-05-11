package com.company;

/*
Practice Problem 2
1180L-06
Shelby Stare
*/

import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws Exception
    {
        //Identify variables
        int players;
        int games;

        //create scanner
        //java.util.Scanner floatScanner = new java.util.Scanner(System.in);
        Scanner input = new Scanner(System.in);

        //user input
        System.out.print("How many people are playing? ");
        players = input.nextInt();

        //equation
        games = players / 4;

        //output
        System.out.println("There are " + games);

        //close scanner
        close.Scanner();
    }
}

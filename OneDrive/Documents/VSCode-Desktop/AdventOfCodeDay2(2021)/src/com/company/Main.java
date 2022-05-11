package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //Day2_1();
        Day2_2();
    }

    public static void Day2_1() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay2(2021)/src/Movement.txt"));

        int total = 0;
        int horiz = 0;
        int depth = 0;

        while(f.hasNext()) {
            String inst = f.nextLine();
            Scanner splitString = new Scanner(inst);
            splitString.useDelimiter(" ");
            String move = splitString.next();
            int movement = splitString.nextInt();
            if(move.equalsIgnoreCase("forward")) {
                horiz += movement;
            } else if (move.equalsIgnoreCase("up")) {
                depth -= movement;
            } else if (move.equalsIgnoreCase("down")) {
                depth += movement;
            }
        }
        total = horiz * depth;
        System.out.println(total);
    }

    public static void Day2_2() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay2(2021)/src/Movement.txt"));

        int total = 0;
        int horiz = 0;
        int depth = 0;
        int aim = 0;

        while(f.hasNext()) {
            String inst = f.nextLine();
            Scanner splitString = new Scanner(inst);
            splitString.useDelimiter(" ");
            String move = splitString.next();
            int movement = splitString.nextInt();
            if(move.equalsIgnoreCase("forward")) {
                horiz += movement;
                depth = depth + (aim * movement);
            } else if (move.equalsIgnoreCase("up")) {
                aim -= movement;
            } else if (move.equalsIgnoreCase("down")) {
                aim += movement;
            }
        }
        total = horiz * depth;
        System.out.println(total);
    }
}

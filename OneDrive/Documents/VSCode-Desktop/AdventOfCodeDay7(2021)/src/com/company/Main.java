package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //Day7_1();
        Day7_2();
    }

    public static void Day7_1() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay7(2021)/src/Move.txt"));

        String move = f.nextLine();
        move = move.replace(",", " ");

        Scanner splitString = new Scanner(move);
        splitString.useDelimiter(" ");
        ArrayList<Integer> placement = new ArrayList<>();

        int max = 0;
        int min = 100;

        while(splitString.hasNext()) {
            int next = splitString.nextInt();
            if (next > max) {
                max = next;
            }
            if (next < min) {
                min = next;
            }
            placement.add(next);
        }

        //int num = 0;
        int dist = 0;
        int maxDist = Integer.MAX_VALUE;

        for (int i = min; i < max; i++) {
            //num = 0;
            dist = 0;

            for (int j = min; j < placement.size(); j++) {
                dist += Math.abs(placement.get(j) - i);
            }

            /*
            for(int j=0; j<num; j++) {
                dist += j;
            }
            */

            if(maxDist > dist) {
                maxDist = dist;
            }
        }

        System.out.println(maxDist);
    }

    public static void Day7_2() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay7(2021)/src/Move.txt"));

        String move = f.nextLine();
        move = move.replace(",", " ");

        Scanner splitString = new Scanner(move);
        splitString.useDelimiter(" ");
        ArrayList<Integer> placement = new ArrayList<>();

        int max = 0;
        int min = 100;

        while(splitString.hasNext()) {
            int next = splitString.nextInt();

            if (next > max) {
                max = next;
            }

            if (next < min) {
                min = next;
            }

            placement.add(next);
        }

        int num = 0;
        int dist = 0;
        int maxDist = Integer.MAX_VALUE;
        int minDist = 0;

        for (int i = min; i < max; i++) {

            dist = 0;

            for (int j = min; j < placement.size(); j++) {
                num = 0;


                num += Math.abs(placement.get(j) - i);

                for(int k=0; k<=num; k++) {
                    dist += k;

                }






            }

            if (maxDist > dist) {
                maxDist = dist;
            }

        }

        System.out.println(maxDist);
    }
}

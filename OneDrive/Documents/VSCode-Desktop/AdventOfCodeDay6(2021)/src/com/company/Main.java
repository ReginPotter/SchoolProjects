package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
	    //Day6_1();
	    Day6_2();
    }

    public static void Day6_1() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay6(2021)/src/TestFish.txt"));

        ArrayList<Integer> fishs = new ArrayList<>();

        String input = f.nextLine();
        input = input.replace(",", "");
        Scanner splitString = new Scanner(input);
        splitString.useDelimiter("");

        for(int i=0; i<input.length(); i++) {
            int num = splitString.nextInt();
            fishs.add(num);
        }

        int sum = 0;
        int oldFishs = fishs.size();
        int newFish = 0;
        for (int i = 0; i<80; i++) {
            newFish = 0;
            for(int j=0; j<fishs.size(); j++) {
                if (fishs.get(j) == 0) {
                    fishs.remove(j);
                    newFish++;
                    j--;
                } else {
                    fishs.set(j, fishs.get(j) - 1);
                }
            }

            for(int j=0; j<newFish; j++) {
                fishs.add(6);
                fishs.add(8);
            }

        }
        sum = fishs.size();
        System.out.println(sum);
    }

    public static void Day6_2() throws FileNotFoundException, InterruptedException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay6(2021)/src/Fish.txt"));


        ArrayList<ArrayList<Integer>> fishs = new ArrayList<>();

        String input = f.nextLine();
        input = input.replace(",", "");
        Scanner splitString = new Scanner(input);
        splitString.useDelimiter("");

        double zeros = 0;
        double ones = 0;
        double twos = 0;
        double threes = 0;
        double fours = 0;
        double fives = 0;
        double sixs = 0;
        double sevens = 0;
        double eights = 0;

        for(int i=0; i<input.length(); i++) {
            int num = splitString.nextInt();
            /*
            ArrayList<Integer> fish = new ArrayList<>();

            fish.add(num);
            fishs.add(fish);
            */

            if(num == 0) {
                zeros++;
            } else if (num == 1) {
                ones++;
            } else if (num == 2) {
                twos++;
            } else if (num == 3) {
                threes++;
            } else if (num == 4) {
                fours++;
            } else if (num == 5) {
                fives++;
            } else if (num == 6) {
                sixs++;
            } else if (num == 7) {
                sevens++;
            } else if (num == 8) {
                eights++;
            }
        }



        double sum = 0;

        for (int i = 0; i<256; i++) {

            double temp = zeros;

            zeros = ones;
            ones = twos;
            twos = threes;
            threes = fours;
            fours = fives;
            fives = sixs;
            sixs = sevens + temp;
            sevens = eights;
            eights = temp;
        }

        sum = zeros + ones + twos + threes + fours + fives + sixs + sevens + eights;

        System.out.println(sum);

    }
}

package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    Day8_1();
        Day8_2();
    }

    public static void Day8_1() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay8(2021)/src/Numbers.txt"));

        ArrayList<String> input = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();

        while(f.hasNext()) {
            String words = f.nextLine();
            words = words.replace("|", " ");
            Scanner splitString = new Scanner(words);
            splitString.useDelimiter("   ");

            input.add(splitString.next());
            output.add(splitString.next());
        }

        String one = "";
        String two = "";
        String three = "";
        String four = "";
        String five = "";
        String six = "";
        String seven = "";
        String eight = "";
        String nine = "";

        int sum = 0;

        for(int i=0; i<input.size(); i++) {
            String nums = input.get(i);
            Scanner splitString = new Scanner(nums);
            splitString.useDelimiter(" ");
            while(splitString.hasNext()) {
                String word = splitString.next();

                if(word.length() == 3) {
                    seven = word;
                } else if (word.length() == 4) {
                    four = word;
                } else if (word.length() == 7) {
                    eight = word;
                } else if (word.length() == 2) {
                    one = word;
                }
            }

            nums = output.get(i);
            splitString = new Scanner(nums);
            splitString.useDelimiter(" ");
            while(splitString.hasNext()) {
                String word = splitString.next();

                if (word.length() == 3) {
                    sum++;
                } else if (word.length() == 4) {
                    sum++;
                } else if (word.length() == 7) {
                    sum++;
                } else if (word.length() == 2) {
                    sum++;
                }
            }
        }

        System.out.println(sum);
    }

    public static void Day8_2() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay8(2021)/src/TestNumbers.txt"));

        ArrayList<String> input = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();

        while(f.hasNext()) {
            String words = f.nextLine();
            words = words.replace("|", " ");
            Scanner splitString = new Scanner(words);
            splitString.useDelimiter("   ");

            input.add(splitString.next());
            output.add(splitString.next());
        }

        String zero = "";
        String one = "";
        String two = "";
        String three = "";
        String four = "";
        String five = "";
        String six = "";
        String seven = "";
        String eight = "";
        String nine = "";

        String[][] board = new String[5][3];

        int sum = 0;


        for(int i=0; i<input.size(); i++) {

            String value = "";


            String nums = input.get(i);
            Scanner splitString = new Scanner(nums);
            splitString.useDelimiter(" ");

            String[] words = new String[10];
            int count = 0;
            while(splitString.hasNext()) {
                words[count] = splitString.next();
                count++;
            }

            Arrays.sort(words, (a, b)->Integer.compare(b.length(), a.length()));

            for(int j=0; j<words.length; j++) {
                System.out.println(words[j]);
            }
            System.out.println("\n");

            if(seven.equals("") || four.equals("") || eight.equals("") || one.equals("")) {
                for (String word : words) {
                    if (word.length() == 3) {
                        seven = word;
                    } else if (word.length() == 4) {
                        four = word;
                    } else if (word.length() == 7) {
                        eight = word;
                    } else if (word.length() == 2) {
                        one = word;
                    }
                }
            } else {
                String temp = eight;
                Scanner eliminator = new Scanner(words[1]);
                eliminator.useDelimiter("");


                while(eliminator.hasNext()) {
                    temp = temp.replace(eliminator.next(), "");
                }
                Scanner first = new Scanner(one);
                first.useDelimiter("");

                Scanner second = new Scanner(four);
                second.useDelimiter("");

                String meh = first.next();
                String neh = first.next();
                if (temp.equals(meh) || temp.equals(neh)) {
                    board[3][0] = temp;
                } else if (temp.equals())

                eliminator = new Scanner(words[])
            }

            /*

            for(int j=0; j<words.length; j++) {
                String word = words[j];
                char[] arr = word.toCharArray();
                Arrays.sort(arr);
                word = new String(arr);

                if(word.length() == 3) {
                    seven = word;
                } else if (word.length() == 4) {
                    four = word;
                } else if (word.length() == 7) {
                    eight = word;
                } else if (word.length() == 2) {
                    one = word;
                } else if (word.equals("bcdef")) {
                    five = word;
                } else if (word.equals("acdfg")) {
                    two = word;
                } else if (word.equals("abcdf")) {
                    three = word;
                } else if (word.equals("abcdef")) {
                    nine = word;
                } else if (word.equals("bcdefg")) {
                    six = word;
                } else if (word.equals("abcdeg")) {
                    zero = word;
                }
            }


            nums = output.get(i);
            splitString = new Scanner(nums);
            splitString.useDelimiter(" ");
            while(splitString.hasNext()) {
                String word = splitString.next();

                char[] arr = word.toCharArray();
                Arrays.sort(arr);
                word = new String(arr);

                if(word.length() == 3) {
                    value += "7";
                } else if (word.length() == 4) {
                    value += "4";
                } else if (word.length() == 7) {
                    value += "8";
                } else if (word.length() == 2) {
                    value += "1";
                } else if (word.equals("bcdef")) {
                    value += "5";
                } else if (word.equals("acdfg")) {
                    value += "2";
                } else if (word.equals("abcdf")) {
                    value += "3";
                } else if (word.equals("abcdef")) {
                    value += "9";
                } else if (word.equals("bcdefg")) {
                    value += "6";
                } else if (word.equals("abcdeg")) {
                    value += "0";
                }
            }
            sum += Integer.parseInt(value);
            */
        }

        System.out.println(sum);

    }
}

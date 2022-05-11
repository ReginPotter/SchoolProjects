package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //Day3_1();
        Day3_2();
    }

    public static void Day3_1() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay3(2021)/src/Binary.txt"));
        ArrayList<String> nums = new ArrayList<>();

        while (f.hasNext()) {
            nums.add(f.nextLine());
        }

        String fin = "";

        for(int i=0; i<nums.get(0).length(); i++) {

            int one = 0;
            int zero = 0;

            for(String s: nums) {
                /*
                Scanner splitString = new Scanner(s);
                splitString.useDelimiter("");
                */
                char[] letters = s.toCharArray();
                if(letters[i] == '1') {
                    one++;
                } else {
                    zero++;
                }
            }
            System.out.println(one);
            System.out.println(zero);
            if (one > zero) {
                fin += "1";
            } else {
                fin += "0";
            }
        }
        System.out.println(fin);
    }

    public static void Day3_2() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay3(2021)/src/Binary.txt"));
        ArrayList<String> nums = new ArrayList<>();
        ArrayList<String> newnums = new ArrayList<>();

        while (f.hasNext()) {
            nums.add(f.nextLine());
        }

        String fin = "";
        int most = 0;
        boolean set = false;

        for(int i=0; i<nums.get(0).length(); i++) {



            int one = 0;
            int zero = 0;


            if (nums.size() == 1) {
                break;
            }

            for(int j=0; j<nums.size(); j++) {
                String s = nums.get(j);
                /*
                Scanner splitString = new Scanner(s);
                splitString.useDelimiter("");
                */
                char[] letters = s.toCharArray();
                if (!set) {

                    if (letters[i] == '1') {
                        one++;
                    } else {
                        zero++;
                    }
                } else {
                    if ((letters[i] - '0') == most) {
                        //System.out.println((letters[i] - '0')+1 + " HELLO");
                        newnums.add(nums.get(j));
                    } else {

                        continue;
                    }
                }
            }


            if (!set) {
                if (one >= zero) {
                    most = 1;
                    i--;
                    set = true;
                } else {

                    most = 0;
                    i--;
                    set = true;
                }
            } else {
                set = !set;
                nums.clear();
                for(int j=0; j<newnums.size(); j++) {
                    nums.add(newnums.get(j));
                }
                newnums.clear();
            }



        }
        System.out.println(nums.get(0));
    }
}

package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
    	//Day1_1();
		Day1_2();
    }

    public static void Day1_2() throws FileNotFoundException {
		Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay1(2021)/src/Measurements.txt"));

		ArrayList<Integer> nums = new ArrayList<>();
		int sum1 = 0;
		int sum2 = 0;
		int inc = 0;

		while(f.hasNext()) {
			nums.add(f.nextInt());
		}

		for(int i=0; i<nums.size()-2; i++) {
			sum2 = sum1;
			sum1 = nums.get(i) + nums.get(i+1) + nums.get(i+2);
			if(sum2 == 0) {
				continue;
			}
			/*
			System.out.println(sum2 + " = sum2");
			System.out.println(sum1 + " = sum1");
			System.out.println(inc);
			System.out.println();
			*/

			if(sum2 < sum1) {
				inc++;
			}
		}
		System.out.println(inc);
	}

    public static void Day1_1() throws FileNotFoundException {

		File file = new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay1(2021)/src/Depth.txt");

		Scanner f = new Scanner(file);

		int now = 0;
		int after = 0;
		int inc = 0;
		while(f.hasNext()) {

			after = now;
			now = f.nextInt();

			if(after == 0) {
				continue;
			}

			if (now > after) {
				inc++;
				continue;
			}
		}

		System.out.println(inc);
	}
}

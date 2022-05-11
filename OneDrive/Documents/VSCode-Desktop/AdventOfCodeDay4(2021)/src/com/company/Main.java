package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    //Day4_1();
        Day4_2();
    }

    public static void Day4_1() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay4(2021)/src/Input.txt"));

        ArrayList<Board> boards = new ArrayList<>();

        String words = f.nextLine();
        f.nextLine();

        while(f.hasNext()) {
            int[][] input = new int[5][5];
            for(int i=0; i<5; i++) {
                String nums = f.nextLine();
                nums = nums.replace("  ", " ");
                Scanner splitString = new Scanner(nums);
                splitString.useDelimiter(" ");
                for(int j=0; j<5; j++) {
                    String num = splitString.next();
                    if(num.equals("")) {
                        input[i][j] = 0;
                    }
                    else {
                        input[i][j] = Integer.parseInt(num);
                    }
                }
            }
            Board b = new Board(input);
            boards.add(b);
            if(f.hasNext()) {
                f.nextLine();
            }
        }

        Scanner splitString = new Scanner(words);
        splitString.useDelimiter(",");
        boolean won = false;
        Board winner = new Board();
        int winningNum = 0;

        while(true) {
            int call = splitString.nextInt();

            for(Board b: boards) {
                for(int i=0; i<5; i++) {
                    for(int j=0 ;j<5; j++) {
                        if(call == b.getNum(i, j)) {
                            b.setRight(i, j);
                        }
                    }
                }
                if(b.won()) {

                    winner = b;
                    won = true;
                    break;
                }
            }
            if(won) {
                winningNum = call;
                break;
            }
        }

        System.out.println(winner.calc() * winningNum);
    }

    public static void Day4_2() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay4(2021)/src/Input.txt"));

        ArrayList<Board> boards = new ArrayList<>();

        String words = f.nextLine();
        f.nextLine();

        while(f.hasNext()) {
            int[][] input = new int[5][5];
            for(int i=0; i<5; i++) {
                String nums = f.nextLine();
                nums = nums.replace("  ", " ");
                Scanner splitString = new Scanner(nums);
                splitString.useDelimiter(" ");
                for(int j=0; j<5; j++) {
                    String num = splitString.next();
                    if(num.equals("")) {
                        input[i][j] = 0;
                    }
                    else {
                        input[i][j] = Integer.parseInt(num);
                    }
                }
            }
            Board b = new Board(input);
            boards.add(b);
            if(f.hasNext()) {
                f.nextLine();
            }
        }

        Scanner splitString = new Scanner(words);
        splitString.useDelimiter(",");
        boolean won = false;
        Board winner = new Board();
        int winningNum = 0;

        while(true) {
            int call = splitString.nextInt();

            for(int k=0; k<boards.size(); k++) {
                Board b = boards.get(k);
                for(int i=0; i<5; i++) {
                    for(int j=0 ;j<5; j++) {
                        if(call == b.getNum(i, j)) {
                            b.setRight(i, j);
                        }
                    }
                }
                if(b.won() && boards.size() == 1) {

                    winner = b;
                    won = true;
                    break;
                } else if (b.won()) {
                    boards.remove(b);
                    k--;
                    continue;
                }
            }
            if(won) {
                winningNum = call;
                break;
            }
        }

        System.out.println(winner.calc() * winningNum);
    }
}

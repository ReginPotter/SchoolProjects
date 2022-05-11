package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Day5_1();
    }

    public static void Day5_1() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay5(2021)/src/Vents.txt"));

        ArrayList<Integer> fileX = new ArrayList<>();
        ArrayList<Integer> fileY = new ArrayList<>();

        while(f.hasNext()) {
            String input = f.nextLine();
            Scanner splitString = new Scanner(input);
            splitString.useDelimiter(" -> ");


            String start = splitString.next();
            Scanner splitStr = new Scanner(start);
            splitStr.useDelimiter(",");
            fileX.add(splitStr.nextInt());
            fileY.add(splitStr.nextInt());

            start = splitString.next();
            splitStr = new Scanner(start);
            splitStr.useDelimiter(",");
            fileX.add(splitStr.nextInt());
            fileY.add(splitStr.nextInt());
        }

        int arrSize = 0;

        for(int i=0; i<fileX.size(); i++) {
            if(fileX.get(i) > arrSize) {
                arrSize = fileX.get(i);
                continue;
            }
        }

        for(int i=0; i<fileY.size(); i++) {
            if(fileY.get(i) > arrSize) {
                arrSize = fileY.get(i);
                continue;
            }
        }


        int[][] arr = new int[arrSize+1][arrSize+1];
        for(int i=0; i<arrSize+1; i++) {
            for(int j=0; j<arrSize+1; j++) {
                arr[i][j] = 0;
            }
        }

        for(int i=0; i<fileX.size(); i+=2) {

            int X = 0;
            int X2 = 0;

            if(fileX.get(i) > fileX.get(i+1)) {
                X = fileX.get(i+1);
                X2 = fileX.get(i);
            } else if (fileX.get(i) <= fileX.get(i+1)) {
                X = fileX.get(i);
                X2 = fileX.get(i+1);
            }

            int Y = 0;
            int Y2 = 0;
            if(fileY.get(i) > fileY.get(i+1)) {
                Y = fileY.get(i+1);
                Y2 = fileY.get(i);
            } else if (fileY.get(i) <= fileY.get(i+1)) {
                Y = fileY.get(i);
                Y2 = fileY.get(i+1);
            }


            if(X == X2) {
                for(int j=Y; j<=Y2; j++) {
                    arr[j][X] = arr[j][X] + 1;
                }
            } else if (Y == Y2) {
                for(int j=X; j<=X2; j++) {
                    arr[Y][j] = arr[Y][j] + 1;
                }
            } else {
                for(int j=0; j<=(X2-X); j++) {
                    if(fileX.get(i) == X && fileY.get(i) == Y) {
                        arr[Y + j][X + j] = arr[Y + j][X + j] + 1;
                    } else if (fileX.get(i) == X2 && fileY.get(i) == Y) {
                        arr[Y + j][X2 - j] = arr[Y + j][X2 - j] + 1;
                    } else if (fileX.get(i) == X && fileY.get(i) == Y2) {
                        arr[Y2 - j][X + j] = arr[Y2 - j][X + j] + 1;
                    } else if (fileX.get(i) == X2 && fileY.get(i) == Y2) {
                        arr[Y2 - j][X2 - j] = arr[Y2 - j][X2 - j] + 1;
                    }
                }
            }
        }

        int sum = 0;
        for(int i=0; i<arrSize+1; i++) {
            for(int j=0; j<arrSize+1; j++) {
                //System.out.print(arr[i][j] + "\t");
                if (arr[i][j] >= 2) {
                    sum++;
                }
            }
            //System.out.println();
        }
        System.out.println(sum);
    }
}

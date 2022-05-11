package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    //Day9_1();
        Day9_2();
    }

    public static void Day9_1() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay9(2021)/src/Vents.txt"));

        int col = 0;
        int row = 0;
        while(f.hasNext()) {
            String input = f.nextLine();
            col = input.length();
            row++;
        }

        int[][] vents = new int[row][col];

        f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay9(2021)/src/Vents.txt"));
        for(int i=0; i<row; i++) {
            String input = f.nextLine();
            Scanner splitString = new Scanner(input);
            splitString.useDelimiter("");
            for(int j=0; j<col; j++) {
                vents[i][j] = splitString.nextInt();
            }
        }

        ArrayList<Integer> lowest = new ArrayList<>();

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if (j == 0 && i == 0) {
                    if(vents[i][j] < vents[i+1][j] && vents[i][j] < vents[i][j+1]) {
                        lowest.add(vents[i][j]);
                    }
                } else if (j == col-1 && i == row-1) {
                    if(vents[i][j] < vents[i-1][j] && vents[i][j] < vents[i][j-1]) {
                        lowest.add(vents[i][j]);
                    }
                } else if (i == 0 && j == col-1){
                    if(vents[i][j] < vents[i+1][j] && vents[i][j] < vents[i][j-1]) {
                        lowest.add(vents[i][j]);
                    }
                } else if (i == 0) {
                    if(vents[i][j] < vents[i+1][j] && vents[i][j] < vents[i][j+1] && vents[i][j] < vents[i][j-1]) {
                        lowest.add(vents[i][j]);
                    }
                } else if (j == 0 && i == row-1) {
                    if(vents[i][j] < vents[i-1][j] && vents[i][j] < vents[i][j+1]) {
                        lowest.add(vents[i][j]);
                    }
                }else if (j == 0) {
                    if(vents[i][j] < vents[i+1][j] && vents[i][j] < vents[i-1][j] && vents[i][j] < vents[i][j+1]) {
                        lowest.add(vents[i][j]);
                    }
                } else if(j == col-1) {
                    if(vents[i][j] < vents[i+1][j] && vents[i][j] < vents[i-1][j] && vents[i][j] < vents[i][j-1]) {
                        lowest.add(vents[i][j]);
                    }
                } else if (i == row-1) {
                    if(vents[i][j] < vents[i-1][j] && vents[i][j] < vents[i][j+1] && vents[i][j] < vents[i][j-1]) {
                        lowest.add(vents[i][j]);
                    }
                } else {
                    if(vents[i][j] < vents[i+1][j] && vents[i][j] < vents[i-1][j] && vents[i][j] < vents[i][j+1] && vents[i][j] < vents[i][j-1]) {
                        lowest.add(vents[i][j]);
                    }
                }
            }
        }

        int sum = 0;

        for(int i : lowest) {
            sum += i+1;
        }

        System.out.println(sum);
    }

    public static void Day9_2() throws FileNotFoundException {
        Scanner f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay9(2021)/src/TestVents.txt"));

        int col = 0;
        int row = 0;
        while(f.hasNext()) {
            String input = f.nextLine();
            col = input.length();
            row++;
        }

        int[][] vents = new int[row][col];

        f = new Scanner(new File("C:/Users/regin/IdeaProjects/AdventOfCodeDay9(2021)/src/TestVents.txt"));

        for(int i=0; i<row; i++) {
            String input = f.nextLine();
            Scanner splitString = new Scanner(input);
            splitString.useDelimiter("");
            for(int j=0; j<col; j++) {
                vents[i][j] = splitString.nextInt();
            }
        }

        ArrayList<Integer> basins = new ArrayList<>();

        for(int i=0; i<row; i++) {
            for(int j = 0; j<col; j++) {
                if(vents[i][j] == 9) {
                    continue;
                } else if (vents[i][j] != 9){
                    if (i == 0) {
                        basins.add(1);
                    } else if(i != 0 && vents[i-1][j] != 9) {
                        basins.set(basins.size()-1, basins.get(basins.size()-1)+1);
                    } else if (i != 0 && vents[i-1][j] == 9) {
                        continue;
                    } else if (i != row-1 && vents[i-1][j] != 9) {
                        basins.set(basins.size(), basins.get(basins.size())+1);
                    } else if (i != row-1 && vents[i-1][j] == 9) {
                        continue;
                    } else {
                        basins.add(1);
                    }
                }
            }
        }

        int sum = 0;

        Collections.sort(basins);
        sum = basins.get(0);

        for(int i=0; i<2; i++) {
            sum *= basins.get(i);
        }

        System.out.println(sum);
    }
}

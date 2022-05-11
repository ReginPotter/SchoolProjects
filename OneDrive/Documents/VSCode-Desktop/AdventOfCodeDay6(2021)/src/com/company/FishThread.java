package com.company;

import java.util.ArrayList;

public class FishThread extends Thread {

    private int start = 0;
    private int end = 0;
    private int sum = 0;
    private ArrayList<Integer> fishs = new ArrayList<>();

    public FishThread(int start, int end, int sum, ArrayList<Integer> fishs) {
        this.start = start;
        this.end = end;
        this.sum = sum;
        this.fishs = (ArrayList<Integer>) fishs.clone();
        /*
        for(int i=0; i<fishs.size(); i++) {
            this.fishs.add(fishs.get(i));
        }

         */
    }

    public void run() {

        int newFish = 0;
        for (int i = start; i<end; i++) {
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
    }

    public int getSum() {
        return sum;
    }
}

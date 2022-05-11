package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class BruteForce {

    /*
    This part I am not sure if it works, it sends back a Chromosome but in my testing it never sent back anything of value
    My last test of this it broke because of not enough heap space but when I pasted it in here just so you knew I tried
    it worked. I dont think it still works because it doesnt loop through all of the items but I cannot check with a smaller set if it is working
    so this is what I have if I were to change anything I would put "masterItems.add(help);" under the getOptimalSet call
     */

    // this creates the needed variables
    private static Chromosome help;
    private static int count = 0;
    private static ArrayList<ArrayList<Chromosome>> subSets = new ArrayList<>();
    private static ArrayList<Chromosome> copyItems = new ArrayList<>();

    // this gets the optimal chromosome
    public static ArrayList<Chromosome> getOptimalSet(ArrayList<Chromosome> masterItems) {

        // if the arraylist of master items has too many items it stops
        if(masterItems.size() > 10) {
            throw new IllegalArgumentException("You must have an array of less than 10 items");
        } else {
            // if the masteritems size is less than or equal to 1 it adds master items to the subsets and returns the best item in subsets
            if (masterItems.size() <= 1) {
                addSubSets(masterItems);
                subSets.sort(new ChromosomeComparator());
                return subSets.get(0);
            }

            // this adds the masterItems arraylist to subsets
            addSubSets(masterItems);

            // this loops through each item in the arraylist of items
            for (int i = 0; i < masterItems.size(); i++) {

                // this makes copyitems equal to master items
                createCopyItems(masterItems);

                // this gets the optimal set of a smaller arraylist
                getOptimalSet(subArrayList(i, copyItems));
            }

            // this sorts all the items in the subsets arraylist and returns the best possible subset
            subSets.sort(new ChromosomeComparator());
            Collections.sort(subSets.get(0));
            return subSets.get(0);
        }
    }

    // this creates a smaller arraylist by removing a specific number and setting help to that number
    public static ArrayList<Chromosome> subArrayList(int index, ArrayList<Chromosome> items) {
        setHelp(items.remove(index));
        return items;
    }

    // this clears the copyitems arraylist
    public static void clearCopyItems() {
        for(int i=0; i<copyItems.size(); i++) {
            copyItems.remove(0);
        }
    }

    // this makes the copyitems arraylist equal to the arraylist fed into the method
    public static void createCopyItems(ArrayList<Chromosome> items) {
        clearCopyItems();
        copyItems.addAll(items);
    }

    // this sets the help variable to something
    public static void setHelp(Chromosome help1) {
        help = help1;
    }

    // this adds the arraylist fed into the method into the subsets arraylist
    public static void addSubSets(ArrayList<Chromosome> items) {
        subSets.add(items);
    }
}

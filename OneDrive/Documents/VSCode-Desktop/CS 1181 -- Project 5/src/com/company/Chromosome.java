package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    public static long dummy = 0;

    // this creates a chromosome with no arguments
    public Chromosome() {

    }

    // this creates a chromosome based off an array list of items
    public Chromosome(ArrayList<Item> items) {
        Random rng = new Random();

        // this loops through the items in the array list
        for(Item i: items) {

            // this copies the items
            Item newItem = new Item(i);

            // this creates a random number for the true/false value
            int include = rng.nextInt(10);

            // if the random number is greater than or equal to 5 the included value will be true
            if(include > 5) {
                newItem.setIncluded(true);
            }

            // if the random number is less than 5 the included value will be false
            else {
                newItem.setIncluded(false);
            }

            // this adds the item to the chromosome
            this.add(newItem);
        }
    }

    // this creates a new chromosome based off of two other chromosomes
    public Chromosome crossover(Chromosome other) {
        Random rng = new Random();

        // this creates a new item arraylist for the child
        ArrayList<Item> kid = new ArrayList<>();

        // this loops through all items in this chromosome's arraylist
        for(int i=0; i<this.size(); i++) {

            // this creates a random number for the included value
            int cross = rng.nextInt(10);

            // if the random number is greater than or equal to 5 the new item will be the second chromosome's item
            if (cross > 5) {
                Item newItem = new Item(other.get(i));
                kid.add(newItem);
            }

            // if the random number is less than 5 the new item will be the first chromosome's item
            else {
                Item newItem = new Item(this.get(i));
                kid.add(newItem);
            }
        }

        // this creates a new chromosome for the child
        Chromosome child = new Chromosome(kid);

        // this returns the chromosome
        return child;
    }

    // this compares two chromosome's fitness values
    public int compareTo (Chromosome other) {

        // if the first chromosome's fitness value is greater than the second one it returns -1
        if(this.getFitness() > other.getFitness()) {
            return -1;
        }

        // if they are equal it returns 0
        else if (this.getFitness() < other.getFitness()) {
            return 1;
        }

        // if the second chromosome's fitness value is greater than the first chromosome's fitness value it returns 1
        else {
            return 0;
        }
    }

    // this checks to see if there are any mutations in a chromosome
    public void mutate() {
        Random rng = new Random();

        // this loops through all items in the arraylist
        for (Item item : this) {

            // this creates a random number for the mutation
            int mutate = rng.nextInt(10);

            // if the random number is 1 the mutation occurs
            if (mutate == 1) {
                item.setIncluded(!item.isIncluded());
            }
        }
    }

    // this creates the fitness value
    public int getFitness() {

        dummy = 0;
        for (int i=0; i<this.size()*1000; i++) {
            dummy += i;
        }

        // this initializes all variables needed
        double fitness = 0;
        int totalValue = 0;

        // this loops through all items in the arraylist
        for(int i=0; i<this.size(); i++) {

            // this checks to see if the item's included value is true
            if(this.get(i).isIncluded()) {

                // if the item's included value is true it adds the weight to the fitness variable and the value to the total value variable
                fitness += this.get(i).getWeight();
                totalValue += this.get(i).getValue();
            }
        }

        // once it has looped through all items if the fitness variable is over 10 it sets the total value variable to 0
        if(fitness > 10) {
            totalValue = 0;
        }

        // this returns the total value variable
        return totalValue;
    }

    // this prints out the chromosome
    public String toString() {

        // this initializes the variables needed
        String s = "";
        int totalCost = 0;

        // this loops through all items in the arraylist
        for(int i=0; i<this.size(); i++){

            // this checks if the included value of the item is true
            if (this.get(i).isIncluded()) {

                // if the item's included value is true it adds the item to the string s
                s += this.get(i) + " ";

                // if the fitness of the chromosome isnt 0 it adds the value to the total cost variable
                if(this.getFitness() != 0) {
                    totalCost += this.get(i).getValue();
                }
            }
        }

        // this adds an arrow and the fitness to the s variable
        s += "-> " + this.getFitness();

        // this returns the s variable
        return s;
    }
}

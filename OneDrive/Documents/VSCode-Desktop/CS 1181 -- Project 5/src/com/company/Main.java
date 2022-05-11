package com.company;

import java.util.*;
import java.io.*;

class Main {

    // this creates all needed variables
    public static final int POP_SIZE = 100;
    public static final int NUM_EPOCHS = 1000;
    public static final int NUM_THREADS = 4;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        // this "starts" the timer
        long startTime = System.currentTimeMillis();

        /*
        NOTE: My multithreading wasn't working and I couldn't figure out why but it was still taking the time so I used that time

        1 Thread: 1167ms
        2 Threads: 633ms
        3 Threads: 459ms
        4 Threads: 380ms
         */

        // this initializes everything needed in main
        ArrayList<Item> items = new ArrayList<>();
        String fileName = "items.txt";

        // this reads in the data from the file and initializes the population and all other needed variables
        items = readData(fileName);
        ArrayList<Chromosome> population = initializePopulation(items);
        ArrayList<GeneticThread> threads = new ArrayList<>();
        ArrayList<Chromosome> bestPop = new ArrayList<>();
        int numOfGenerations = NUM_EPOCHS / NUM_THREADS;
        int bestChromosomes = POP_SIZE / NUM_THREADS;

        // this creates all the threads wanted and puts them into the threads arraylist
        for(int i=0; i<NUM_THREADS; i++) {
            GeneticThread t = new GeneticThread(population, numOfGenerations, bestChromosomes);
            threads.add(t);
        }

        // this starts all the threads
        for(Thread t: threads) {
            t.start();
        }

        // this waits for all the threads to join back in
        for(Thread t: threads) {
            t.join();
        }

        // this adds all of the best from each thread to an arraylist
        for(Thread t: threads) {
            for(int i=0; i<(((GeneticThread) t).getBestPop().size()); i++) {
                bestPop.add(((GeneticThread) t).getBestPop().get(i));
            }
        }

        // this prints out the best of each thread
        try {
            for (int i = 0; i < threads.size(); i++) {
                GeneticThread t = threads.get(i);
                System.out.println("Thread " + i + "'s best possible outcome was " + t.getBestPop().get(0));
            }
        } catch (Exception e) {
            System.out.println("oopsies");
        }

        // this sorts the best of all the threads and prints out the absolute best of the threads
        Collections.sort(bestPop);
        System.out.println("The best outcome for all threads was " + bestPop.get(0));

        // this prints out the best chromosome from the BruteForce optimal set
        ArrayList<Chromosome> bestChrom = BruteForce.getOptimalSet(population);
        System.out.println("The best possible outcome for brute force was " + bestChrom.get(0));

        // this prints out the best result from the genetic algorithm
        Chromosome best = runGeneticAlgorithm(population);
        System.out.println("The best possible outcome for running the genetic algorithm was " + best);

        // this "stops" the timer and calculates the time taken
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime + "ms = time taken");
    }

    // this reads in the data from the file
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        Scanner file = new Scanner(new File(filename));

        // this creates the array list for the items
        ArrayList<Item> items = new ArrayList<>();

        // this loops until the file has no more items in it
        while (file.hasNext()) {

            // this reads in the first item and stores it in the string line
            String line = file.nextLine();

            // this initializes the delimiter to read in the correct items without the extra characters
            Scanner splitString = new Scanner(line);
            splitString.useDelimiter(", ");

            // this creates the new item and adds it to the array list
            items.add(new Item(splitString.next(), splitString.nextDouble(), splitString.nextInt()));
        }

        // this returns the arraylist of items
        return items;
    }

    // this initializes the population
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items) {


        // this creates an array list of chromosomes for the population
        ArrayList<Chromosome> pop = new ArrayList<>();

        // this loops through the population size so it creates the correct amount of chromosomes
        for (int i = 0; i < POP_SIZE; i++) {

            // this creates the new chromosome and adds it to the population
            Chromosome person = new Chromosome(items);
            pop.add(person);
        }

        // this returns the population
        return pop;
    }

    public static Chromosome runGeneticAlgorithm(ArrayList<Chromosome> nextGen) {
        int populationSize = 10;
        int numOfGenerations = NUM_EPOCHS / NUM_THREADS;

        ArrayList<Chromosome> finalGen = new ArrayList<>();

        for (int i = 0; i < numOfGenerations; i++) {
            int nextGenSize = nextGen.size();

            // this initializes the next generation
            //ArrayList<Chromosome> nextGen = (ArrayList<Chromosome>) population.clone();
            //System.out.println(nextGen.size() + " = nextGen size");

            // this mates 2 random parents and creates a new child 5 times
            Collections.shuffle(nextGen);
            for (int j = 0; j < nextGenSize-1; j+=2) {
                // this initializes parent1 and parent2 and creates the child
                Chromosome parent1 = new Chromosome(nextGen.get(j));
                Chromosome parent2 = new Chromosome(nextGen.get(j + 1));
                Chromosome child = parent1.crossover(parent2);

                // this adds the child into the next generation
                nextGen.add(child);
            }

            // this mutates two of the next generation
            Collections.shuffle(nextGen);
            for (int j = 0; j < Math.round(nextGen.size()/10.0); j++) {
                // this mutates the first of the next generation
                Chromosome mutated = nextGen.get(j);
                mutated.mutate();
            }

            // this sorts the generation
            Collections.sort(nextGen);

            // this clears the population
            for (int j = 0; j < 10; j++) {
                finalGen.add(nextGen.get(j));
            }

            for (int j = 0; j < nextGen.size(); j++) {
                nextGen.remove(0);
                //System.out.println(nextGen.size());
            }

            nextGen = finalGen;

            Collections.sort(finalGen);
        }
        return finalGen.get(0);
    }
}

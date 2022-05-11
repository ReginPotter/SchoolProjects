package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class GeneticThread extends Thread {

    // this initalizes needed variables
    private final int numOfGenerations;
    private final int bestChromosomes;
    private ArrayList<Chromosome> nextGen;
    private ArrayList<Chromosome> bestPop = new ArrayList<>();

    // this is the constructor
    public GeneticThread (ArrayList<Chromosome> population, int numOfGenerations, int bestChromosomes) {
        this.nextGen = population;
        this.numOfGenerations = numOfGenerations;
        this.bestChromosomes = bestChromosomes;
    }

    // this runs the thread
    public void run() {
        /*boolean running = true;
        while(running) {
            try {*/

                // this creates the final gen arraylist
                ArrayList<Chromosome> finalGen = new ArrayList<>();

                // this runs for every generation
                for (int i = 0; i < numOfGenerations; i++) {

                    // at the beginning of each generation it clears the final gen arraylist and sets the nextgensize to the size of nextgen
                    finalGen.clear();
                    int nextGenSize = nextGen.size();

                    // this shuffles nextgen and pairs up the parents
                    Collections.shuffle(nextGen);
                    for (int j = 0; j < nextGenSize - 1; j += 2) {

                        // this initializes parent1 and parent2 and creates the child
                        Chromosome parent1 = new Chromosome(nextGen.get(j));
                        Chromosome parent2 = new Chromosome(nextGen.get(j + 1));
                        Chromosome child = parent1.crossover(parent2);

                        // this adds the child into the next generation
                        nextGen.add(child);
                    }

                    // this shuffles the next generation and mutates 10% of the population
                    Collections.shuffle(nextGen);
                    for (int j = 0; j < Math.round(nextGen.size() / 10.0); j++) {

                        // this mutates the first of the next generation
                        Chromosome mutated = nextGen.get(j);
                        mutated.mutate();
                    }

                    // this sorts the generation
                    Collections.sort(nextGen);

                    // this is created to combat the concurrentmodificationexception
                    Chromosome[] chrom = nextGen.toArray(new Chromosome[nextGen.size()]);

                    // this adds the top 10 chromosomes to the final generation
                    for (int j = 0; j < 10; j++) {
                        finalGen.add(chrom[j]);
                    }

                    // this clears out the next generation arraylist
                    for (int j = 0; j < nextGenSize * 1.5; j++) {
                        nextGen.remove(0);
                    }

                    // this adds the top 10 of the generation back into the next generation
                    for (int j = 0; j < finalGen.size(); j++) {
                        nextGen.add(finalGen.get(j));
                    }

                    // this sorts the final generation and adds the best member of the generation into the best population generation
                    Collections.sort(finalGen);
                    bestPop.add(finalGen.get(0));
                }

                // this sorts the best population and makes final generation be a clone of the best population
                Collections.sort(bestPop);
                finalGen = (ArrayList<Chromosome>) bestPop.clone();

                // if the best population is greater than 0 it clears it
                if (bestPop.size() > 0) {
                    bestPop.subList(0, bestPop.size()).clear();
                }

                // this adds the number of best chromosomes wanted back to the best population arraylist
                for (int i = 0; i < bestChromosomes; i++) {
                    bestPop.add(finalGen.get(i));
                }
                /*running = false;
            } catch (Exception e) {
                System.out.println(e);
            }
        }*/
    }

    // this returns best population
    public ArrayList<Chromosome> getBestPop() {
        return bestPop;
    }
}

package com.company;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

// this compares two ArrayLists of Chromosomes based off of their median grade
public class ChromosomeComparator implements Comparator<ArrayList<Chromosome>> {
    public int compare(ArrayList<Chromosome> s1, ArrayList<Chromosome> s2) {
        Collections.sort(s1);
        Collections.sort(s2);
        return s1.get(0).compareTo(s2.get(0));
    }
}

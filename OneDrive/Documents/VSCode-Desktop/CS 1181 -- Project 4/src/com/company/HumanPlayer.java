package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class HumanPlayer extends Player{

    // this creates the human player
    public HumanPlayer(ArrayList<Card> deck, Stack<Card> discard) {
        super(deck, discard);
    }
}

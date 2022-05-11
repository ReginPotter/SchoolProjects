package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class Player {

    // this creates all needed variables for the Player class
    ArrayList<Card> hand = new ArrayList<>();
    ArrayList<Card> deck;
    Stack<Card> discard;

    // this is the constructor for the player
    public Player(ArrayList<Card> deck, Stack<Card> discard) {
        this.deck = deck;
        this.discard = discard;
    }

    // this adds a card to the player's hand
    public void add(Card c) {
        hand.add(c);
    }

    // this checks if the game is over from a certain player
    public boolean gameOver() {

        // if all of the cards have the same value it returns true
        if(hand.get(0).getValue().equals(hand.get(1).getValue())) {
            if(hand.get(1).getValue().equals(hand.get(2).getValue())) {
                if(hand.get(2).getValue().equals(hand.get(3).getValue())) {
                    return true;
                }
            }
        }

        // if at least one card has a different value it returns false;
        return false;
    }

    // this draws a card for the player
    public Card drawCard() {

        // this takes a card off of the top of the deck and adds it to the hand and removes the first card of the deck
        hand.add(deck.get(0));
        return deck.remove(0);
    }

    // this discards a specific card for the player
    public void discard(int discarded) {
        discard.add(hand.get((discarded-1)));
        hand.remove((discarded-1));
    }

    // this prints out all valuable information with card numbers
    public String printCards() {
        String s = "";
        int cardNum = 1;

        // this loops through all cards in the hand
        for(Card c: hand) {

            // if the card is the last card in the hand it adds the card with the card number without the enter to the string
            if (c == hand.get(hand.size()-1)) {
                s += cardNum + ": " + c;
            }

            // if the card isnt the last card in the hand it adds the card with the card number with the enter to the string
            else {
                s += cardNum + ": " + c + "\n";
            }
            cardNum++;
        }
        return s;
    }

    // this prints out all valuable info to the Console
    public String toString() {
        String s = "";

        // this loops through all cards in the hand
        for(Card c: hand) {

            // if the card is the last one in the hand it adds the card without an enter to the string
            if (c == hand.get(hand.size()-1)) {
                s += c;
            }

            // if the card isnt the last one in the hand it adds the card with an enter to the string
            else {
                s += c + "\n";
            }
        }
        return s;
    }
}

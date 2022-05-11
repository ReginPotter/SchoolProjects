package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class AIPlayer extends Player{

    // this creates the AI player
    public AIPlayer(ArrayList<Card> deck, Stack<Card> discard) {
        super(deck, discard);
    }

    // This checks what value the AI player is trying to get
    public String checkValues () {

        // this sets the maxvalue to the value of the first card in the hand and the secondary value to the value of the second card in the hand
        String maxValue = hand.get(0).getValue();
        String posValue = hand.get(1).getValue();

        // if the two values of the first two cards are equal it makes the secondary value equal to the value of the third card in the hand
        if(posValue.equals(maxValue)) {
            posValue = hand.get(2).getValue();

            // if the three values of the first three cards are equal it makes the secondary value equal to the value of the fourth card in the hand
            if(posValue.equals(maxValue)) {
                posValue = hand.get(3).getValue();

                // if the four values of all of the cards in the hand are equal it returns the first value
                if(posValue.equals(maxValue)) {
                    return maxValue;
                }
            }
        }

        // this initializes all needed values
        int totalMaxValue = 1;
        int posMaxValue = 0;
        int endMaxValue = 0;

        // this loops through all of the cards in the hand
        for(Card c: hand) {

            // if the card is the first card it goes back to the beginning of the loop
            if (c == hand.get(0)) {
                continue;
            }

            // if the card isnt the first card it checks the value
            else {

                // if the value is equal to the max value it increases the total max value integer
                if(c.getValue().equals(maxValue)){
                    totalMaxValue++;
                }

                // if the value equals the secondary value it increases the total secondary value integer
                else if (c.getValue().equals(posValue)) {
                    posMaxValue++;
                }

                // if it is something completely different it increases the end max value integer
                else {
                    endMaxValue++;
                }
            }

            // if the secondary max value integer is greater than the total max value integer it sets the max value equal to the secondary value
            if (posMaxValue > totalMaxValue) {
                maxValue = posValue;
            }
        }

        // this returns the value that the AI is trying to get
        return maxValue;
    }

    // this discards a card in the AI's hand
    public Card discard() {

        // if the value of the first card isn't what the AI wants it discards it and adds the card to the discard pile
        if(!hand.get(0).getValue().equals(this.checkValues())) {
            discard.add(hand.get(0));
            return hand.remove(0);
        }

        // if the value of the second card isn't what the AI wants it discards it and adds the card to the discard pile
        else if (!hand.get(1).getValue().equals(this.checkValues())) {
            discard.add(hand.get(1));
            return hand.remove(1);
        }

        // if the value of the third card isn't what the AI wants it discards it and adds the card to the discard pile
        else if (!hand.get(2).getValue().equals(this.checkValues())) {
            discard.add(hand.get(2));
            return hand.remove(2);
        }

        // if the value of the fourth card isn't what the AI wants it discards it and adds the card to the discard pile
        else if (!hand.get(3).getValue().equals(this.checkValues())) {
            discard.add(hand.get(3));
            return hand.remove(3);
        }

        // if none of these are valid it discards the card it just drew
        else {
            discard.add(hand.get(4));
            return hand.remove(4);
        }
    }
}

package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // this creates the discard pile and the deck
        ArrayList<Card> deck = makeDeck();
        Stack<Card> discard = new Stack<>();
        Queue<Player> order = new LinkedList<>();
        boolean gameOver = false;

        // this creates the players (two pla
        HumanPlayer p = new HumanPlayer(deck, discard);
        AIPlayer p2 = new AIPlayer(deck, discard);
//        AIPlayer p3 = new AIPlayer(deck, discard);
//        AIPlayer p4 = new AIPlayer(deck, discard);

        // this deals everyone cards
        dealCards(p);
        dealCards(p2);
//        dealCards(p3);
//        dealCards(p4);

        // this adds the players to the order
        order.offer(p);
        order.offer(p2);
//        order.offer(p3);
//        order.offer(p4);

        // this loops through all players in the order queue
        for(int i=0; i<order.size(); i++) {
            Player playing = order.poll();

            // this checks if the game is over
            if(playing.gameOver()) {

                // if the game is over it sets the gameOver boolean to true and prints out the correct information
                gameOver = true;
                if(playing instanceof HumanPlayer) {
                    System.out.println("You win!");
                } else if (playing instanceof AIPlayer) {
                    System.out.println("I win!");
                }
                break;
            }

            // this offers back the player to the order queue
            order.offer(playing);
        }


         // this loops until the game is over
         while (!gameOver) {

             // this pulls the player that is up out of the queue
             Player playing = order.poll();

             // this checks if the player is human
             if (playing instanceof HumanPlayer) {

                 // this prints out the human player's cards
                 System.out.println("Your cards are: ");
                 System.out.println(playing);

                 // if there are no cards in the discard pile it prints out this
                 if(discard.size() == 0) {
                     System.out.println("The discard pile is currently empty -- you must draw a card");

                     // this draws the top card of the deck and tells the player what card they drew
                     Card drewCard = playing.drawCard();
                     System.out.println("You drew the " + drewCard);

                     // this tells the human player what their cards are now
                     System.out.println("Now your cards are: ");
                     System.out.println(playing.printCards());

                     // this asks the human player what number is the card they want to discard
                     System.out.print("What card do you want to discard? ");
                     int discarded = in.nextInt();

                     // this discards the intended card
                     playing.discard(discarded);
                 }

                 // if there are things on top of the discard pile it prints out what is on top and asks if the player wants to pick that up or draw a card
                 else {
                     System.out.println("The top of the discard pile is the " + discard.peek());
                     System.out.print("Do you want to pick up the " + discard.peek() + " (1) or draw a card (2)? ");
                     int input = in.nextInt();

                     // this goes into a switch statement based off of the player's choices
                     switch (input) {

                         // if the player wants to pick up the card off of the top of the discard pile it tells the player what they drew and adds it to the hand
                         case 1: Card drewCard = discard.pop();
                                System.out.println("You drew the " + drewCard);
                                playing.add(drewCard);

                                // this prints out the player's current hand
                                System.out.println("Now your cards are: ");
                                System.out.println(playing.printCards());

                                // this asks the player what card they want to discard
                                System.out.print("What card do you want to discard? ");
                                int discarded = in.nextInt();

                                // this discards the intended card
                                playing.discard(discarded);
                                break;

                         // if the player wants to draw from the hand it draws and tells the player what card was drawn
                         case 2: drewCard = playing.drawCard();
                                System.out.println("You drew the " + drewCard);

                                // this prints out the cards  the player has
                                System.out.println("Now your cards are: ");
                                System.out.println(playing.printCards());

                                // this asks the human player what number is the card they want to discard
                                System.out.print("What card do you want to discard? ");
                                discarded = in.nextInt();

                                // this discards the intended card
                                playing.discard(discarded);
                                break;
                     }
                 }

                 // this offers the player back into the order queue
                 order.offer(playing);
             }

             // if the player isn't human it does this
             else {

                 // this checks if the card on the top of the discard pile is the card the AI wants or if there are no more cards in the deck and draws from the discard pile
                 if (discard.peek().getValue().equals(((AIPlayer) playing).checkValues()) || deck.size() == 0) {
                     System.out.println("I will draw a new card.");
                     playing.add(discard.pop());

                     // this tells the players what card the AI is discarding and offers the AI player back into the order queue
                     System.out.println("I will discard " + ((AIPlayer) playing).discard());
                     order.offer(playing);
                 }

                 // if the discard pile card isn't what the AI wants it draws a card from the deck
                 else {
                     System.out.println("I will draw a new card.");
                     playing.drawCard();

                     // this tells all player what the AI player will discard and offers the AI player back into the order queue
                     System.out.println("I will discard " + ((AIPlayer) playing).discard());
                     order.offer(playing);
                 }
             }

             // once the player's turn is over (no matter if they are AI or a human player) it checks to see if the game can end
             if(playing.gameOver()) {

                 // if the game can end and the player wins it prints out the correct statement and changes the gameOver boolean to true
                 gameOver = true;
                 if(playing instanceof HumanPlayer) {
                     System.out.println("You win!");
                 } else if (playing instanceof AIPlayer) {
                     System.out.println("I win!");
                 }
             }
         }
    }

    // this makes the deck
    public static ArrayList<Card> makeDeck () {
        ArrayList<Card> deck = new ArrayList<>();

        // this loops through how many suits there are
        for(int i=0; i<4; i++) {

            // this loops through how many cards in each suit there are
            for(int j=0; j<13; j++) {

                // this creates a new card
                Card test = new Card("" + (j+1), "Hearts");

                // if it is the second of the suits loop it makes the suit Diamonds
                if (i == 1) {
                    test.setSuit("Diamonds");
                }

                // if it is the third of the suits loop it makes the suit Clubs
                else if (i == 2) {
                    test.setSuit("Clubs");
                }

                // if it is the fourth of the suits loop it makes the suit Spades
                else if (i == 3) {
                    test.setSuit("Spades");
                }

                // if the number of the value loop is 0 it sets the value to Ace
                if (j == 0) {
                    test.setValue("Ace");
                }

                // if the number of the value loop is 10 it sets the value to Jack
                else if(j == 10) {
                    test.setValue("Jack");
                }

                // if the number of the value loop is 11 it sets the value to Queen
                else if (j == 11) {
                    test.setValue("Queen");
                }

                // if the number of the value loop is 12 it sets the value to King
                else if(j == 12) {
                    test.setValue("King");
                }
                deck.add(test);
            }
        }

        // this shuffles the deck and returns it
        Collections.shuffle(deck);
        return deck;
    }

    // this deals 4 starting cards to a player
    public static void dealCards(Player p) {
        for(int i=0; i<4; i++) {
            p.drawCard();
        }
    }
}

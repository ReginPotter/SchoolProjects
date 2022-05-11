package com.company;
import java.util.Random;

public class Room {

    // this initializes everything needed for a room
    private String name;
    private int exits;
    private boolean isEnemyHere;
    private boolean isSafe;
    private boolean isGen;

    // this creates a room with no args
    public Room() {

    }

    // this creates a room with everything already done
    public Room(String name, int exits, boolean isEnemyHere, boolean isSafe, boolean isGen) {
        this.name = name;
        this.exits = exits;
        this.isEnemyHere = isEnemyHere;
        this.isSafe = isSafe;
        this.isGen = isGen;
    }

    // this returns the name
    public String getName() {
        return name;
    }

    // this returns the exits
    public int getExits() {

        return exits;
    }

    // this returns if the enemy is here
    public boolean getIsEnemyHere() {
        return isEnemyHere;
    }

    // this returns if the room is safe
    public boolean getIsSafe() {
        return isSafe;
    }

    // this returns if the room is a generator
    public boolean getIsGen() {
        return isGen;
    }

    // this sets the name
    public void setName(String name) {
        this.name = name;
    }

    // this sets the number of exits
    public void setExits(int exits) {
        this.exits = exits;
    }

    // this randomly sets if the enemy is here
    public void setIsEnemyHere() {
        Random rng = new Random();

        if(rng.nextDouble() < .2) {
            isEnemyHere = true;
        } else {
            isEnemyHere = false;
        }
    }

    // this sets if the room is safe
    public void setIsSafe(boolean isSafe) {
        this.isSafe = isSafe;
    }

    // this randomly sets if the room is a generator
    public void setIsGen() {
        Random rng = new Random();

        if(rng.nextDouble() < .2) {
            isGen = true;
        } else {
            isGen = false;
        }
    }

    // this prints out all relevant info about the room
    public String toString() {
        String s = name;

        s += ": there are " + exits + " exits, ";

        s += "the enemy ";

        if(isEnemyHere) {
            s += "is here, ";
        } else {
            s += " isn't here, ";
        }

        s += "and the room ";

        if(isSafe) {
            s += "is safe.";
        } else {
            s += "isn't safe.";
        }

        return s;
    }
}

package com.company;

import java.util.Random;

public class Game {

    // this sets the variables needed for the class
    boolean gen1Start = false;
    boolean gen2Start = false;
    boolean gen3Start = false;
    boolean gen4Start = false;
    int generators = 1;

    // this creates the game
    public Game() {

    }

    // this creates a random room
    public Room createRoom() {
        Random rng = new Random();
        Room room = new Room();
        int name = 0;
        int exits = 0;

        // this sets if the room is a generator
        room.setIsGen();

        // if the room is a generator this happens
        if(room.getIsGen()) {
            room.setName("Generator Room #" + generators);
            generators++;
            return room;
        }

        // if the room isnt a generator this happens
        else {

            // this loops until exits isnt 0 and sets the number of exits
            do {
                exits = rng.nextInt(5);
            } while (exits == 0);
            room.setExits(exits);

            // this creates a random number that isnt less than 99 for the room
            do {
                name = rng.nextInt(999);
            } while (name < 99);

            // this uniquely gives the room a name
            if (room.getExits() == 4) {
                room.setName("Four-Way Hallway #" + name);
            } else if (room.getExits() == 3) {
                room.setName("T Shaped Hallway #" + name);
            } else if (room.getExits() == 2) {
                room.setName("Straight Hallway #" + name);
            } else {
                room.setName("Dead End #" + name);
            }

            // this sets if the enemy is there
            room.setIsEnemyHere();
        }

        // this returns it to the class that called it
        return room;
    }

    // this makes the room work or not
    public boolean roomWorks(Room room) {

        // if the room is the first generator this happens
        if(room.getName().equalsIgnoreCase("Generator Room #1")) {

            // if it has been started it returns false
            if(gen1Start) {
                return false;
            }

            // otherwise it returns true and sets it to true
            else {
                gen1Start = true;
                return true;

            }
        }

        // if the room is the second generator this happens
        else if(room.getName().equalsIgnoreCase("Generator Room #2")) {

            // if it has been started it returns false
            if(gen2Start) {
                return false;
            }

            // otherwise it returns true and sets the value to true
            else {
                gen2Start = true;
                return true;
            }
        }

        // if the room is the third generator this happens
        else if(room.getName().equalsIgnoreCase("Generator Room #3")) {

            // if it has been started it returns false
            if(gen3Start) {
                return false;
            }

            // otherwise it returns true and sets the value to true
            else {
                gen3Start = true;
                return true;
            }
        }

        // if the room is the fourth generator this happens
        else if(room.getName().equalsIgnoreCase("Generator Room #4")) {

            // if it has been started it returns false
            if(gen4Start) {
                return false;
            }

            // otherwise it returns true and sets the value to true
            else {
                gen4Start = true;
                return true;
            }
        }

        // if none of these are correct it returns true
        return true;
    }
}

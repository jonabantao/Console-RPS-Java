package com.company;

import java.util.*;

class Game {

    private final Set<String> VALID_MOVES = new HashSet<>(Arrays.asList("rock", "paper", "scissors"));
    private final Set<String> VALID_INPUTS = new HashSet<>(Arrays.asList("play", "history", "quit"));
    private ArrayList<String> history = new ArrayList<>();
    // Player

    private boolean isValidPlayer(String input) {
        return VALID_INPUTS.contains(input.toLowerCase());
    }

    private boolean isValidMove(String input) {
        return VALID_MOVES.contains(input.toLowerCase());
    }

    void start() {
        // Write an intro to the program and await input
        displayIntro();
    }

    private void displayIntro() {
        System.out.println("Welcome to Rock Paper Scissors!\n");
        System.out.println("MAIN MENU");
        System.out.println("=========");
        System.out.println("1. Type 'play' to play");
        System.out.println("2. Type 'history' to show history");
        System.out.println("3. Type 'quit' to quit\n");
    }
}

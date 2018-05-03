package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Human extends Player {
    private final Set<String> VALID_MOVES = new HashSet<>(Arrays.asList("rock", "paper", "scissors"));

    public Human(String name) {
        super(name);
    }

    private boolean isValidMove(String input) {
        return VALID_MOVES.contains(input.toLowerCase());
    }

    public String requestPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        String humanInput;

        while (true) {
            System.out.println("Choose between 'rock', 'paper', 'scissors'");
            humanInput = scanner.nextLine().toLowerCase();

            if (isValidMove(humanInput)) {
                return humanInput;
            } else {
                System.out.println("Invalid move!");
            }
        }
    }
}

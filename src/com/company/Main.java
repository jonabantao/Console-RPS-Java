package com.company;

import java.util.HashSet;

public class Main {

    private static final HashSet<String> VALID_MOVES = new HashSet<String>() {{
       add("rock");
       add("scissors");
       add("paper");
    }};

    public static void main(String[] args) {
	// write your code here
    }

    private static boolean isValidPlayer(String input) {
        String lowercaseInput = input.toLowerCase();

        return lowercaseInput.equals("computer") || lowercaseInput.equals("human");
    }

    private static boolean isValidMove(String input) {
        return VALID_MOVES.contains(input.toLowerCase());
    }
}

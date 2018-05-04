package com.company;

import java.util.*;

class Game {

    private final Map<String, String> WINNING_HANDS= new HashMap<String, String>() {{
        put("rock", "scissors");
        put("scissors", "paper");
        put("paper", "rock");
    }};
    private ArrayList<String> history = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean isPlaying = true;

    private Player player1;
    private Player player2;

    void start() {
        String lowercaseInput;

        System.out.println("Welcome to Rock Paper Scissors!\n");

        while (isPlaying) {
            displayIntro();

            lowercaseInput = scanner.nextLine();

            switch(lowercaseInput) {
                case "play":
                    play();
                    break;
                case "history":
                    showHistory();
                    break;
                case "quit":
                    quit();
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }

        System.out.println("Thanks for playing!");
    }

    private void displayIntro() {
        System.out.println("MAIN MENU");
        System.out.println("=========");
        System.out.println("1. Type 'play' to play");
        System.out.println("2. Type 'history' to show history");
        System.out.println("3. Type 'quit' to quit");
    }

    private void play() {
        // Int arguments in each player are used solely for naming the computers if CPU chosen
        player1 = requestPlayer(1);
        player2 = requestPlayer(2);

        playRound();
        askToPlayAgain();
    }

    private void playRound() {
        while(true) {
            String firstPlayerInput = player1.requestPlayerInput();
            String secondPlayerInput = player2.requestPlayerInput();
            int compareInput = determineWinningPlayer(firstPlayerInput, secondPlayerInput);

            switch (compareInput) {
                case 1:
                    addToHistory(player1.getName(), firstPlayerInput, player2.getName(), secondPlayerInput);
                    System.out.println(player1.getName() + " chooses " + firstPlayerInput + " and wins!");
                    return;
                case 2:
                    addToHistory(player2.getName(), firstPlayerInput, player1.getName(), secondPlayerInput);
                    System.out.println(player2.getName() + " chooses " + secondPlayerInput + " and wins!");
                    return;
                default:
                    System.out.println("Tie! Play again!");
            }
        }
    }


    /*
    *   Returns the player number who wins based on hand played
    *
    *   @param input1   Player one's hand
    *   @param input2   Player two's hand
    *   @return         Integer of winning player, 3 if it's a tie
     */
    private int determineWinningPlayer(String input1, String input2) {
        if (input1.equals(input2)) {
            return 3;
        }

        String losingHand = WINNING_HANDS.get(input1);

        return input2.equals(losingHand) ? 1 : 2;
    }

    private void quit() {
        isPlaying = false;
    }

    private void showHistory() {
        history.forEach(System.out::println);
        System.out.println("Press Enter/Return to Continue...");
        scanner.nextLine();
    }

    private void addToHistory(String winner, String winningMove, String loser, String losingMove) {
        String log = winner + " (" + winningMove + ")" + " beats " + loser + " (" + losingMove + ")";
        history.add(log);
    }

    private Player requestPlayer(int computerNum) {
        String playerInput;

        while (true) {
            System.out.println("Select 'human' or 'computer' for Player " + computerNum + ": ");
            playerInput = scanner.nextLine().toLowerCase();

            switch(playerInput) {
                case "human":
                    System.out.println("What is your name?");
                    String playerName = scanner.nextLine();
                    return new Human(playerName);
                case "computer":
                    return new Computer("Player " + computerNum + " (CPU)");
                default:
                    System.out.println("Please choose 'human' or 'computer'");
            }
        }
    }

    private void askToPlayAgain() {
        String userInput;
        System.out.println("Do you want to play again? (y/n)");

        userInput = scanner.nextLine();

        if (!userInput.toLowerCase().equals("y")) {
            isPlaying = false;
        }
    }
}

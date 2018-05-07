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
            displayMenu();

            try {
                lowercaseInput = scanner.nextLine().toLowerCase();

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
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        System.out.println("Thanks for playing!");
    }

    private void displayMenu() {
        System.out.println(
                "MAIN MENU\n" +
                "=========\n" +
                "1. Type 'play' to play\n" +
                "2. Type 'history' to show history\n" +
                "3. Type 'quit' to quit"
        );
    }

    private void play() {
        setupPlayers();
        playRound();
        askToPlayAgain();
    }

    private void setupPlayers() {
        // Int arguments in each player are used solely for naming the computers if CPU chosen
        player1 = requestPlayer(1);
        player2 = requestPlayer(2);
    }

    private void playRound() {
        while(true) {
            String firstPlayerInput = player1.requestPlayerInput();
            String secondPlayerInput = player2.requestPlayerInput();
            int compareInput = determineWinningPlayer(firstPlayerInput, secondPlayerInput);

            switch (compareInput) {
                case 1:
                    addToHistory(player1.getName(), firstPlayerInput, player2.getName(), secondPlayerInput);
                    return;
                case 2:
                    addToHistory(player2.getName(), secondPlayerInput, player1.getName(), firstPlayerInput);
                    return;
                default:
                    System.out.println("Tie! Play again!");
            }
        }
    }


    /**
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
        try {
            scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToHistory(String winner, String winningMove, String loser, String losingMove) {
        String dateNow = Util.createDateNow();
        String roundHistory = String.format("WINNER: %s (%s)  |  LOSER: %s (%s)", winner, winningMove, loser, losingMove);
        String log = String.format("[%s] %s", dateNow, roundHistory);
        System.out.println(roundHistory);
        history.add(log);
    }

    private Player requestPlayer(int playerNum) {
        String playerInput;

        while (true) {
            try {
                System.out.printf("Select 'human' or 'computer' for Player %s: ", playerNum);
                playerInput = scanner.nextLine().toLowerCase();

                switch(playerInput) {
                    case "human":
                        System.out.println("What is your name?");
                        String playerName;
                        while (true) {
                            playerName = scanner.nextLine().trim();

                            try {
                                validateUserName(playerName);
                                break;
                            } catch(NullPointerException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        return new Human(playerName);
                    case "computer":
                        return new Computer(String.format("Player %s (CPU)", playerNum));
                    default:
                        System.out.println("Please choose 'human' or 'computer'");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void validateUserName(String name) throws NullPointerException {
        if (name.isEmpty()) {
            throw new NullPointerException("Name cannot be blank!");
        }
    }

    private void askToPlayAgain() {
        String userInput;
        System.out.println("Do you want to play again? (y/n)");

        userInput = scanner.nextLine().toLowerCase();

        if (!userInput.equals("y")) {
            isPlaying = false;
        }
    }
}

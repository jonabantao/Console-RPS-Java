package com.company;

public class Computer extends Player {
    private String[] choices = { "rock", "paper", "scissors" };

    public Computer(String name) {
        super(name);
    }

    public String requestPlayerInput() {
        int randomInt = (int) (Math.random() * 3);

        return choices[randomInt];
    }
}

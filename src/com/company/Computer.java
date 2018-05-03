package com.company;

public class Computer extends Player {
    private final String[] CHOICES = { "rock", "paper", "scissors" };

    Computer(String name) {
        super(name);
    }

    public String requestPlayerInput() {
        int randomInt = (int) (Math.random() * 3);

        return CHOICES[randomInt];
    }
}

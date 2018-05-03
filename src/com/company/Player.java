package com.company;

abstract class Player {
    private String name;

    public Player(String inputName) {
        this.name = inputName;
    }

    abstract String requestPlayerInput();
}

package com.company;

abstract class Player {
    private String name;

    Player(String inputName) {
        this.name = inputName;
    }

    public String getName() {
        return name;
    }

    abstract String requestPlayerInput();
}

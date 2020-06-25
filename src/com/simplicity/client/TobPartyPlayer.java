package com.simplicity.client;

public class TobPartyPlayer {
    private String name;
    private boolean hasEntered;

    public TobPartyPlayer(String name, boolean hasEntered) {
        this.name = name;
        this.hasEntered = hasEntered;
    }

    public String getName() {
        return name;
    }

    public boolean isHasEntered() {
        return hasEntered;
    }
}

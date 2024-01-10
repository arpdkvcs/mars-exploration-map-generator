package com.codecool.marsexploration.model;

public enum GroupTerrainType {
    MOUNTAIN("^"), PIT("#");

    private final String symbol;

    GroupTerrainType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

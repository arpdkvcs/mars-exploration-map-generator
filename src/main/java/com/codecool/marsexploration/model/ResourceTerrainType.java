package com.codecool.marsexploration.model;

public enum ResourceTerrainType {
    MINERAL("*", GroupTerrainType.MOUNTAIN), WATER("~", GroupTerrainType.PIT);

    private final String symbol;
    private final GroupTerrainType groupToPlaceNextTo;

    ResourceTerrainType(String symbol, GroupTerrainType groupToPlaceNextTo) {
        this.symbol = symbol;
        this.groupToPlaceNextTo = groupToPlaceNextTo;
    }

    public String getSymbol() {
        return symbol;
    }

    public GroupTerrainType getGroup() {
        return groupToPlaceNextTo;
    }
}

package com.codecool.marsexploration.model;

public class TerrainMap {
    private final int width;
    private final String[][] strings;

    public TerrainMap(int width) {
        this.width = width;
        this.strings = new String[width][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < width; y++) {
                strings[x][y] = " ";
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public String[][] getCells() {
        return strings;
    }

    public void setCell(Coordinate coordinate, String tileType) {
        strings[coordinate.x()][coordinate.y()] = tileType;
    }
}

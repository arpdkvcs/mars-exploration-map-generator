package com.codecool.marsexploration.service.map_creator;

import com.codecool.marsexploration.configuration.Configuration;
import com.codecool.marsexploration.model.Coordinate;
import com.codecool.marsexploration.model.GroupTerrainType;
import com.codecool.marsexploration.model.ResourceTerrainType;
import com.codecool.marsexploration.model.TerrainMap;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SimpleRandomMapCreator implements MapCreator {
    private final Configuration configuration;
    private final TerrainMap map;
    private final Random random = new Random();
    private Coordinate coordinate;

    public SimpleRandomMapCreator(Configuration configuration) {
        this.configuration = configuration;
        this.map = new TerrainMap(configuration.getWidth());
        this.coordinate = new Coordinate(0, 0);
    }

    @Override
    public TerrainMap createMap() {
        placeMountains();
        placePits();
        placeMinerals();
        placeWater();

        return map;
    }

    private void placeMountains() {
        List<Integer> numberOfPeaks = configuration.getGroupTerrainConfig().get(GroupTerrainType.MOUNTAIN);
        int sumOfPeaks = numberOfPeaks.stream().mapToInt(e -> e).sum();

        for (int i = 0; i < sumOfPeaks; i++) {
            coordinate = getCoordinate();
            map.getCells()[coordinate.x()][coordinate.y()] = "^";
        }
    }

    private void placePits() {
        List<Integer> numberOfPits = configuration.getGroupTerrainConfig().get(GroupTerrainType.PIT);
        int sumOfPits = numberOfPits.stream().mapToInt(e -> e).sum();

        for (int i = 0; i < sumOfPits; i++) {
            coordinate = getCoordinate();
            map.getCells()[coordinate.x()][coordinate.y()] = "#";
        }
    }

    private void placeMinerals() {
        int sumOfMinerals = configuration.getResourceTerrainConfig().get(ResourceTerrainType.MINERAL);

        for (int i = 0; i < sumOfMinerals; i++) {
            coordinate = getCoordinate();
            map.getCells()[coordinate.x()][coordinate.y()] = "*";
        }
    }

    private void placeWater() {
        int sumOfWater = configuration.getResourceTerrainConfig().get(ResourceTerrainType.WATER);

        for (int i = 0; i < sumOfWater; i++) {
            coordinate = getCoordinate();
            map.getCells()[coordinate.x()][coordinate.y()] = "~";
        }
    }

    private Coordinate generateRandomCoordinate() {
        int x = random.nextInt(configuration.getWidth());
        int y = random.nextInt(configuration.getWidth());
        return new Coordinate(x, y);
    }

    private Coordinate getCoordinate() {
        Coordinate coord = generateRandomCoordinate();

        while (!Objects.equals(map.getCells()[coord.x()][coord.y()], " ")) {
            coord = generateRandomCoordinate();
        }

        return coord;
    }
}
